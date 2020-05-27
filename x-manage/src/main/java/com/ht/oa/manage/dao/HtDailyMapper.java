package com.ht.oa.manage.dao;

import com.ht.oa.manage.model.HtDaily;
import com.ht.oa.manage.pojo.HtDailyData;

import java.util.List;

public interface HtDailyMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HtDaily record);

    int insertSelective(HtDaily record);

    HtDaily selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HtDaily record);

    int updateByPrimaryKey(HtDaily record);

    int completeSubmit(HtDaily htDaily);

    List<HtDailyData> queryListByDate(String date);

}