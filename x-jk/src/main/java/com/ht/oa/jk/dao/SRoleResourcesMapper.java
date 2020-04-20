package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SRoleResources;

import java.util.List;

public interface SRoleResourcesMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRoleResources record);

    int insertSelective(SRoleResources record);

    SRoleResources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRoleResources record);

    int updateByPrimaryKey(SRoleResources record);

    int delRecordByRoleId(SRoleResources record);

    int batchInsert(List<SRoleResources> list);

}