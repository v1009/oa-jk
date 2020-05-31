package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.SRoleMenu;

import java.util.List;

public interface MenuService {

    /**
     * 查询用户的菜单列表
     *
     * @param userId
     * @return
     */
    List<SMenu> queryAllMenus(long userId);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<SMenu> queryAllMenus();

    /**
     * 添加菜单
     *
     * @param model
     * @return
     */
    boolean add(SMenu model);

    /**
     * 修改菜单
     *
     * @param model
     * @return
     */
    boolean modify(SMenu model);

    /**
     * 查询资源通过角色id
     *
     * @param roleId
     * @return
     */
    List<SMenu> getMenuListByRoleId(long roleId);

    /**
     * 启动菜单
     *
     * @param model
     * @return
     */
    boolean enable(SMenu model);

    /**
     * 停用菜单
     *
     * @param model
     * @return
     */
    boolean stop(SMenu model);

    /**
     * 查询所有菜单通过用户id
     *
     * @param sRoleMenu
     * @return
     */
    List<SMenu> getAllMenuByRoleId(SRoleMenu sRoleMenu);

}
