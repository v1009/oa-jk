package com.ht.oa.jk.controller.security.service.impl;

import com.ht.oa.jk.controller.security.service.MenuService;
import com.ht.oa.jk.dao.SMenuMapper;
import com.ht.oa.jk.dao.SMenuMapper;
import com.ht.oa.jk.model.SMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SMenuMapper sMenuMapper;

    @Override
    public List<SMenu> getAllResources(long mid) {
        return sMenuMapper.queryMenusByMid(mid);
    }

    @Override
    public List<SMenu> getAllResources() {
        return sMenuMapper.queryAllMenus();
    }

    @Override
    public boolean add(SMenu model) {
        int leaf = model.getLeaf();
        long parentId = model.getParentId();
        model.setLeaf(1);
        model.setStatus(1);
        if (leaf == 1) {
            sMenuMapper.updateLeafByMenuId(parentId);
        }
        Integer lastPriority = sMenuMapper.queryLastChildPriorityByParentId(parentId);
        model.setPriority(lastPriority + 1);
        int res = sMenuMapper.insert(model);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean modify(SMenu model) {
        int res = sMenuMapper.modify(model);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SMenu> getResourcesByRoleId(long roleId) {
        return sMenuMapper.queryMenusByRoleId(roleId);
    }

    @Override
    public boolean enable(SMenu model) {
        int res = sMenuMapper.enable(model);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean stop(SMenu model) {
        int res = sMenuMapper.stop(model);
        if (res > 0) {
            return true;
        }
        return false;
    }

}
