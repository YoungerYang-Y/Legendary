package pers.legendary.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/11 22:42
 */
@Configuration
public class GatewayConfig {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //支持所有方法
        config.addAllowedMethod("*");
        //跨域处理 允许所有的域
        config.addAllowedOrigin("*");
        //支持所有请求头
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        //匹配所有请求
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
