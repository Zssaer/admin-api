package com.admin.provider.web.controller;

import com.admin.common.result.Result;
import com.admin.common.result.ResultBuilder;
import com.admin.provider.component.FileComponent;
import com.admin.provider.component.PathComponent;
import com.admin.provider.web.controller.response.UploadResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Objects;

/**
 * @description: 上传控制类
 * @author: Zhaotianyi
 * @time: 2021/9/13 14:02
 */
@Controller
@Api(tags = {"上传文件"})
public class UploadController {
    @Autowired
    private PathComponent pathComponent;
    @Autowired
    private FileComponent fileComponent;

    /**
     * 实现文件上传
     * @throws Exception 错误
     */
    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation(value = "文件/图片上传", notes = "文件/图片上传")
    public Result fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String dbFileName;
        // 判断文件种类
        if (Objects.requireNonNull(file.getContentType()).contains("image")) {
            dbFileName = fileComponent.storeImage(file);
        } else {
            dbFileName = fileComponent.storeSource(file);
        }
        UploadResp resp = new UploadResp();

        resp.setFileSize(file.getSize());
        resp.setFileName(file.getName());
        resp.setDbFileUrl(dbFileName);
        resp.setAccessUrl(pathComponent.getAccessUrl(dbFileName));
        return ResultBuilder.successResult(resp);
    }

    /**
     * 实现富文本文件上传
     *
     * @throws Exception 错误
     */
    @PostMapping("/uploadEditorPic")
    @ResponseBody
    @ApiOperation(value = "富文本图片上传", notes = "富文本图片上传")
    public Result uploadEditorPic(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator iter = multiRequest.getFileNames();
        MultipartFile file = null;
        while (iter.hasNext()) {
            file = multiRequest.getFile((String) iter.next());
        }
        String dbFileName;
        if (Objects.requireNonNull(file).getContentType().contains("image")) {
            dbFileName = fileComponent.storeImage(file);
        } else {
            dbFileName = fileComponent.storeSource(file);
        }
        UploadResp resp = new UploadResp();

        resp.setFileSize(file.getSize());
        resp.setFileName(file.getName());
        resp.setDbFileUrl(dbFileName);
        resp.setAccessUrl(pathComponent.getAccessUrl(dbFileName));
        return ResultBuilder.successResult(resp);
    }


}
