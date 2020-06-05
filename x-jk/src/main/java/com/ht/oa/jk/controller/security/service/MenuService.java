package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.SRoleMenu;
import com.ht.oa.jk.model.req.SRolesReq;
import com.ht.oa.jk.model.req.SUserRoleReq;

import java.util.List;

public interface MenuService {

    /**
     * 查询用户的菜单列表
     *
     * @return
     */
    List<SMenu> queryAllMenus(SUserRoleReq sUserRoleReq);

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
     * @return
     */
    List<SMenu> getMenuListByRole(SRolesReq sRolesReq);

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
