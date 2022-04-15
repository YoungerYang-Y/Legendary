package pers.legendary.common.mbg.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.legendary.common.api.business.user.entity.SysUserInfo;
import pers.legendary.common.mbg.rbac.mapper.SysUserInfoMapper;
import pers.legendary.common.mbg.rbac.service.ISysUserInfoService;

/**
 *
 * Description: 针对表【sys_user_info(用户信息表（user扩展表）)】的数据库操作Service实现
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo>
    implements ISysUserInfoService{

}




