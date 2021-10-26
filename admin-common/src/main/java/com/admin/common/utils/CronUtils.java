package com.admin.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @description: Cron生成工具类
 * @author: Zhaotianyi
 * @time: 2021/10/12 16:25
 */
public class CronUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

    /***
     *  功能描述：格式化日期Cron
     * @param date 日期
     */
    private static String formatDateByPattern(Date date) {
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     *  功能描述：格式化日期Cron
     * @param localDateTime 日期
     */
    private static String formatDateByPattern(LocalDateTime localDateTime) {
        String formatTimeStr = null;
        if (Objects.nonNull(localDateTime)) {
            formatTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy"));
        }
        return formatTimeStr;
    }

    /***
     * Data转换至Conrn
     * convert Date to cron, eg "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     */
    public static String getCron(Date date) {
        return formatDateByPattern(date);
    }

    /***
     * LocalDateTime转换至Conrn
     * convert Date to cron, eg "0 07 10 15 1 ? 2016"
     * @param localDateTime  : 时间点
     */
    public static String getCron(LocalDateTime localDateTime) {
        return formatDateByPattern(localDateTime);
    }
}
