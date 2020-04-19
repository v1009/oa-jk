package com.ht.oa.jk.service;

import com.ht.oa.jk.model.HtDaily;
import com.ht.oa.jk.model.req.HtDailyReq;
import com.ht.oa.jk.pojo.HtDailyData;

import java.util.List;
import java.util.Map;

public interface DailyService {

    /**
     * 添加
     *
     * @param htDaily
     * @return
     */
    boolean add(HtDaily htDaily);

    /**
     * 修改
     *
     * @param htDaily
     * @return
     */
    boolean modify(HtDaily htDaily);

    /**
     * 查询记录
     *
     * @param htDailyReq
     * @return
     */
    List<Map<String, Object>> list(HtDailyReq htDailyReq);

    /**
     * 查询某天的日报记录
     *
     * @param date
     * @return
     */
    List<HtDailyData> queryListByDate(String date);

}
