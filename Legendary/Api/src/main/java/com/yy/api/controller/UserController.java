package com.yy.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yy.api.application.sys.menu.dto.MenuTree;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.user.IUserManage;
import com.yy.api.application.sys.user.dto.UserDto;
import com.yy.api.application.sys.user.dto.UserParam;
import com.yy.api.controller.vo.add.AddUser;
import com.yy.api.controller.vo.result.MenuTreeResult;
import com.yy.api.controller.vo.result.PermissionResult;
import com.yy.api.controller.vo.result.RoleResult;
import com.yy.api.controller.vo.result.UserResult;
import com.yy.api.controller.vo.update.*;
import com.yy.core.common.CommonResult;
import com.yy.core.utils.CollectionCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController:
 *
 * @Author: YangYang
 * @Date: 2021/2/24 22:38
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/sys/users")
public class UserController {

    @Autowired
    private IUserManage userManage;

    /*****************************************GET**********************************************************/

    @RequiresPermissions({"sys:users:query"})
    @ApiOperation("获取用户的基本信息")
    @GetMapping(value = "/{id}")
    public CommonResult getUser(@PathVariable("id") Integer id) {

        UserResult result = new UserResult();
        UserDto user = userManage.getUser(id);
        BeanUtil.copyProperties(user, result);
        return CommonResult.success(result);
    }

    @RequiresPermissions({"sys:users:query"})
    @ApiOperation("获取用户的访问菜单结构")
    @GetMapping(value = "/{id}/menu-tree")
    public CommonResult getMenuTree(@PathVariable("id") Integer id) {

        List<MenuTree> menuTreeList = userManage.getUserMenuTree(id);
        List<MenuTreeResult> result = CollectionCopyUtil.copyProperties(menuTreeList, MenuTreeResult.class);
        return CommonResult.success(result);
    }

    @RequiresPermissions({"sys:users:query"})
    @ApiOperation("获取用户的访问权限")
    @GetMapping(value = "/{id}/permissions")
    public CommonResult getUserPermission(@PathVariable("id") Integer id) {

        List<PermissionResultDto> permissionDtoList = userManage.getUserPermission(id);
        List<PermissionResult> result = CollectionCopyUtil.copyProperties(permissionDtoList,PermissionResult.class);
        return CommonResult.success(result);
    }

    @RequiresPermissions({"sys:users:query"})
    @ApiOperation("获取用户拥有的角色信息")
    @GetMapping(value = "/{id}/roles")
    public CommonResult getUserRole(@PathVariable("id") Integer id) {

        List<RoleDto> userRole = userManage.getUserRole(id);
        List<RoleResult> result = CollectionCopyUtil.copyProperties(userRole, RoleResult.class);
        return CommonResult.success(result);
    }
    /*****************************************POST*********************************************************/
    @RequiresPermissions({"sys:users:add"})
    @ApiOperation("新增一个用户")
    @PostMapping("/user")
    public CommonResult addUser(@RequestBody @Valid AddUser user){

        UserParam userParam = new UserParam();
        BeanUtil.copyProperties(user,userParam);
        if (userManage.addUser(userParam)){
            return CommonResult.success(user);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:users:add"})
    @ApiOperation("新增用户的角色")
    @PostMapping("/user-role")
    public CommonResult addUserRole(@RequestBody @Valid UpdateUserRole updateUserRole){

        if (userManage.addUserRole(updateUserRole.getUserId(),updateUserRole.getRoleId())){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }
    /*****************************************PUT**********************************************************/
    @RequiresPermissions({"sys:users:update"})
    @ApiOperation("修改用户信息")
    @PutMapping(value = "/{id}")
    public CommonResult changeUser(@PathVariable("id") Integer id, @RequestBody @Valid UpdateUser updateUser){
        UserParam userParam = new UserParam();
        BeanUtil.copyProperties(updateUser,userParam);
        if(userManage.changeUser(id, userParam)){
            return CommonResult.success(updateUser);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }
    /*****************************************PATCH********************************************************/
    @RequiresPermissions({"sys:users:update"})
    @ApiOperation("修改用户密码")
    @PatchMapping(value = "/{id}/password")
    public CommonResult changePassword(@PathVariable("id") Integer id, @RequestBody @Valid UpdatePassword updatePassword){
        UserParam userParam = new UserParam();
        BeanUtil.copyProperties(updatePassword,userParam);
        if(userManage.changeUser(id, userParam)){
            return CommonResult.success(updatePassword);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:users:update"})
    @ApiOperation("修改用户状态")
    @PatchMapping(value = "/{id}/status")
    public CommonResult changeStatus(@PathVariable("id") Integer id, @RequestBody @Valid UpdateStatus updateStatus){
        UserParam userParam = new UserParam();
        BeanUtil.copyProperties(updateStatus,userParam);
        if(userManage.changeUser(id, userParam)){
            return CommonResult.success(updateStatus);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:users:update"})
    @ApiOperation("修改用户头像")
    @PatchMapping(value = "/{id}/icon")
    public CommonResult changeIcon(@PathVariable("id") Integer id, @RequestBody @Valid UpdateIcon updateIcon){
        UserParam userParam = new UserParam();
        BeanUtil.copyProperties(updateIcon,userParam);
        if(userManage.changeUser(id, userParam)){
            return CommonResult.success(updateIcon);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************DELETE*******************************************************/
    @RequiresPermissions({"sys:users:delete"})
    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteUser(@PathVariable("id") Integer id) {

        if (userManage.removeUser(id)){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:users:delete"})
    @ApiOperation("删除用户的角色")
    @DeleteMapping("/user-role")
    public CommonResult removeUserRole(@RequestBody @Valid UpdateUserRole updateUserRole){

        if (userManage.removeUserRole(updateUserRole.getUserId(),updateUserRole.getRoleId())){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }
}
