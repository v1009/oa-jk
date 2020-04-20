package com.ht.oa.jk.controller.security.service.impl;

import com.ht.oa.jk.controller.security.service.UserService;
import com.ht.oa.jk.dao.SUserRoleMapper;
import com.ht.oa.jk.model.SUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SUserRoleMapper sUserRoleMapper;

    @Override
    public List<Map<String, Object>> queryRoleByModel(SUserRole sUserRole) {
        return sUserRoleMapper.queryRoleByModel(sUserRole);
    }

    @Override
    @Transactional
    public boolean addRoleToUser(List<SUserRole> sUserRoleList) {
        long userId = sUserRoleList.get(0).getUserId();
        long ownerMid = sUserRoleList.get(0).getOwnerMid();

        SUserRole sUserRole = new SUserRole();
        sUserRole.setUserId(userId);
        sUserRole.setOwnerMid(ownerMid);
        sUserRoleMapper.delRecordByModel(sUserRole);
        int res = sUserRoleMapper.batchInsert(sUserRoleList);
        if (res > 0) {
            return true;
        } else {
            throw new RuntimeException("addRoleToUser:批量插入失败");
        }
    }

}
