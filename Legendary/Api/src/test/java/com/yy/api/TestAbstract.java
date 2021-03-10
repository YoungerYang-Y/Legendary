package com.yy.api;

import com.yy.api.controller.vo.param.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring测试
 */
@SpringBootTest(classes = ApiApplication.class)
@TestPropertySource("classpath:application.yml")
@RunWith(SpringRunner.class)
@Slf4j
public class TestAbstract {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void beanWrapperTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Object obj = Class.forName("com.yy.api.controller.vo.param.LoginParam").newInstance();
        BeanWrapper wrapper = new BeanWrapperImpl(obj);
        wrapper.setPropertyValue("username","yy");
        wrapper.setPropertyValue("password","pass");
        log.info(wrapper.getPropertyValue("username").toString());
        log.info(wrapper.getPropertyValue("password").toString());

        LoginParam loginParam = (LoginParam) obj;
        log.info(loginParam.toString());
//        Object loginParam = applicationContext.getBean("LoginParam");
//        LoginParam bean = (LoginParam) loginParam;
//        log.info(bean.toString());
    }
}
