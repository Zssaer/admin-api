package com.admin.provider.vo;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/26 11:20
 */
public class ConfigVO {
    private Integer id;
    private String label;
    private String configKey;
    private String configValue;
    private Integer status;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
