package com.yy.api.application.sys.menu.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * PermissionResultDto: 权限传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/20 8:46
 */
@Setter
@Getter
@ToString
public class PermissionDto {

    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    private String icon;

    private String uri;

    private Integer sort;

}
