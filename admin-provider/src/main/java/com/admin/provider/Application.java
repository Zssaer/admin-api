package com.admin.provider;

import com.admin.provider.component.ConfigComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 启动类
 * @author: Zhaotianyi
 * @time: 2021/8/26 15:37
 */
@SpringBootApplication(scanBasePackages = {"com.admin.provider", "com.admin.core"})
public class Application implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Resource
    private ConfigComponent configComponent;
    public static int port = 0;

    private static final Log logger = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        logger.info("API接口测试平台:" + address.getHostAddress() + ":" + port + "/doc.html");
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        port = configComponent.getAddress();
        // 若数据库未自定义配置端口,或者关闭,则默认端口号为8085
        factory.setPort(port);
    }
}
