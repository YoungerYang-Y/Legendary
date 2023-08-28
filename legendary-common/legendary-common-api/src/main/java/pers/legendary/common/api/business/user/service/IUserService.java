package pers.legendary.common.api.business.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.model.UserViewModel;

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
     * @return 用户信息，包含角色与权限
     */
    UserModel getUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     * @param search    查询参数
     * @return 分页查询结果
     * @author YangYang
     * @date 2023-02-03 21:29
     */
    Page<UserViewModel> getPage(Page<UserViewModel> pageParam, UserViewModel search);

    /**
     * 创建用户
     * <p>
     * 1. 创建用户
     * 2. 添加用户角色关系
     *
     * @param vo 新创建用户信息
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-03 21:33
     */
    boolean createUser(UserModel vo);

    /**
     * 修改用户信息
     *
     * @param vo 用户视图
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-03 21:56
     */
    boolean modifyUser(UserViewModel vo);

    /**
     * 删除用户
     *
     * @param username 用户名【用户唯一标识】
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-03 21:58
     */
    boolean removeUser(String username);

    /**
     * 根据id获取用户信息
     *
     * @param username 用户名【用户唯一标识】
     * @return 用户信息
     * @author YangYang
     * @date 2023-02-06 22:13
     */
    SysUser get(String username);
}
