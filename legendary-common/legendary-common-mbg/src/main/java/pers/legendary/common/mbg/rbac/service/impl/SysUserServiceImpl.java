package pers.legendary.common.mbg.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.mbg.rbac.mapper.SysUserMapper;
import pers.legendary.common.mbg.rbac.service.ISysUserService;

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
    implements ISysUserService {

}




