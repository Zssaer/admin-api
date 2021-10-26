package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;
import com.admin.provider.model.SysLog;
import com.admin.provider.web.service.SysLogService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* Created by zty on 2021/08/31.
*/
@RestController
@RequestMapping("/sys/logs")
@Api(tags = "系统日志管理")
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    @PostMapping
    @ApiOperation(value = "新增系统日志", notes = "新增系统日志")
    @SaCheckPermission("sysLog-post")
    public Result add(@RequestBody SysLog sysLog) {
        sysLogService.save(sysLog);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除系统日志", notes = "删除系统日志")
    @SaCheckPermission("sysLog-delete")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(SysLog.class);
    	con.createCriteria().andIn("id", ids);
        sysLogService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新系统日志", notes = "更新系统日志")
    @SaCheckPermission("sysLog-put")
    public Result update(@RequestBody SysLog sysLog) {
        sysLogService.update(sysLog);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取系统日志列表", notes = "获取系统日志列表")
    @SaCheckPermission("sysLog-get")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(SysLog.class);
        Criteria cri = con.createCriteria();
        
        List<SysLog> list = sysLogService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<SysLog>(list));
    }

    @ResponseExcel(name = "SysLog", sheets = @Sheet(sheetName = "系统日志"),password = "12345600")
    @GetMapping("/output")
    @ApiOperation(value = "导出系统日志", notes = "导出系统日志")
    @SaCheckPermission("sysLog-get")
    public List<SysLog> outputData() {
        List<SysLog> list = sysLogService.findAll();
        return list;
    }

}
