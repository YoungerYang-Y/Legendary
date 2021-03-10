package com.yy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangyang
 */
@SpringBootApplication(scanBasePackages="com.yy")
@MapperScan({"com.yy.mbg.*.*.mapper","com.yy.mbg.extend.mapper"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
