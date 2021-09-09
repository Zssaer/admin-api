package com.admin.provider.vo;

/**
 * @description: 路由菜单中的元素类
 * @author: Zhaotianyi
 * @time: 2021/9/1 15:40
 */
public class Meta {
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否隐藏子类菜单
     */
    private Boolean hideChildrenInMenu;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHideChildrenInMenu() {
        return hideChildrenInMenu;
    }

    public void setHideChildrenInMenu(Boolean hideChildrenInMenu) {
        this.hideChildrenInMenu = hideChildrenInMenu;
    }

    public Meta(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
