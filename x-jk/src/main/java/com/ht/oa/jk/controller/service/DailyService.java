package com.ht.oa.jk.controller.service;

import com.ht.oa.jk.model.HtDaily;
import com.ht.oa.jk.model.req.HtDailyReq;

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

}
