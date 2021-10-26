package com.admin.provider.web.service;

import com.admin.provider.model.TaskDefine;
import org.quartz.*;

/**
 * @description: 定时任务服务
 * @author: Zhaotianyi
 * @time: 2021/10/12 14:15
 */
public interface QuartzJobService {
    /**
     * 创建和启动 定时任务
     */
    void scheduleJob(TaskDefine define) throws SchedulerException;

    /**
     * 暂停Job
     */
    void pauseJob(JobKey jobKey) throws SchedulerException;

    /**
     * 恢复Job
     */
    void resumeJob(JobKey jobKey) throws SchedulerException;

    /**
     * 删除Job
     */
    void deleteJob(JobKey jobKey) throws SchedulerException;

    /**
     * 修改现存在的Job触发器 的cron表达式
     */
    boolean modifyJobCron(TaskDefine define);

    /**
     * 是否注册定时任务
     */
    boolean hasJob(JobKey jobKey) throws SchedulerException;

}
