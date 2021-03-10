package com.yy.core.shiro.service;

import com.yy.core.TestAbstract;
import com.yy.core.shiro.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * ILoginServiceTest:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 11:50
 */
@Slf4j
public class ILoginServiceTest extends TestAbstract {

    @Autowired
    private ILoginService loginService;


    @Test
    public void getUserByName() {

        User yy = loginService.getUserByName("yy");
        log.info(yy.toString());
    }
}