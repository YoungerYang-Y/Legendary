package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * UpdateIcon:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:38
 */
@Setter
@Getter
public class UpdateIcon {

    @ApiModelProperty("图标地址")
    private String icon;
}
