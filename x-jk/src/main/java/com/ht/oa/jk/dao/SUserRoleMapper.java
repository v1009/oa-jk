package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SUserRole;

import java.util.List;
import java.util.Map;

public interface SUserRoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUserRole record);

    int insertSelective(SUserRole record);

    SUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUserRole record);

    int updateByPrimaryKey(SUserRole record);

    List<Map<String, Object>> queryRoleByModel(SUserRole record);

    int delRecordByModel(SUserRole record);

    int batchInsert(List<SUserRole> list);
}