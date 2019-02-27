[TOC]

# SpringCloud搭建模板

* SpringBoot.version:2.0.2
* Spring-cloud.version:Finchley.RELEASE

# 1. 主目录结构
* `cilent/product` :商品信息的微服务
* `cilent/order` :订单信息的微服务
* `eureka_server,eureka_server2` : 服务中心
* `config-service`:统一配置中心服务
* `config-repo`: 微服务配置文件
* `api-zuul`: 网关服务
* `user`:用户服务
* `cilent/product/product_client`:要mvn install的自己的仓库

# 2. 微服务目录结构
* `client` :Feign接口提供
* `common` :公用的对象
* `service`:业务逻辑

# 3. 统一配置服务中心(Spring cloud bus)

1. 刷新配置的方式
	* post请求 `http://127.0.0.1:8100/actuator/bus-refresh`  请求头:`Content-Type:application/json`就可以刷新配置信息

	* 可以使用git的push是的webhook,如果webhook的`actuator/bus-refresh`不行,可以使用`/webhook/push`
2. 刷新spring自身的配置信息,如:数据库连接信息刷新
	* 在order,product和api-zuul的config中有相关实现方式

# 4. rabbitmq和Spring cloud stream
* 实现案例在order项目中
* 在message,collection,test包下

# 5. spring cloud zuul

* 跨域:`com.chc.api_zuul.config.CorsConfig`
* 买家和卖家权限控制:`com.chc.api_zuul.filter.AuthBuyerFilter`,`com.chc.api_zuul.filter.AuthSellerFilter`
* 解决敏感头cookie的问题:`配置文件`

# 6. spring cloud Hystrix
* 案例:cilent/order HystrixController和config-repo.order-dev.properties
* feign使用hystrix案例:在order和product/product_client中
* 单例版的hystrix监控:`http://127.0.0.1:8082/actuator/hystrix.stream`
* 集群版的hystrix监控:`https://www.cnblogs.com/duanxz/p/7525753.html`

# 7. spring cloud sleuth
* 视图工具:[https://zipkin.io/](https://zipkin.io/ "Zipkin")
* zipkin的案例在:order中
* 事件类型
	1. cs(client send):客户端发起请求的时间
	2. cr(client received):客户端接收到处理完请求的时间
	3. ss(service send):服务端处理完请求的时间
	4. sr(service received):服务端收到调用端请求的时间
	5. 客户端调用时间=cr-cs
	6. 服务端处理时间=sr-ss

# 8. 统一配置服务中心
* spring cloud bus : 通过轻量消息代理连接各个分布的节点。管理和传播所有分布式项目中的消息，本质是利用了MQ的广播机制在分布式的系统中传播消息，目前常用的有Kafka和RabbitMQ

# 9. 分布式事务

## 1. 消息驱动模式

* 服务调用链
![](https://youdaoyun-chc.oss-cn-shenzhen.aliyuncs.com/%E5%88%86%E5%B8%83%E5%BC%8F%E4%BA%8B%E5%8A%A1/%E6%B6%88%E6%81%AF%E9%A9%B1%E5%8A%A8%E6%A8%A1%E5%BC%8F%E7%9A%84%E6%A1%88%E4%BE%8B%E8%AE%BE%E8%AE%A1%E6%B5%81%E7%A8%8B.PNG)

* 服务异常处理,超时处理:order服务`com.chc.order_service.message.TicketOrderMQ`
* 请求连接order服务:`http://127.0.0.1:8082/ticketOrder/sentLockTicket`
* 参数:`{"userId":1,"title":"ticket_test2","ticketNum":100,"amount":70}`

