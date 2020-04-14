package com.ht.oa.jk.controller.service;

import com.ht.oa.jk.model.HtDaily;

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

}
