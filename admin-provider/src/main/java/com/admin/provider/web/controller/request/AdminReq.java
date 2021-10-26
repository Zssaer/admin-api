package com.admin.provider.web.controller.request;

import com.admin.common.page.PageReq;

/**
 * @description: 管理员查询请求
 * @author: Zhaotianyi
 * @time: 2021/10/25 14:37
 */
public class AdminReq extends PageReq {
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
