package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.HtDaily;

public interface HtDailyMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HtDaily record);

    int insertSelective(HtDaily record);

    HtDaily selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HtDaily record);

    int updateByPrimaryKey(HtDaily record);
}