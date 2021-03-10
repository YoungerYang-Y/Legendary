package com.yy.mbg.domain.service;

import com.yy.mbg.domain.entity.SysUserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author YangYang
 * @since 2021-02-19
 */
public interface ISysUserRoleRelationService extends IService<SysUserRoleRelation> {

    /**
     * 根据用户id获取用户角色关系列表
     *
     * @param id 用户id
     * @return 返回用户角色关系列表
     */
    List<SysUserRoleRelation> getRelationById(Integer id);
}
