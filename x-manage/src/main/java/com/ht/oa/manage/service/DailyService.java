package com.ht.oa.manage.service;

import com.ht.oa.manage.model.HtDaily;
import com.ht.oa.manage.pojo.HtDailyData;

import java.util.List;

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
     * 提交完成内容
     *
     * @param htDaily
     * @return
     */
    boolean completeSubmit(HtDaily htDaily);

    /**
     * 查询某天的日报记录
     *
     * @param date
     * @return
     */
    List<HtDailyData> queryListByDate(String date);

}
