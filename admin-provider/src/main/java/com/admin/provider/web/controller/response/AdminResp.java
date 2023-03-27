package com.admin.provider.web.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description: AdminResp
 * @author: Zhaotianyi
 * @time: 2021/10/25 11:49
 */
public class AdminResp {
    @ApiModelProperty(value = "id", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(value = "登录名", dataType = "String")
    private String loginName;
    @ApiModelProperty(value = "角色id", dataType = "Integer")
    private Integer roleId;
    @ApiModelProperty(value = "头像", dataType = "String")
    private String pic;
    @ApiModelProperty(value = "头像图片物理地址", dataType = "String")
    private String accessImage;
    @ApiModelProperty(value = "用户状态", dataType = "Integer")
    private Integer adminStatus;
    @ApiModelProperty(value = "注册时间", dataType = "LocalDateTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;
    @ApiModelProperty(value = "创建者", dataType = "Integer")
    private Integer createBy;

    public String getAccessImage() {
        return accessImage;
    }

    public void setAccessImage(String accessImage) {
        this.accessImage = accessImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
}
