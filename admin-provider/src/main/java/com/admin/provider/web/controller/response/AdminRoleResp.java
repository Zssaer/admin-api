package com.admin.provider.web.controller.response;

import java.util.Date;

/**
 * @author: Zhaotianyi
 * @time: 2021/10/25 14:42
 */
public class AdminRoleResp {
    private Integer id;
    private String roleName;
    private Integer isSys;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
