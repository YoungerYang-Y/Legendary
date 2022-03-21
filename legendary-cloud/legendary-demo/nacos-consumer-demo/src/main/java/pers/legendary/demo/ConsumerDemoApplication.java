package pers.legendary.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description: 服务消费方Demo
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 0:13
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ConsumerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class);
    }
}
