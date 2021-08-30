package com.admin.provider.web.service;
import com.admin.provider.model.AdminRole;
import com.admin.common.service.Service;

import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
public interface AdminRoleService extends Service<AdminRole> {
    List<String> getRole(Integer loginId);

}
