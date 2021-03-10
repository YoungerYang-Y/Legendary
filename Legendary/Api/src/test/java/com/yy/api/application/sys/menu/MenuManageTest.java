package com.yy.api.application.sys.menu;

import com.yy.api.TestAbstract;
import com.yy.api.application.sys.menu.dto.MenuParam;
import com.yy.api.application.sys.menu.dto.MenuTree;
import com.yy.api.application.sys.role.dto.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * MenuManageTest:
 *
 * @Author: YangYang
 * @Date: 2021/2/19 22:26
 */
@Slf4j
public class MenuManageTest extends TestAbstract {

    @Autowired
    private IMenuManage menuManage;

    @Test
    public void getMenuTree() {

    }


    @Test
    public void addMenu() {

        MenuParam menuParam = new MenuParam();
        menuParam.setPid(0);
        menuParam.setName("add测试");
        menuParam.setSort(1);
//        menuParam.setStatus(1);
        menuParam.setType(0);
        menuManage.addMenu(menuParam);
    }

    @Test
    public void changeMenu() {

        MenuParam menuParam = new MenuParam();
        menuParam.setName("change测试");
        menuManage.changeMenu(menuParam,14);
    }

    @Test
    public void removeMenu() {
        menuManage.removeMenu(8);
    }


}