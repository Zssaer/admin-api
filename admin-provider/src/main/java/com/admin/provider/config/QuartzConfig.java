package com.admin.provider.config;

import com.admin.provider.web.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description: 定时任务配置类
 * @author: Zhaotianyi
 * @time: 2021/10/27 9:47
 */
@Configuration
public class QuartzConfig {
    private static final Log logger = LogFactory.getLog(QuartzConfig.class);
    @Resource
    private TaskService taskService;

    @Bean
    public void startTask() throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        taskService.startTask();
        logger.info("定时任务服务启动!");
    }
}
