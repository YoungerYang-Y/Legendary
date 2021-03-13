package com.yy.api.application.sys.user;

import com.yy.api.TestAbstract;
import com.yy.api.application.sys.permision.dto.PermissionResultDto;
import com.yy.api.controller.vo.result.PermissionResult;
import com.yy.core.utils.CollectionCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * IUserManageTest:
 *
 * @Author: YangYang
 * @Date: 2021/2/24 11:45
 */
@Slf4j
public class IUserManageTest extends TestAbstract {

    @Autowired
    private IUserManage userManage;


    @Test
    public void copy(){

        List<PermissionResultDto> list = userManage.getUserPermission(1);

        List<PermissionResult> voList = CollectionCopyUtil.copyProperties(list,PermissionResult.class);
        log.info(voList.toString());
    }

    @Test
    public void removeUser(){

        userManage.removeUser(3);


    }
}