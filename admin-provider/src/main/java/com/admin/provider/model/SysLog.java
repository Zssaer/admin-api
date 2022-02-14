package com.admin.provider.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@ApiModel("系统日志实体类")
@Table(name = "sys_log")
public class SysLog {
    @ApiModelProperty("日志id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty(index = 0)
    private Integer id;

    /**
     * 操作人ID
     */
    @ApiModelProperty("操作人ID")
    @Column(name = "user_id")
    @ExcelProperty(index = 1)
    private Integer userId;

    /**
     * 操作人项目
     */
    @ApiModelProperty("操作人项目")
    @ColumnWidth(20)
    @ExcelProperty(value="管理员名",index = 2)

    @Column(name = "user_name")
    private String userName;

    /**
     * 操作名称
     */
    @ApiModelProperty("操作名称")
    @ColumnWidth(20)
    @ExcelProperty(value="操作方法",index = 3)
    @Column(name = "operation_name")
    private String operationName;

    /**
     * 操作人IP地址
     */
    @ApiModelProperty("操作人IP地址")
    @ColumnWidth(20)
    @ExcelProperty(value="IP地址",index = 4)
    @Column(name = "user_ip")
    private String userIp;

    /**
     * 记录时间
     */
    @ApiModelProperty("记录时间")
    @ColumnWidth(20)
    @ExcelProperty(value="操作时间",index = 5)
    @Column(name = "log_time")
    private LocalDateTime logTime;

    /**
     * 操作参数
     */
    @ApiModelProperty("操作参数")
    @ColumnWidth(80)
    @ExcelProperty(value="操作参数",index = 6)
    @Column(name = "operation_param")
    private String operationParam;


    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

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
     * 获取操作人ID
     *
     * @return user_id - 操作人ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作人ID
     *
     * @param userId 操作人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作人项目
     *
     * @return user_name - 操作人项目
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作人项目
     *
     * @param userName 操作人项目
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作名称
     *
     * @return operation_name - 操作名称
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作名称
     *
     * @param operationName 操作名称
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 获取操作人IP地址
     *
     * @return user_ip - 操作人IP地址
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 设置操作人IP地址
     *
     * @param userIp 操作人IP地址
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }


    /**
     * 获取操作参数
     *
     * @return operation_param - 操作参数
     */
    public String getOperationParam() {
        return operationParam;
    }

    /**
     * 设置操作参数
     *
     * @param operationParam 操作参数
     */
    public void setOperationParam(String operationParam) {
        this.operationParam = operationParam;
    }
}