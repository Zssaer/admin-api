package com.admin.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int NOT_LIMITED = 0;

    /**
     * qps (每秒并发量)
     */
    @AliasFor("qps") double value() default NOT_LIMITED;

    /**
     * qps (每秒并发量)
     */
    @AliasFor("value") double qps() default NOT_LIMITED;

    /**
     * 超时时长,默认不等待
     */
    int timeout() default 0;

    /**
     * 超时时间单位,默认毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MICROSECONDS;
}
