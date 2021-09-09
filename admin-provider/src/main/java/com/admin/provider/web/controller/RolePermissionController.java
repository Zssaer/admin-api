package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;
import com.admin.core.annotation.SysLog;
import com.admin.provider.dto.MenuDTO;
import com.admin.provider.model.RolePermission;
import com.admin.provider.web.service.RolePermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限管理
* Created by zty on 2021/08/27.
*/
@RestController
@RequestMapping("/role/permissions")
@Api(tags = "角色权限管理")
public class RolePermissionController {
    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping
    @ApiOperation(value = "新增角色权限", notes = "新增角色权限")
    @SaCheckPermission("role-post")
    @SysLog("新增角色权限")
    public Result add(@RequestBody RolePermission rolePermission) {
        rolePermissionService.save(rolePermission);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除角色权限", notes = "删除角色权限")
    @SaCheckPermission("role-delete")
    @SysLog("删除角色权限")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(RolePermission.class);
    	con.createCriteria().andIn("id", ids);
        rolePermissionService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新角色权限", notes = "更新角色权限")
    @SaCheckPermission("role-put")
    @SysLog("更新角色权限")
    public Result update(@RequestBody RolePermission rolePermission) {
        rolePermissionService.update(rolePermission);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取角色权限列表", notes = "获取角色权限列表")
    @SaCheckPermission("role-get")
    @SysLog("获取角色权限列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(RolePermission.class);
        Criteria cri = con.createCriteria();
        
        List<RolePermission> list = rolePermissionService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<RolePermission>(list));
    }

    @GetMapping("/getMenuList")
    @ApiOperation(value = "获取当前用户菜单列表", notes = "获取当前用户菜单列表")
    public Result getMyMenuList() {
        String loginId = StpUtil.getLoginId().toString();
        List<MenuDTO> menuDTOList = rolePermissionService.getMenu(Integer.valueOf(loginId));
        return ResultBuilder.successResult(menuDTOList);
    }

}
