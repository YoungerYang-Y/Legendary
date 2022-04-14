package pers.legendary.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Description: Token令牌的配置
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/12 23:31
 */
@Configuration
public class AccessTokenConfig {

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String key = "12382199281736732";
        converter.setSigningKey(key);
        return converter;
    }
}
