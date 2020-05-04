package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.req.SRolesReq;

import java.util.List;
import java.util.Map;

public interface SRolesMapper extends BaseMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SRoles record);

    int insertSelective(SRoles record);

    SRoles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SRoles record);

    int updateByPrimaryKey(SRoles record);

    List<Map<String,Object>> queryAllRoles(SRoles record);

    int queryRoleNameIsUse(SRoles sRoles);

    int queryRoleIsUse(long roleId);

    int updateToDisable(SRoles sRoles);

    int modify(SRoles sRoles);

    List<Map<String,Object>> list(SRolesReq sRolesReq);

}