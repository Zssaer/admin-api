package com.admin.provider.config.constant;

/**
 * @description: 项目包常量
 * @author: Zhaotianyi
 * @time: 2021/5/6 14:26
 */
public class DirConstant {
    public static final String BASE_PACKAGE = "com.admin.provider";//项目基础包名称，根据自己公司的项目修改

    public static final String FRAMEWORK_PACKAGE = "com.admin.common";//插件接口基础类，本配置不动

    public static final String BUSINESS_PACKAGE = ".web";//根据业务包名称修改
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static final String MAPPER_PACKAGE = "com.admin.provider.web.mapper";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + BUSINESS_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + BUSINESS_PACKAGE + ".controller";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = FRAMEWORK_PACKAGE + ".mapper.Mapper";//Mapper插件基础接口的完全限定名
}
