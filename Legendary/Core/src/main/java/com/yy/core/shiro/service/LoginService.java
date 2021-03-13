package com.yy.core.shiro.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.core.shiro.dto.Permission;
import com.yy.core.shiro.dto.Role;
import com.yy.core.shiro.dto.User;
import com.yy.mbg.domain.entity.*;
import com.yy.mbg.domain.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LoginService: Shiro用户数据获取服务
 *
 * @Author: YangYang
 * @Date: 2021/3/1 9:38
 */
@Component
@Slf4j
public class LoginService implements ILoginService {

    /**
     * 用户缓存
     *
     * ps：配置ehcache后，可以不需要使用Map来进行缓存用户
     */
    private Map<String, User> userCache = new HashMap<>();

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserRoleRelationService userRoleRelationService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysRolePermissionRelationService rolePermissionRelationService;
    @Autowired
    private ISysPermissionService permissionService;

    protected User getUserByName(String name) {

        log.info("查询用户【" + name + "】权限---Start");
        User result = new User();
        // 根据名字获取用户信息
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",name);
        SysUser sysUser = userService.getOne(userQueryWrapper, true);
        if (BeanUtil.isEmpty(sysUser)){
            return null;
        }
        result.setId(sysUser.getId());
        result.setUsername(sysUser.getUsername());
        result.setPassword(sysUser.getPassword());
        result.setStatus(sysUser.getStatus());
        // 根据用户id获取对应角色
        QueryWrapper<SysUserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper<>();
        userRoleRelationQueryWrapper.select("role_id")
                .eq("user_id",sysUser.getId());
        List<SysUserRoleRelation> list = userRoleRelationService.list(userRoleRelationQueryWrapper);
        Set<Role> roles = new HashSet<>();
        if(list.size() > 0){
            List<SysRole> sysRoles = roleService.listByIds(list.stream().map(SysUserRoleRelation::getRoleId).collect(Collectors.toList()));
            // 根据角色id获取角色对应的权限
            for (SysRole sysRole : sysRoles){
                Role role = new Role();
                role.setId(sysRole.getId());
                role.setName(sysRole.getName());

                QueryWrapper<SysRolePermissionRelation> rolePermissionRelationQueryWrapper = new QueryWrapper<>();
                rolePermissionRelationQueryWrapper.select("permission_id")
                        .eq("role_id" , sysRole.getId());
                List<SysRolePermissionRelation> list1 = rolePermissionRelationService.list(rolePermissionRelationQueryWrapper);
                List<SysPermission> sysPermissions = permissionService.listByIds(list1.stream().map(SysRolePermissionRelation::getPermissionId).collect(Collectors.toList()));
                // 将权限写入对应角色
                Set<Permission> permissions = new HashSet<>();
                for (SysPermission permission : sysPermissions){
                    Permission permissionTmp = new Permission();
                    permissionTmp.setId(permission.getId());
                    permissionTmp.setValue(permission.getValue());
                    permissions.add(permissionTmp);
                }
                role.setPermissions(permissions);
                roles.add(role);
            }
        }
        result.setRoles(roles);

        setUser(name, result);
        log.info("【查询用户【" + name + "】权限---End】");
        return result;
    }

    @Override
    public User getUser(String name){
        User user = userCache.get(name);
        if(BeanUtil.isNotEmpty(user)){
            return user;
        }
        else {
            return getUserByName(name);
        }
    }

    protected void setUser(String name, User user){
        userCache.put(name, user);
    }
}
