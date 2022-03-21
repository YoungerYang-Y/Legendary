package pers.legendary.business.user.controller;

import pers.legendary.rbac.entity.SysPermission;
import pers.legendary.rbac.service.ISysPermissionService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * Description: 针对表【sys_permission(系统权限表)】的数据库操作Controller实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:57
 * @version 1.0.0
 */
@RestController
@RequestMapping("/sys_permission")
public class SysPermissionController{

    private final ISysPermissionService service;

    public SysPermissionController(ISysPermissionService service) {
        this.service = service;
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public Page<SysPermission> getPage(@RequestBody Page<SysPermission> page) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysPermission vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysPermission vo) {
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