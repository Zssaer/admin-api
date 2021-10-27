package com.admin.provider.web.service;
import com.admin.provider.model.Admin;
import com.admin.common.service.Service;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.dto.AdminDTO;
import com.admin.provider.web.controller.request.RegisterReq;
import com.admin.provider.web.controller.request.ResetReq;
import org.quartz.SchedulerException;


/**
 * Created by zty on 2021/08/26.
 */
public interface AdminService extends Service<Admin> {
    AdminDTO login(LoginReq req);
    String logout();

    String register(RegisterReq req) throws SchedulerException;
    String reset(ResetReq req);

    void postAdmin(String loginName,String password,Integer roleId,Integer createBy);
}
