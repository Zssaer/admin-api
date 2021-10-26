package com.admin.provider.vo;

import java.util.Date;

/**
 * @description: 任务VO类
 * @author: Zhaotianyi
 * @time: 2021/10/26 16:52
 */
public class TaskVO {
    private String taskName;
    private String JobKeyName;
    private String JobKeyGroup;
    private String taskDescription;
    private String taskCron;
    private String taskClass;
    private Integer status;
    private Date createTime;
    private String createdby;
    private String taskData;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getJobKeyName() {
        return JobKeyName;
    }

    public void setJobKeyName(String jobKeyName) {
        JobKeyName = jobKeyName;
    }

    public String getJobKeyGroup() {
        return JobKeyGroup;
    }

    public void setJobKeyGroup(String jobKeyGroup) {
        JobKeyGroup = jobKeyGroup;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getTaskData() {
        return taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }
}
