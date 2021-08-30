package com.admin.provider.web.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.exception.ServiceException;
import com.admin.provider.component.RolePermissionComponent;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.dto.AdminDTO;
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
     * @return AdminDTO
     */
    @Override
    public AdminDTO login(LoginReq req) {
        String passWord = req.getPassWord();

        Condition condition = new Condition(Admin.class);
        condition.createCriteria().andEqualTo("loginName", "admin");
        List<Admin> admins = adminMapper.selectByCondition(condition);
        if (!admins.isEmpty()) {
            if (passWord.equals(admins.get(0).getPassword())) {
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
                //获取当前登陆用户Token名和Token值传递至前端
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                adminDTO.setTokenName(tokenInfo.getTokenName());
                adminDTO.setTokenValue(tokenInfo.getTokenValue());
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
     * @return String
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
}
