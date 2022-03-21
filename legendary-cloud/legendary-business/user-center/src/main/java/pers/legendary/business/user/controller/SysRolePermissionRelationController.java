package pers.legendary.business.user.controller;

import pers.legendary.rbac.entity.SysRolePermissionRelation;
import pers.legendary.rbac.service.ISysRolePermissionRelationService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * Description: 针对表【sys_role_permission_relation(角色权限关系表)】的数据库操作Controller实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@RestController
@RequestMapping("/sys_role_permission_relation")
public class SysRolePermissionRelationController{

    private final ISysRolePermissionRelationService service;

    public SysRolePermissionRelationController(ISysRolePermissionRelationService service) {
        this.service = service;
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public Page<SysRolePermissionRelation> getPage(@RequestBody Page<SysRolePermissionRelation> page) {
        QueryWrapper<SysRolePermissionRelation> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysRolePermissionRelation vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysRolePermissionRelation vo) {
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