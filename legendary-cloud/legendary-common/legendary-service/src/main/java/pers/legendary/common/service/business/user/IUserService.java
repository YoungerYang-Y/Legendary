package pers.legendary.common.service.business.user;

import pers.legendary.common.mbg.rbac.model.UserModel;

/**
 * Description: 用户服务
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/19 20:35
 */
public interface IUserService {

    /**
     * 根据username【用户唯一标识】获取用户信息
     *
     * @param username 用户唯一标识
     * @return 用户信息
     */
    UserModel getUserByUsername(String username);
}
