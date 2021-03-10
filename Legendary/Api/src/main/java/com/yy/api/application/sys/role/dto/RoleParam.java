package com.yy.api.application.sys.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * RoleParam:
 *
 * @Author: YangYang
 * @Date: 2021/2/22 10:56
 */
@Setter
@Getter
@ToString
public class RoleParam {


    private String name;

    private Integer status;
}
