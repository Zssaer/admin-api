package com.admin.provider.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description: 任务VO类
 * @author: Zhaotianyi
 * @time: 2021/10/26 16:52
 */
@ApiModel("任务请求类")
public class TaskVO {
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("Job名称")
    private String JobKeyName;
    @ApiModelProperty("Job组名")
    private String JobKeyGroup;
    @ApiModelProperty("任务说明")
    private String taskDescription;
    @ApiModelProperty("任务Cron表达式")
    private String taskCron;
    @ApiModelProperty("任务类")
    private String taskClass;
    @ApiModelProperty("任务状态")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建人")
    private String createdby;
    @ApiModelProperty("任务数据")
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
