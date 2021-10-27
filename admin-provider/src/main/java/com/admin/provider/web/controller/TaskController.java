package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.page.PageReq;
import com.admin.core.annotation.SysLog;
import com.admin.provider.model.Task;
import com.admin.provider.vo.TaskVO;
import com.admin.provider.web.service.QuartzJobService;
import com.admin.provider.web.service.TaskService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zty on 2021/10/26.
 */
@RestController
@RequestMapping("/tasks")
@Api(tags = "定时任务管理")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping
    @ApiOperation(value = "新增定时任务", notes = "新增定时任务")
    @SaCheckPermission("task-post")
    @SysLog("新增定时任务")
    public Result add(@RequestBody TaskVO taskVO) throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        taskService.saveTask(taskVO);
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除定时任务", notes = "删除定时任务")
    @SaCheckPermission("task-delete")
    @SysLog("删除定时任务")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) throws SchedulerException {
        taskService.deleteTasks(ids);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新定时任务", notes = "更新定时任务")
    @SaCheckPermission("task-put")
    @SysLog("更新定时任务")
    public Result update(@RequestBody Task task) throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        taskService.updateTask(task);
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取定时任务列表", notes = "获取定时任务列表")
    @SaCheckPermission("task-get")
    @SysLog("获取定时任务列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());

        Condition con = new Condition(Task.class);
        Criteria cri = con.createCriteria();

        List<Task> list = taskService.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<Task>(list));
    }

}
