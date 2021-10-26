package com.admin.provider.model;

import javax.persistence.*;

@Table(name = "config_group")
public class ConfigGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 配置组代号
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 状态(0:关闭,1:开启)
     */
    private Integer status;

    /**
     * 配置类型(1:系统配置,2:常用配置)
     */
    private Integer groupType;

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
     * 获取配置组名
     *
     * @return group_name - 配置组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置配置组名
     *
     * @param groupName 配置组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取配置组代号
     *
     * @return group_code - 配置组代号
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置配置组代号
     *
     * @param groupCode 配置组代号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取状态(0:关闭,1:开启)
     *
     * @return status - 状态(0:关闭,1:开启)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(0:关闭,1:开启)
     *
     * @param status 状态(0:关闭,1:开启)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }
}