package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.common.utils.MD5Utils;
import com.admin.provider.cache.ImgValidService;
import com.admin.provider.web.controller.response.CaptchaResp;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/5/10 15:55
 */
@Api(tags = {"图片验证码管理"})
@Controller
public class CaptchaController {
    @Resource
    private ImgValidService imgValidService;

    @ResponseBody
    @GetMapping("/captcha")
    @ApiOperation(value = "获取图像验证码",notes = "获取图像验证码")
    public Result captcha() throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(128, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String MD5verCode = MD5Utils.md5(verCode);

        imgValidService.add(MD5verCode,verCode);
        return ResultBuilder.successResult(new CaptchaResp(specCaptcha.toBase64(),MD5verCode));
    }
}
