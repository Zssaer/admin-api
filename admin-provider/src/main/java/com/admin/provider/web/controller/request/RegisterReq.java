package com.admin.provider.web.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description:  注册用户请求
 * @author: Zhaotianyi
 * @time: 2021/8/31 14:39
 */
@ApiModel("注册请求类")
public class RegisterReq {
    @ApiModelProperty(value = "管理员登陆名", dataType = "String")
    private String loginName;
    @ApiModelProperty(value = "管理员密码", dataType = "String")
    private String password;
    @ApiModelProperty(value = "确认密码", dataType = "String")
    private String RePassWord;
    @ApiModelProperty(value = "管理员角色id", dataType = "Integer")
    private Integer roleId;
    @ApiModelProperty(value = "注册时间", dataType = "LocalDateTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

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
