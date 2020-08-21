# cloudproject

<p align="center"> 
 <img src="https://img.shields.io/circleci/project/vuejs/vue/dev.svg" alt="Build Status">
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR5.RELEASE-blue.svg" alt="Coverage Status">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.7.RELEASE-blue.svg" alt="Downloads">
</p>

### 项目结构

```
myProject
    com-common              公共聚集模块
        com-common-core     公共工具模块
        com-common-cache    缓存模块
        com-common-generator 代码生成模块
        com-common-mybatis   数据持久层模块
        com-common-swagger   接口文档生成模块
    com-tools-provider      相关三方工具提供
        com-tools-api    内部调用feign接口
        com-tools-sms    短信服务模块
        com-tools-oss    存储服务模块
    com-auth-center         认证中心
        auth-server    自研token授权服务
        oauth2-server  oauth2授权服务(密码模式，code授权码模式，客户端模式)
    com-gateway             服务网关
    com-job-center          定时任务中心
    com-monitor             服务监控平台
        com-boot-admin  admin监控
    com-register-center     服务注册中心
        eureka-server   eureka注册中心
    com-user                用户模块   

```
swagger 文档地址：
 http://localhost:8002/user/doc.html
 http://localhost:8010/auth/doc.html

### 项目技术栈

名称 | 框架 |
----| ----| 
数据持久层| mybatis-plus| 
数据库连接池 |druid | 
安全组件| spring security| 
分页插件| pagehelper+spring| 
数据校验| spring-validation|
api文档| swagger2 |
日志框架| logback|


### 相关技术指南

* [mybatis-plus](https://mp.baomidou.com/)
* [pagehelper分页插件](https://pagehelper.github.io/)
* [spring-validation](https://www.cnblogs.com/cjsblog/p/8946768.html)
