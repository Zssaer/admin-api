package com.admin.provider.web.service.impl;

import com.admin.provider.model.Admin;
import com.admin.provider.model.AdminPermission;
import com.admin.provider.web.mapper.RolePermissionMapper;
import com.admin.provider.model.RolePermission;
import com.admin.provider.web.service.AdminPermissionService;
import com.admin.provider.web.service.AdminService;
import com.admin.provider.web.service.RolePermissionService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private AdminPermissionService permissionService;
    @Resource
    private AdminService adminService;

    @Override
    public List<String> getRolePermissionById(Integer loginId) {
        List<String> permissionMethodList = new ArrayList<>();

        Admin admin = adminService.findById(loginId);
        Integer roleId = admin.getRole();
        //获取角色权限表
        Condition rolePermissionCd = new Condition(RolePermission.class);
        rolePermissionCd.createCriteria().andEqualTo("roleId",roleId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByCondition(rolePermissionCd);
        //获取对应功能权限的值
        for (RolePermission rolePermission:rolePermissionList) {
            AdminPermission menuPermission = permissionService.findById(rolePermission.getPermissionId());
            Condition condition=new Condition(AdminPermission.class);
            condition.createCriteria().andEqualTo("pid",menuPermission.getId()).andIsNotNull("method");
            List<AdminPermission> permissionList = permissionService.findByCondition(condition);
            for (AdminPermission permission:permissionList) {
                String path = permission.getPath();
                String method = permission.getMethod();
                permissionMethodList.add(path+"-"+method);
            }
        }
        return permissionMethodList;
    }
}
