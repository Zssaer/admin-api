package com.admin.provider.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.core.annotation.SysLog;
import com.admin.provider.model.Config;
import com.admin.provider.model.ConfigGroup;
import com.admin.provider.web.controller.response.ConfigTreeResp;
import com.admin.provider.web.service.ConfigGroupService;
import com.admin.provider.web.service.ConfigService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置信息管理
 * Created by zty on 2021/08/27.
 */
@RestController
@RequestMapping("/configs")
@Api(tags = "配置信息管理")
public class ConfigController {
    @Resource
    private ConfigService configService;
    @Resource
    private ConfigGroupService configGroupService;

    @GetMapping("/common")
    @ApiOperation(value = "获取常用配置列表", notes = "获取常用配置列表")
    @SaCheckPermission("common-get")
    @SysLog("获取常用配置列表")
    public Result getCommonConfigList() {
        List<ConfigGroup> configGroupList = configGroupService.getCommonConfigGroupList();
        List<ConfigTreeResp> resps = configGroupService.fillConfigGroupList(configGroupList);

        return ResultBuilder.successResult(resps);
    }

    @GetMapping("/sys")
    @ApiOperation(value = "获取系统配置列表", notes = "获取系统配置列表")
    @SaCheckPermission("sys-get")
    @SysLog("获取系统配置列表")
    public Result getSysConfigList() {
        List<ConfigGroup> configGroupList = configGroupService.getSysfigGroupList();
        List<ConfigTreeResp> resps = configGroupService.fillConfigGroupList(configGroupList);

        return ResultBuilder.successResult(resps);
    }


    @PutMapping("/common")
    @ApiOperation(value = "修改常用配置", notes = "修改常用配置")
    @SaCheckPermission("common-put")
    @SysLog("修改常用配置")
    public Result updateCommonConfig(@RequestBody Config config) {
        configService.update(config);
        return ResultBuilder.successResult();
    }

    @PutMapping("/sys")
    @ApiOperation(value = "修改系统配置", notes = "修改系统配置")
    @SaCheckPermission("sys-put")
    @SysLog("修改系统配置")
    public Result updateSysConfig(@RequestBody Config config) {
        configService.update(config);
        return ResultBuilder.successResult();
    }

    @PutMapping("/common-group")
    @ApiOperation(value = "修改常用配置组", notes = "修改常用配置组")
    @SaCheckPermission("common-put")
    @SysLog("修改常用配置组")
    public Result updateCommonConfigGroup(@RequestBody ConfigGroup configGroup) {
        configGroupService.update(configGroup);
        return ResultBuilder.successResult();
    }

    @PutMapping("/sys-group")
    @ApiOperation(value = "修改系统配置组", notes = "修改系统配置组")
    @SaCheckPermission("sys-put")
    @SysLog("修改系统配置组")
    public Result updateSysConfigGroup(@RequestBody ConfigGroup configGroup) {
        configGroupService.update(configGroup);
        return ResultBuilder.successResult();
    }

}
