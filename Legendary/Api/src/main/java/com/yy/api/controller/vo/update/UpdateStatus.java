package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

/**
 * UpdateStatus:
 *
 * @Author: YangYang
 * @Date: 2021/2/25 22:30
 */
@Setter
@Getter
public class UpdateStatus {

    @Range(min = 0,max = 1)
    @ApiModelProperty(value = "是否启用",required = true,allowableValues = "0, 1")
    private Integer status;
}
