package com.admin.provider.web.controller.response;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/5/10 16:01
 */
public class CaptchaResp {
    private String baseImg;
    private String validKey;

    public CaptchaResp(String baseImg, String validKey) {
        this.baseImg = baseImg;
        this.validKey = validKey;
    }

    public String getBaseImg() {
        return baseImg;
    }

    public void setBaseImg(String baseImg) {
        this.baseImg = baseImg;
    }

    public String getValidKey() {
        return validKey;
    }

    public void setValidKey(String validKey) {
        this.validKey = validKey;
    }
}
