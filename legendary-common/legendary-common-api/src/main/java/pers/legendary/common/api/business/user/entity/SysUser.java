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
 * 用户表
 * </p>
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022-03-19 20:07:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 手机号码
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @TableField(value = "email")
    private String email;

    /**
     * 性别：0->未知；1->男；2->女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 所在城市
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "city")
    private String city;

    /**
     * 头像
     */
    @Size(max = 500, message = "编码长度不能超过500")
    @TableField(value = "icon")
    private String icon;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 版本号
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @TableField(value = "status")
    private Integer status;

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

}
