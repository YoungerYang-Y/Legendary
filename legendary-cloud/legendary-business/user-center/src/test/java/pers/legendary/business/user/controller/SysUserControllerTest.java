package pers.legendary.business.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pers.legendary.common.api.business.user.entity.SysUser;
import pers.legendary.common.mbg.rbac.service.ISysUserService;
import pers.legendary.common.mbg.rbac.service.impl.SysUserServiceImpl;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/28 9:35
 */
@WebMvcTest(SysUserController.class)
class SysUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(classes = {SysUserServiceImpl.class})
    private ISysUserService service;

    @Test
    void getPage() throws Exception {
        Page<SysUser> page = new Page<>();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        BDDMockito.given(this.service.page(page, wrapper)).willReturn(page);
        mvc.perform(MockMvcRequestBuilders
                        .post("/sys_user/page")
                        .content(JSON.toJSONString(page))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}