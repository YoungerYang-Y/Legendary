package pers.legendary.business.user.controller;

import pers.legendary.rbac.entity.SysUserInfo;
import pers.legendary.rbac.service.ISysUserInfoService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * Description: 针对表【sys_user_info(用户信息表（user扩展表）)】的数据库操作Controller实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@RestController
@RequestMapping("/sys_user_info")
public class SysUserInfoController{

    private final ISysUserInfoService service;

    public SysUserInfoController(ISysUserInfoService service) {
        this.service = service;
    }

    /**
    * 分页查询
    */
    @PostMapping("/page")
    public Page<SysUserInfo> getPage(@RequestBody Page<SysUserInfo> page) {
        QueryWrapper<SysUserInfo> wrapper = new QueryWrapper<>();
        return service.page(page, wrapper);
    }

    /**
     * 保存
     */
    @PostMapping
    public boolean save(@RequestBody SysUserInfo vo) {
        return service.save(vo);
    }

    /**
     * 更新
     */
    @PutMapping
    public boolean updateById(@RequestBody SysUserInfo vo) {
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