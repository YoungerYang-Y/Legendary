package pers.legendary.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;

/**
 * Description: 网关全局过滤器配置
 *  TODO
 *      1. 将令牌携带的用户信息解析出来，封装成JSON数据，然后通过Base64加密，放入到请求头中，转发给下游微服务。
 *          这样一来，下游微服务只需要解密请求头中的JSON数据，即可获取用户的详细信息。
 *      2. 网关转发到业务模块时候会自动截取前缀，不用再每个微服务路由配置了StripPrefixFilter https://www.yuque.com/pig4cloud/pig/za3uwl
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 21:57
 */
@Configuration
@RequiredArgsConstructor
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    private final TokenStore tokenStore;
//    private final ServerBearerTokenAuthenticationConverter converter;

    private static final String GATEWAY_CLIENT_AUTHORIZATION = "Basic " +
            Base64.getEncoder().encodeToString("gateway-client:gateway-client".getBytes());

    /**
     * 请求头中赋予网关服务合法客户端身份
     */
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //检查请求头是否有携带token令牌
//        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (StringUtils.isEmpty(token)) {
//            return Mono.error(new InvalidTokenException("token不存在"));
//        }
//        converter.convert(exchange).
//        Mono<Authentication> mono = converter.convert(exchange);
//        mono.
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
//        Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
//        // 获取用户身份信息
//        String userName = additionalInformation.get("user_name").toString();
//        // 获取用户权限
//        List<String> authorities = (List<String>) additionalInformation.get("authorities");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("user_name",userName);
//        jsonObject.put("authorities",authorities);
//        String base64 = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes(StandardCharsets.UTF_8));

        //网关身份
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        builder.header("Authorization", GATEWAY_CLIENT_AUTHORIZATION);
//        builder.header("token_info", base64);
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return SecurityWebFiltersOrder.AUTHENTICATION.getOrder();
    }
}
