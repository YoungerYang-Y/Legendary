package pers.legendary.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description: Token令牌的配置
 *  TODO
 *      1. 替换成jwt公钥，不适用固定key
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
        ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext();
        Resource resource = cx.getResource("classpath:public.txt");
        String publicKey;
        try {
            publicKey = inputStream2String(resource.getInputStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        converter.setSigningKey(publicKey);
        return converter;
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        String key = "12382199281736732";
//        converter.setSigningKey(key);
//        return converter;
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }
}
