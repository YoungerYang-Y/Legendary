package pers.legendary.gateway.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 配置网关访问白名单，指定路径不做拦截。
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/13 13:20
 */
@Data
@Component
@ConfigurationProperties(prefix = WhitelistPathConfig.PREFIX)
public class WhitelistPathConfig {
    static final String PREFIX = "ignore";
    /**
     * 白名单 Url
     */
    private List<String> urls = new ArrayList<>();
}
