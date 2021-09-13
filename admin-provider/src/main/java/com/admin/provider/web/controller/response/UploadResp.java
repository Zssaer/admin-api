package com.admin.provider.web.controller.response;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/5/31 14:31
 */
public class UploadResp {
    private String fileName;
    //数据库地址
    private String dbFileUrl;
    //真实访问地址
    private String accessUrl;
    //文件大小
    private  Long fileSize;
    //私有文件id
    private  Integer fileId;


    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDbFileUrl() {
        return dbFileUrl;
    }

    public void setDbFileUrl(String dbFileUrl) {
        this.dbFileUrl = dbFileUrl;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
