package com.admin.provider.aop;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.admin.common.utils.IPUtils;
import com.admin.provider.dto.AdminDTO;
import com.admin.provider.model.SysLog;
import com.admin.provider.web.controller.AdminController;
import com.admin.provider.web.controller.LoginController;
import com.admin.provider.web.service.SysLogService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @description: 系统日志处理
 * @author: Zhaotianyi
 * @time: 2021/8/31 10:20
 */
@Aspect
@Component
@Order(2)
public class SysLogAspect {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    SysLogService sysLogService;

    @Pointcut("@annotation(com.admin.core.annotation.SysLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        String result1 = JSON.toJSONString(result);
        JSONObject jsonObject = JSON.parseObject(result1);
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time, jsonObject);
        return result;
    }

    /**
     * 保存系统操作日志
     * @param joinPoint
     * @param time
     * @param jsonObject
     * @throws Exception
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time, JSONObject jsonObject) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.admin.core.annotation.SysLog logAnn = method.getAnnotation(com.admin.core.annotation.SysLog.class);

        SysLog sysLog = new SysLog();
        if (logAnn != null) {
            // 设置系统日志中执行的方法名
            sysLog.setOperationName(logAnn.value());
        }

        //请求的方法
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String invokeMethod = className + "." + methodName + "()";

        //请求的参数
        String params = "";
        params = JSON.toJSONString(joinPoint.getArgs());
        //日志过滤用户登录/修改中的密码
        if (LoginController.class.getName().equals(className)) {
            params = params.replaceAll("\"passWord\":\"(.*)\",\"rememberMe\"", "\"passWord\":\"**********\",\"rememberMe\"");
        }
        if (AdminController.class.getName().equals(className)){
            params = params.replaceAll("\"password\":\"(.*)\",\"RePassWord\":\"(.*)\"", "\"password\":\"**********\",\"RePassWord\":\"**********\"");
            //params = params.replaceAll("\"confirmPassword\":\"(.*)\",\"newPassword\":\"(.*)\",\"oldPassword\":\"(.*)\"", "\"confirmPassword\":\"**********\",\"newPassword\":\"**********\",\"oldPassword\":\"**********\"");
        }


        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取操作时间
        Date date = new Date();
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        //保存系统日志
        logger.info("请求URL : " + request.getRequestURL().toString());
        logger.info("操作 : " + sysLog.getOperationName());
        logger.info("请求IP : " + IPUtils.getIpAddr(request));
        logger.info("请求方法 : " + invokeMethod);
        logger.info("方法接收参数 : " + params);
        logger.info("耗时 : " + time);

        String loginId = StpUtil.getLoginId().toString();
        sysLog.setUserId(Integer.valueOf(loginId));
        //获取session中的admin传输类
        SaSession session = StpUtil.getSession();
        AdminDTO adminDTO = (AdminDTO)session.get("adminDTO");
        if (adminDTO!=null){
            sysLog.setUserName(adminDTO.getLoginName());
        }
        sysLog.setUserIp(IPUtils.getIpAddr(request));
        sysLog.setLogTime(localDateTime);

        // 操作内容限制长度
        if (params.length() > 1024) {
            sysLog.setOperationParam(params.substring(1024));
        } else {
            sysLog.setOperationParam(params);
        }
        sysLogService.save(sysLog);
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws JsonProcessingException {
        // 处理完请求，返回内容
        ObjectMapper mapper = new ObjectMapper();
        logger.info("接口返回 : " + mapper.writeValueAsString(ret));
    }
}
