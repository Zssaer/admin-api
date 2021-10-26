package com.admin.provider.cache;

import com.admin.core.utils.EhcacheUtils;
import com.admin.provider.web.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 缓存服务管理
 * @author: Zhaotianyi
 * @time: 2021/5/10 16:06
 */
@Service
public class CommonCacheService {
    @Autowired
    private ConfigService configService;
    //@Autowired
    //private RedisUtils redisUtils;
    @Autowired
    private EhcacheUtils ehcacheUtils;

    public void set(String key, Object value) {
        if (configService.isRedisCache()) {
            //this.redisUtils.set(key, value);
        } else {
            this.ehcacheUtils.put(key, value);
        }
    }

    public void set(String key, Object value, long expire) {
        if (configService.isRedisCache()) {
            //this.redisUtils.set(key, value, expire);
        } else {
            this.ehcacheUtils.put(key, value, (int) expire);
        }

    }

    public <T> T get(String key, Class<T> clazz) {
        return /*configService.isRedisCache() ? (T)this.redisUtils.get(key) :*/ this.ehcacheUtils.get(key, clazz);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        return /*configService.isRedisCache() ? (T)this.redisUtils.get(key) :*/ this.ehcacheUtils.get(key, clazz);
    }

    public void delete(String key) {
        if (configService.isRedisCache()) {
            // this.redisUtils.del(key);
        } else {
            this.ehcacheUtils.remove(key);
        }

    }
}
