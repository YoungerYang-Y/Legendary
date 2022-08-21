package pers.legendary.common.api.business.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * Description: 菜单模型
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 16:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 类型
     */
    private String type;

    /**
     * 前端资源路径
     */
    private String uri;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子菜单
     */
    private ArrayList<MenuModel> subMenu;
}
