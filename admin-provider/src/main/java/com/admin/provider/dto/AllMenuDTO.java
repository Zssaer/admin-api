package com.admin.provider.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description: 分配时的全部菜单展示
 * @author: Zhaotianyi
 * @time: 2022/1/4 16:50
 */
public class AllMenuDTO {
    @ApiModelProperty(value = "id", dataType = "Integer")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "菜单名称", dataType = "String")
    private String label;

    /**
     * 子类菜单
     */
    @ApiModelProperty(value = "子类菜单")
    private List<AllMenuDTO> children;

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

    public List<AllMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AllMenuDTO> children) {
        this.children = children;
    }
}
