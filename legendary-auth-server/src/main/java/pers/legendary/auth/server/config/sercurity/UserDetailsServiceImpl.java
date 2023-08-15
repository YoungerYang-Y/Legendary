package pers.legendary.auth.server.config.sercurity;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.legendary.auth.server.constant.MessageConstant;
import pers.legendary.common.api.business.user.entity.SysPermission;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.service.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description: 自定义的用户服务
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 22:04
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference(interfaceClass = IUserService.class, version = "0.0.1")
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserModel user;
        try {
            user = userService.getUserByUsername(account);
        } catch (Exception e) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        // Spring Security中的权限校验只比较字符串，不像Shiro一样认为角色与权限有从属关系
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            Set<SysPermission> permissions = role.getPermissions();
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getValue())));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
