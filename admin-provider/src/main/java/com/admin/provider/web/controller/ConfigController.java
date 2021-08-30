package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;
import com.admin.core.annotation.SysLog;
import com.admin.provider.model.Config;
import com.admin.provider.web.service.ConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
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

    @PostMapping
    @ApiOperation(value = "新增配置信息", notes = "新增信息")
    @SysLog("新增信息")
    public Result add(@RequestBody Config config) {
        configService.save(config);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除信息", notes = "删除信息")
    @SysLog("删除信息")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(Config.class);
    	con.createCriteria().andIn("id", ids);
        configService.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @SysLog("更新信息")
    public Result update(@RequestBody Config config) {
        configService.update(config);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取信息列表", notes = "获取信息列表")
    @SysLog("获取信息列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(Config.class);
        Criteria cri = con.createCriteria();
        
        List<Config> list = configService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<Config>(list));
    }
}
