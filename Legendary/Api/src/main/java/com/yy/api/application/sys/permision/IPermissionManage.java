package com.yy.api.application.sys.permision;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.application.sys.permision.dto.PermissionParamDto;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.core.common.CommonPage;

/**
 * IPermissionManage:
 *
 * @Author: YangYang
 * @Date: 2021/2/27 21:35
 */
public interface IPermissionManage {

    /**
     * 获取权限数据（分页获取）
     *
     * @param page 分页信息
     * @return 返回查询结果
     */
    CommonPage<PermissionResultDto> getPage(Page page);

    /**
     * 根据id获取权限数据
     *
     * @param id 权限id
     * @return 返回权限数据
     */
    PermissionResultDto get(Integer id);

    /**
     * 新增一个权限
     *
     * @param dto 新增的权限
     * @return 返回执行结果
     */
    boolean addPermission(PermissionParamDto dto);

    /**
     * 修改权限数据
     * @param id 权限id
     * @param dto 修改数据
     * @return 返回执行结果
     */
    boolean changePermission(Integer id, PermissionParamDto dto);

    /**
     * 根据id删除权限数据
     * @param id 权限id
     * @return 返回执行结果
     */
    boolean removePermission(Integer id);
}
