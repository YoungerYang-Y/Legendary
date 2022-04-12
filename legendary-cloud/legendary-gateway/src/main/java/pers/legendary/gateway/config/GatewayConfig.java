package pers.legendary.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/11 22:42
 */
@Configuration
@EnableWebFluxSecurity
public class GatewayConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // 配置白名单和访问规则，CommonEnum枚举类
        http.csrf().disable();
        return http.build();
    }
}
