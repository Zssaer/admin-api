package com.admin.provider.web.service;

import com.admin.provider.model.Task;
import com.admin.common.service.Service;
import com.admin.provider.vo.TaskVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import java.util.List;


/**
 * Created by zty on 2021/10/26.
 */
public interface TaskService extends Service<Task> {
    void saveTask(TaskVO taskVO) throws JsonProcessingException, ClassNotFoundException, SchedulerException;
    void deleteTasks(List<Integer> ids) throws SchedulerException;
    void deleteTasks(JobKey jobkey) throws SchedulerException;
    void updateTask(Task task) throws JsonProcessingException, ClassNotFoundException, SchedulerException;
    void startTask()throws JsonProcessingException, ClassNotFoundException, SchedulerException;


}
