 
# 简介
xd是基于`spring boot 2.1.0`和 `spirng cloud (Finchley SR2)`的微服务开发框架，代码简洁，结构清晰，适用于学习和小公司快速开发。
**该架构未在大型生产环境进行压测验证，未来会采用JMeter压测**
# [技术选型](https://github.com/xionggit/xd/wiki/%E6%8A%80%E6%9C%AF%E9%80%89%E5%9E%8B) 
# [迭代计划](https://github.com/xionggit/xd/wiki/%E8%BF%AD%E4%BB%A3%E8%A7%84%E5%88%92)
# [开发洁癖](https://github.com/xionggit/xd/wiki/%E4%BB%A3%E7%A0%81%E6%B4%81%E7%99%96)
# 框架目录规划
    * `xd`
        * `xd-common`
            * `xd-common-global-config`  全局异常、Redis配置、国际化、Validation
            * `xd-common-util` 常用工具类
            * `xd-common-persistence` 对mybatis的相关封装，多数据源、pagehelper分页插件
            * `xd-common-user-pojo` 用于系统内部转换使用，只有实体对象
            * `xd-common-generator-util` 代码生成器，准备在1.1版本整合swagger和hibernate validation
        * `xd-cloud-frameword`
            * `xd-cloud-zuul` 使用zuul2.0实现网关
            * `xd-cloud-config` (是否使用consule的配置待定) spring config配置中心，所有服务从配置中心读取文件
            * `xd-cloud-zipkin` 服务追踪
            * `xd-cloud-oauth2-server` (是不是放到这一级待定) 实现`spring security`统一认证个`oauth2`授权，
        * `xd-web-parent`
            * `xd-web-admin`
                * `xd-web-admin-mp` 统一用户管理（用户、角色、资源）
                * `xd-web-admin-ui` 基于bootstrap、vue的后台页面
            * `xd-web-search`
                * `xd-web-search-pojo` search服务实体类， 只有实体，可以内部服务直接提供
                * `xd-web-search-service` search主服务，search实体对应的mapper 和mapper.xml 存放在主服务中
                * `xd-web-search-api` 对外提供的rest接口，返回对象可直接使用xd-search-pojo 实体
            * `xd-web-other` 其他所有服务的层级军参考这种目录结构
                * `xd-web-other-pojo` 
                * `xd-web-other-service`
                * `xd-web-other-api`    

# 环境配置（版本参考技术选型中所列模型）
    * jdk1.8
    * mysql数据库
    * consule注册中心
    * Nginx反向代理服务器，暂时只作为文件服务器使用
    * Git版本管理
    * Redis
    * lombok
        * idea直接在线安装lombok即可
        * Eclipse安装lombok。1、在配置文件eclipse.ini中最后一行添加内容: -javaagent:lombok.jar
            2、找到maven本地仓库的lombok.jar，并复制到与eclipse.ini同级目录，重启IDE，clean项目 
    * RabbitMQ
    

**参考了网上很多文章，这里不一一列举了，如有侵权请及时联系我们进行处理，谢谢**    