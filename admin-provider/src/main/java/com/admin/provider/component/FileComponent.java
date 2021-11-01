package com.admin.provider.component;

import com.admin.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件存储组件
 * @author: Zhaotianyi
 * @time: 2021/8/30 14:16
 */
@Component
public class FileComponent {
    @Autowired
    private LocalFileComponent localFileComponent;
    @Autowired
    private ConfigComponent configComponent;
    @Autowired
    private OssComponent ossComponent;

    private boolean isOss;

    @Bean
    private void isOss() {
        isOss = configComponent.isOssServer();
    }


    /**
     * 存储图片文件 返回数据库文件名
     *
     * @param file 多文件类型
     * @return 数据库文件名
     */
    public String storeImage(MultipartFile file) throws Exception {
        if (isOss) {
            return ossComponent.storeImage(file);
        } else {
            return localFileComponent.storeImage(file);
        }
    }

    /**
     * 删除素材文件
     *
     * @param fileUrl
     * @throws Exception
     */
    public void deleteOssImage(String fileUrl) throws Exception {
        if (isOss) {
            ossComponent.deleteOssImage(fileUrl);
        }else{
            throw new ServiceException("未开启OSS服务器");
        }
    }

    /**
     * 存储素材文件 返回数据库文件名
     *
     * @param file
     * @return 数据库文件名
     * @throws Exception
     */
    public String storeSource(MultipartFile file) throws Exception {
        return localFileComponent.storeSource(file);

    }

    /**
     * 删除素材文件
     *
     * @param fileUrl
     * @throws Exception
     */
    public void deleteSource(String fileUrl) throws Exception {
        localFileComponent.deleteFile(fileUrl);
    }



    /**
     * 存储私有资源（图片）
     *
     * @param file
     * @return 数据库文件名
     * @throws Exception
     */
    public String storePrivateImage(MultipartFile file) throws Exception {
        return localFileComponent.storePrivateImage(file);
    }

}