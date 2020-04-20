package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SRoleResources;
import com.ht.oa.jk.model.SRoles;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 添加角色
     *
     * @param sRoles
     * @return
     */
    int add(SRoles sRoles);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Map<String, Object>> queryAllRoles(SRoles sRoles);

    /**
     * 检测角色名称是否存在
     *
     * @param sRoles
     * @return
     */
    boolean checkRoleNameIsExist(SRoles sRoles);

    /**
     * 更新角色
     *
     * @param sRoles
     * @return
     */
    boolean update(SRoles sRoles);

    /**
     * 删除角色
     *
     * @param sRoles
     * @return
     */
    boolean del(SRoles sRoles);

    boolean checkRoleIsEnabledDel(int roleId);

    /**
     * 给角色赋资源
     *
     * @param sRoleResourceList
     * @return
     */
    boolean addResourceToRole(List<SRoleResources> sRoleResourceList);

}
