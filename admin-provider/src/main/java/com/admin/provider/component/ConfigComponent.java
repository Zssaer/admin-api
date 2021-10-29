package com.admin.provider.component;

import com.admin.provider.web.service.ConfigService;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.admin.provider.component.constant.ConfigKeyConstant.SERVER_PORT;


/**
 * @description: 配置组件(获取配置信息)
 * @author: Zhaotianyi
 * @time: 2021/10/26 10:00
 */
@Component
public class ConfigComponent {
    private static final Logger log = LoggerFactory.getLogger(ConfigComponent.class);
    @Resource
    private ConfigService configService;

    /**
     * 获取服务器地址
     *
     * @return
     */
    public Integer getAddress() {
        String port = configService.getConfig(SERVER_PORT);
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");

        Matcher isNum = pattern.matcher(port);
        if (!isNum.matches() || StringUtils.isEmpty(port)) {
            log.error("自定义端口配置失败,项目默认端口启动,现将项目以默认端口启动");
            return 8085;
        }
        if (Integer.parseInt(port) < 1024 || Integer.parseInt(port) > 65535) {
            log.error("自定义端口配置不在用户端口范围中,请将其设置在1024-65535之间,现将项目以默认端口启动");
            return 8085;
        }
        ;
        return Integer.parseInt(port);
    }


}
