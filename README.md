# cloudproject

<p align="center"> 
 <img src="https://img.shields.io/circleci/project/vuejs/vue/dev.svg" alt="Build Status">
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR5.RELEASE-blue.svg" alt="Coverage Status">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.7.RELEASE-blue.svg" alt="Downloads">
</p>

### 项目结构

```
myProject
    cgm-common              公共类[工具、实体]    
    cgm-protocol            内部通信协议定义
    com-tools-provider      相关三方工具提供
    com-auth-center         认证中心
    com-gateway             服务网关
    com-job-center          定时任务中心
    com-monitor             服务监控平台
    com-register-center     服务注册中心
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
