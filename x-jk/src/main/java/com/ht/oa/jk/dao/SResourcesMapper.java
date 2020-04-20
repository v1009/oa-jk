package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SResources;

import java.util.List;

public interface SResourcesMapper extends BaseMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SResources record);

    int insertSelective(SResources record);

    SResources selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SResources record);

    int updateByPrimaryKey(SResources record);

    List<SResources> queryAllResources();

    List<SResources> queryResourcesByMid(long mid);

    int updateLeafByResourceId(long resourceId);

    int updateResource(SResources model);

    List<SResources> queryResourcesByRoleId(long roleId);

}