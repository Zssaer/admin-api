package com.admin.provider.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.admin.provider.component.PathComponent;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.nio.charset.Charset;
import java.util.List;


/**
 * @description: TODO
 * @author: Zhaotianyi
 * @time: 2021/5/6 14:37
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Autowired
    private PathComponent pathComponent;

    public String getLocalstorageDir(){
        String localStorageDir = "file:/" + pathComponent.getLocalStorageDir();
        return localStorageDir;
    }

    //静态资源 映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 解决swagger的相关文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        //设置本地存储文件夹资源映射
        registry.addResourceHandler("/resources/**").addResourceLocations(this.getLocalstorageDir());
    }

    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
        // 注册路由登录拦截器,拦截除登录接口以外的所有接口地址,都需要登录
//        registry.addInterceptor(new SaRouteInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/login");
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.addInterceptors(registry);
    }
}
