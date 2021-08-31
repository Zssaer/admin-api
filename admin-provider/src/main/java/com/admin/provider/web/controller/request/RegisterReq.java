package com.admin.provider.web.controller.request;

/**
 * @description:  注册用户请求
 * @author: Zhaotianyi
 * @time: 2021/8/31 14:39
 */
public class RegisterReq {
    private String loginName;
    private String password;
    private String RePassWord;
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassWord() {
        return RePassWord;
    }

    public void setRePassWord(String rePassWord) {
        RePassWord = rePassWord;
    }
}
