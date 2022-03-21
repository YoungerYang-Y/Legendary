package pers.legendary.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.legendary.rbac.entity.SysRole;
import pers.legendary.rbac.service.ISysRoleService;
import pers.legendary.rbac.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
 *
 * Description: 针对表【sys_role(角色表)】的数据库操作Service实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:57
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements ISysRoleService{

}




