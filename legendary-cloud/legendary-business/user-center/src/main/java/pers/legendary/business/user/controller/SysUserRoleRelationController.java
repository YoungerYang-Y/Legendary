package pers.legendary.business.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pers.legendary.common.api.business.user.entity.SysUserRoleRelation;
import pers.legendary.common.mbg.rbac.service.ISysUserRoleRelationService;

/**
 *
 * Description: 针对表【sys_user_role_relation(用户角色关系表)】的数据库操作Controller实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@RestController
@RequestMapping("/sys_user_role_relation")
public class SysUserRoleRelationController{

    private final ISysUserRoleRelationService service;

    public SysUserRoleRelationController(ISysUserRoleRelationService service) {
        this.service = service;
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public Page<SysUserRoleRelation> getPage(@RequestBody Page<SysUserRoleRelation> page) {
        QueryWrapper<SysUserRoleRelation> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysUserRoleRelation vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysUserRoleRelation vo) {
        return service.updateById(vo);
    }

    /**
    * 根据ID删除
    */
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") String id) {
        return service.removeById(id);
    }
}