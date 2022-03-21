package pers.legendary.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.legendary.common.model.CommonResponse;
import pers.legendary.common.model.Demo;
import pers.legendary.demo.feign.ProviderDemoService;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 1:14
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ProviderDemoService service;

    @GetMapping("/get/{id}")
    public CommonResponse get(@PathVariable("id") String id) {

        return service.save(new Demo(1,"12",2));
    }
}
