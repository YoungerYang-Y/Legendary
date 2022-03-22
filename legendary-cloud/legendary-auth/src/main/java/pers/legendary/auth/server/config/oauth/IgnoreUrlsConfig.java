package pers.legendary.auth.server.config.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 允许匿名访问的资源白名单
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 22:23
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
