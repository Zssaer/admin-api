package com.admin.provider.web.controller.response;

import com.admin.provider.dto.AllMenuDTO;
import com.admin.provider.dto.MenuDTO;
import com.admin.provider.vo.MenuTreeVO;

import java.util.List;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/25 16:57
 */
public class MenuListResp {
    private List<AllMenuDTO> allMenuList; // 所有菜单列表
    private MenuTreeVO menuTree;   // 角色拥有菜单树

    public MenuListResp(List<AllMenuDTO> allMenuList, MenuTreeVO menuTree) {
        this.allMenuList = allMenuList;
        this.menuTree = menuTree;
    }

    public List<AllMenuDTO> getAllMenuList() {
        return allMenuList;
    }

    public void setAllMenuList(List<AllMenuDTO> allMenuList) {
        this.allMenuList = allMenuList;
    }

    public MenuTreeVO getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(MenuTreeVO menuTree) {
        this.menuTree = menuTree;
    }
}
