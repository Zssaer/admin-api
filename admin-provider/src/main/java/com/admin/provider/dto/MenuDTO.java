package com.admin.provider.dto;

import com.admin.provider.vo.MetaVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/9/1 15:38
 */
public class MenuDTO {
    @ApiModelProperty(value = "id", dataType = "Integer")
    private Integer id;
    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称", dataType = "String")
    private String name;
    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址", dataType = "String")
    private String path;
    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址", dataType = "String")
    private String redirect;
    /**
     * 菜单元素
     */
    @ApiModelProperty(value = "菜单元素", dataType = "Object")
    private MetaVO metaVO;
    /**
     * 组件名
     */
    @ApiModelProperty(value = "组件名", dataType = "String")
    private String component;
    /**
     * 子类菜单
     */
    @ApiModelProperty(value = "子类菜单", dataType = "String")
    private List<MenuDTO> children;

    @ApiModelProperty(value = "路由页", dataType = "String")
    private String page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public MetaVO getMeta() {
        return metaVO;
    }

    public void setMeta(MetaVO metaVO) {
        this.metaVO = metaVO;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }
}
