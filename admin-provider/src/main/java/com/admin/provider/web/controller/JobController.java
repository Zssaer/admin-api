package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.provider.job.RegisterAdminLogic;
import com.admin.provider.model.TaskDefine;
import com.admin.provider.web.service.QuartzJobService;
import io.swagger.annotations.Api;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/12 14:37
 */
@RestController
@RequestMapping("/job")
@Api(tags = "定时任务管理")
public class JobController {
    @Resource
    private QuartzJobService quartzJobService;

    //假如 这个定时任务的 名字叫做HelloWorld, 组名GroupOne
    private final JobKey jobKey = JobKey.jobKey("HelloWorld", "GroupOne");

    /**
     * 启动 hello world
     */
    @GetMapping("/start")
    public Result startHelloWorldJob() throws SchedulerException {

        //创建一个定时任务
        TaskDefine task = new TaskDefine(jobKey,
                "这是一个测试的 任务",       //定时任务 的描述
                "0/2 * * * * ? ",           //定时任务 的cron表达式
                null,
                RegisterAdminLogic.class //定时任务 的具体执行逻辑
        );
        quartzJobService.scheduleJob(task);
        return ResultBuilder.successResult("start HelloWorld Job success!");
    }

    /**
     * 暂停 hello world
     */
    @GetMapping("/pause")
    public Result pauseHelloWorldJob() throws SchedulerException {
        quartzJobService.pauseJob(jobKey);
        return ResultBuilder.successResult("pause HelloWorld Job success!");
    }


    /**
     * 恢复 hello world
     */
    @GetMapping("/resume")
    public Result resumeHelloWorldJob() throws SchedulerException {
        quartzJobService.resumeJob(jobKey);
        return ResultBuilder.successResult("resume HelloWorld Job success!");
    }

    /**
     * 删除 hello world
     */
    @GetMapping("/delete")
    public Result deleteHelloWorldJob() throws SchedulerException {
        quartzJobService.deleteJob(jobKey);
        return ResultBuilder.successResult("delete HelloWorld Job success!");
    }

    /**
     * 修改 hello world 的cron表达式
     */
    @GetMapping("/modifyCron")
    public Result modifyHelloWorldJobCron() {
        //这是即将要修改cron的定时任务
        TaskDefine task = new TaskDefine(jobKey,
                "这是一个测试的 任务",  //定时任务 的描述
                "0/5 * * * * ? ", //定时任务 的cron表达式
                null,
                RegisterAdminLogic.class //定时任务 的具体执行逻辑
        );
        if (quartzJobService.modifyJobCron(task))
            return ResultBuilder.successResult("modify HelloWorld Job Cron success!");
        else return ResultBuilder.successResult("modify HelloWorld Job Cron fail!");
    }

}
