package com.admin.provider.dto;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/8/27 14:23
 */
public class AdminDTO {
    @ApiModelProperty(value = "管理员登录名", dataType = "String")
    private String loginName;
    @ApiModelProperty(value = "管理员角色id", dataType = "Integer")
    private Integer roleId;
    @ApiModelProperty(value = "管理员权限", dataType = "List")
    private List<String> permission;
    @ApiModelProperty(value = "Token名称", dataType = "String")
    private String tokenName;
    @ApiModelProperty(value = "Token值", dataType = "String")
    private String tokenValue;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
