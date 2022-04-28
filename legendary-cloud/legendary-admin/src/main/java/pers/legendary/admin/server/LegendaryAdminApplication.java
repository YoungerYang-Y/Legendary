package pers.legendary.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: Spring Boot Admin
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/26 14:33
 */
@EnableAdminServer
@SpringBootApplication
public class LegendaryAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegendaryAdminApplication.class, args);
    }
}
