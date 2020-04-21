package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SUserRole;
import com.ht.oa.jk.model.SUsers;
import com.ht.oa.jk.model.req.SUsersReq;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 添加用户
     *
     * @param sUsers
     * @return
     */
    boolean add(SUsers sUsers);

    /**
     * 查询用户列表
     *
     * @param sUsersReq
     * @return
     */
    List<Map<String, Object>> list(SUsersReq sUsersReq);

    /**
     * 查询角色
     *
     * @param sUserRole
     * @return
     */
    List<Map<String, Object>> queryRoleByModel(SUserRole sUserRole);

    /**
     * 添加用户的角色
     *
     * @return
     */
    boolean addRoleToUser(List<SUserRole> sUserRoleList);

}
