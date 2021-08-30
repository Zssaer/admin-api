package com.admin.provider.cache;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: 图像缓存获取
 * @author: Zhaotianyi
 * @time: 2021/5/10 16:35
 */
@Service
public class ImgValidService {
    @Value("${sysConfig.cache.imgValid.expire}")
    private long expireSeconds;

    @Autowired
    private CommonCacheService commonCacheService;

    public void add(String key,String value) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            throw new Exception("缓存参数错误");
        }
        commonCacheService.set(key, value, expireSeconds);
    }

    public String get(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            throw new Exception("缓存获取参数错误");
        }

        return commonCacheService.get(key, String.class, expireSeconds);
    }

    public void remove(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            throw new Exception("缓存删除参数错误");
        }

        commonCacheService.delete(key);
    }

}
