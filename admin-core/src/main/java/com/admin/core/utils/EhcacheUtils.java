package com.admin.core.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description: Ehcache工具类
 * @author: Zhaotianyi
 * @time: 2021/5/10 16:23
 */
@Component
public class EhcacheUtils {
    private static CacheManager cacheManager = null;
    private static Cache cache = null;
    //设置默认缓存对象名
    private static final String CACHE_NAME = "cache";
    private static final int TIME_TO_LIVE_SECONDS_MAX = 60 * 60 * 24 * 365;

    static {
        initCacheManager();
        initCache();
    }

    @PostConstruct
    private void init() {
        // 关闭tomcat时增加删除回调的钩子
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
    }

    @PreDestroy
    private void destroy() {
        // 关闭tomcat时，执行相应的关闭
        cacheManager.shutdown();
    }

    /**
     * 默认过期时长，单位：秒
     */
    private final static int DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     * 本组件实际设置过期时长：TIME_TO_LIVE_SECONDS_MAX
     */
    private final static int NOT_EXPIRE = -1;

    private static void initCacheManager() {
        cacheManager = CacheManager.getInstance();
    }

    private static void initCache() {
        if (null == cacheManager.getCache(CACHE_NAME)) {
            cacheManager.addCache(CACHE_NAME);
        }
        if (null == cache) {
            cache = cacheManager.getCache(CACHE_NAME);
        }
    }
    /**
     * 存放数据 带有过期时间
     *
     */
    public void put(Object key, Object value, int timeToIdleSeconds) {
        if (NOT_EXPIRE == timeToIdleSeconds) {
            timeToIdleSeconds = TIME_TO_LIVE_SECONDS_MAX;
        }

        Element element = new Element(key, value, timeToIdleSeconds, TIME_TO_LIVE_SECONDS_MAX);
        cache.put(element);
        cache.flush();
    }
    /**
     * 存放数据
     *
     */
    public void put(Object key, Object value) {
        Element element = new Element(key, value,true);
        cache.put(element);
        cache.flush();
    }
    /**
     * 取出数据
     *
     */
    public <T> T get(Object key, Class<T> clazz) {
        Element element = cache.get(key);
        cache.flush();
        if (null != element) {
            Object value = element.getObjectValue();
            return null == value ? null : (T) value;
        } else {
            return null;
        }
    }
    /**
     * 移除数据
     *
     */
    public void remove(Object key) {
        cache.remove(key);
        cache.flush();
    }
}
