package com.ht.oa.jk.service.impl;

import com.ht.oa.jk.dao.HtDailyMapper;
import com.ht.oa.jk.model.HtDaily;
import com.ht.oa.jk.model.req.HtDailyReq;
import com.ht.oa.jk.pojo.HtDailyData;
import com.ht.oa.jk.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DailyServiceImpl implements DailyService {

    @Autowired
    private HtDailyMapper htDailyMapper;

    @Override
    public boolean add(HtDaily htDaily) {
        int res = htDailyMapper.insert(htDaily);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean modify(HtDaily htDaily) {
        return false;
    }

    @Override
    public List<Map<String, Object>> list(HtDailyReq htDailyReq) {
        return htDailyMapper.list(htDailyReq);
    }

    @Override
    public List<HtDailyData> queryListByDate(String date) {
        return htDailyMapper.queryListByDate(date);
    }

}
