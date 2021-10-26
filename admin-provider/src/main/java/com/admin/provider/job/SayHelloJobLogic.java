package com.admin.provider.job;

import com.admin.provider.web.service.AdminService;
import com.admin.provider.web.service.QuartzJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;


/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/12 14:53
 */
public class SayHelloJobLogic extends QuartzJobBean {
    @Resource
    private AdminService adminService;
    @Resource
    private QuartzJobService quartzJobService;

    private static final Logger log = LoggerFactory.getLogger(SayHelloJobLogic.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //写你自己的逻辑
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        String loginName = (String) jobDataMap.get("LoginName");
        String password = (String) jobDataMap.get("Password");
        Integer roleId = (Integer) jobDataMap.get("RoleId");
        adminService.postAdmin(loginName,password,roleId);

        JobKey key = jobDetail.getKey();
        log.info(key.getName()+" 注册定时任务执行完毕!");

        try {
            quartzJobService.deleteJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
