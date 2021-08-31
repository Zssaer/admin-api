package com.admin.provider.web.controller.request;

/**
 * @description: 重置密码请求
 * @author: Zhaotianyi
 * @time: 2021/8/31 15:50
 */
public class ResetReq {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
