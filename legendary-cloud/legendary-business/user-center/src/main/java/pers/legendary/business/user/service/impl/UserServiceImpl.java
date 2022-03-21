package pers.legendary.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import pers.legendary.rbac.entity.SysUser;
import pers.legendary.rbac.service.ISysUserService;
import pers.legendary.service.business.user.IUserService;

/**
 * Description: 用户服务实现类
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/19 20:36
 */
@Data
@Slf4j
@Component
@DubboService(group = "UserService", version = "0.0.1")
public class UserServiceImpl implements IUserService {

    /**
     * sys_user表仓储服务
     */
    private final ISysUserService userService;

    public UserServiceImpl(ISysUserService userService) {
        this.userService = userService;
    }

    @Override
    public SysUser getUserByUsername(String username) {

        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername, username);
        return userService.getOne(query);
    }
}
