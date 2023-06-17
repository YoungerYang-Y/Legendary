package pers.legendary.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

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
        Resource resource = new ClassPathResource("public.txt");
        String publicKey;
        try {
            ;
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }

//    private String inputStream2String(InputStream inputStream) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder buffer = new StringBuilder();
//        String line;
//        while ((line = in.readLine()) != null){
//            buffer.append(line);
//        }
//        return buffer.toString();
//    }
}
