package pers.legendary.business.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pers.legendary.common.api.business.user.entity.SysPermission;
import pers.legendary.common.api.business.user.entity.SysRole;
import pers.legendary.common.api.business.user.entity.SysRolePermissionRelation;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.api.business.user.entity.SysUserRoleRelation;
import pers.legendary.common.api.business.user.model.RoleModel;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.model.UserViewModel;
import pers.legendary.common.api.business.user.service.IUserService;
import pers.legendary.common.core.cache.CacheConstant;
import pers.legendary.common.core.exception.ServiceException;
import pers.legendary.common.core.util.BeanUtils;
import pers.legendary.common.mbg.rbac.service.ISysPermissionService;
import pers.legendary.common.mbg.rbac.service.ISysRolePermissionRelationService;
import pers.legendary.common.mbg.rbac.service.ISysRoleService;
import pers.legendary.common.mbg.rbac.service.ISysUserRoleRelationService;
import pers.legendary.common.mbg.rbac.service.ISysUserService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
@DubboService(version = "1.0.0")
public class UserServiceImpl implements IUserService {

    private final ISysUserService userService;
    private final ISysUserRoleRelationService userRoleRelationService;
    private final ISysRoleService roleService;
    private final ISysRolePermissionRelationService rolePermissionRelationService;
    private final ISysPermissionService permissionService;

    @Override
    @Cacheable(cacheNames = CacheConstant.USER_DETAIL, key = "#username")
    public UserModel getUserByUsername(String username) {

        UserModel result = new UserModel();

        // 1、查询用户信息
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername, username);
        SysUser user = userService.getOne(query);
        BeanUtil.copyProperties(user, result);

        // 2、查询角色信息
        LambdaQueryWrapper<SysUserRoleRelation> query2 = new LambdaQueryWrapper<>();
        query2.eq(SysUserRoleRelation::getUserId, user.getId());
        List<SysUserRoleRelation> list = userRoleRelationService.list(query2);

        List<Integer> roleIds = list.stream().map(SysUserRoleRelation::getRoleId).collect(Collectors.toList());
        if (ObjectUtil.isEmpty(roleIds)) {
            throw new ServiceException("该用户未分配角色");
        }
        List<SysRole> sysRoles = roleService.listByIds(roleIds);
        Set<RoleModel> roles = new HashSet<>(sysRoles.size());
        sysRoles.forEach(role -> {
            RoleModel roleModel = new RoleModel();
            BeanUtil.copyProperties(role, roleModel);

            // 3、查询权限信息
            LambdaQueryWrapper<SysRolePermissionRelation> query3 = new LambdaQueryWrapper<>();
            query3.eq(SysRolePermissionRelation::getRoleId, role.getId());
            List<SysRolePermissionRelation> list1 = rolePermissionRelationService.list(query3);

            List<Integer> permissionIds = list1.stream().map(SysRolePermissionRelation::getPermissionId).collect(Collectors.toList());
            if (ObjectUtil.isEmpty(permissionIds)) {
                return;
            }
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
        BeanUtil.copyProperties(pageParam, page);
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
        BeanUtil.copyProperties(pageResult, result);
        result.setRecords(models);
        return result;
    }

    @Override
    public boolean createUser(UserModel vo) {
        // add user-role relation
        if (CollUtil.isNotEmpty(vo.getRoles())) {
            ArrayList<SysUserRoleRelation> relations = new ArrayList<>(vo.getRoles().size());
            for (RoleModel roleModel : vo.getRoles()) {
                SysUserRoleRelation relation = new SysUserRoleRelation();
                relation.setRoleId(roleModel.getId());
                relation.setUserId(vo.getId());
                relations.add(relation);
            }
            userRoleRelationService.saveBatch(relations);
        }

        // add user
        return userService.save(vo);
    }

    @Override
    public boolean modifyUser(UserViewModel vo) {
        if (ObjectUtil.isEmpty(vo.getId())) {
            throw new ServiceException("用户id不能为空");
        }
        SysUser user = userService.getById(vo.getId());
        BeanUtil.copyProperties(vo, user, "loginTime");
        return userService.updateById(user);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @CacheEvict(key = CacheConstant.USER_DETAIL + "#username")
    @Override
    public boolean removeUser(String username) {
        // 1.获取用户，判断用户是否存在
        SysUser user = get(username);

        // 2.删除用户与角色关系表数据
        LambdaQueryWrapper<SysUserRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleRelation::getUserId, user.getId());
        userRoleRelationService.remove(queryWrapper);

        // 3.清除用户表数据
        return userService.removeById(user);
    }

    @Override
    public SysUser get(@NotNull String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser user = userService.getOne(wrapper, true);
        if (ObjectUtil.isEmpty(user)) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }
}
