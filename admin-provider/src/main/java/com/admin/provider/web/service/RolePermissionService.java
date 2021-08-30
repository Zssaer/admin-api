package com.admin.provider.web.service;
import com.admin.provider.model.RolePermission;
import com.admin.common.service.Service;

import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
public interface RolePermissionService extends Service<RolePermission> {
    List<String> getRolePermissionById(Integer loginId);
}
