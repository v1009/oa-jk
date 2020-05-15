package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SMenu;

import java.util.List;

public interface MenuService {

    List<SMenu> getAllResources(long mid);

    List<SMenu> getAllResources();

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
    List<SMenu> getResourcesByRoleId(long roleId);

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

}
