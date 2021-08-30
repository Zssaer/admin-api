package com.admin.provider.component;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 存储图片文件 返回数据库文件名
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String storeImage(MultipartFile file) throws Exception {
            return localFileComponent.storeImage(file);
    }

    /**
     * 删除素材文件
     * @param fileUrl
     * @throws Exception
     */
    public void deleteSource(String fileUrl) throws Exception {
            localFileComponent.deleteFile(fileUrl);
    }

    /**
     * 存储素材文件 返回数据库文件名
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String storeSource(MultipartFile file) throws Exception {
            return localFileComponent.storeSource(file);

    }

    /**
     * 存储私有资源（图片）
     * @param file
     * @return
     * @throws Exception
     */
    public String storePrivateImage(MultipartFile file) throws Exception {
        return localFileComponent.storePrivateImage(file);
    }

}