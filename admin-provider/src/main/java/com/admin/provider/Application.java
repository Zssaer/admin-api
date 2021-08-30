package com.admin.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/8/26 15:37
 */
@SpringBootApplication(scanBasePackages = {"com.admin.provider","com.admin.core"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
