package com.admin.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @description: UUID工具类
 * @author: Zhaotianyi
 * @time: 2021/10/25 11:03
 */
public class UUIDUtils {
    private static final char[] CHAR_ARR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * 生成uuid
     *
     * @return
     */
    public static String getUUID() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        return uid;
    }

    /**
     * 获取随机字符串
     *
     * @param len 长度
     * @return
     */
    public static String getRandomChar(int len) {
        StringBuffer sb = new StringBuffer("");
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(CHAR_ARR[random.nextInt(CHAR_ARR.length)]);
        }
        return sb.toString();
    }
}
