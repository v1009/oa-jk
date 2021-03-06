package com.ht.oa.jk.controller.security.service.impl;

import com.ht.oa.jk.controller.security.service.RoleService;
import com.ht.oa.jk.dao.SRoleResourcesMapper;
import com.ht.oa.jk.dao.SRolesMapper;
import com.ht.oa.jk.model.SRoleResources;
import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.req.SRolesReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SRolesMapper sRolesMapper;
    @Autowired
    private SRoleResourcesMapper sRoleResourcesMapper;

    @Override
    public boolean add(SRoles sRoles) {
        int res = sRolesMapper.insert(sRoles);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(SRoles sRoles) {
        return sRolesMapper.queryAllRoles(sRoles);
    }

    @Override
    public boolean checkRoleNameIsExist(SRoles sRoles) {
        int res = sRolesMapper.queryRoleNameIsUse(sRoles);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(SRoles sRoles) {
        return sRolesMapper.modify(sRoles) == 1;
    }

    @Override
    public boolean del(SRoles sRoles) {
        int res = sRolesMapper.updateToDisable(sRoles);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRoleIsEnabledDel(long roleId) {
        int res = sRolesMapper.queryRoleIsUse(roleId);
        if (res > 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean addResourceToRole(List<SRoleResources> sRoleResourceList) {
        long roleId = sRoleResourceList.get(0).getRoleId();
        long ownerMid = sRoleResourceList.get(0).getOwnerMid();

        SRoleResources sRoleResource = new SRoleResources();
        sRoleResource.setRoleId(roleId);
        sRoleResource.setOwnerMid(ownerMid);
        int res = sRoleResourcesMapper.delRecordByRoleId(sRoleResource);
        res = sRoleResourcesMapper.batchInsert(sRoleResourceList);
        if (res < 1) {
            throw new RuntimeException("addResourceToRole:批量插入失败");
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> list(SRolesReq sRolesReq) {
        return sRolesMapper.list(sRolesReq);
    }

    @Override
    public boolean modify(SRoles sRoles) {
        int res = sRolesMapper.modify(sRoles);
        if (res > 0) {
            return true;
        }
        return false;
    }

}
