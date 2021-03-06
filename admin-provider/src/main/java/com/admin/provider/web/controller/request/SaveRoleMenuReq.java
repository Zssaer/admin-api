package com.admin.provider.web.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/29 9:39
 */
@ApiModel("角色权限保存请求类")
public class SaveRoleMenuReq {
    //  角色ID
    @ApiModelProperty("角色ID")
    private Integer roleId;

    // 选中菜单的ID
    @ApiModelProperty("选中的权限ID")
    private List<Integer> Menuids;

    // 取消菜单的ID
    @ApiModelProperty("取消的权限ID")
    private List<Integer> canceMenuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuids() {
        return Menuids;
    }

    public void setMenuids(List<Integer> menuids) {
        Menuids = menuids;
    }

    public List<Integer> getCanceMenuIds() {
        return canceMenuIds;
    }

    public void setCanceMenuIds(List<Integer> canceMenuIds) {
        this.canceMenuIds = canceMenuIds;
    }
}
