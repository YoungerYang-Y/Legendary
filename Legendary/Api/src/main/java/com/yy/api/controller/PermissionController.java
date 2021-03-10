package com.yy.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.IPermissionManage;
import com.yy.api.application.sys.permision.dto.PermissionParamDto;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.controller.vo.add.AddPermission;
import com.yy.api.controller.vo.result.PermissionResult;
import com.yy.api.controller.vo.update.UpdateName;
import com.yy.api.controller.vo.update.UpdatePermission;
import com.yy.api.controller.vo.update.UpdateStatus;
import com.yy.core.common.CommonPage;
import com.yy.core.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * PermissionController: 权限管理
 *
 * @Author: YangYang
 * @Date: 2021/3/3 22:35
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/sys/permissions")
public class PermissionController {

    @Autowired
    private IPermissionManage permissionManage;

    /*****************************************GET********************************************************/
    @RequiresPermissions({"sys:permissions:query"})
    @ApiOperation("获取所有权限信息")
    @GetMapping("/page/current/{current}/size/{size}")
    public CommonResult<CommonPage> getPage(@PathVariable("current") Integer current, @PathVariable("size") Integer size){

        Page<Object> page = new Page<>(current, size);
        CommonPage<PermissionResultDto> list = permissionManage.getPage(page);
        return CommonResult.success(list);
    }

    @RequiresPermissions({"sys:permissions:query"})
    @ApiOperation("根据id获取权限信息")
    @GetMapping("/{id}")
    public CommonResult getPermission(@PathVariable("id") Integer id){

        PermissionResult result = new PermissionResult();
        PermissionResultDto dto = permissionManage.get(id);
        BeanUtil.copyProperties(dto, result);
        return CommonResult.success(result);
    }

    /*****************************************POST********************************************************/
    @RequiresPermissions({"sys:permissions:add"})
    @ApiOperation("新增一个权限")
    @PostMapping("/permission")
    public CommonResult addRole(@RequestBody @Valid AddPermission addPermission){

        PermissionParamDto permissionParamDto = new PermissionParamDto();
        BeanUtil.copyProperties(addPermission,permissionParamDto);
        if(permissionManage.addPermission(permissionParamDto)){
            return CommonResult.success(addPermission);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************PUT********************************************************/
    @RequiresPermissions({"sys:permissions:update"})
    @ApiOperation("修改权限信息")
    @PutMapping("/{id}")
    public CommonResult changeRole(@PathVariable("id") Integer id,@RequestBody @Valid UpdatePermission updatePermission){

        PermissionParamDto permissionParamDto = new PermissionParamDto();
        BeanUtil.copyProperties(updatePermission,permissionParamDto);
        if (permissionManage.changePermission(id,permissionParamDto)){
            return CommonResult.success(updatePermission);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************PATCH********************************************************/
    @RequiresPermissions({"sys:permissions:update"})
    @ApiOperation("修改权限名称")
    @PatchMapping("/{id}/name")
    public CommonResult changeName(@PathVariable("id") Integer id,@RequestBody @Valid UpdateName updateName){

        PermissionParamDto permissionParamDto = new PermissionParamDto();
        BeanUtil.copyProperties(updateName,permissionParamDto);
        if (permissionManage.changePermission(id, permissionParamDto)){
            return CommonResult.success(updateName);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    @RequiresPermissions({"sys:permissions:update"})
    @ApiOperation("修改权限状态")
    @PatchMapping("/{id}/status")
    public CommonResult changeStatus(@PathVariable("id") Integer id,@RequestBody @Valid UpdateStatus updateStatus){

        PermissionParamDto permissionParamDto = new PermissionParamDto();
        BeanUtil.copyProperties(updateStatus, permissionParamDto);
        if (permissionManage.changePermission(id, permissionParamDto)){
            return CommonResult.success(updateStatus);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************DELETE********************************************************/
    @RequiresPermissions({"sys:permissions:delete"})
    @ApiOperation("删除权限")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteRole(@PathVariable("id") Integer id) {

        if (permissionManage.removePermission(id)){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }
}
