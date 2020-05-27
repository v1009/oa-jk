package com.ht.oa.jk.controller.security.service;

import com.ht.oa.jk.model.SUserRole;
import com.ht.oa.jk.model.SUsers;
import com.ht.oa.jk.model.req.SUsersReq;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 添加用户信息
     *
     * @param sUsers
     * @return
     */
    boolean add(SUsers sUsers);

    /**
     * 修改用户信息
     *
     * @param sUsers
     * @return
     */
    boolean modify(SUsers sUsers);

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

    /**
     * 删除用户
     *
     * @param sUsers
     * @return
     */
    boolean del(SUsers sUsers);

    /**
     * 查询用户通过userId
     *
     * @param userId
     * @return
     */
    Map<String, Object> queryModelByUserId(long userId);

}
