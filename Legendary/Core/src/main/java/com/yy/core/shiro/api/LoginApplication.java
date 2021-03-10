package com.yy.core.shiro.api;

import cn.hutool.core.util.StrUtil;
import com.yy.core.shiro.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * LoginApplication: Shiro登录校验API
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:36
 */
@Slf4j
@Component
public class LoginApplication implements LoginApi{

    @Override
    public boolean login(User user) throws RuntimeException{

        if (StrUtil.isEmpty(user.getUsername()) || StrUtil.isEmpty(user.getPassword())) {
            throw new RuntimeException("请输入用户名和密码！");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword(),
                user.isRememberMe()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);

        } catch (LockedAccountException e){
            log.warn("账号已被锁定,请联系管理员！");
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        } catch (AuthenticationException e) {
            log.warn("账号或密码错误！");
            throw new RuntimeException("账号或密码错误");
        } catch (AuthorizationException e) {
            log.warn("没有权限");
            throw new RuntimeException("没有权限");
        } catch (Exception e){
            log.warn("其他异常");
            throw new RuntimeException("其他异常");
        }
        return true;
    }

    @Override
    public boolean logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return true;
    }
}
