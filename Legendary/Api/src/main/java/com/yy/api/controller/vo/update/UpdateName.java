package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * UpdateName:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 10:58
 */
@Setter
@Getter
public class UpdateName {

    @ApiModelProperty(value = "名称",required = true)
    private String name;
}
