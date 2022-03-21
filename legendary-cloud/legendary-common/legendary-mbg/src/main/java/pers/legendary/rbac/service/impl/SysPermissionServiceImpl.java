package pers.legendary.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.legendary.rbac.entity.SysPermission;
import pers.legendary.rbac.service.ISysPermissionService;
import pers.legendary.rbac.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

/**
 *
 * Description: 针对表【sys_permission(系统权限表)】的数据库操作Service实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:57
 * @version 1.0.0
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
    implements ISysPermissionService{

}




