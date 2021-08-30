package com.admin.common.utils.common;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/8/30 17:03
 */
public class Md5Result {
    private String result;
    private String salt;

    public Md5Result(String result, String salt) {
        this.result = result;
        this.salt = salt;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
