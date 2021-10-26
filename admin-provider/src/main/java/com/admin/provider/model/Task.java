package com.admin.provider.model;

import java.util.Date;
import javax.persistence.*;

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 任务jobkey
     */
    @Column(name = "task_job_key")
    private String taskJobKey;

    /**
     * 任务描述
     */
    @Column(name = "task_description")
    private String taskDescription;

    /**
     * 任务cron表达式
     */
    @Column(name = "task_cron")
    private String taskCron;

    /**
     * 任务执行类
     */
    @Column(name = "task_class")
    private String taskClass;

    /**
     * 任务运行状态(0:关闭,1:执行)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建任务的管理员名称
     */
    @Column(name = "createdBy")
    private String createdby;

    /**
     * 任务传递数据
     */
    @Column(name = "task_data")
    private String taskData;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取任务名称
     *
     * @return task_name - 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取任务jobkey
     *
     * @return task_job_key - 任务jobkey
     */
    public String getTaskJobKey() {
        return taskJobKey;
    }

    /**
     * 设置任务jobkey
     *
     * @param taskJobKey 任务jobkey
     */
    public void setTaskJobKey(String taskJobKey) {
        this.taskJobKey = taskJobKey;
    }

    /**
     * 获取任务描述
     *
     * @return task_description - 任务描述
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * 设置任务描述
     *
     * @param taskDescription 任务描述
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * 获取任务cron表达式
     *
     * @return task_cron - 任务cron表达式
     */
    public String getTaskCron() {
        return taskCron;
    }

    /**
     * 设置任务cron表达式
     *
     * @param taskCron 任务cron表达式
     */
    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    /**
     * 获取任务执行类
     *
     * @return task_class - 任务执行类
     */
    public String getTaskClass() {
        return taskClass;
    }

    /**
     * 设置任务执行类
     *
     * @param taskClass 任务执行类
     */
    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    /**
     * 获取任务运行状态(0:关闭,1:执行)
     *
     * @return status - 任务运行状态(0:关闭,1:执行)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置任务运行状态(0:关闭,1:执行)
     *
     * @param status 任务运行状态(0:关闭,1:执行)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建任务的管理员名称
     *
     * @return createdBy - 创建任务的管理员名称
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建任务的管理员名称
     *
     * @param createdby 创建任务的管理员名称
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     * 获取任务传递数据
     *
     * @return task_data - 任务传递数据
     */
    public String getTaskData() {
        return taskData;
    }

    /**
     * 设置任务传递数据
     *
     * @param taskData 任务传递数据
     */
    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }
}