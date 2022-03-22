package pers.legendary.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;

/**
 * Description: 网关过滤器配置
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 21:57
 */
@Order(1)
@Configuration
public class GatewayClientFilter implements GlobalFilter {

    private static final String GATEWAY_CLIENT_AUTHORIZATION = "Basic " +
            Base64.getEncoder().encodeToString("gateway-client:gateway-client".getBytes());

    /**
     * 请求头中赋予网关服务合法客户端身份
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关身份
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        builder.header("Authorization", GATEWAY_CLIENT_AUTHORIZATION);
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }
}
