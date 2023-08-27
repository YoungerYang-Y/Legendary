package pers.legendary.business.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pers.legendary.common.api.business.user.entity.SysPermission;
import pers.legendary.common.api.business.user.entity.SysRole;
import pers.legendary.common.api.business.user.entity.SysRolePermissionRelation;
import pers.legendary.common.api.business.user.entity.SysUserRoleRelation;
import pers.legendary.common.api.business.user.model.RoleModel;
import pers.legendary.common.api.business.user.model.UserViewModel;
import pers.legendary.common.api.business.user.service.IRoleService;
import pers.legendary.common.core.util.BeanUtils;
import pers.legendary.common.mbg.rbac.service.ISysRolePermissionRelationService;
import pers.legendary.common.mbg.rbac.service.ISysRoleService;
import pers.legendary.common.mbg.rbac.service.ISysUserRoleRelationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 角色服务实现类
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/20 9:57
 */
@Slf4j
@Component
@RequiredArgsConstructor
@DubboService(version = "0.0.1")
public class RoleServiceImpl implements IRoleService {

    private final ISysUserRoleRelationService userRoleRelationService;
    private final ISysRoleService roleService;
    private final ISysRolePermissionRelationService rolePermissionRelationService;

    @Override
    public Page<RoleModel> getPage(Page<RoleModel> pageParam, RoleModel search) {
        // 构建查询条件
        Page<SysRole> page = new Page<>();
        BeanUtil.copyProperties(pageParam, page);
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getName()), SysRole::getName, search.getName());
        queryWrapper.like(ObjectUtil.isNotEmpty(search.getMean()), SysRole::getMean, search.getMean());
        queryWrapper.eq(ObjectUtil.isNotEmpty(search.getStatus()), SysRole::getStatus, search.getStatus());

        // 转化查询结果
        Page<SysRole> pageResult = roleService.page(page, queryWrapper);
        List<SysRole> records = pageResult.getRecords();
        List<RoleModel> models = BeanUtils.copyListProperties(records, RoleModel::new);
        Page<RoleModel> result = new Page<>();
        BeanUtil.copyProperties(pageResult, result);
        result.setRecords(models);
        return result;
    }

    @Override
    public RoleModel get(RoleModel roleModel) {
        SysRole sysRole = roleService.getById(roleModel.getId());
        RoleModel result = new RoleModel();
        BeanUtil.copyProperties(sysRole, result);
        return result;
    }

    @Transactional
    @Override
    public boolean createRole(RoleModel roleModel) {
        // add role
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(roleModel, sysRole);
        roleService.save(sysRole);

        // add permission
        if (CollUtil.isNotEmpty(roleModel.getPermissions())) {
            List<SysRolePermissionRelation> relations = new ArrayList<>(roleModel.getPermissions().size());
            for (SysPermission permission : roleModel.getPermissions()) {
                SysRolePermissionRelation relation = new SysRolePermissionRelation();
                relation.setRoleId(sysRole.getId());
                relation.setPermissionId(permission.getId());
                relations.add(relation);
            }
            rolePermissionRelationService.saveBatch(relations);
        }

        return true;
    }

    @Override
    public boolean modifyRole(RoleModel roleModel) {
        LambdaUpdateWrapper<SysRole> wrapper = new LambdaUpdateWrapper<>();
        // 查询条件
        wrapper.eq(SysRole::getId, roleModel.getId());

        // 更新字段
        wrapper.set(CharSequenceUtil.isNotEmpty(roleModel.getName()), SysRole::getName, roleModel.getName());
        wrapper.set(CharSequenceUtil.isNotEmpty(roleModel.getMean()), SysRole::getMean, roleModel.getMean());
        return roleService.update(wrapper);
    }

    @Override
    public boolean switchRoleStatus(int id, boolean status) {
        LambdaUpdateWrapper<SysRole> wrapper = new LambdaUpdateWrapper<>();
        // 查询条件
        wrapper.eq(SysRole::getId, id);

        // 更新字段
        wrapper.set(SysRole::getStatus, status);
        roleService.update(wrapper);
        return false;
    }

    @Override
    public boolean assigningRoles(RoleModel roleModel, List<UserViewModel> userList) {
        List<SysUserRoleRelation> relations = new ArrayList<>(userList.size());
        for (UserViewModel user : userList) {
            SysUserRoleRelation relation = new SysUserRoleRelation();
            relation.setRoleId(roleModel.getId());
            relation.setUserId(user.getId());
            relations.add(relation);
        }
        return userRoleRelationService.saveBatch(relations);
    }

    @Transactional
    @Override
    public boolean removeRole(RoleModel roleModel) {
        // remove user-role relation
        LambdaQueryWrapper<SysUserRoleRelation> userRoleRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleRelationLambdaQueryWrapper.eq(SysUserRoleRelation::getRoleId, roleModel.getId());
        userRoleRelationService.remove(userRoleRelationLambdaQueryWrapper);

        // remove role-permission relation
        LambdaQueryWrapper<SysRolePermissionRelation> rolePermissionRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        rolePermissionRelationLambdaQueryWrapper.eq(SysRolePermissionRelation::getRoleId, roleModel.getId());
        rolePermissionRelationService.remove(rolePermissionRelationLambdaQueryWrapper);

        // remove role
        return roleService.removeById(roleModel);
    }
}
