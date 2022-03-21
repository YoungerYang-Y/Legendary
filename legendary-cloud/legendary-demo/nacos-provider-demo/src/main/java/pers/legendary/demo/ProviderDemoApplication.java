package pers.legendary.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description: Nacos 服务提供者 Demo
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/17 23:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDemoApplication.class);
    }
}
