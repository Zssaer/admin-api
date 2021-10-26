package com.admin.provider.model;

import javax.persistence.*;

/**
 * 配置信息
 */
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置组id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 配置Key
     */
    @Column(name = "config_key")
    private String configKey;

    /**
     * 配置Value
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 是否系统内置配置(0:不是,1:是)
     */
    @Column(name = "is_sys")
    private Integer isSys;

    /**
     * 配置状态(0:关闭,1:开启)
     */
    @Column(name = "status")
    private Integer status;

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

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 获取是否系统内置配置
     *
     * @return is_sys - 是否系统内置配置
     */
    public Integer getIsSys() {
        return isSys;
    }

    /**
     * 设置是否系统内置配置
     *
     * @param isSys 是否系统内置配置
     */
    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}