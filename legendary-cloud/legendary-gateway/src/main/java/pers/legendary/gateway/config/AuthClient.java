package pers.legendary.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 22:03
 */
@Slf4j
@Component
public class AuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.security.oauth2.authorization.check-token-access}")
    private String checkTokenUrl;

    public boolean hasPermissionControl(String url) {
        return url.startsWith("/user_center");
    }

    public boolean accessible(ServerHttpRequest request) {
        String token = request.getQueryParams().getFirst("token");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(checkTokenUrl).queryParam("token", token);
        URI url = builder.build().encode().toUri();

        HttpEntity<?> entity = new HttpEntity<>(request.getHeaders());

        try {
            ResponseEntity<TokenInfo> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenInfo.class);
            log.info("oauth request: {}, response body: {}, response status: {}",
                    entity, response.getBody(), response.getStatusCode());
            return response.getBody() != null && response.getBody().isActive();
        } catch (RestClientException e) {
            log.error("oauth failed.", e);
        }
        return false;
    }
}
