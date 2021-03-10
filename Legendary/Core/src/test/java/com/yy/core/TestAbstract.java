package com.yy.core;

import com.yy.core.CoreApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring测试
 */
@SpringBootTest(classes = CoreApplication.class)
@TestPropertySource("classpath:application.yml")
@RunWith(SpringRunner.class)
public class TestAbstract {

}
