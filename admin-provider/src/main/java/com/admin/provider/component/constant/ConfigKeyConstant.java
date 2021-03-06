package com.admin.provider.component.constant;

/**
 * @description: 配置信息Key常量
 * @author: Zhaotianyi
 * @time: 2021/8/31 16:52
 */
public class ConfigKeyConstant {
    public static final String STATUS_OFF="Config's status is OFF";  //配置关闭返回

    public static final String DEFAULT_PIC_PATH = "images/";    //图片默认存储目录
    public static final String DEFAULT_PIC = "default.png";    //默认图片
    public static final String DEFAULT_FILE_PATH = "files/";    //附件默认存储目录
    public static final String DEFAULT_WEB_RESOURECES_PATH = "http://127.0.0.1:8080/resources/";    //附件默认存储目录

    //下面Key常量,对于数据库中的config表中的Config_Key
    public static final String IMG_SAVE_PATH ="Img_Save_Path";    //图片存储目录
    public static final String FILE_SAVE_PATH ="File_Save_Path";    //附件存储目录
    public static final String LOCAL_DIR ="Local_Dir";    //本地存储地址
    public static final String WEB_RESOURECES_PATH ="Web_Resoureces_Path";    //网页资源映射目录

    public static final String SERVER_PORT ="Server_Port";    //项目启动端口号
    public static final String IS_REDIS_CACHE ="Is_Redis_Cache";    //是否启动Redis缓存存储

    public static final String IS_OSS_SERVER ="Is_Oss_Server";    //是否启动OSS存储

    public static final String DOMAIN ="Domain";    //OSS配置中的Domain
    public static final String ENDPOINT ="Endpoint";    //OSS配置中的Endpoint
    public static final String ACCESSKEYID ="AccessKeyId";    //OSS配置中的AccessKeyId
    public static final String ACCESSKEYSECRET ="AccessKeySecret";    //OSS配置中的AccessKeySecret
    public static final String BUCKETNAME ="BucketName";    //OSS配置中的BucketName
    public static final String DISKIMAGE ="DiskImage";    //OSS配置中的DiskImage
    public static final String DISKSOURCE ="DiskSource";    //OSS配置中的DiskSource
    public static final String PERSONAL ="Personal";    //OSS配置中的Personal



}
