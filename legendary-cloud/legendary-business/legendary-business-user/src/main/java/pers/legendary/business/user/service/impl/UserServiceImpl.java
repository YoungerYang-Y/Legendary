package pers.legendary.business.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pers.legendary.common.api.business.user.entity.SysPermission;
import pers.legendary.common.api.business.user.entity.SysRole;
import pers.legendary.common.api.business.user.entity.SysRolePermissionRelation;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.api.business.user.entity.SysUserRoleRelation;
import pers.legendary.common.api.business.user.model.RoleModel;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.model.UserViewModel;
import pers.legendary.common.api.business.user.service.IUserService;
import pers.legendary.common.core.util.BeanUtils;
import pers.legendary.common.mbg.rbac.service.ISysPermissionService;
import pers.legendary.common.mbg.rbac.service.ISysRolePermissionRelationService;
import pers.legendary.common.mbg.rbac.service.ISysRoleService;
import pers.legendary.common.mbg.rbac.service.ISysUserRoleRelationService;
import pers.legendary.common.mbg.rbac.service.ISysUserService;

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
@CacheConfig(cacheNames = {"UserService"})
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
    @Cacheable
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

    @Override
    public Page<UserViewModel> getPage(Page<UserViewModel> pageParam, UserViewModel search) {
        // 构建查询条件
        Page<SysUser> page = new Page<>();
        BeanUtils.copyProperties(pageParam, page);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getUsername()), SysUser::getUsername, search.getUsername());
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getNickname()), SysUser::getNickname, search.getNickname());
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getPhone()), SysUser::getPhone, search.getPhone());
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getEmail()), SysUser::getEmail, search.getEmail());
        queryWrapper.eq(ObjectUtil.isNotEmpty(search.getGender()), SysUser::getGender, search.getGender());
        queryWrapper.eq(ObjectUtil.isNotEmpty(search.getStatus()), SysUser::getStatus, search.getStatus());

        // 转化查询结果
        Page<SysUser> pageResult = userService.page(page, queryWrapper);
        List<SysUser> records = pageResult.getRecords();
        List<UserViewModel> models = BeanUtils.copyListProperties(records, UserViewModel::new);
        Page<UserViewModel> result = new Page<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setRecords(models);
        return result;
    }

    @Override
    public boolean addUser(UserViewModel vo) {
        return false;
    }

    @Override
    public boolean modifyUser(UserViewModel vo) {
        return false;
    }

    @Override
    public boolean removeUser(String id) {
        return false;
    }
}
