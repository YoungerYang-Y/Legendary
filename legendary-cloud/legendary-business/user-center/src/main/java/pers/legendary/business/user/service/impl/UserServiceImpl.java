package pers.legendary.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pers.legendary.common.mbg.rbac.entity.*;
import pers.legendary.common.mbg.rbac.service.*;
import pers.legendary.common.mbg.rbac.model.RoleModel;
import pers.legendary.common.mbg.rbac.model.UserModel;
import pers.legendary.common.service.business.user.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
@RequiredArgsConstructor
@DubboService(group = "UserService", version = "0.0.1")
public class UserServiceImpl implements IUserService {

    private final ISysUserService userService;
    private final ISysUserRoleRelationService userRoleRelationService;
    private final ISysRoleService roleService;
    private final ISysRolePermissionRelationService rolePermissionRelationService;
    private final ISysPermissionService permissionService;

    /**
     * TODO: 是否考虑通过Mybatis的一对多查询进行优化
     */
    @Override
    public UserModel getUserByUsername(String username) {

        UserModel result = new UserModel();

        // 1、查询用户信息
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername, username);
        SysUser user = userService.getOne(query);
        BeanUtils.copyProperties(user, result);

        // 2、查询角色信息
        LambdaQueryWrapper<SysUserRoleRelation> query2 = new LambdaQueryWrapper<>();
        query2.eq(SysUserRoleRelation::getUserId, user.getId());
        List<SysUserRoleRelation> list = userRoleRelationService.list(query2);

        List<Integer> roleIds = list.stream().map(SysUserRoleRelation::getRoleId).collect(Collectors.toList());
        List<SysRole> sysRoles = roleService.listByIds(roleIds);
        Set<RoleModel> roles = new HashSet<>(sysRoles.size());
        sysRoles.forEach(role -> {
            RoleModel roleModel = new RoleModel();
            BeanUtils.copyProperties(role, roleModel);

            // 3、查询权限信息
            LambdaQueryWrapper<SysRolePermissionRelation> query3 = new LambdaQueryWrapper<>();
            query3.eq(SysRolePermissionRelation::getRoleId, role.getId());
            List<SysRolePermissionRelation> list1 = rolePermissionRelationService.list(query3);

            List<Integer> permissionIds = list1.stream().map(SysRolePermissionRelation::getPermissionId).collect(Collectors.toList());
            List<SysPermission> sysPermissions = permissionService.listByIds(permissionIds);
            Set<SysPermission> permissions = new HashSet<>(sysPermissions);
            roleModel.setPermissions(permissions);

            roles.add(roleModel);
        });
        result.setRoles(roles);

        return result;
    }
}
