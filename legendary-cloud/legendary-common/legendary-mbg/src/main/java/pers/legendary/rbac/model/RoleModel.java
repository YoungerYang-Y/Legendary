package pers.legendary.rbac.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.legendary.rbac.entity.SysPermission;
import pers.legendary.rbac.entity.SysRole;

import java.util.Set;

/**
 * Description: 角色模型，包含该角色所对应的权限
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/21 21:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends SysRole {

    private Set<SysPermission> permissions;
}
