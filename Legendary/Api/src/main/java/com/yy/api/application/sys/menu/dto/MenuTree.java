package com.yy.api.application.sys.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MenuTree: 一个多级菜单实体
 *
 * @Author: YangYang
 * @Date: 2021/1/25 23:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTree implements Cloneable{

    /**
     * 菜单编号
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 权限值
     */
    private String value;
    /**
     * 菜单访问路径
     */
    private String uri;
    /**
     * 菜单顺序
     */
    private Integer sort;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<MenuTree> children;

    @Override
    protected Object clone() {
        try {
            Object obj = super.clone();
            return obj;
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
    }
}
