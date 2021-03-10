package com.yy.api.application.sys.menu.dto;

import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * AddMenu: 新增菜单参数
 *
 * @Author: YangYang
 * @Date: 2021/2/19 20:31
 */
@Getter
@Setter
public class MenuParam {

    private Integer pid;

    private String name;

    private String value;

    private Integer type;

    private String uri;

    private Integer sort;

    private String icon;

    private Integer status;

}
