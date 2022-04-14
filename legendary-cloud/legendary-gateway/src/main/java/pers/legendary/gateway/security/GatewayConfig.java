package pers.legendary.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import pers.legendary.gateway.exception.RequestAccessDeniedHandler;
import pers.legendary.gateway.exception.RequestAuthenticationEntryPoint;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/11 22:42
 */
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class GatewayConfig {

    /**
     * JWT 鉴权管理器
     */
    private final JwtAccessManager accessManager;
    /**
     * JWT 校验管理器
     */
    private final ReactiveAuthenticationManager tokenAuthManager;
    /**
     * token过期异常处理
     */
    private final RequestAuthenticationEntryPoint requestAuthenticationEntryPoint;
    /**
     * 权限不足异常处理
     */
    private final RequestAccessDeniedHandler requestAccessDeniedHandler;
    /**
     * 白名单
     */
    private final WhitelistPathConfig whitelistPathConfig;

    /**
     *  认证过滤器
     */
    @Bean
    public AuthenticationWebFilter authenticationWebFilter(){
        // 向认证过滤器中放入token校验管理器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
        return authenticationWebFilter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        // 配置白名单和访问规则，CommonEnum枚举类
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                // 增加白名单
                .pathMatchers(whitelistPathConfig.getUrls().toArray(new String[0])).permitAll()
                .anyExchange().access(accessManager)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(requestAuthenticationEntryPoint)
                .accessDeniedHandler(requestAccessDeniedHandler)
                .and()
                // 添加token的认证过滤器
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}