package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SRoleResources;
import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.req.SRolesReq;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 添加角色
     *
     * @param sRoles
     * @return
     */
    boolean add(SRoles sRoles);

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

    /**
     * 查询角色是否已经被使用
     *
     * @param roleId
     * @return
     */
    boolean checkRoleIsEnabledDel(long roleId);

    /**
     * 给角色赋资源
     *
     * @param sRoleResourceList
     * @return
     */
    boolean addResourceToRole(List<SRoleResources> sRoleResourceList);

    /**
     * 查询角色
     *
     * @param sRolesReq
     * @return
     */
    List<Map<String, Object>> list(SRolesReq sRolesReq);

    /**
     * 修改信息
     *
     * @param sRoles
     * @return
     */
    boolean modify(SRoles sRoles);

}
