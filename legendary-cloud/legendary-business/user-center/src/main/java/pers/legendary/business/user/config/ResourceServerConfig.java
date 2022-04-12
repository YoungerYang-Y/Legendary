package pers.legendary.business.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * Description: 资源服务器配置
 * 注：优先级    ResourceServerConfigurerAdapter > WebSecurityConfigurerAdapter
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 13:30
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${spring.security.oauth2.client.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.authorization.check-token-access}")
    private String checkTokenEndpointUrl;

    /**
     * TODO AuthServer 是否采用独立的数据源
     */
    private final DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices tokenServices = new RemoteTokenServices();
        // 设置客户端id与secret，注意：client_secret值不能使用passwordEncoder加密！
        tokenServices.setClientId(clientId);
        tokenServices.setClientSecret(clientSecret);
        // 设置授权服务器check_token端点完整地址
        tokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
        tokenServices.setAccessTokenConverter(accessTokenConverter());
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String key = "12382199281736732";
        converter.setSigningKey(key);
        return converter;
    }

    /**
     * 指定当前资源的id和存储方案
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                // 当前资源服务的id
                .resourceId("user-center")
                // 指定令牌如何访问
                .tokenStore(tokenStore())
                // 无状态模式
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //指定不同请求方式访问资源所需要的权限，一般查询是read，其余是write。
                // TODO 是根据客户端的scope来的，并不是根据permission来
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScopeMatching('.*:read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScopeMatching('.*:write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScopeMatching('.*:write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScopeMatching('.*:write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScopeMatching('.*:write')")
                .and()
                .headers().addHeaderWriter((request, response) -> {
                    //允许跨域
                    response.addHeader("Access-Control-Allow-Origin", "*");
                    //如果是跨域的预检请求，则原封不动向下传达请求头信息
                    if ("OPTIONS".equals(request.getMethod())) {
                        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                    }
                });
    }


}
