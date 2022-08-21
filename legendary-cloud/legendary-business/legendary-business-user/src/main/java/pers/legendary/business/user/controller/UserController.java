package pers.legendary.business.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.service.IUserService;

/**
 * Description: 用户控制器
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 19:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CacheConfig(cacheNames = {"UserController"})
public class UserController {
    private final IUserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名【用户唯一标识】
     */
    @PostMapping("/{username}")
    public UserModel getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
}
