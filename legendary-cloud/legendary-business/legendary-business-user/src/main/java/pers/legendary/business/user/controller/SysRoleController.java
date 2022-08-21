package pers.legendary.business.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.legendary.common.api.business.user.entity.SysRole;
import pers.legendary.common.mbg.rbac.service.ISysRoleService;

/**
 * Description: 针对表【sys_role(角色表)】的数据库操作Controller实现
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022-03-19 20:07:57
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys_role")
public class SysRoleController {

    private final ISysRoleService service;

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Page<SysRole> getPage(@RequestBody Page<SysRole> page) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysRole vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysRole vo) {
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