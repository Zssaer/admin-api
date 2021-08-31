package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.page.PageReq;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.core.annotation.SysLog;
import com.admin.provider.model.AdminRole;
import com.admin.provider.web.service.AdminRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员角色管理
* Created by zty on 2021/08/27.
*/
@RestController
@RequestMapping("/admin/roles")
@Api(tags = "管理员角色管理")
public class AdminRoleController {
    @Resource
    private AdminRoleService adminRoleService;

    @PostMapping
    @ApiOperation(value = "新增管理员角色", notes = "新增角色")
    @SaCheckPermission("role-post")
    @SysLog("新增管理员角色")
    public Result add(@RequestBody AdminRole adminRole) {
        adminRoleService.save(adminRole);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除管理员角色", notes = "删除管理员角色")
    @SaCheckPermission("role-delete")
    @SysLog("删除管理员角色")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(AdminRole.class);
    	con.createCriteria().andIn("id", ids);
        adminRoleService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新管理员角色", notes = "更新管理员角色")
    @SaCheckPermission("role-put")
    @SysLog("更新管理员角色")
    public Result update(@RequestBody AdminRole adminRole) {
        adminRoleService.update(adminRole);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取管理员角色列表", notes = "获取管理员角色列表")
    @SaCheckPermission("role-get")
    @SysLog("获取管理员角色列表")
    public Result list(PageReq req) {
        StpUtil.checkPermission("role-get");

        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(AdminRole.class);
        Criteria cri = con.createCriteria();
        
        List<AdminRole> list = adminRoleService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<AdminRole>(list));
    }
}
