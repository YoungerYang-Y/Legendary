package pers.legendary.business.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.*;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.mbg.rbac.service.ISysUserService;

/**
 * Description: 针对表【sys_user(用户表)】的数据库操作Controller实现
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022-03-19 20:07:58
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys_user")
public class SysUserController {

    private final ISysUserService service;

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Page<SysUser> getPage(@RequestBody Page<SysUser> page) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysUser vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysUser vo) {
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