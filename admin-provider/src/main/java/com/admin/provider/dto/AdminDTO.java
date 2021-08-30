package com.admin.provider.dto;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/8/27 14:23
 */
public class AdminDTO {
    private String loginName;
    private Integer role;
    private List<String> permission;
    private String tokenName;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
