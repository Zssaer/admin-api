package com.admin.core.dto;

/**
 * @description: OSS配置传输类
 * @author: Zhaotianyi
 * @time: 2021/11/1 15:05
 */
public class OssConfigDto {
    private String domain;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private boolean personal;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }
}
