package com.yy.mgb.extend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.mgb.extend.dto.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRoleMapper:
 *
 * @Author: YangYang
 * @Date: 2021/2/24 21:25
 */
@Component
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDto> {

    /**
     * Description：根据用户id获取用户的权限集
     *
     * @author: yangyang
     * @date: 2021/2/24 21:28
     * @param id 用户id
     * @return: 用户对于的角色集
     */
    @Select("SELECT DISTINCT role.* FROM sys_user_role_relation surr\n" +
            "LEFT JOIN sys_role role ON surr.role_id = role.id\n" +
            "WHERE surr.user_id = #{id}")
    List<UserRoleDto> getUserRoles(@Param("id") Integer id);
}
