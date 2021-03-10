package com.yy.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yy.api.application.sys.login.ILoginManage;
import com.yy.api.application.sys.login.dto.LoginParamDto;
import com.yy.api.controller.vo.param.LoginParam;
import com.yy.core.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * LoginController: 登录控制器
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:42
 */
@Api(tags = "登录接口")
@RestController
public class LoginController {

    @Autowired
    private ILoginManage loginManage;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Valid LoginParam param){

        LoginParamDto loginParamDto = new LoginParamDto();
        BeanUtil.copyProperties(param, loginParamDto);
        if (loginManage.login(loginParamDto)){
            return CommonResult.success("login success");
        }
        else {
            return CommonResult.failed("login failed");
        }
    }

    /**
     * 登出  这个方法没用到,用的是shiro默认的logout
     * @return
     */
    @ApiOperation("退出")
    @GetMapping("/logout")
    public CommonResult logout() {

        if (loginManage.logout()){
            return CommonResult.success("logout success");
        }
        else {
            return CommonResult.failed("logout failed");
        }
    }
}
