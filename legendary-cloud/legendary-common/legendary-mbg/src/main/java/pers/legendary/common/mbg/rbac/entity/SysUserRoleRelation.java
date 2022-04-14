package pers.legendary.common.mbg.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * <p>
 * 用户角色关系表
 * </p>
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_user_role_relation")
public class SysUserRoleRelation implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @NotNull(message="[主键]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

}
