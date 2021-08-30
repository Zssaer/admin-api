package com.admin.provider.web.service;
import com.admin.provider.model.Admin;
import com.admin.common.service.Service;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.dto.AdminDTO;


/**
 * Created by zty on 2021/08/26.
 */
public interface AdminService extends Service<Admin> {
    AdminDTO login(LoginReq req);
    String logout();
}
