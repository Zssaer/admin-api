package com.admin.provider.component;

import com.admin.provider.web.service.ConfigService;
import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;

import static com.admin.provider.component.constant.ConfigKeyConstant.*;

/**
 * @description: 路径组装 组件
 * @author: Zhaotianyi
 * @time: 2021/8/30 10:56
 */
@Component
public class PathComponent {
    @Resource
    private ConfigService configService;


    /**
     * 获取图片数据库保存,,用作返回相对位置,保存在数据库中
     * 比如 "images/2021-8-24/1629772956671_1.png"
     *
     * @param fileName  图片名称
     * @param dataTime 上传时间
     * @return 图片相对位置
     */
    public String getImgDbFileName(String fileName,String dataTime) {
        String imgSavePath = configService.getSysconfig(IMG_SAVE_PATH);

        if (StringUtils.isEmpty(imgSavePath)) {
            return DEFAULT_PIC_PATH + fileName;
        } else {
            return imgSavePath + dataTime + fileName;
        }
    }

    /**
     * 获取附件数据库保存,用作返回相对位置,保存在数据库中
     * 比如 "files/2021-8-24/1629772956671_1.pdf"
     *
     * @param fileName 附件名称
     * @param dataTime  上传时间
     * @return  附件相对位置
     */
    public String getFileDbFileName(String fileName,String dataTime) {
        String fileSavePath = configService.getSysconfig(FILE_SAVE_PATH);

        if (StringUtils.isEmpty(fileSavePath)) {
            return DEFAULT_FILE_PATH + fileName;
        } else {
            return fileSavePath + dataTime + fileName;
        }
    }

    /**
     * 获取本地存储文件夹前路径,用作本地路径映射到网络地址
     */
    public String getLocalStorageDir() {
        return configService.getSysconfig("Local_Dir");
    }

    /**
     * 获取本地存储文件夹下 图片存储文件夹完整路径
     * 比如 "D://storage/img/2018-04-25/1629772956671_1.png"
     *
     */
    public String getLocalImgPrefix(String fileName,String dataTime) throws Exception {
        String localDir = getLocalStorageDir();
        String imgDbFileName = getImgDbFileName(fileName,dataTime);
        if (StringUtils.isEmpty(localDir) || StringUtils.isEmpty(imgDbFileName)) {
            return ResourceUtils.getURL("classpath:").getPath() + DEFAULT_PIC_PATH;
        }
        return localDir + imgDbFileName;
    }

    /**
     * 获取本地存储文件夹下 附件存储文件夹完整路径
     * 比如 "D://storage/files/2021-8-24/1629772956671_1.pdf"
     *
     */
    public String getLocalFilePrefix(String fileName,String dataTime) throws Exception {
        String localDir = getLocalStorageDir();
        String fileDbFileName = getFileDbFileName(fileName, dataTime);
        if (StringUtils.isEmpty(localDir) || StringUtils.isEmpty(fileDbFileName)) {
            return ResourceUtils.getURL("classpath:").getPath() + DEFAULT_FILE_PATH;
        }
        return localDir + fileDbFileName;
    }

    /**
     * 通过数据库中的文件相对路径,来获取文件的绝对路径
     *
     * @param dbFileName 文件相对路径
     * @return String 文件绝对路径
     */
    public String getAccessUrl(String dbFileName) {
        String webResourecesPath = configService.getSysconfig(WEB_RESOURECES_PATH);
        if (StringUtils.isEmpty(dbFileName)) {
            dbFileName = DEFAULT_PIC_PATH+DEFAULT_PIC;
        }
        if (StringUtils.isEmpty(webResourecesPath)) {
            return DEFAULT_WEB_RESOURECES_PATH + DEFAULT_PIC_PATH;
        } else {
            return webResourecesPath + dbFileName;
        }
    }

}
