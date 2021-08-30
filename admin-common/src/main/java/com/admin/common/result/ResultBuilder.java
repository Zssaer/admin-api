package com.admin.common.result;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: Result构建类
 * @author: Zhaotianyi
 * @time: 2021/5/6 10:44
 */
public class ResultBuilder {
    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    public static Result successResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result successResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result failResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static void httpResult(HttpServletResponse response, Integer httpCode, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(httpCode);
        try {
            response.getWriter().write(JSON.toJSONString(msg));
        } catch (IOException ex) {
        }
    }
}
