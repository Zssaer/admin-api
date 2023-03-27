package com.admin.provider.component;

import com.admin.core.dto.OssConfigDto;
import com.admin.provider.web.service.ConfigService;
import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.admin.provider.component.constant.ConfigKeyConstant.*;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/11/1 15:13
 */
@Component
public class OssComponent {
    @Lazy
    @Autowired
    private PathComponent pathComponent;
    @Autowired
    private ConfigComponent configComponent;
    @Autowired
    private ConfigService configService;

    static final OssConfigDto ossConfigDto = new OssConfigDto();

    @Bean
    private void initOssConfigDTO() {
        if (configComponent.isOssServer()) {
            ossConfigDto.setDomain(configService.getConfig(DOMAIN));
            ossConfigDto.setEndpoint(configService.getConfig(ENDPOINT));
            ossConfigDto.setAccessKeyId(configService.getConfig(ACCESSKEYID));
            ossConfigDto.setAccessKeySecret(configService.getConfig(ACCESSKEYSECRET));
            ossConfigDto.setBucketName(configService.getConfig(BUCKETNAME));
            ossConfigDto.setPersonal("1".equals(configService.getConfig(PERSONAL)));
        }
    }

    /**
     * 初始化OSS实体类
     */
    private OSS init() {
        return new OSSClientBuilder().build(ossConfigDto.getEndpoint(), ossConfigDto.getAccessKeyId(),
                ossConfigDto.getAccessKeySecret());
    }

    /**
     * OSS对象存储
     *
     * @param inputStream 输入流
     * @param fileName  文件名称
     */
    private String uploadObject2OSS(InputStream inputStream, String fileName, String dateDir) {
        String imgSavePath = configService.getConfig(IMG_SAVE_PATH);
        OSS ossClient = init();
        PutObjectResult putObjectResult = ossClient.putObject(ossConfigDto.getBucketName(), imgSavePath + dateDir + fileName, inputStream);
        ossClient.shutdown();
        return putObjectResult.getETag();
    }

    /**
     * OSS对象删除
     *
     * @param diskName 文件路径(包含文件名)
     */
    private void deleteObject2OSS(String diskName) {
        OSS ossClient = init();
        ossClient.deleteObject(ossConfigDto.getBucketName(), diskName);
        ossClient.shutdown();
    }

    private String generateStorageName(String fileName) {
        return System.currentTimeMillis() + "_" + fileName;
    }

    /**
     * 存储图片文件
     *
     * @param file
     * @return 数据库文件名
     * @throws IOException
     */
    public String storeImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ? "auto" : file.getOriginalFilename();
        String storageName = generateStorageName(fileName);
        String dataTime = gnerateFileDirName();
        uploadObject2OSS(file.getInputStream(), storageName, dataTime);
        return pathComponent.getImgDbFileName(storageName, dataTime);
    }

    /**
     * 获取私有链接,用于文件/图片回显等
     *
     * @param dbFileName
     * @return
     */
    public String getPrivateAccessUrl(String dbFileName) {
        OSS ossClient = init();
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest;
        generatePresignedUrlRequest = new GeneratePresignedUrlRequest(ossConfigDto.getBucketName(), dbFileName);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    /**
     * 生成保存文件的文件夹名
     */
    public String gnerateFileDirName() {
        Date date = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dataTime = sdFormatter.format(date);
        return dataTime + "/";
    }

    /**
     * OSS删除图片操作
     *
     * @param fileUrl
     */
    public void deleteOssImage(String fileUrl) {
        String imgSavePath = configService.getConfig(IMG_SAVE_PATH);
        //获取文件名
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        // 获取文件时间文件夹路径
        String fileDir = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
        String date = fileDir.substring(fileDir.lastIndexOf("/") + 1);
        deleteObject2OSS(imgSavePath + date + "/" + fileName);
    }
}
