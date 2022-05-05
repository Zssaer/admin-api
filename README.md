# Admin-API

**Admin-API** 是一个轻量级 Java Spring集成框架,使用Admin-API会极大减少日常项目的代码量。

## 🔥项目特点 

1. 框架集成JavaWeb开发常见功能：`文件上传`、`角色授权`、`全局异常处理`、`redis控制台`、`API日志统计`、`全局配置`、`跨域处理`、`定时操作` 等等..

2. 内置MVC代码生成器。

3. 你只需写上简单的表注释，即可直接生成：`java代码`、`后台管理接口`、`接口文档`等，使项目中80%的代码做到自动化，省时省力。

4. 实现接口限流，轻松解决中小型项目下的接口QPS限流问题。


##  🔧功能架构

- 项目基于`springboot 2.4.4`搭建，以`freemarker`作为代码生成器模板
- 采用了如下第三方包：
  - Sa-Token
  - Redis
  - Swagger
  - knife4J-UI
  - Mybatis
  - MybatisGenerator
  - PageHelper
  - Fastjson
  - Druid
  - Easy-Captcha
  - Ehcache
  - oss
  - excel-spring-boot-starter
  - quartz
  - screw

## 📣Q&A
### Q:Swagger文档页面无法打开？
A：项目框架采用了knife4J-UI，它是一个基于Swagger的第三方UI组件，采用Bootstrap，Swagger文档页面其在`/doc.htm`下。
### Q：我该如何进行在此项目框架中完成我的业务？
A：你只需要编写对应的表，然后生成代码即可，修改Controller、Service类即可。
### Q:如何生成代码？
A：生成代码的方法是：在数据库中创建对应的表，然后在admin-provider下src/test/com/provider目录编辑CodeGenerator类。需要修改数据库连接信息，
将其需要生成的表名传入main方法中的genCode中，作为参数，支持同时生成多个表，最后运行CodeGenerator类即可。
### Q:如何配置跨域？
A：项目框架默认开启了全局跨域，允许任何请求来源。在实际生产项目中，需要配置具体的跨域规则请修改com.admin.core.config.CorsConfig类。
### Q:任何接口限流?
A：需要在Controller类上方法加上@Limit注解，并且需要配置相应的限流参数，具体参数请参考com.admin.core.annotation.RateLimiter类注释。
### Q:项目框架是否需要必须搭配Redis服务运行?
A：是的。





