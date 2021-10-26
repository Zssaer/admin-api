package com.admin.provider.web.service.impl;

import com.admin.provider.enums.ConfigGroupEnum;
import com.admin.provider.model.Config;
import com.admin.provider.vo.ConfigVO;
import com.admin.provider.web.controller.response.ConfigTreeResp;
import com.admin.provider.web.mapper.ConfigGroupMapper;
import com.admin.provider.model.ConfigGroup;
import com.admin.provider.web.mapper.ConfigMapper;
import com.admin.provider.web.service.ConfigGroupService;
import com.admin.common.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2021/10/26.
 */
@Service
@Transactional
public class ConfigGroupServiceImpl extends AbstractService<ConfigGroup> implements ConfigGroupService {
    @Resource
    private ConfigGroupMapper configGroupMapper;
    @Resource
    private ConfigMapper configMapper;

    /**
     * 获取常用配置组列表
     * @return
     */
    @Override
    public List<ConfigGroup> getCommonConfigGroupList() {
        Condition con = new Condition(ConfigGroup.class);
        con.createCriteria().andEqualTo("groupType", ConfigGroupEnum.COMMON.getCode());

        List<ConfigGroup> list = configGroupMapper.selectByCondition(con);
        return list;
    }

    /**
     * 获取系统配置组列表
     * @return
     */
    @Override
    public List<ConfigGroup> getSysfigGroupList() {
        Condition con = new Condition(ConfigGroup.class);
        con.createCriteria().andEqualTo("groupType", ConfigGroupEnum.SYSTEM.getCode());

        List<ConfigGroup> list = configGroupMapper.selectByCondition(con);
        return list;
    }

    /**
     * 拼装配置组列表
     * @param configGrouplist
     * @return
     */
    @Override
    public List<ConfigTreeResp> fillConfigGroupList(List<ConfigGroup> configGrouplist) {
        List<ConfigTreeResp> resps = new ArrayList<>();
        for (ConfigGroup group : configGrouplist) {
            ConfigTreeResp resp = new ConfigTreeResp();
            BeanUtils.copyProperties(group,resp);
            resp.setLabel(group.getGroupName());

            Condition con=new Condition(Config.class);
            con.createCriteria().andEqualTo("groupId",group.getId());
            List<Config> configList = configMapper.selectByCondition(con);

            List<ConfigVO> configVOList = new ArrayList<>();
            for (Config config:configList) {
                ConfigVO configVO = new ConfigVO();
                BeanUtils.copyProperties(config,configVO);
                configVO.setLabel(config.getConfigKey());
                configVOList.add(configVO);
            }
            resp.setChildren(configVOList);
            resps.add(resp);
        }
        return resps;
    }
}
