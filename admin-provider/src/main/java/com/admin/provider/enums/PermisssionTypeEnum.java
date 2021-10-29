package com.admin.provider.enums;

/**
 * 权限类型枚举
 */
public enum PermisssionTypeEnum {
    MENU(1, "菜单权限"),
    METHOD(2, "操作权限");

    private Integer code;
    private String desc;

    PermisssionTypeEnum(Integer code, String desc) {
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
