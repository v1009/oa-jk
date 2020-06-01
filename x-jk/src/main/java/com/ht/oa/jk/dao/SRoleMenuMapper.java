package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SRoleMenu;

import java.util.List;

public interface SRoleMenuMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRoleMenu record);

    int insertSelective(SRoleMenu record);

    SRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRoleMenu record);

    int updateByPrimaryKey(SRoleMenu record);

    int delRecordByRoleId(SRoleMenu sRoleMenu);

    int batchInsert(List<SRoleMenu> sRoleMenuList);

}