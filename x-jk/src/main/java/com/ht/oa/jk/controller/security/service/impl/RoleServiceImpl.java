package com.ht.oa.jk.controller.security.service.impl;

import com.ht.oa.jk.controller.security.service.RoleService;
import com.ht.oa.jk.dao.SRoleResourcesMapper;
import com.ht.oa.jk.dao.SRolesMapper;
import com.ht.oa.jk.model.SRoleResources;
import com.ht.oa.jk.model.SRoles;
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
    private SRoleResourcesMapper sRoleResourceMapper;

    @Override
    public int add(SRoles sRoles) {
        return sRolesMapper.insert(sRoles);
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(SRoles sRoles) {
        return sRolesMapper.queryAllRoles(sRoles);
    }

    @Override
    public boolean checkRoleNameIsExist(SRoles sRoles) {
        return sRolesMapper.queryRoleNameIsUse(sRoles) > 0;
    }

    @Override
    public boolean update(SRoles sRoles) {
        return sRolesMapper.modify(sRoles) == 1;
    }

    @Override
    public boolean del(SRoles sRoles) {
        return sRolesMapper.updateToDisable(sRoles) == 1;
    }

    @Override
    public boolean checkRoleIsEnabledDel(int roleId) {
        return sRolesMapper.queryRoleIsUse(roleId) == 0;
    }

    @Override
    @Transactional
    public boolean addResourceToRole(List<SRoleResources> sRoleResourceList) {
        long roleId = sRoleResourceList.get(0).getRoleId();
        long ownerMid = sRoleResourceList.get(0).getOwnerMid();

        SRoleResources sRoleResource = new SRoleResources();
        sRoleResource.setRoleId(roleId);
        sRoleResource.setOwnerMid(ownerMid);
        int res = sRoleResourceMapper.delRecordByRoleId(sRoleResource);
        res = sRoleResourceMapper.batchInsert(sRoleResourceList);
        if (res < 1) {
            throw new RuntimeException("addResourceToRole:批量插入失败");
        }
        return true;
    }

}
