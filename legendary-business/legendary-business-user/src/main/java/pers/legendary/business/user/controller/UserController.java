package pers.legendary.business.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.legendary.common.api.business.user.model.UserModel;
import pers.legendary.common.api.business.user.model.UserViewModel;
import pers.legendary.common.api.business.user.service.IUserService;

/**
 * Description: 用户管理控制器
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 19:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名【用户唯一标识】
     * @return 用户信息，包含所示角色与拥有权限
     * @author YangYang
     * @date 2023-02-03 23:19
     */
    @PostMapping("/username/{username}")
    public ResponseEntity<UserModel> getUserInfo(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    /**
     * 分页查询
     *
     * @param search  查询参数
     * @param current 当前页
     * @param size    分页大小
     * @return 查询结果
     * @author YangYang
     * @date 2023-02-03 23:04
     */
    @PostMapping("/page/current/{current}/size/{size}")
    public ResponseEntity<Page<UserViewModel>> getPage(@RequestBody UserViewModel search, @PathVariable Long current, @PathVariable Long size) {
        Page<UserViewModel> page = new Page<>(current, size);
        return ResponseEntity.ok(userService.getPage(page, search));
    }

    /**
     * 新增用户
     * <p>
     * 1. 创建用户
     * 2. 添加用户角色关系
     *
     * @param vo 新增用户信息
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-06 21:28
     */
    @PostMapping
    public ResponseEntity<Boolean> addUser(@RequestBody UserModel vo) {
        return ResponseEntity.ok(userService.createUser(vo));
    }

    /**
     * 修改用户信息
     *
     * @param vo 用户视图
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-03 21:56
     */
    @PutMapping
    public ResponseEntity<Boolean> modifyUser(@RequestBody UserViewModel vo) {
        return ResponseEntity.ok(userService.modifyUser(vo));
    }

    /**
     * 删除用户
     *
     * @param username 用户名【用户唯一标识】
     * @return 执行结果
     * @author YangYang
     * @date 2023-02-03 21:58
     */
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Boolean> removeUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.removeUser(username));
    }
}
