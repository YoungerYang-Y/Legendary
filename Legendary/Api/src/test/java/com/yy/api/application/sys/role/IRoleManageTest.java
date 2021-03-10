package com.yy.api.application.sys.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.api.TestAbstract;
import com.yy.api.application.sys.role.dto.RoleDto;
import com.yy.api.application.sys.role.dto.RoleParam;
import com.yy.core.common.CommonPage;
import com.yy.mbg.domain.entity.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IRoleManageTest:
 *
 * @Author: YangYang
 * @Date: 2021/2/22 11:11
 */
@Slf4j
public class IRoleManageTest extends TestAbstract {

    @Autowired
    private IRoleManage roleManage;

    @Test
    public void list() {

        Page<SysRole> page = new Page<>(1,5);
        CommonPage<RoleDto> list = roleManage.list(page);

        log.info(list.getList().getClass().getName());
    }

    @Test
    public void addRole() {
        RoleParam roleParam = new RoleParam();
        roleParam.setName("测试角色");
        roleParam.setStatus(1);
        roleManage.addRole(roleParam);
    }

    @Test
    public void changeRole() {
        RoleParam roleParam = new RoleParam();
        roleParam.setName("测试角色change");
        roleManage.changeRole(roleParam,5);
    }

    @Test
    public void removeRole() {
        roleManage.removeRole(5);
    }
}