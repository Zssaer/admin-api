package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;
import com.admin.core.annotation.SysLog;
import com.admin.provider.model.AdminPermission;
import com.admin.provider.web.service.AdminPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限管理
* Created by zty on 2021/08/27.
*/
@RestController
@RequestMapping("/admin/permissions")
@Api(tags = "权限管理")
public class AdminPermissionController {
    @Resource
    private AdminPermissionService adminPermissionService;

    @PostMapping
    @ApiOperation(value = "新增权限", notes = "新增权限")
    @SaCheckPermission("role-post")
    @SysLog("新增权限")
    public Result add(@RequestBody AdminPermission adminPermission) {
        adminPermissionService.save(adminPermission);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除权限", notes = "删除权限")
    @SaCheckPermission("role-delete")
    @SysLog("删除权限")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(AdminPermission.class);
    	con.createCriteria().andIn("id", ids);
        adminPermissionService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新权限", notes = "更新权限")
    @SaCheckPermission("role-put")
    @SysLog("更新权限")
    public Result update(@RequestBody AdminPermission adminPermission) {
        adminPermissionService.update(adminPermission);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取权限列表", notes = "获取权限列表")
    @SaCheckPermission("role-get")
    @SysLog("获取权限列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(AdminPermission.class);
        Criteria cri = con.createCriteria();
        
        List<AdminPermission> list = adminPermissionService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<AdminPermission>(list));
    }
}
