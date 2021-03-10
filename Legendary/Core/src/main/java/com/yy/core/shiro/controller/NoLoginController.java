package com.yy.core.shiro.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yy.core.common.CommonResult;
import com.yy.core.common.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController: 未登录返回错误信息
 *
 * @Author: YangYang
 * @Date: 2021/3/9 15:57
 */
@RestController
public class NoLoginController {

    @GetMapping("/noLogin")
    public CommonResult noLogin(){

        return CommonResult.failed(ResultCode.UNAUTHORIZED);
    }
}
