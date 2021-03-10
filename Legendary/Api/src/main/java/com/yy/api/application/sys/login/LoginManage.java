package com.yy.api.application.sys.login;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.api.application.sys.login.dto.LoginParamDto;
import com.yy.core.shiro.api.LoginApi;
import com.yy.core.shiro.dto.User;
import com.yy.mbg.domain.entity.SysUser;
import com.yy.mbg.domain.entity.SysUserInfo;
import com.yy.mbg.domain.service.ISysUserInfoService;
import com.yy.mbg.domain.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * LoginManage:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:44
 */
@Component
public class LoginManage implements ILoginManage {

    @Autowired
    private LoginApi loginApplication;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserInfoService userInfoService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean login(LoginParamDto loginParamDto){

        User user = new User();
        BeanUtil.copyProperties(loginParamDto,user);
        // Shiro 登录校验
        if (loginApplication.login(user)){

            // 更新登录时间
            QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
            sysUserQueryWrapper.eq("username", user.getUsername());
            SysUser sysUser = userService.getOne(sysUserQueryWrapper);
            sysUser.setLoginTime(DateTime.now());
            userService.updateById(sysUser);

            // 更新user_info信息
            QueryWrapper<SysUserInfo> userInfoQueryWrapper = new QueryWrapper<>();
            userInfoQueryWrapper.eq("user_id", sysUser.getId());
            SysUserInfo sysUserInfo = userInfoService.getOne(userInfoQueryWrapper);
            // 登录次数+1
            sysUserInfo.setLoginFrequency(sysUserInfo.getLoginFrequency() + 1);
            userInfoService.updateById(sysUserInfo);

            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean logout() {

        loginApplication.logout();
        return true;
    }
}
