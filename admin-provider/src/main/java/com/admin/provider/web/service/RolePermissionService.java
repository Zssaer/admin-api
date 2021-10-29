package com.admin.provider.web.service;
import com.admin.provider.dto.MenuDTO;
import com.admin.provider.model.RolePermission;
import com.admin.common.service.Service;
import com.admin.provider.vo.MenuTreeVO;
import com.admin.provider.web.controller.request.SaveRoleMenuReq;

import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
public interface RolePermissionService extends Service<RolePermission> {
    List<String> getRolePermissionById(Integer loginId);

    List<MenuDTO> getMenuDto(Integer roleId);

    List<MenuDTO> getAllMenuList();
    MenuTreeVO getMenuList(Integer roleId);

    void saveRoleMenu(SaveRoleMenuReq req);
}
