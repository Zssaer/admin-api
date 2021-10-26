package com.admin.provider.web.service.impl;

import com.admin.provider.model.ConfigGroup;
import com.admin.provider.web.mapper.ConfigMapper;
import com.admin.provider.model.Config;
import com.admin.provider.web.service.ConfigGroupService;
import com.admin.provider.web.service.ConfigService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

import static com.admin.provider.component.constant.ConfigKeyConstant.IS_REDIS_CACHE;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class ConfigServiceImpl extends AbstractService<Config> implements ConfigService {
    @Resource
    private ConfigMapper configMapper;
    @Resource
    private ConfigGroupService configGroupService;

    /**
     * 获取配置信息
     *
     * @param key 配置名
     * @return String
     */
    @Override
    public String getConfig(String key) {
        Condition condition = new Condition(Config.class);
        condition.createCriteria().andEqualTo("configKey", key);
        List<Config> configList = configMapper.selectByCondition(condition);
        if (configList.get(0).getStatus() == 0) {
            return "Config's status is OFF";
        }
        return configList.get(0).getConfigValue();
    }

    /**
     * 获取配置列表
     *
     * @param groupCode 配置组Code
     * @return
     */
    @Override
    public List<Config> getConfigList(String groupCode) {
        Condition condition = new Condition(ConfigGroup.class);
        condition.createCriteria().andEqualTo("groupCode", groupCode);
        Integer groupId = configGroupService.findByCondition(condition).get(0).getId();

        Condition con = new Condition(Config.class);
        con.createCriteria().andEqualTo("groupId", groupId);
        List<Config> configList = configMapper.selectByCondition(con);
        return configList;
    }


    /**
     * 是否采用Redis来进行存储缓存
     */
    @Override
    public boolean isRedisCache() {
        Condition condition = new Condition(Config.class);
        condition.createCriteria().andEqualTo("isSys", 1).andEqualTo("configKey", IS_REDIS_CACHE);
        List<Config> configList = configMapper.selectByCondition(condition);
        return configList.get(0).getConfigValue().equals("1");
    }
}
