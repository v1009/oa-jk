package com.ht.oa.jk.model.req;

import com.ht.oa.jk.model.BaseModel;

import java.io.Serializable;

public class SUserRoleReq extends BaseModel implements Serializable {
    private Long id;

    private Long userId;

    private Long roleId;

    private Long ownerMid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOwnerMid() {
        return ownerMid;
    }

    public void setOwnerMid(Long ownerMid) {
        this.ownerMid = ownerMid;
    }

}