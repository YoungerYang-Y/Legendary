package com.yy.api.application.sys.menu.util;

import cn.hutool.core.bean.BeanUtil;
import com.yy.api.application.sys.menu.dto.MenuTree;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.core.common.CommonConst;
import com.yy.core.utils.ListSortUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MenuTreeUtil:
 *
 * @Author: YangYang
 * @Date: 2021/2/24 22:14
 */
public class MenuTreeUtil {

    /**
     * Description：根据目录菜单权限集生成菜单树状结构
     *
     * @author: yangyang
     * @date: 2021/2/20 10:56
     * @param list 权限集
     * @return: java.util.List<com.yy.api.application.sys.menu.dto.MenuTree>
     */
    public static List<MenuTree> getMenu(List<PermissionResultDto> list){

        List<MenuTree> result = new ArrayList<>();
        // 1、寻找顶级菜单
        for (PermissionResultDto dto : list){
            if (CommonConst.TOP_MENU.compareTo(dto.getPid()) == 0){
                MenuTree tree = new MenuTree();
                BeanUtil.copyProperties(dto,tree);
                result.add(tree);
            }
        }
        ListSortUtil.sortByAsc(result, CommonConst.MENU_SORT_FIELD);

        // 2、寻找子菜单
        for (MenuTree tree : result){
            List<MenuTree> children = new ArrayList<>();
            Iterator<PermissionResultDto> iterator = list.iterator();
            while (iterator.hasNext()){
                PermissionResultDto next = iterator.next();
                if (tree.getId().compareTo(next.getPid()) == 0){
                    MenuTree child = new MenuTree();
                    BeanUtil.copyProperties(next,child);
                    children.add(child);
                    iterator.remove();
                }
            }
            ListSortUtil.sortByAsc(children, CommonConst.MENU_SORT_FIELD);
            tree.setChildren(children);
        }

        return result;
    }
}
