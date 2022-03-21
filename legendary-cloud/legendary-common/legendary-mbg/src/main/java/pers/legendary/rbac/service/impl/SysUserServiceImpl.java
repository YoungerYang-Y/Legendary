package pers.legendary.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.legendary.rbac.entity.SysUser;
import pers.legendary.rbac.service.ISysUserService;
import pers.legendary.rbac.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 *
 * Description: 针对表【sys_user(用户表)】的数据库操作Service实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements ISysUserService{

}




