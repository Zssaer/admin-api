package com.admin.provider;

import com.admin.provider.component.ConfigComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import javax.annotation.Resource;

/**
 * @description: 启动类
 * @author: Zhaotianyi
 * @time: 2021/8/26 15:37
 */
@SpringBootApplication(scanBasePackages = {"com.admin.provider", "com.admin.core"})
public class Application implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Resource
    private ConfigComponent configComponent;
    public static int port = 0;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }
    
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        port = configComponent.getAddress();
        // 若数据库未自定义配置端口,或者关闭,则默认端口号为8085
        factory.setPort(port);
    }
}
