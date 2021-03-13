package com.yy.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.application.sys.role.IRoleManage;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.role.dto.RoleParam;
import com.yy.api.controller.vo.add.AddRole;
import com.yy.api.controller.vo.result.RoleResult;
import com.yy.api.controller.vo.update.UpdateName;
import com.yy.api.controller.vo.update.UpdateRole;
import com.yy.api.controller.vo.update.UpdateRolePermission;
import com.yy.api.controller.vo.update.UpdateStatus;
import com.yy.core.common.CommonPage;
import com.yy.core.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RoleController: 角色管理
 *
 * @Author: YangYang
 * @Date: 2021/2/22 11:25
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/roles")
public class RoleController {

    @Autowired
    private IRoleManage roleManage;

    /*****************************************GET********************************************************/
    @RequiresPermissions({"sys:roles:query"})
    @ApiOperation("根据id获取角色信息")
    @GetMapping("/{id}")
    public CommonResult<RoleResult> getRole(@PathVariable("id") Integer id){

        RoleResult result = new RoleResult();
        RoleDto dto = roleManage.get(id);
        BeanUtil.copyProperties(dto, result);
        return CommonResult.success(result);
    }

    @RequiresPermissions({"sys:roles:query"})
    @ApiOperation("获取所有角色信息")
    @GetMapping("/page/current/{current}/size/{size}")
    public CommonResult<CommonPage> getPage(@PathVariable("current") Integer current,@PathVariable("size") Integer size){

        Page<Object> page = new Page<>(current, size);

        CommonPage<RoleDto> list = roleManage.list(page);
        return CommonResult.success(list);
    }

    @RequiresPermissions({"sys:roles:query"})
    @ApiOperation("查询角色的权限")
    @GetMapping("/{id}/permission")
    public CommonResult getRolePermission(@PathVariable("id") Integer id){

        List<PermissionResultDto> list = roleManage.getRolePermission(id);
        return CommonResult.success(list);
    }

    /*****************************************POST********************************************************/

    @RequiresPermissions({"sys:roles:add"})
    @ApiOperation("新增一个角色")
    @PostMapping("/role")
    public CommonResult addRole(@RequestBody @Valid AddRole addRole){

        RoleParam roleParam = new RoleParam();
        BeanUtil.copyProperties(addRole,roleParam);
        if(roleManage.addRole(roleParam)){
            return CommonResult.success(addRole);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:roles:add"})
    @ApiOperation("新增角色的权限")
    @PostMapping("/role-permission")
    public CommonResult addRolePermission(@RequestBody @Valid UpdateRolePermission updateRolePermission){

        if (roleManage.addRolePermission(updateRolePermission.getRoleId(),updateRolePermission.getPermissionId())){
            return CommonResult.success(updateRolePermission);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }
    /*****************************************PUT********************************************************/
    @RequiresPermissions({"sys:roles:update"})
    @ApiOperation("修改角色信息")
    @PutMapping("/{id}")
    public CommonResult changeRole(@PathVariable("id") Integer id,@RequestBody @Valid UpdateRole updateRole){

        RoleParam roleParam = new RoleParam();
        BeanUtil.copyProperties(updateRole,roleParam);
        if (roleManage.changeRole(roleParam,id)){
            return CommonResult.success(updateRole);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************PATCH********************************************************/
    @RequiresPermissions({"sys:roles:update"})
    @ApiOperation("修改角色名称")
    @PatchMapping("/{id}/name")
    public CommonResult changeName(@PathVariable("id") Integer id,@RequestBody @Valid UpdateName updateName){

        RoleParam roleParam = new RoleParam();
        BeanUtil.copyProperties(updateName,roleParam);
        if (roleManage.changeRole(roleParam,id)){
            return CommonResult.success(updateName);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:roles:update"})
    @ApiOperation("修改角色状态")
    @PatchMapping("/{id}/status")
    public CommonResult changeStatus(@PathVariable("id") Integer id,@RequestBody @Valid UpdateStatus updateStatus){

        RoleParam roleParam = new RoleParam();
        BeanUtil.copyProperties(updateStatus,roleParam);
        if (roleManage.changeRole(roleParam,id)){
            return CommonResult.success(updateStatus);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }


    /*****************************************DELETE********************************************************/
    @RequiresPermissions({"sys:roles:delete"})
    @ApiOperation("删除角色")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteRole(@PathVariable("id") Integer id) {

        if (roleManage.removeRole(id)){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:roles:delete"})
    @ApiOperation("删除角色的权限")
    @DeleteMapping("/role-permission")
    public CommonResult removeRolePermission(@RequestBody UpdateRolePermission updateRolePermission){

        if (roleManage.removeRolePermission(updateRolePermission.getRoleId(),updateRolePermission.getPermissionId())){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

}
