package pers.legendary.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pers.legendary.common.model.CommonResponse;
import pers.legendary.common.model.Demo;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 0:17
 */
@FeignClient(value = "nacos-provider-demo")
@RequestMapping("/provider")
public interface ProviderDemoService {
    @GetMapping("/demo/{id}")
    CommonResponse demo(@PathVariable("id") String id);

    @PostMapping("/demo")
    CommonResponse save(@RequestBody Demo demo);
}
