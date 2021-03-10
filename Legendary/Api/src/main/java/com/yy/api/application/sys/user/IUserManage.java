package com.yy.api.application.sys.user;

import com.yy.api.application.sys.menu.dto.MenuTree;
import com.yy.api.application.sys.menu.dto.PermissionDto;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.user.dto.UserDto;
import com.yy.api.application.sys.user.dto.UserParam;

import java.util.List;

/**
 * IUserManage: 用户管理
 *
 * @Author: YangYang
 * @Date: 2021/2/19 17:15
 */
public interface IUserManage {

    /**
     * 新增用户
     *
     * @param userParam 用户信息
     * @return 返回执行结果
     */
    boolean addUser(UserParam userParam);

    /**
     * 修改用户信息（改密，修改个人信息，改头像）
     *
     * @param id 用户id
     * @param userParam 用户信息
     * @return 返回执行结果
     */
    boolean changeUser(Integer id ,UserParam userParam);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 返回执行结果
     */
    boolean removeUser(Integer id);

    /**
     * 分配用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 返回执行结果
     */
    boolean addUserRole(Integer userId, Integer roleId);

    /**
     * 删除用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 返回执行结果
     */
    boolean removeUserRole(Integer userId, Integer roleId);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserDto getUser(Integer id);

    /**
     * 获取用户权限集
     *
     * @param id 用户id
     * @return 权限集
     */
    List<PermissionDto> getUserPermission(Integer id);

    /**
     * 获取用户的所有角色信息
     *
     * @param id 用户id
     * @return 所有信息
     */
    List<RoleDto> getUserRole(Integer id);

    /**
     * 获取多级菜单树结构
     *
     * @param id 用户id
     * @return 菜单集
     */
    List<MenuTree> getUserMenuTree(Integer id);
}
