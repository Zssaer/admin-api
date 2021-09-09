package com.admin.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 启动类
 * @author: Zhaotianyi
 * @time: 2021/8/26 15:37
 */
@SpringBootApplication(scanBasePackages = {"com.admin.provider","com.admin.core"})
public class Application {
    @Value("${server.port}")
    private static int serverPort;

    private static final Log logger = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        logger.info("API接口测试平台:"+ address.getHostAddress()+serverPort+"/doc.html");
    }

}
