package pers.legendary.auth.server.config.sercurity;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pers.legendary.rbac.entity.SysUser;
import pers.legendary.service.business.user.IUserService;

import java.util.ArrayList;
import java.util.List;

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

    @DubboReference(interfaceClass = IUserService.class, group = "UserService",version = "0.0.1")
    private IUserService userService;

    /**
     * TODO 循环依赖？
     */
//    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUser user = userService.getUserByUsername(account);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
