package com.yy.api.application.sys.menu;

import com.yy.api.application.sys.menu.dto.MenuParam;
import com.yy.api.application.sys.menu.dto.MenuResultDto;

/**
 * IMenuManage: 菜单管理
 *
 * @Author: YangYang
 * @Date: 2021/2/19 20:13
 */
public interface IMenuManage {

    /**
     * 根据id获取菜单
     *
     * @param id 菜单id
     * @return 返回菜单信息
     */
    MenuResultDto getMenu(Integer id);

    /**
     * 新增一个菜单
     *
     * @param menu 菜单信息
     * @return 返回执行结果
     */
    boolean addMenu(MenuParam menu);

    /**
     * 修改菜单信息
     *
     * @param menu  菜单信息
     * @param id    被修改的数据id
     * @return 返回执行结果
     */
    boolean changeMenu(MenuParam menu, Integer id);

    /**
     * 删除菜单以及其子菜单
     *
     * @param id 被删除的数据id
     * @return 返回执行结果
     */
    boolean removeMenu(Integer id);
}
