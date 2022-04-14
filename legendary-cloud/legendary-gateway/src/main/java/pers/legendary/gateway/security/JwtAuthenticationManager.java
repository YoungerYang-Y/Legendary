package pers.legendary.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Description: 认证管理器
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/12 23:32
 */
@Slf4j
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenStore tokenStore;

    public JwtAuthenticationManager(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken -> {
                    // 解析令牌
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    // 校验token
                    if(oAuth2AccessToken == null){
                        return Mono.error(new InvalidTokenException("无效的token!"));
                    }else if(oAuth2AccessToken.isExpired()){
                        return Mono.error(new InvalidTokenException("token已过期!"));
                    }
                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(accessToken);
                    if(oAuth2Authentication == null){
                        return Mono.error(new InvalidTokenException("无效的token!"));
                    }
                    else{
                        return Mono.just(oAuth2Authentication);
                    }
                })).cast(Authentication.class);
    }
}
