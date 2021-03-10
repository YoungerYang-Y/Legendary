package com.yy.mbg.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表（user扩展表）
 * </p>
 *
 * @author YangYang
 * @since 2021-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserInfo对象", description="用户信息表（user扩展表）")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "登录次数")
    private Integer loginFrequency;


}
