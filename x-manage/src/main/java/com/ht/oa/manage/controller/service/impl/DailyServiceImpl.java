package com.ht.oa.manage.controller.service.impl;

import com.ht.oa.manage.controller.service.DailyService;
import com.ht.oa.manage.dao.HtDailyMapper;
import com.ht.oa.manage.model.HtDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
