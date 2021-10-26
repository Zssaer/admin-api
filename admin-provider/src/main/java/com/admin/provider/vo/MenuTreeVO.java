package com.admin.provider.vo;

import java.util.List;

/**
 * @description:  分配菜单权限下的菜单列表树类
 * @author: Zhaotianyi
 * @time: 2021/10/25 16:16
 */
public class MenuTreeVO {
    //展开的树ID列表
    private List<Integer> expandedIds;
    //被选中的树ID列表
    private List<Integer> checkedIds;

    public List<Integer> getExpandedIds() {
        return expandedIds;
    }

    public void setExpandedIds(List<Integer> expandedIds) {
        this.expandedIds = expandedIds;
    }

    public List<Integer> getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(List<Integer> checkedIds) {
        this.checkedIds = checkedIds;
    }
}
