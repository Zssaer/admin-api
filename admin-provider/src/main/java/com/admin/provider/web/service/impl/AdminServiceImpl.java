package com.admin.provider.web.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.exception.ServiceException;
import com.admin.common.utils.CronUtils;
import com.admin.common.utils.MD5Utils;
import com.admin.common.utils.common.Md5Result;
import com.admin.provider.component.ConfigComponent;
import com.admin.provider.component.RolePermissionComponent;
import com.admin.provider.job.RegisterAdminLogic;
import com.admin.provider.vo.TaskVO;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.dto.AdminDTO;
import com.admin.provider.web.controller.request.RegisterReq;
import com.admin.provider.web.controller.request.ResetReq;
import com.admin.provider.web.mapper.AdminMapper;
import com.admin.provider.model.Admin;
import com.admin.provider.web.service.AdminService;
import com.admin.common.service.AbstractService;
import com.admin.provider.web.service.ConfigService;
import com.admin.provider.web.service.QuartzJobService;
import com.admin.provider.web.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.admin.provider.config.constant.QuartzConstant.EXACTLY_ONCE;


/**
 * Created by zty on 2021/08/26.
 */
@Service
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private RolePermissionComponent rolePermissionConfig;
    @Resource
    private QuartzJobService quartzJobService;
    @Resource
    private ConfigComponent configComponent;
    @Resource
    private ConfigService configService;
    @Resource
    private TaskService taskService;

    /**
     * 管理员登陆
     *
     * @param req 前端请求
     * @return AdminDTO Admin传输类
     */
    @Override
    public AdminDTO login(LoginReq req) {
        String passWord = req.getPassWord();

        Condition condition = new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName", "admin");
        List<Admin> admins = adminMapper.selectByCondition(condition);
        if (!admins.isEmpty()) {
            // 通过登录请求中的明文密码与数据库加密Md5进行验证,判断是否为相等.
            boolean isRight = MD5Utils.getSaltverifyMd5AndSha(passWord, admins.get(0).getPassword());
            if (isRight) {
                if (admins.get(0).getAdminStatus() != 1) {
                    throw new ServiceException("账号被停止使用!");
                }
                // 是否使用"记住我"功能
                if (req.isRememberMe()) {
                    // Sa-Token进行登录操作
                    StpUtil.login(admins.get(0).getId());
                } else {
                    StpUtil.login(admins.get(0).getId(), false);
                }
                AdminDTO adminDTO = new AdminDTO();
                BeanUtils.copyProperties(admins.get(0), adminDTO);
                adminDTO.setPermission(rolePermissionConfig.getPermissionList(StpUtil.getLoginId(), StpUtil.getLoginType()));
                //获取当前登陆用户Token名和Token值
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                adminDTO.setTokenName(tokenInfo.getTokenName());
                adminDTO.setTokenValue(tokenInfo.getTokenValue());
                //传递至Session中
                SaSession session = StpUtil.getSession(true);
                session.set("adminDTO", adminDTO);

                return adminDTO;
            } else {
                throw new ServiceException("登入密码错误!");
            }
        } else {
            throw new ServiceException("登陆用户不存在!");
        }
    }

    /**
     * 管理员退出
     *
     * @return String 结果
     */
    @Override
    public String logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
            return "账户退出成功!";
        } else {
            throw new ServiceException("账户未登陆,无法退出!");
        }
    }

    /**
     * 注册管理员账户
     *
     * @return String 结果
     */
    @Override
    public String register(RegisterReq req) throws SchedulerException {
        if (!req.getPassword().equals(req.getRePassWord())) {
            throw new ServiceException("输入的两次密码不一致,请重新输入!");
        }
        //判断是否存在相同登录名用户
        String loginName = req.getLoginName();
        Condition condition = new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName", loginName);
        List<Admin> adminList = adminMapper.selectByCondition(condition);
        if (!adminList.isEmpty()) {
            throw new ServiceException("输入登录名已经存在,请更换其他登录名!");
        }

        //是否时定时任务
        if (req.getRegisterTime() != null) {
            AdminDTO adminDTO = (AdminDTO) StpUtil.getSession().get("adminDTO");

            //装载注册数据
            Map<String, Object> data = new HashMap<>();
            data.put("LoginName", loginName);
            data.put("Password", req.getPassword());
            data.put("RoleId", req.getRoleId());
            data.put("CreatedBy", StpUtil.getLoginId());
            data.put(EXACTLY_ONCE, 1);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = "";
            try {
                s = objectMapper.writeValueAsString(data);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            TaskVO taskVO = new TaskVO();
            taskVO.setTaskName("定时注册用户任务");
            taskVO.setJobKeyName(loginName + " Register");
            taskVO.setJobKeyGroup("GroupOne");
            taskVO.setTaskDescription("这是一个" + loginName + "的定时注册任务");
            taskVO.setTaskCron(CronUtils.getCron(req.getRegisterTime()));
            taskVO.setTaskData(s);
            taskVO.setTaskClass(RegisterAdminLogic.class.getName());
            taskVO.setStatus(1);
            taskVO.setCreateTime(new Date());
            taskVO.setCreatedby(adminDTO.getLoginName());
            try {
                taskService.saveTask(taskVO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "定时注册设置成功!";
        }

        postAdmin(loginName, req.getPassword(), req.getRoleId(), (Integer) StpUtil.getLoginId());
        return "注册管理员账户成功!";
    }

    @Override
    public String reset(ResetReq req) {
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            throw new ServiceException("输入的两次新密码不一致,请重新输入!");
        }
        //通过当前登录用户来判断老密码是否正确
        String loginId = StpUtil.getLoginId().toString();
        List<Admin> adminList = adminMapper.selectByIds(loginId);
        boolean isRight = MD5Utils.getSaltverifyMd5AndSha(req.getOldPassword(), adminList.get(0).getPassword());
        if (!isRight) {
            throw new ServiceException("当前密码输入错误,请重新当前正确密码!");
        }

        Admin admin = adminList.get(0);
        Md5Result saltMd5AndSha = MD5Utils.getSaltMd5AndSha(req.getNewPassword());
        admin.setPassword(saltMd5AndSha.getResult());
        admin.setSalt(saltMd5AndSha.getSalt());
        adminMapper.updateByPrimaryKey(admin);

        return "密码修改成功!";
    }


    public void postAdmin(String loginName, String password, Integer roleId, Integer createdBy) {
        //明文密码随机加盐加密
        Md5Result md5AndSalt = MD5Utils.getSaltMd5AndSha(password);
        Admin admin = new Admin();
        admin.setLoginName(loginName);
        admin.setPassword(md5AndSalt.getResult());
        admin.setSalt(md5AndSalt.getSalt());
        admin.setRoleId(roleId);
        admin.setAdminStatus(1);
        admin.setCreateBy(createdBy);
        admin.setRegisterTime(LocalDateTime.now());
        adminMapper.insert(admin);
    }
}
