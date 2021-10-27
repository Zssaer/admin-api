package com.admin.provider.job;

import com.admin.provider.web.service.AdminService;
import com.admin.provider.web.service.TaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

import static com.admin.provider.config.constant.QuartzConstant.EXACTLY_ONCE;


/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/10/12 14:53
 */
public class RegisterAdminLogic extends QuartzJobBean {
    @Resource
    private AdminService adminService;
    @Resource
    private TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(RegisterAdminLogic.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //写你自己的逻辑
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        String loginName = (String) jobDataMap.get("LoginName");
        String password = (String) jobDataMap.get("Password");
        Integer roleId = (Integer) jobDataMap.get("RoleId");
        Integer createBy = Integer.valueOf((String) jobDataMap.get("CreatedBy"));
        adminService.postAdmin(loginName, password, roleId, createBy);

        JobKey key = jobDetail.getKey();
        log.info(key.getName() + " 注册定时任务执行完毕!");

        // 如果传入只执行一次参数,且为1,则删除该定时任务
        if ((Integer) jobDataMap.get(EXACTLY_ONCE) == 1) {
            try {
                taskService.deleteTasks(key);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
