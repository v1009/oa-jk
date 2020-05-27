package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.HtDaily;
import com.ht.oa.jk.model.req.HtDailyReq;
import com.ht.oa.jk.pojo.HtDailyData;

import java.util.List;
import java.util.Map;

public interface HtDailyMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HtDaily record);

    int insertSelective(HtDaily record);

    HtDaily selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HtDaily record);

    int updateByPrimaryKey(HtDaily record);

    List<Map<String,Object>> list(HtDailyReq htDailyReq);

    List<HtDailyData> queryListByDate(String date);

}