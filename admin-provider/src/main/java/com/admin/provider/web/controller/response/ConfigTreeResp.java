package com.admin.provider.web.controller.response;

import com.admin.provider.vo.ConfigVO;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/26 11:17
 */
public class ConfigTreeResp {
    private Integer id;
    private String label;
    private String groupName;
    private String groupCode;
    private Integer status;
    private List<ConfigVO> children;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ConfigVO> getChildren() {
        return children;
    }

    public void setChildren(List<ConfigVO> children) {
        this.children = children;
    }
}
