package pers.legendary.gateway.filter;

import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import pers.legendary.common.core.constant.AuthConstant;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Description: 全局拦截器
 *  1.对请求进行清洗
 *  TODO
 *      2. 网关转发到业务模块时候会自动截取前缀，不用再每个微服务路由配置了StripPrefixFilter https://www.yuque.com/pig4cloud/pig/za3uwl
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/17 22:50
 */
@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    private static final String GATEWAY_CLIENT_AUTHORIZATION = "Basic " +
            Base64.getEncoder().encodeToString("gateway-client:gateway-client".getBytes());

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //检查请求头是否有携带token令牌
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER.getCode());
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }

        //从token中解析用户信息并设置到Header中去
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX.getCode(), "");
        JWSObject jwsObject = JWSObject.parse(realToken);
        String userStr = jwsObject.getPayload().toString();
        log.info("AuthGlobalFilter.filter() user:{}",userStr);
        String base64 = Base64.getEncoder().encodeToString(userStr.getBytes(StandardCharsets.UTF_8));
        ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER.getCode(), base64).build();
        exchange = exchange.mutate().request(request).build();

        //网关身份
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        builder.header("Authorization", GATEWAY_CLIENT_AUTHORIZATION);

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return SecurityWebFiltersOrder.AUTHENTICATION.getOrder();
    }
}
