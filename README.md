 
# 简介
xd是基于`spring boot 2.1.0`和 `spirng cloud (Finchley SR2)`的微服务开发框架，代码简洁，结构清晰，适用于学习和小公司快速开发。
# 框架目录规划
* `xd`
    * `xd-common`
        * `xd-common-global-config`  全局异常、Redis配置、国际化、Validation
        * `xd-common-util` 常用工具类
        * `xd-common-persistence` 对mybatis的相关封装，多数据源、pagehelper分页插件
        * `xd-common-user-pojo` 用于系统内部转换使用，只有实体对象
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
    