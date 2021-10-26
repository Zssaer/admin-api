package com.admin.provider.component;

import com.admin.provider.model.Config;
import com.admin.provider.web.service.ConfigService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.admin.provider.component.constant.ConfigKeyConstant.NORMAL;


/**
 * @description: 配置组件(获取配置信息)
 * @author: Zhaotianyi
 * @time: 2021/10/26 10:00
 */
@Component
public class ConfigComponent {
    public static final String OFF = "Config's status is OFF";
    @Resource
    private ConfigService configService;




}
