package com.yy.api.controller.vo.update;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

/**
 * UpdateRole:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 10:53
 */
@Setter
@Getter
public class UpdateRole {

    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    @Range(min = 0,max = 1)
    @ApiModelProperty(value = "状态：0->禁用；1->启用",required = true, allowableValues = "0,1,2")
    private Integer status;

}
