## TODO
### legendary-auth

1. ClientId,ResourceIds,scopes的使用 
2. OAuth的mysql库与业务库拆分
3. 增加Redis缓存Token
4. ~~替换jwt为非对称加密~~

### legendary-business

#### user-center
1. 调整permission表的value

### legendary-common

#### legendary-core
1. 公共的BaseController，统一返回CommonResponse，封装常用接口
2. @SysLog：异步日志，AOP注解

#### legendary-mbg
1. 多级缓存，caffeine + redis
2. ~~移动XML到resources~~

### legendary-gateway
1. ~~全局拦截器，解析JWT放到Header中~~
2. 自动截取前缀【暂时不做】
3. sentinel流量保护【集成，但未做限流与熔断】
4. 动态路由


### 其他功能
1. seata分布式事务
2. OSS文件上传，MinIO
3. 集成SpringBootAdmin，实时查看服务日志，实时调整服务日志级别
