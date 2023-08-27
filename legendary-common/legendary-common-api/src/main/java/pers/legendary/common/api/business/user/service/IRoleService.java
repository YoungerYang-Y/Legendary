package pers.legendary.common.api.business.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.legendary.common.api.business.user.model.RoleModel;
import pers.legendary.common.api.business.user.model.UserViewModel;

import java.util.List;

/**
 * Description: 角色服务接口
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/19 22:42
 */
public interface IRoleService {

    /**
     * 基于给定的pageParam和搜索条件，检索RoleModel对象的一个页面。
     *
     * @param pageParam 包含分页信息的页面对象
     * @param search    表示搜索条件的RoleModel对象
     * @return 匹配搜索条件的RoleModel对象的页面
     */
    Page<RoleModel> getPage(Page<RoleModel> pageParam, RoleModel search);

    /**
     * 根据提供的RoleModel对象检索一个RoleModel对象。
     *
     * @param roleModel 要检索的RoleModel对象
     * @return 检索到的RoleModel对象
     */
    RoleModel get(RoleModel roleModel);

    /**
     * 使用提供的角色模型创建一个角色。
     *
     * @param roleModel 包含要创建的角色的详细信息的角色模型
     * @return 如果角色成功创建，则为true；否则为false
     */
    boolean createRole(RoleModel roleModel);

    /**
     * 修改指定的角色。
     *
     * @param roleModel 要修改的角色模型
     * @return 如果成功修改角色，则为true；否则为false
     */
    boolean modifyRole(RoleModel roleModel);

    /**
     * 切换角色的状态。
     *
     * @param id     角色的ID
     * @param status 角色的新状态
     * @return 如果状态成功切换，则为true；否则为false
     */
    boolean switchRoleStatus(int id, boolean status);

    /**
     * 为给定的RoleModel和UserViewModel列表分配角色。
     *
     * @param roleModel 表示要分配的角色的RoleModel实例
     * @param user      表示要分配角色的用户的UserViewModel实例列表
     * @return 表示角色是否成功分配的布尔值
     */
    boolean assigningRoles(RoleModel roleModel, List<UserViewModel> user);

    /**
     * 移除一个角色。
     *
     * @param roleModel 要移除的角色模型
     * @return 如果成功移除角色，则为true；否则为false
     */
    boolean removeRole(RoleModel roleModel);
}
