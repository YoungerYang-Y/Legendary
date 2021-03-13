package com.yy.api.application.sys.role;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.role.dto.RoleParam;
import com.yy.core.shiro.api.CacheApi;
import com.yy.core.common.CommonPage;
import com.yy.core.utils.CollectionCopyUtil;
import com.yy.mbg.domain.entity.SysPermission;
import com.yy.mbg.domain.entity.SysRole;
import com.yy.mbg.domain.entity.SysRolePermissionRelation;
import com.yy.mbg.domain.service.ISysPermissionService;
import com.yy.mbg.domain.service.ISysRolePermissionRelationService;
import com.yy.mbg.domain.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RoleManage: 角色管理
 *
 * @Author: YangYang
 * @Date: 2021/2/19 17:17
 */
@Component
public class RoleManage implements IRoleManage {

    @Autowired
    private CacheApi cacheApi;

    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysRolePermissionRelationService rolePermissionRelationService;
    @Autowired
    private ISysPermissionService permissionService;

    @Override
    public RoleDto get(Integer id) {
        RoleDto result = new RoleDto();
        BeanUtil.copyProperties(roleService.getById(id),result);
        return result;
    }

    @Override
    public CommonPage<RoleDto> list(Page page) {

        Page sysRolePage = roleService.page(page);
        return CommonPage.restPage(sysRolePage,RoleDto.class);
    }

    @Override
    public boolean addRole(RoleParam roleParam) {

        SysRole role = new SysRole();
        BeanUtil.copyProperties(roleParam,role);
        return roleService.save(role);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean changeRole(RoleParam roleParam, Integer id) {

        SysRole sysRole = roleService.getById(id);
        if (BeanUtil.isEmpty(sysRole)){
            throw new RuntimeException("数据不存在");
        }
        sysRole.setName(roleParam.getName());
        sysRole.setStatus(roleParam.getStatus());
        return roleService.updateById(sysRole);
    }

    @Override
    public boolean removeRole(Integer id) {

        // 1、删除角色权限关系表中的数据
        QueryWrapper<SysRolePermissionRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<SysRolePermissionRelation> list = rolePermissionRelationService.list(queryWrapper);
        rolePermissionRelationService.removeByIds(list.stream().map(SysRolePermissionRelation::getId).collect(Collectors.toList()));
        // 2、删除角色
        return roleService.removeById(id);
    }

    @Override
    public List<PermissionResultDto> getRolePermission(Integer roleId) {

        List<PermissionResultDto> result = new ArrayList<>();
        QueryWrapper<SysRolePermissionRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id" , roleId);
        List<SysRolePermissionRelation> list = rolePermissionRelationService.list(queryWrapper);
        if (list.size() > 0){
            List<SysPermission> permissions = permissionService.listByIds(list.stream().map(SysRolePermissionRelation::getPermissionId).collect(Collectors.toList()));
            result = CollectionCopyUtil.copyProperties(permissions, PermissionResultDto.class);
        }
        return result;
    }

    @Override
    public boolean addRolePermission(Integer roleId, Integer permissionId) {

        SysRolePermissionRelation sysRolePermissionRelation = new SysRolePermissionRelation();
        sysRolePermissionRelation.setRoleId(roleId);
        sysRolePermissionRelation.setPermissionId(permissionId);
        if(rolePermissionRelationService.save(sysRolePermissionRelation)){

            return cacheApi.clearAllCache();
        }
        else{
            return false;
        }
    }

    @Override
    public boolean removeRolePermission(Integer roleId, Integer permissionId) {

        QueryWrapper<SysRolePermissionRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId)
                .eq("permission_id", permissionId);
        if(rolePermissionRelationService.remove(queryWrapper)){

            return cacheApi.clearAllCache();
        }
        else{
            return false;
        }
    }
}
