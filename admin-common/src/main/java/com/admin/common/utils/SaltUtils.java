package com.admin.common.utils;

import java.util.Random;

/**
 * @description: 随机Salt生成工具
 * @author: Zhaotianyi
 * @time: 2021/5/6 10:46
 */
public class SaltUtils {
    /**
     * 生成随机Salt的静态方法，以确保Salt不固定
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTYUWXWZabcdefghijklmnopqrstyuwxwz0123456789!@#$%^&*()".toCharArray();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c=chars[new Random().nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
