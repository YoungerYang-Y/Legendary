package pers.legendary.common.mbg.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.legendary.common.api.business.user.entity.SysRolePermissionRelation;
import pers.legendary.common.mbg.rbac.mapper.SysRolePermissionRelationMapper;
import pers.legendary.common.mbg.rbac.service.ISysRolePermissionRelationService;

/**
 *
 * Description: 针对表【sys_role_permission_relation(角色权限关系表)】的数据库操作Service实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@Service
public class SysRolePermissionRelationServiceImpl extends ServiceImpl<SysRolePermissionRelationMapper, SysRolePermissionRelation>
    implements ISysRolePermissionRelationService {

}




