package com.admin.provider.enums;

/**
 * 配置组类型枚举
 */
public enum ConfigGroupEnum {
    SYSTEM(1, "系统配置"),
    COMMON(2, "常用配置");
    private Integer code;
    private String desc;

    ConfigGroupEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
