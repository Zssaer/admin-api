package com.admin.provider.config;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.admin.common.exception.ServiceException;
import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;

//import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @description: 自定义错误拦截
 * @author: Zhaotianyi
 * @time: 2021/5/6 16:49
 */
@RestControllerAdvice

public class ExceptionHandleController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);

    /**
     * 普通权限报错拦截
     *
     * @param ex 错误类型
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResultBuilder.failResult("请求错误,请前往服务器控制台查看问题!");
    }

    /**
     * 未登录账户报错
     *
     * @return Result
     */
    @ExceptionHandler(NotLoginException.class)
    public Result notLoginexceptionHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        switch (ex.getMessage()) {
            case "token已被顶下线":
                return ResultBuilder.failResult("账户以被其它地方登录,请重新登录!");
            case "token已过期":
                return ResultBuilder.failResult("账户信息已过期,请重新登录!");
        }
        return ResultBuilder.failResult("未登录账户,请登录后进行操作!");
    }

    /**
     * 服务操作报错拦截
     *
     * @param ex 错误类型
     * @return Result
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceExceptionHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResultBuilder.failResult(ex.getMessage());
    }

    /**
     * 账户无权限报错拦截
     *
     * @return Result
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result notPermissionExceptionHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResultBuilder.failResult("当前账户无权限进行此操作,请确保账户拥有该操作权限!");
    }

    /**
     * 数据库超时报错拦截
     *
     * @return Result
     */
    @ExceptionHandler(QueryTimeoutException.class)
    public Result TimeoutHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResultBuilder.failResult("数据库服务连接超时,请确保相关数据库服务已开启!");
    }

    /**
     * 数据库超时报错拦截
     * @return Result
     */
//    @ExceptionHandler(RedisConnectionFailureException.class)
//    public Result RedisFailHandle(Exception ex) {
//        logger.error(ex.getMessage(),ex);
//        return ResultBuilder.failResult("Redis服务连接错误,请确保Redis服务已开启!");
//    }

    /**
     * API地址报错拦截
     *
     * @return Result
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result notFindHandle(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResultBuilder.failResult("API调用地址错误");
    }
}
