package com.admin.provider.web.controller.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/8/27 14:14
 */
public class LoginReq {
    @ApiModelProperty(value = "管理员登陆名", dataType = "String")
    private String loginName;
    @ApiModelProperty(value = "管理员登陆密码", dataType = "String")
    private String passWord;
    @ApiModelProperty(value = "验证码Key")
    private String validKey;
    @ApiModelProperty(value = "验证码Code")
    private String verifyCode;
    @ApiModelProperty(value = "记住我",dataType = "boolean")
    private boolean rememberMe;

    public String getValidKey() {
        return validKey;
    }

    public void setValidKey(String validKey) {
        this.validKey = validKey;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
