package com.ht.oa.jk.controller.security.service;


import com.ht.oa.jk.model.SResources;

import java.util.List;

public interface ResourceService {

    List<SResources> getAllResources(long mid);

    List<SResources> getAllResources();

    int add(SResources model);

    int updateResource(SResources sResources);

    /**
     * 查询资源通过角色id
     * @param roleId
     * @return
     */
    List<SResources> getResourcesByRoleId(long roleId);

}
