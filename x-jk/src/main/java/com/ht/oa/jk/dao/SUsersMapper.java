package com.ht.oa.jk.dao;

import com.ht.oa.jk.model.SUsers;
import com.ht.oa.jk.model.req.SUsersReq;

import java.util.List;
import java.util.Map;

public interface SUsersMapper extends BaseMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SUsers record);

    int insertSelective(SUsers record);

    SUsers selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SUsers record);

    int updateByPrimaryKey(SUsers record);

    List<Map<String,Object>> list(SUsersReq sUsersReq);

    int modify(SUsers sUsers);

    int updateToDisable(SUsers sUsers);

}