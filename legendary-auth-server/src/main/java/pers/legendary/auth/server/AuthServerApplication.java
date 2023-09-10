package pers.legendary.auth.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Description: OAuth2 Server
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 22:37
 */
@EnableDubbo
@ConfigurationPropertiesScan
@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class);
    }
}
