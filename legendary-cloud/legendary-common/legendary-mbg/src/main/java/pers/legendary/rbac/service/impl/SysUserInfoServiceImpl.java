package pers.legendary.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.legendary.rbac.entity.SysUserInfo;
import pers.legendary.rbac.service.ISysUserInfoService;
import pers.legendary.rbac.mapper.SysUserInfoMapper;
import org.springframework.stereotype.Service;

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




