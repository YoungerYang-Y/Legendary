package pers.legendary.business.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 用户中心
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/19 20:57
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"pers.legendary.*"})
public class BusinessUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessUserApplication.class);
    }
}

