package pers.legendary.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 系统权限表
 * </p>
 * @author YangYang
 * @date 2022-03-19 20:07:57
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_permission")
public class SysPermission implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @NotNull(message="[主键]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级权限id
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 名称
     */
    @Size(max= 100,message="编码长度不能超过100")
    @TableField(value = "name")
    private String name;

    /**
     * 权限值
     */
    @Size(max= 200,message="编码长度不能超过200")
    @TableField(value = "value")
    private String value;

    /**
     * 图标
     */
    @Size(max= 500,message="编码长度不能超过500")
    @TableField(value = "icon")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 前端资源路径
     */
    @Size(max= 200,message="编码长度不能超过200")
    @TableField(value = "uri")
    private String uri;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 启用状态；0->禁用；1->启用
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
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.UPDATE)
    private Date gmtModified;

}
