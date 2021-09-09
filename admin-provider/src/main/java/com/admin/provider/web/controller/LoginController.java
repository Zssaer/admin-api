package com.admin.provider.web.controller;

import com.admin.common.exception.ServiceException;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.core.annotation.SysLog;
import com.admin.provider.cache.ImgValidService;
import com.admin.provider.web.controller.request.LoginReq;
import com.admin.provider.web.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登陆管理
 * @description: 登陆管理
 * @author: Zhaotianyi
 * @time: 2021/8/27 14:08
 */
@RestController
@RequestMapping("/login")
@Api(tags = "管理员登陆")
public class LoginController {
    @Resource
    private AdminService adminService;
    @Resource
    private ImgValidService imgValidService;

    @PostMapping
    @ApiOperation(value = "管理员登陆",notes = "用于管理员登陆")
    @SysLog("管理员登陆")
    public Result login(@RequestBody LoginReq req) throws Exception{
        //判断是否验证码是否输入正确
        String cacheVerifyCode = imgValidService.get(req.getValidKey());
//        if (!req.getVerifyCode().toLowerCase().equals(cacheVerifyCode)){
//            throw new ServiceException("验证码输入错误,请重新输入!");
//        }
        return ResultBuilder.successResult(adminService.login(req));
    }

    @GetMapping("/exit")
    @ApiOperation(value = "退出登陆",notes = "用于管理员退出登陆")
    public Result logout(){
        return ResultBuilder.successResult(adminService.logout());
    }
}
