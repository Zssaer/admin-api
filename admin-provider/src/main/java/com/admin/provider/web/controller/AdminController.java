package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;

import com.admin.core.annotation.SysLog;
import com.admin.provider.model.Admin;
import com.admin.provider.web.controller.request.RegisterReq;
import com.admin.provider.web.controller.request.ResetReq;
import com.admin.provider.web.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
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

    @PostMapping
    @ApiOperation(value = "新增admin", notes = "新增admin")
    @SaCheckPermission("admin-post")
    @SysLog("新增admin")
    public Result add(@RequestBody RegisterReq req) {
        return ResultBuilder.successResult(adminService.register(req));
    }

    @DeleteMapping
    @ApiOperation(value = "删除admin", notes = "删除admin")
    @SaCheckPermission("admin-delete")
    @SysLog("删除admin")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(Admin.class);
    	con.createCriteria().andIn("id", ids);
        adminService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新admin", notes = "更新admin")
    @SaCheckPermission("admin-put")
    @SysLog("更新admin")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取admin列表", notes = "获取admin列表")
    @SaCheckPermission("admin-get")
    @SysLog("获取admin列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(Admin.class);
        Criteria cri = con.createCriteria();
        List<Admin> list = adminService.findByCondition(con);
        return ResultBuilder.successResult(new PageInfo<Admin>(list));
    }

    @GetMapping("/reset")
    @ApiOperation(value = "重置当前账户密码", notes = "重置当前账户密码")
    @SaCheckLogin
    @SysLog("重置密码")
    public Result reset(ResetReq req){
        return ResultBuilder.successResult(adminService.reset(req));
    }

}
