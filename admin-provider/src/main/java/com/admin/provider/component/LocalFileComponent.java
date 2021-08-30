package com.admin.provider.component;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 本地文件存储组件
 * @author: Zhaotianyi
 * @time: 2021/5/31 14:17
 */
@Component
public class LocalFileComponent {
    @Autowired
    private PathComponent pathComponent;

    /**
     * 存储图片文件 返回数据库文件名
     *
     */
    public String storeImage(MultipartFile file) throws Exception {
        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ? "auto" : file.getOriginalFilename();

        fileName = generateFileName(fileName);
        String dataTime = gnerateFileDirName();

        String path = pathComponent.getLocalImgPrefix(fileName,dataTime);
        this.storeFile(file, path);
        return pathComponent.getImgDbFileName(fileName, dataTime);
    }


    /**
     * 存储素材文件 返回数据库文件名
     *
     */
    public String storeSource(MultipartFile file) throws Exception {
        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ? "auto" : file.getOriginalFilename();

        fileName = generatePaperFileName(fileName);
        String dataTime = gnerateFileDirName();

        String path = pathComponent.getLocalFilePrefix(fileName,dataTime);
        this.storeFile(file, path);
        return pathComponent.getFileDbFileName(fileName, dataTime);
    }

    /**
     * 存储私有图片
     *
     */
    public String storePrivateImage(MultipartFile file) throws Exception {
        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ? "auto" : file.getOriginalFilename();

        fileName = generateFileName(fileName);
        String dataTime = gnerateFileDirName();

        String path = pathComponent.getLocalImgPrefix(fileName,dataTime);
        this.storeFile(file, path);
        return pathComponent.getImgDbFileName(fileName, path);
    }


    /**
     * 存储文件
     *
     */
    public void storeFile(MultipartFile file, String path) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件是空的");
        }
        File dest = new File(path);

        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            throw new Exception("保存文件失败");
        }
    }

    /**
     * 删除附件
     *
     */
    public void deleteFile(String fileUrl) throws Exception {
        String path = pathComponent.getLocalStorageDir();
        //获取文件名
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        // 获取文件时间文件夹路径
        String fileDir = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
        String fileDir2 = fileDir.substring(fileDir.lastIndexOf("/") + 1);

        String fileDbFileName = pathComponent.getFileDbFileName(fileName,fileDir2);
        if (fileUrl.isEmpty()) {
            throw new Exception("文件是空的");
        }
        try {
            File file = new File(path + fileDbFileName);
            file.delete();
        } catch (Exception e) {
            throw new Exception("删除文件失败");
        }
    }

    /**
     * 生成保存文件的文件夹名
     *
     */
    public String gnerateFileDirName(){
        Date date = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dataTime = sdFormatter.format(date);
        return dataTime + "/";
    }

    /**
     * 生成保存的文件名规则
     *
     */
    public String generateFileName(String fileName)throws UnsupportedEncodingException {
        if (isChinese(fileName)){
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            Random rand =new Random();
            String s = Integer.toString(rand.nextInt(99999));
            return System.currentTimeMillis() + "_" + s + suffix;
        }
        return System.currentTimeMillis() + "_" + fileName;
    }


    /**
     * 生成保存的论文文件名
     *
     */
    public String generatePaperFileName(String fileName)throws UnsupportedEncodingException {
        if (isChinese(fileName)){
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            Random rand =new Random();
            String s = Integer.toString(rand.nextInt(99999));
            return System.currentTimeMillis() + "_" + s + suffix;
        }
        return System.currentTimeMillis() + "_" + fileName;
    }

    /**
     * 检查是否含有中文
     * @param str 需要检测的字符串
     */
    public boolean isChinese(String str) throws UnsupportedEncodingException
    {
        int len = str.length();
        for(int i = 0;i < len;i ++)
        {
            String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
            if(temp.equals(str.charAt(i) + ""))
                continue;
            String[] codes = temp.split("%");
            //判断是中文还是字符(下面判断不精确，部分字符没有包括)
            for(String code:codes)
            {
                if(code.compareTo("40") > 0)
                    return true;
            }
        }
        return false;
    }
}
