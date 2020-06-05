package com.ht.oa.jk.model.req;

import com.ht.oa.jk.model.BaseModel;

import java.io.Serializable;

public class SRolesReq extends BaseModel implements Serializable {
    private Long roleId;

    private String roleName;

    private Long ownerMid;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Long getOwnerMid() {
        return ownerMid;
    }

    public void setOwnerMid(Long ownerMid) {
        this.ownerMid = ownerMid;
    }
}