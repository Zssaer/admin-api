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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
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
     * ???????????????
     *
     * @param req ????????????
     * @return AdminDTO Admin?????????
     */
    @Override
    public AdminDTO login(LoginReq req) {
        String passWord = req.getPassWord();

        Condition condition = new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName", "admin");
        List<Admin> admins = adminMapper.selectByCondition(condition);
        if (!admins.isEmpty()) {
            // ??????????????????????????????????????????????????????Md5????????????,?????????????????????.
            boolean isRight = MD5Utils.getSaltverifyMd5AndSha(passWord, admins.get(0).getPassword());
            if (isRight) {
                if (admins.get(0).getAdminStatus() != 1) {
                    throw new ServiceException("?????????????????????!");
                }
                // ????????????"?????????"??????
                if (req.isRememberMe()) {
                    // Sa-Token??????????????????
                    StpUtil.login(admins.get(0).getId());
                } else {
                    StpUtil.login(admins.get(0).getId(), false);
                }
                AdminDTO adminDTO = new AdminDTO();
                BeanUtils.copyProperties(admins.get(0), adminDTO);
                adminDTO.setPermission(rolePermissionConfig.getPermissionList(StpUtil.getLoginId(), StpUtil.getLoginType()));
                //????????????????????????Token??????Token???
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                adminDTO.setTokenName(tokenInfo.getTokenName());
                adminDTO.setTokenValue(tokenInfo.getTokenValue());
                //?????????Session???
                SaSession session = StpUtil.getSession(true);
                session.set("adminDTO", adminDTO);

                return adminDTO;
            } else {
                throw new ServiceException("??????????????????!");
            }
        } else {
            throw new ServiceException("?????????????????????!");
        }
    }

    /**
     * ???????????????
     *
     * @return String ??????
     */
    @Override
    public String logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
            return "??????????????????!";
        } else {
            throw new ServiceException("???????????????,????????????!");
        }
    }

    /**
     * ?????????????????????
     *
     * @return String ??????
     */
    @Override
    public String register(RegisterReq req) throws SchedulerException {
        if (!req.getPassword().equals(req.getRePassWord())) {
            throw new ServiceException("??????????????????????????????,???????????????!");
        }
        //???????????????????????????????????????
        String loginName = req.getLoginName();
        Condition condition = new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName", loginName);
        List<Admin> adminList = adminMapper.selectByCondition(condition);
        if (!adminList.isEmpty()) {
            throw new ServiceException("???????????????????????????,????????????????????????!");
        }

        //?????????????????????
        if (req.getRegisterTime() != null) {
            AdminDTO adminDTO = (AdminDTO) StpUtil.getSession().get("adminDTO");

            //??????????????????
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
            taskVO.setTaskName("????????????????????????");
            taskVO.setJobKeyName(loginName + " Register");
            taskVO.setJobKeyGroup("GroupOne");
            taskVO.setTaskDescription("????????????" + loginName + "?????????????????????");
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
            return "????????????????????????!";
        }

        postAdmin(loginName, req.getPassword(), req.getRoleId(), (Integer) StpUtil.getLoginId());
        return "???????????????????????????!";
    }

    @Override
    public String reset(ResetReq req) {
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            throw new ServiceException("?????????????????????????????????,???????????????!");
        }
        //??????????????????????????????????????????????????????
        String loginId = StpUtil.getLoginId().toString();
        List<Admin> adminList = adminMapper.selectByIds(loginId);
        boolean isRight = MD5Utils.getSaltverifyMd5AndSha(req.getOldPassword(), adminList.get(0).getPassword());
        if (!isRight) {
            throw new ServiceException("????????????????????????,???????????????????????????!");
        }

        Admin admin = adminList.get(0);
        Md5Result saltMd5AndSha = MD5Utils.getSaltMd5AndSha(req.getNewPassword());
        admin.setPassword(saltMd5AndSha.getResult());
        admin.setSalt(saltMd5AndSha.getSalt());
        adminMapper.updateByPrimaryKey(admin);

        return "??????????????????!";
    }


    public void postAdmin(String loginName, String password, Integer roleId, Integer createdBy) {
        //??????????????????????????????
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
