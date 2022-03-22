package pers.legendary.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
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

    private RestTemplate restTemplate = new RestTemplate();

    @Value("#{'${service.url.oauth}'+'/oauth/check_token'}")
    private String checkTokenUrl;

    public boolean hasPermissionControl(String url) {
        return url.startsWith("/house");
    }

    public boolean accessable(ServerHttpRequest request) {
        String token = request.getQueryParams().getFirst("token");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(checkTokenUrl).queryParam("token", token);
        URI url = builder.build().encode().toUri();

        HttpEntity<?> entity = new HttpEntity<>(request.getHeaders());

//        try {
//            ResponseEntity<TokenInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity, TokenInfo.class);
//            log.info("oauth request: {}, response body: {}, reponse status: {}",
//                    entity, response.getBody(), response.getStatusCode());
//            return response.getBody() != null && response.getBody().isActive();
//        } catch (RestClientException e) {
//            log.error("oauth failed.", e);
//        }
        return false;
    }
}
