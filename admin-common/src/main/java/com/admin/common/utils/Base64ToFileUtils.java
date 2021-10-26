package com.admin.common.utils;

import com.admin.common.utils.common.Base64ToMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: Base64转图片文件工具类
 * @author: Zhaotianyi
 * @time: 2021/10/25 10:32
 */
public class Base64ToFileUtils {
    public static MultipartFile transform(String base64) {
        final String[] base64Array = base64.split(",");
        String dataUri, data;
        // 判断Base64中是否带有DataURI字段
        if (base64Array.length > 1) {
            dataUri = base64Array[0];
            data = base64Array[1];
        } else {
            // 手动生成DataURI
            dataUri = "data:image/jpg;base64";
            data = base64Array[0];
        }
        return new Base64ToMultipartFile(data, dataUri);
    }
}
