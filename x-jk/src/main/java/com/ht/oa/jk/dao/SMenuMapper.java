package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.SRoleMenu;
import com.ht.oa.jk.model.req.SRolesReq;
import com.ht.oa.jk.model.req.SUserRoleReq;

import java.util.List;

public interface SMenuMapper extends BaseMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SMenu record);

    int insertSelective(SMenu record);

    SMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SMenu record);

    int updateByPrimaryKey(SMenu record);

    List<SMenu> queryAllMenus();

    List<SMenu> queryMenusByMid(SUserRoleReq sUserRoleReq);

    int updateLeafByMenuId(long resourceId);

    int modify(SMenu model);

    List<SMenu> queryMenusByRoleId(SRolesReq sRolesReq);

    int enable(SMenu record);

    int stop(SMenu record);

    Integer queryLastChildPriorityByParentId(long parentId);

    List<SMenu> getAllMenuByRoleId(SRoleMenu model);

}