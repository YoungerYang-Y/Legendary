package com.yy.api.application.sys.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.role.dto.RoleParam;
import com.yy.core.common.CommonPage;

import java.util.List;

/**
 * IRoleManage: 角色管理
 *
 * @Author: YangYang
 * @Date: 2021/2/19 17:17
 */
public interface IRoleManage {

    /**
     * 根据id获取角色
     *
     * @param id 角色id
     * @return 返回角色信息
     */
    RoleDto get(Integer id);

    /**
     * 浏览所有角色信息
     *
     * @param page 分页信息
     * @return 所有信息
     */
    CommonPage<RoleDto> list(Page page);

    /**
     * 新增角色
     *
     * @param roleParam 角色信息参数
     * @return 返回执行结果
     */
    boolean addRole(RoleParam roleParam);

    /**
     * 修改角色信息
     *
     * @param roleParam 角色信息参数
     * @param id 角色id
     * @return 返回执行结果
     */
    boolean changeRole(RoleParam roleParam,Integer id);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 返回执行结果
     */
    boolean removeRole(Integer id);

    /**
     * 根据角色id查询权限
     * @param roleId 角色id
     * @return 返回权限集
     */
    List<PermissionResultDto> getRolePermission(Integer roleId);

    /**
     * 新增角色的权限
     *
     * @param roleId        角色id
     * @param permissionId  权限id
     * @return 返回执行结果
     */
    boolean addRolePermission(Integer roleId, Integer permissionId);

    /**
     * 移除角色的权限
     *
     * @param roleId        角色id
     * @param permissionId  权限id
     * @return 返回执行结果
     */
    boolean removeRolePermission(Integer roleId, Integer permissionId);

}
