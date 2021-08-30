package com.admin.provider.model;

import javax.persistence.*;

@Table(name = "admin_permission")
public class AdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限类型(1:菜单权限,2功能权限)
     */
    private Integer type;

    /**
     * 权限路径/权限值
     */
    private String path;

    /**
     * 功能权限-请求类型
     */
    private String method;

    /**
     * 菜单权限-菜单图标
     */
    private String icon;

    /**
     * 菜单权限-排名
     */
    private Integer sort;

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
     * 获取父ID
     *
     * @return pid - 父ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父ID
     *
     * @param pid 父ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取权限名称
     *
     * @return name - 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名称
     *
     * @param name 权限名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限类型(1:菜单权限,2功能权限)
     *
     * @return type - 权限类型(1:菜单权限,2功能权限)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置权限类型(1:菜单权限,2功能权限)
     *
     * @param type 权限类型(1:菜单权限,2功能权限)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取权限路径/权限值
     *
     * @return path - 权限路径/权限值
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置权限路径/权限值
     *
     * @param path 权限路径/权限值
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取功能权限-请求类型
     *
     * @return method - 功能权限-请求类型
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置功能权限-请求类型
     *
     * @param method 功能权限-请求类型
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取菜单权限-菜单图标
     *
     * @return icon - 菜单权限-菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置菜单权限-菜单图标
     *
     * @param icon 菜单权限-菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取菜单权限-排名
     *
     * @return sort - 菜单权限-排名
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置菜单权限-排名
     *
     * @param sort 菜单权限-排名
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}