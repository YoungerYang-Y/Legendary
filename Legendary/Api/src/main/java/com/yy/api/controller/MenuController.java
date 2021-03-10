package com.yy.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yy.api.application.sys.menu.IMenuManage;
import com.yy.api.application.sys.menu.dto.MenuParam;
import com.yy.api.application.sys.menu.dto.MenuResultDto;
import com.yy.api.controller.vo.add.AddMenu;
import com.yy.api.controller.vo.result.MenuResult;
import com.yy.api.controller.vo.update.UpdateMenu;
import com.yy.api.controller.vo.update.UpdateStatus;
import com.yy.core.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * MenuController: 菜单管理接口
 *
 * @Author: YangYang
 * @Date: 2021/2/22 8:51
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/sys/menus")
public class MenuController {

    @Autowired
    private IMenuManage menuManage;

    /*****************************************GET********************************************************/
    @RequiresPermissions({"sys:menus:query"})
    @ApiOperation("根据id获取菜单信息")
    @GetMapping("/{id}")
    public CommonResult getMenu(@PathVariable("id") Integer id){

        MenuResult result = new MenuResult();
        MenuResultDto menu = menuManage.getMenu(id);
        BeanUtil.copyProperties(menu, result);

        return CommonResult.success(result);
    }

    /*****************************************POST********************************************************/
    @RequiresPermissions({"sys:menus:add"})
    @ApiOperation("添加菜单")
    @PostMapping("/menu")
    public CommonResult addMenu(@RequestBody @Valid AddMenu addMenu) {

        MenuParam menuParam = new MenuParam();
        BeanUtil.copyProperties(addMenu,menuParam);
        if (menuManage.addMenu(menuParam)){
            return CommonResult.success(addMenu);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************PUT********************************************************/

    @RequiresPermissions({"sys:menus:update"})
    @ApiOperation("修改菜单")
    @PutMapping("/{id}")
    public CommonResult changeMenu(@PathVariable("id") Integer id, @RequestBody @Valid UpdateMenu updateMenu) {

        MenuParam menuParam = new MenuParam();
        BeanUtil.copyProperties(updateMenu, menuParam);
        if (menuManage.changeMenu(menuParam, id)){
            return CommonResult.success(updateMenu);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************PATCH********************************************************/

    @RequiresPermissions({"sys:menus:update"})
    @ApiOperation("修改菜单启用状态")
    @PatchMapping(value = "/{id}/status")
    public CommonResult changeMenuStatus(@PathVariable("id") Integer id, @RequestBody @Valid UpdateStatus status) {

        MenuParam menuParam = new MenuParam();
        BeanUtil.copyProperties(status,menuParam);
        if (menuManage.changeMenu(menuParam,id)){
            return CommonResult.success(status);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

    /*****************************************DELETE********************************************************/

    @RequiresPermissions({"sys:menus:delete"})
    @ApiOperation("删除菜单")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteMenu(@PathVariable("id") Integer id) {

        if (menuManage.removeMenu(id)){
            return CommonResult.success(null);
        }
        else{
            return CommonResult.failed("操作失败");
        }
    }

}
