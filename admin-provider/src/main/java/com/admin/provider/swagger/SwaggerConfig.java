package com.admin.provider.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket adminApi(Environment environment) {
        //获取项目的开发环境:dev
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles判断是否当前是否处在设定当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Back-End API")
                .enable(flag)
                .apiInfo(apiInfo("通用后台API"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.admin.provider.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String title) {
        //作者信息
        Contact contact = new Contact("赵天一", "http://zssaer.cn", "ntc001@163.com");
        return new ApiInfoBuilder()
                .title(title)
                .description("通用后台API 用作各种后台API的基础模板")
                .contact(contact)
                .version("1.0")
                .build();
    }

}