package pers.legendary.common.api.business.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022-03-19 20:07:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "name")
    private String name;

    /**
     * 状态：0->禁用；1->启用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 版本号
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 角色含义
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @TableField(value = "mean")
    private String mean;

}
