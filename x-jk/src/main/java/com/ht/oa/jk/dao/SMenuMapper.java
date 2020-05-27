package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.SMenu;

import java.util.List;

public interface SMenuMapper extends BaseMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SMenu record);

    int insertSelective(SMenu record);

    SMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SMenu record);

    int updateByPrimaryKey(SMenu record);

    List<SMenu> queryAllMenus();

    List<SMenu> queryMenusByMid(long mid);

    int updateLeafByMenuId(long resourceId);

    int modify(SMenu model);

    List<SMenu> queryMenusByRoleId(long roleId);

    int enable(SMenu record);

    int stop(SMenu record);

    Integer queryLastChildPriorityByParentId(long parentId);

}