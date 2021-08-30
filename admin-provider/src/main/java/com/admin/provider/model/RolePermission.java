package com.admin.provider.model;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限ID
     */
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 可否使用权限 0:不能 1可以
     */
    @Column(name = "is_use")
    private Integer isUse;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限ID
     *
     * @return permission_id - 权限ID
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限ID
     *
     * @param permissionId 权限ID
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取可否使用权限 0:不能 1可以
     *
     * @return is_use - 可否使用权限 0:不能 1可以
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * 设置可否使用权限 0:不能 1可以
     *
     * @param isUse 可否使用权限 0:不能 1可以
     */
    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
}