package com.admin.provider.component;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.provider.web.service.AdminRoleService;
import com.admin.provider.web.service.RolePermissionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户授权组件
 * @author: Zhaotianyi
 * @time: 2021/8/27 15:50
 */
@Component
public class RolePermissionComponent implements StpInterface {
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private AdminRoleService roleService;
    /**
     * 返回一个账号所拥有的权限集合
     * @param loginId 账号登陆ID
     * @param loginType 账号类型
     * @return List<String> 权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        //获取用户Session缓存,没有则创建一个
        SaSession session = StpUtil.getSession(true);
        //根据Session中缓存来直接读取权限,减缓数据库压力,优化授权时间
        if (session.has("permissionList")){
            return (List<String>)session.get("permissionList");
        }else {
            List<String> permissionList = rolePermissionService.getRolePermissionById(Integer.valueOf(loginId.toString()));
            session.set("permissionList",permissionList);
            return permissionList;
        }
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     *  @param loginId 账号登陆ID
     *  @param loginType 账号类型
     *  @return List<String> 拥有角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //获取用户Session缓存,没有则创建一个
        SaSession session = StpUtil.getSession(true);
        //根据Session中缓存来直接读取角色,减缓数据库压力,优化授权时间
        if (session.has("roleList")){
            return (List<String>)session.get("roleList");
        }else {
            List<String> roleList = roleService.getRole(Integer.valueOf(loginId.toString()));
            session.set("roleList",roleList);
            return roleList;
        }
    }
}
