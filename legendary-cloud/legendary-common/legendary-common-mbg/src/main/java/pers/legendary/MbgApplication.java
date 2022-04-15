package pers.legendary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/20 0:16
 */
@SpringBootApplication(scanBasePackages = "pers.legendary.*")
public class MbgApplication {
    public static void main(String[] args) {
        SpringApplication.run(MbgApplication.class);
    }
}
