package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;

import com.admin.core.annotation.SysLog;
import com.admin.provider.component.ConfigComponent;
import com.admin.provider.component.PathComponent;
import com.admin.provider.model.Admin;
import com.admin.provider.model.Config;
import com.admin.provider.web.controller.request.AdminReq;
import com.admin.provider.web.controller.request.RegisterReq;
import com.admin.provider.web.controller.request.ResetReq;
import com.admin.provider.web.controller.response.AdminResp;
import com.admin.provider.web.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员管理
 * Created by zty on 2021/08/26.
 */
@RestController
@RequestMapping("/admins")
@Api(tags = "管理员管理")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private PathComponent pathComponent;

    @PostMapping
    @ApiOperation(value = "新增管理员", notes = "新增管理员")
    @SaCheckPermission("admin-post")
    @SysLog("新增管理员")
    public Result add(@RequestBody RegisterReq req) throws SchedulerException {
        return ResultBuilder.successResult(adminService.register(req));
    }

    @DeleteMapping
    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @SaCheckPermission("admin-delete")
    @SysLog("删除admin")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
        Condition con = new Condition(Admin.class);
        con.createCriteria().andIn("id", ids);
        adminService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新管理员", notes = "更新管理员")
    @SaCheckPermission("admin-put")
    @SysLog("更新管理员")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表")
    @SaCheckPermission("admin-get")
    @SysLog("获取管理员列表")
    public Result list(AdminReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());

        Condition con = new Condition(Admin.class);
        con.createCriteria().andEqualTo("loginName", req.getLoginName());
        con.setOrderByClause("id desc,register_time desc");

        List<Admin> list = adminService.findByCondition(con);
        List<AdminResp> respList = new ArrayList<>();
        for (Admin admin : list) {
            AdminResp resp = new AdminResp();
            BeanUtils.copyProperties(admin, resp);
            resp.setAccessImage(pathComponent.getAccessUrl(admin.getPic()));
            respList.add(resp);
        }
        PageInfo info = new PageInfo<>(list);
        info.setList(respList);
        return ResultBuilder.successResult(info);
    }


    @GetMapping("/reset")
    @ApiOperation(value = "重置当前账户密码", notes = "重置当前账户密码")
    @SaCheckLogin
    @SysLog("重置密码")
    public Result reset(ResetReq req) {
        return ResultBuilder.successResult(adminService.reset(req));
    }

}
