package com.yy.api.application.sys.permision;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.dto.PermissionParamDto;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.core.common.CommonPage;
import com.yy.mbg.domain.entity.SysPermission;
import com.yy.mbg.domain.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PermissionManage: 权限管理
 *
 * @Author: YangYang
 * @Date: 2021/2/27 21:36
 */
@Component
public class PermissionManage implements IPermissionManage {

    @Autowired
    private ISysPermissionService service;

    @Override
    public CommonPage<PermissionResultDto> getPage(Page page) {

        Page list = service.page(page);
        return CommonPage.restPage(list,PermissionResultDto.class);
    }

    @Override
    public PermissionResultDto get(Integer id) {

        SysPermission sysPermission = service.getById(id);
        PermissionResultDto result = new PermissionResultDto();
        BeanUtil.copyProperties(sysPermission,result);
        return result;
    }

    @Override
    public boolean addPermission(PermissionParamDto dto) {

        SysPermission sysPermission = new SysPermission();
        BeanUtil.copyProperties(dto,sysPermission);
        return service.save(sysPermission);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean changePermission(Integer id, PermissionParamDto dto) {

        SysPermission sysPermission = service.getById(id);
        if (BeanUtil.isEmpty(sysPermission)){
            throw new RuntimeException("数据不存在");
        }

        sysPermission.setName(dto.getName());
        sysPermission.setType(dto.getType());
        sysPermission.setPid(dto.getPid());
        sysPermission.setValue(dto.getValue());
        sysPermission.setUri(dto.getUri());
        sysPermission.setStatus(dto.getStatus());
        sysPermission.setIcon(dto.getIcon());
        sysPermission.setSort(dto.getSort());

        return service.updateById(sysPermission);
    }

    @Override
    public boolean removePermission(Integer id) {
        return service.removeById(id);
    }
}
