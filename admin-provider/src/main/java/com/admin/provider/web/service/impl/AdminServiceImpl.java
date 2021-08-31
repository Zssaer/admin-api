package com.admin.provider.web.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.exception.ServiceException;
import com.admin.common.utils.MD5Utils;
import com.admin.common.utils.common.Md5Result;
import com.admin.provider.component.RolePermissionComponent;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.dto.AdminDTO;
import com.admin.provider.web.controller.request.RegisterReq;
import com.admin.provider.web.controller.request.ResetReq;
import com.admin.provider.web.mapper.AdminMapper;
import com.admin.provider.model.Admin;
import com.admin.provider.web.service.AdminService;
import com.admin.common.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


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

    /**
     * 管理员登陆
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
                // 是否使用"记住我"功能
                if (req.isRememberMe()){
                    // Sa-Token进行登录操作
                    StpUtil.login(admins.get(0).getId());
                }else {
                    StpUtil.login(admins.get(0).getId(),false);
                }
                AdminDTO adminDTO = new AdminDTO();
                BeanUtils.copyProperties(admins.get(0), adminDTO);
                adminDTO.setPermission(rolePermissionConfig.getPermissionList(StpUtil.getLoginId(),StpUtil.getLoginType()));
                //获取当前登陆用户Token名和Token值
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                adminDTO.setTokenName(tokenInfo.getTokenName());
                adminDTO.setTokenValue(tokenInfo.getTokenValue());
                //传递至Session中
                SaSession session = StpUtil.getSession(true);
                session.set("adminDTO",adminDTO);

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
     * @return String 结果
     */
    @Override
    public String logout() {
        if (StpUtil.isLogin()){
            StpUtil.logout();
            return "账户退出成功!";
        }else {
            throw new ServiceException("账户未登陆,无法退出!");
        }
    }

    /**
     * 注册管理员账户
     * @return String 结果
     */
    @Override
    public String register(RegisterReq req) {
        if (!req.getPassword().equals(req.getRePassWord())){
            throw new ServiceException("输入的两次密码不一致,请重新输入!");
        }
        //判断是否存在相同登录名用户
        String loginName = req.getLoginName();
        Condition condition=new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName",loginName);
        List<Admin> adminList = adminMapper.selectByCondition(condition);
        if (!adminList.isEmpty()){
            throw new ServiceException("输入登录名已经存在,请更换其他登录名!");
        }
        //明文密码随机加盐加密
        Md5Result md5AndSalt = MD5Utils.getSaltMd5AndSha(req.getPassword());
        Admin admin = new Admin();
        admin.setLoginName(loginName);
        admin.setPassword(md5AndSalt.getResult());
        admin.setSalt(md5AndSalt.getSalt());
        admin.setRole(req.getRoleId());
        adminMapper.insert(admin);

        return "注册管理员账户成功!";
    }

    @Override
    public String reset(ResetReq req) {
        if (!req.getNewPassword().equals(req.getConfirmPassword())){
            throw new ServiceException("输入的两次新密码不一致,请重新输入!");
        }
        //通过当前登录用户来判断老密码是否正确
        String loginId = StpUtil.getLoginId().toString();
        List<Admin> adminList = adminMapper.selectByIds(loginId);
        boolean isRight = MD5Utils.getSaltverifyMd5AndSha(req.getOldPassword(), adminList.get(0).getPassword());
        if (!isRight){
            throw new ServiceException("当前密码输入错误,请重新当前正确密码!");
        }

        Admin admin = adminList.get(0);
        Md5Result saltMd5AndSha = MD5Utils.getSaltMd5AndSha(req.getNewPassword());
        admin.setPassword(saltMd5AndSha.getResult());
        admin.setSalt(saltMd5AndSha.getSalt());
        adminMapper.updateByPrimaryKey(admin);

        return "密码修改成功!";
    }
}
