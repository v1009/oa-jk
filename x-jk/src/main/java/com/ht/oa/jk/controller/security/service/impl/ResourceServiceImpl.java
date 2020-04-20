package com.ht.oa.jk.controller.security.service.impl;

import com.ht.oa.jk.controller.security.service.ResourceService;
import com.ht.oa.jk.dao.SResourcesMapper;
import com.ht.oa.jk.model.SResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SResourcesMapper sResourcesMapper;

    @Override
    public List<SResources> getAllResources(long mid) {
        return sResourcesMapper.queryResourcesByMid(mid);
    }

    @Override
    public List<SResources> getAllResources() {
        return sResourcesMapper.queryAllResources();
    }

    @Override
    public int add(SResources model) {
        boolean leaf = model.getLeaf();
        long parentId = model.getParentId();
        model.setLeaf(true);
        model.setStatus(1);
        if (leaf) {
            sResourcesMapper.updateLeafByResourceId(parentId);
        }
        return sResourcesMapper.insert(model);
    }

    @Override
    public int updateResource(SResources sResources) {
        return sResourcesMapper.updateResource(sResources);
    }

    @Override
    public List<SResources> getResourcesByRoleId(long roleId) {
        return sResourcesMapper.queryResourcesByRoleId(roleId);
    }
}
