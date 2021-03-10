package com.yy.mgb.extend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.mgb.extend.dto.UserPermissionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserPermissionMapper: 用户权限Mapper
 *
 * @Author: YangYang
 * @Date: 2021/2/23 22:38
 */
@Component
@Mapper
public interface UserPermissionMapper extends BaseMapper<UserPermissionDto> {

    /**
     * Description：根据用户id获取权限集
     *
     * @author: yangyang
     * @date: 2021/2/23 22:42
     * @param id 用户id
     * @return: 返回权限集
     */
    @Select("SELECT sp.* FROM sys_user usr\n" +
            "LEFT JOIN sys_user_role_relation syrr ON usr.id = syrr.user_id\n" +
            "LEFT JOIN sys_role_permission_relation srpr ON syrr.role_id = srpr.role_id\n" +
            "LEFT JOIN sys_permission sp ON srpr.permission_id = sp.id\n" +
            "Where usr.id = #{id}")
    List<UserPermissionDto> getUserPermission(@Param("id") Integer id);

    /**
     * Description：根据用户id获取菜单集
     *
     * @author: yangyang
     * @date: 2021/2/23 22:42
     * @param id 用户id
     * @return: 返回权限集
     */
    @Select("SELECT sp.* FROM sys_user usr\n" +
            "LEFT JOIN sys_user_role_relation syrr ON usr.id = syrr.user_id\n" +
            "LEFT JOIN sys_role_permission_relation srpr ON syrr.role_id = srpr.role_id\n" +
            "LEFT JOIN sys_permission sp ON srpr.permission_id = sp.id\n" +
            "Where usr.id = #{id} AND (sp.type = 0 OR sp.type = 1)")
    List<UserPermissionDto> getUserMenu(@Param("id") Integer id);

}
