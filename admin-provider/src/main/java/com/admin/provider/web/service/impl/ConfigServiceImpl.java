package com.admin.provider.web.service.impl;

import com.admin.provider.web.mapper.ConfigMapper;
import com.admin.provider.model.Config;
import com.admin.provider.web.service.ConfigService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class ConfigServiceImpl extends AbstractService<Config> implements ConfigService {
    @Resource
    private ConfigMapper configMapper;

    /**
     * 获取系统配置信息
     * @param key 配置名
     * @return String
     */
    @Override
    public String getSysconfig(String key) {
        Condition condition=new Condition(Config.class);
        condition.createCriteria().andEqualTo("isSys",1).andEqualTo("configKey",key);
        List<Config> configList = configMapper.selectByCondition(condition);
        return configList.get(0).getConfigValue();
    }

    /**
     * 是否采用Redis来进行存储缓存
     */
    @Override
    public boolean isRedisCache() {
        Condition condition=new Condition(Config.class);
        condition.createCriteria().andEqualTo("isSys",1).andEqualTo("configKey","IsRedisCache");
        List<Config> configList = configMapper.selectByCondition(condition);
        if (configList.get(0).getConfigValue().equals("1")){
            return true;
        }
        return false;
    }
}
