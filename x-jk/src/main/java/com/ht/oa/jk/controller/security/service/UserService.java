package com.ht.oa.jk.controller.security.service;


import com.ht.oa.jk.model.SUserRole;

import java.util.List;
import java.util.Map;

public interface UserService {

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
