package com.admin.provider.web.service.impl;

import com.admin.provider.job.RegisterAdminLogic;
import com.admin.provider.model.TaskDefine;
import com.admin.provider.vo.TaskVO;
import com.admin.provider.web.mapper.TaskMapper;
import com.admin.provider.model.Task;
import com.admin.provider.web.service.QuartzJobService;
import com.admin.provider.web.service.TaskService;
import com.admin.common.service.AbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by zty on 2021/10/26.
 */
@Service
@Transactional
public class TaskServiceImpl extends AbstractService<Task> implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private QuartzJobService quartzJobService;

    /**
     * 保存定时任务 (随便创建启动定时任务)
     */
    @Override
    public void saveTask(TaskVO taskVO) throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        // 如果创建的定时任务状态为开启,则先创建启动定时任务
        if (taskVO.getStatus() == 1) {
            JobKey jobKeyV = JobKey.jobKey(taskVO.getJobKeyName(), taskVO.getJobKeyGroup());
            String description = taskVO.getTaskDescription();
            String cron = taskVO.getTaskCron();
            // 使用ObjectMapper将String装换为Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(taskVO.getTaskData(), new TypeReference<Map<String, Object>>() {
            });
            // 设置Job实现类
            Class<? extends Job> taskClass = (Class<? extends Job>) Class.forName(taskVO.getTaskClass());
            TaskDefine taskDefine = new TaskDefine(
                    jobKeyV, description, cron, data, taskClass
            );
            // 创建并启动定时任务
            quartzJobService.scheduleJob(taskDefine);
        }
        // 拼装Task定时任务类 再保存数据库中
        String jobKey = taskVO.getJobKeyName() + ";" + taskVO.getJobKeyGroup();
        Task task = new Task();
        task.setId(0);
        BeanUtils.copyProperties(taskVO, task);
        task.setTaskJobKey(jobKey);
        super.save(task);
    }

    /**
     * 在后台界面选择删除多个定时任务
     */
    @Override
    public void deleteTasks(List<Integer> ids) throws SchedulerException {
        Condition con = new Condition(Task.class);
        con.createCriteria().andIn("id", ids);
        List<Task> tasks = taskMapper.selectByCondition(con);
        //删除数据库内容前 务必删除定时任务
        for (Task task : tasks) {
            String[] jobkeyList = task.getTaskJobKey().split(";");
            JobKey jobKey = JobKey.jobKey(jobkeyList[0], jobkeyList[1]);
            if (quartzJobService.hasJob(jobKey)) {
                quartzJobService.deleteJob(jobKey);
            }
        }
        super.deleteByCondition(con);

    }

    /**
     * 删除一次性定时任务
     */
    @Override
    public void deleteTasks(JobKey jobKey) throws SchedulerException {
        String taskJobKey = jobKey.getName() + ";" + jobKey.getGroup();
        Condition con = new Condition(Task.class);
        con.createCriteria().andEqualTo("taskJobKey", taskJobKey);
        quartzJobService.deleteJob(jobKey);
        super.deleteByCondition(con);
    }

    /**
     * 更新定时任务(随便恢复or创建启动定时任务)
     */
    @Override
    public void updateTask(Task task) throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        if (task.getStatus() == 1) {
            String[] jobkeyList = task.getTaskJobKey().split(";");
            JobKey jobKey = JobKey.jobKey(jobkeyList[0], jobkeyList[1]);

            String description = task.getTaskDescription();
            String cron = task.getTaskCron();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(task.getTaskData(), new TypeReference<Map<String, Object>>() {
            });
            Class<? extends Job> taskClass = (Class<? extends Job>) Class.forName(task.getTaskClass());
            TaskDefine taskDefine = new TaskDefine(
                    jobKey, description, cron, data, taskClass
            );
            // 如果Quartz已经注册该定时任务,只是修改时间等细节的话 可以直接恢复定时任务运行
            if (quartzJobService.hasJob(jobKey)) {
                quartzJobService.modifyJobCron(taskDefine);
            }
            // 创建并启动定时任务
            quartzJobService.scheduleJob(taskDefine);
        }
        super.update(task);
    }

    /**
     * 开启定时任务,主要用于服务器开启时
     */
    @Override
    public void startTask() throws JsonProcessingException, ClassNotFoundException, SchedulerException {
        Condition con = new Condition(Task.class);
        con.createCriteria().andEqualTo("status", 1);
        List<Task> startedTaskList = taskMapper.selectByCondition(con);

        for (Task task : startedTaskList) {
            String[] jobkeyList = task.getTaskJobKey().split(";");
            JobKey jobKey = JobKey.jobKey(jobkeyList[0], jobkeyList[1]);
            String description = task.getTaskDescription();
            String cron = task.getTaskCron();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(task.getTaskData(), new TypeReference<Map<String, Object>>() {
            });
            Class<? extends Job> taskClass = (Class<? extends Job>) Class.forName(task.getTaskClass());
            TaskDefine taskDefine = new TaskDefine(
                    jobKey, description, cron, data, taskClass
            );
            // 创建并启动定时任务
            quartzJobService.scheduleJob(taskDefine);
        }

    }
}
