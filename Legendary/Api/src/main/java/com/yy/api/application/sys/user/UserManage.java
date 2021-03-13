package com.yy.api.application.sys.user;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.api.application.sys.menu.dto.MenuTree;
import com.yy.api.application.sys.menu.util.MenuTreeUtil;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.user.dto.UserDto;
import com.yy.api.application.sys.user.dto.UserParam;
import com.yy.core.utils.CollectionCopyUtil;
import com.yy.mbg.domain.entity.SysUser;
import com.yy.mbg.domain.entity.SysUserInfo;
import com.yy.mbg.domain.entity.SysUserRoleRelation;
import com.yy.mbg.domain.service.ISysUserInfoService;
import com.yy.mbg.domain.service.ISysUserRoleRelationService;
import com.yy.mbg.domain.service.ISysUserService;
import com.yy.mbg.extend.dto.UserPermissionDto;
import com.yy.mbg.extend.dto.UserRoleDto;
import com.yy.mbg.extend.mapper.UserPermissionMapper;
import com.yy.mbg.extend.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserManage: 用户管理
 *
 * @Author: YangYang
 * @Date: 2021/2/19 17:16
 */
@Slf4j
@Component
public class UserManage implements IUserManage {

    @Autowired
    private ISysUserRoleRelationService roleRelationService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ISysUserInfoService userInfoService;

    protected SysUser getUserByName(String username){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userService.getOne(queryWrapper);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean addUser(UserParam userParam) {

        // 检查用户名是否重复
        String username = userParam.getUsername();
        SysUser one = getUserByName(username);
        if (!BeanUtil.isEmpty(one)){
            throw new RuntimeException("用户名重复");
        }

        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(userParam, sysUser);
        if (userService.save(sysUser)){

            SysUser addUser = getUserByName(username);

            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setUserId(addUser.getId());
            sysUserInfo.setLoginFrequency(0);
            return userInfoService.save(sysUserInfo);
        }
        else{
            return false;
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean changeUser(Integer id, UserParam userParam) {

        SysUser sysUser = getSysUser(id);

        sysUser.setUsername(userParam.getUsername());
        sysUser.setPassword(userParam.getPassword());
        sysUser.setBirthday(userParam.getBirthday());
        sysUser.setCity(userParam.getCity());
        sysUser.setEmail(userParam.getEmail());
        sysUser.setGender(userParam.getGender());
        sysUser.setIcon(userParam.getIcon());
        sysUser.setNickname(userParam.getNickname());
        sysUser.setPhone(userParam.getPhone());
        sysUser.setStatus(userParam.getStatus());

        return userService.updateById(sysUser);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean removeUser(Integer id) {

        // 删除该用户与角色的关系记录
        QueryWrapper<SysUserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<SysUserRoleRelation> list = roleRelationService.list(queryWrapper);
        roleRelationService.removeByIds(list.stream().map(SysUserRoleRelation::getId).collect(Collectors.toList()));

        // 删除info表信息
        QueryWrapper<SysUserInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("user_id", id);
        userInfoService.remove(infoQueryWrapper);

        return userService.removeById(id);
    }

    @Override
    public boolean addUserRole(Integer userId, Integer roleId) {

        SysUserRoleRelation sysUserRoleRelation = new SysUserRoleRelation();
        sysUserRoleRelation.setRoleId(roleId);
        sysUserRoleRelation.setUserId(userId);
        return roleRelationService.save(sysUserRoleRelation);
    }

    @Override
    public boolean removeUserRole(Integer userId, Integer roleId) {

        QueryWrapper<SysUserRoleRelation>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("role_id",roleId);
        return roleRelationService.remove(queryWrapper);
    }

    @Override
    public UserDto getUser(Integer id) {

        SysUser sysUser = getSysUser(id);
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(sysUser,userDto);
        return userDto;
    }

    @Override
    public List<PermissionResultDto> getUserPermission(Integer id) {

        List<UserPermissionDto> userPermission = userPermissionMapper.getUserPermission(id);
        return CollectionCopyUtil.copyProperties(userPermission,PermissionResultDto.class);
    }

    @Override
    public List<RoleDto> getUserRole(Integer id) {

        List<UserRoleDto> userRoles = userRoleMapper.getUserRoles(id);

        return CollectionCopyUtil.copyProperties(userRoles,RoleDto.class);
    }

    @Override
    public List<MenuTree> getUserMenuTree(Integer id) {

        // 1、根据用户id获取符合条件的目录菜单权限集
        List<UserPermissionDto> list = userPermissionMapper.getUserMenu(id);
        List<PermissionResultDto> resources = CollectionCopyUtil.copyProperties(list, PermissionResultDto.class);
        // 2、生成菜单树
        return MenuTreeUtil.getMenu(resources);
    }

    /**
     * 根据id获取用户信息，不存在则抛出运行时异常
     */
    protected SysUser getSysUser(Integer id){
        SysUser user = userService.getById(id);
        if (BeanUtil.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }
        return user;
    }
}
