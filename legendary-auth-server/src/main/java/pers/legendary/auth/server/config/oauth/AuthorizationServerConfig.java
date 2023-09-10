package pers.legendary.auth.server.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import pers.legendary.auth.server.config.sercurity.UserDetailsServiceImpl;
import pers.legendary.common.api.business.user.entity.SysUser;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 授权服务器配置
 * 1、配置客户端
 * 2、配置令牌服务
 * 3、配置令牌端点安全约束
 * <p>
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 22:18
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    /**
     * 用户信息
     */
    private final UserDetailsServiceImpl userDetailsService;
    /**
     * 授权模式专用对象
     */
    private final AuthenticationManager authenticationManager;
    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * OAuth2的主配置信息——授权管理Bean参数，用户校验，token缓存方式
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.approvalStore(approvalStore())
                // 开启密码授权类型
                .authenticationManager(authenticationManager)
                // 要使用refresh_token的话，需要额外配置userDetailsService
                .userDetailsService(userDetailsService)
                // 授权码服务
                .authorizationCodeServices(authorizationCodeServices())
                // 开启json web token模式
                .accessTokenConverter(accessTokenConverter())
                // 配置token存储方式
                .tokenStore(tokenStore());
    }

    /**
     * OAuth客户端配置，指定客户端信息的数据库来源
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * 检查token的策略
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer serverSecurityConfigurer) {
        serverSecurityConfigurer
                // 允许客户端发送表单来进行权限认证来获取令牌
                .allowFormAuthenticationForClients()
                // 只允许认证的客户端，比如网关服务才可以获取和校验token
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

    /**
     * 客户端信息来源
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 授权码存储方式
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * Token保存策略
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 授权信息保存策略
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
     */
    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "2sMsWi".toCharArray());
        return keyStoreKeyFactory.getKeyPair("oauth2", "2sMsWi".toCharArray());
    }

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices services = new DefaultTokenServices();
        // 客户端信息服务
        services.setClientDetailsService(clientDetailsService());
        //是否产生刷新令牌
        services.setSupportRefreshToken(true);
        //令牌存储策略
        services.setTokenStore(tokenStore());
        // JWT 令牌增强【将需要的内容放入JWT中】
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(accessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        services.setTokenEnhancer(tokenEnhancerChain);
        // 生成有效时间（2小时）
        services.setAccessTokenValiditySeconds(7200);
        // 刷新令牌有效时间（1天）
        services.setRefreshTokenValiditySeconds(86400);
        return services;
    }

    /**
     * JWT内容增强，加入用户Id
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> map = new HashMap<>(2);
            SysUser user = (SysUser) authentication.getUserAuthentication().getPrincipal();
            map.put("AuthConstants.JWT_USER_ID_KEY", user.getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        };
    }
}
