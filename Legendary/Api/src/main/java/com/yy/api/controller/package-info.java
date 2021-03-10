/**
 * package-info: Api接口
 *
 * *********************************************************************************************
 *  菜单管理MenuController
 *      - Get
 *          - /sys/menus/{id}                                   获取菜单信息
 *      - Post
 *          - /sys/menus/menu                                   新增菜单
 *
 *      - Put
 *          - /sys/menus/{id}                                   修改菜单
 *
 *      - Patch
 *          - /sys/menus/{id}/status                            修改菜单状态
 *
 *      - Delete
 *          - /sys/menus/{id}                                   删除菜单
 *
 *
 *
 *
 * *********************************************************************************************
 *  角色管理RoleController
 *      - Get
 *          - /sys/roles/{id}                                   获取角色
 *          - /sys/roles/page/current/{current}/size/{size}     分页查询角色
 *
 *      - Post
 *          - /sys/roles/role                                   新增角色
 *          - /sys/roles/role-permission                        新增角色的权限
 *
 *      - Put
 *          - /sys/roles/{id}                                   修改角色信息
 *
 *      - Patch
 *          - /sys/roles/{id}/name                              修改角色名称
 *          - /sys/roles/{id}/status                            修改角色状态
 *
 *      - Delete
 *          - /sys/roles/{id}                                   删除角色
 *
 *
 *
 *
 * *********************************************************************************************
 *  用户管理UserController
 *      - Get
 *          - /sys/users/{id}                                   查询用户信息
 *          - /sys/users/{id}/menu-tree                         获取用户菜单
 *          - /sys/users/{id}/permissions                       获取用户权限
 *          - /sys/users/{id}/roles                             获取用户角色
 *
 *      - Post
 *          - /sys/users/user                                   新增用户
 *          - /sys/users/user-role                              新增用户角色
 *
 *      - Put
 *          - /sys/users/{id}                                   修改用户信息
 *
 *      - Patch
 *          - /sys/users/{id}/password                          修改用户密码
 *          - /sys/users/{id}/status                            修改用户状态
 *          - /sys/users/{id}/icon                              修改用户头像
 *
 *
 *      - Delete
 *          - /sys/users/{id}                                   删除用户
 *          - /sys/users//user-role                             删除用户角色
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @Author: YangYang
 * @Date: 2021/2/25 16:10
 */
package com.yy.api.controller;