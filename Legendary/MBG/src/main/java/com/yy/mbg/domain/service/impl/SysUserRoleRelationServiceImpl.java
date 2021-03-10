package com.yy.mbg.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.mbg.domain.entity.SysUserRoleRelation;
import com.yy.mbg.domain.mapper.SysUserRoleRelationMapper;
import com.yy.mbg.domain.service.ISysUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author YangYang
 * @since 2021-02-19
 */
@Service
public class SysUserRoleRelationServiceImpl extends ServiceImpl<SysUserRoleRelationMapper, SysUserRoleRelation> implements ISysUserRoleRelationService {

    @Override
    public List<SysUserRoleRelation> getRelationById(Integer id) {
        QueryWrapper<SysUserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        return list(wrapper);
    }
}
