package com.admin.provider.component;

import com.admin.common.utils.CmdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 容器加载后自动监听
 * @author: Zhaotianyi
 * @time: 2021/11/1 10:38
 */
@Component
public class CommandRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandRunner.class);
    private int port;

    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        port = event.getWebServer().getPort();
    }

    @Override
    public void run(String... args) throws Exception {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String url = address.getHostAddress() + ":" + port + "/swagger-ui/";
//        CmdUtil.executeCommandAndGetLines("cmd /c start http://" + url);
        logger.info("API接口测试平台:" + url);
    }
}
