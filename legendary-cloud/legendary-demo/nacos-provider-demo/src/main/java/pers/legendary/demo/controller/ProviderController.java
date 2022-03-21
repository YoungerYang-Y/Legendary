package pers.legendary.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pers.legendary.common.model.CommonResponse;
import pers.legendary.common.model.Demo;

/**
 * Description: 服务提供者demo
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/17 23:40
 */
@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/demo/{id}")
    public CommonResponse demo(@PathVariable("id") String id){
        log.info("模拟Get查询");
        return CommonResponse.ok(id);
    }

    @PostMapping("/demo")
    public CommonResponse save(@RequestBody Demo demo){
        log.info("模拟Post Save");
        return CommonResponse.data(demo);
    }
}
