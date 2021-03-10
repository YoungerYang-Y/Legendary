package com.yy.api.application.impl;

import com.yy.api.controller.vo.param.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashMap;

/**
 * ApiTestTest:
 *
 * @Author: YangYang
 * @Date: 2021/2/13 11:49
 */
@Slf4j
class ApiTestTest {


    @Test
    void getAll() {
        HashMap<String, Object> map = new HashMap<>();

        String[] a = {"id"};

        map.put("1",a);
        String[] o =(String[]) map.get("1");
        log.info(o[0]);
    }

    @Test
    public void wrapper() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Object obj = Class.forName("com.yy.api.controller.vo.param.LoginParam").newInstance();
        BeanWrapper wrapper = new BeanWrapperImpl(obj);
        wrapper.setPropertyValue("username","yy");
        wrapper.setPropertyValue("password","pass");
        log.info(wrapper.getPropertyValue("username").toString());
        log.info(wrapper.getPropertyValue("password").toString());

        LoginParam loginParam = (LoginParam) obj;
        log.info(loginParam.toString());
    }

}