package com.yy.api.application.sys.menu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.yy.api.application.sys.menu.dto.MenuParam;
import com.yy.api.application.sys.menu.dto.MenuResultDto;
import com.yy.mbg.domain.entity.SysPermission;
import com.yy.mbg.domain.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * MenuManage: 菜单管理实现
 *
 * @Author: YangYang
 * @Date: 2021/2/19 20:13
 */
@Slf4j
@Component
public class MenuManage implements IMenuManage {

    @Autowired
    private ISysPermissionService permissionService;

    @Override
    public MenuResultDto getMenu(Integer id) {

        MenuResultDto resultDto = new MenuResultDto();
        SysPermission menu = permissionService.getById(id);
        BeanUtil.copyProperties(menu,resultDto);
        return resultDto;
    }

    @Override
    public boolean addMenu(MenuParam menu) {

        SysPermission permission = new SysPermission();
        BeanUtil.copyProperties(menu,permission);
        return permissionService.save(permission);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean changeMenu(MenuParam menu, Integer id) {

        SysPermission permission = permissionService.getById(id);
        if (BeanUtil.isEmpty(permission)){
            throw new RuntimeException("数据不存在");
        }
        permission.setPid(menu.getPid());
        permission.setName(menu.getName());
        permission.setIcon(menu.getIcon());
        permission.setSort(menu.getSort());
        permission.setStatus(menu.getStatus());
        permission.setUri(menu.getUri());
        permission.setValue(menu.getValue());
        permission.setType(menu.getType());
        return permissionService.updateById(permission);
    }

    @Override
    public boolean removeMenu(Integer id) {

        return permissionService.removeById(id);
    }
}
