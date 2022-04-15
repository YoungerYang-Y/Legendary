package pers.legendary.common.api.business.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.legendary.common.api.business.user.entity.SysUser;

import java.util.Set;

/**
 * Description: 用户模型，包含用户信息、相关角色信息、相关权限信息
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/19 20:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends SysUser {

    private Set<RoleModel> roles;
}
