package pers.legendary.auth.server.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 22:47
 */
@Configuration
public class JwtTokeStoreConfig {

//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String key = "12382199281736732";
        converter.setSigningKey(key);
        return converter;
    }
}
