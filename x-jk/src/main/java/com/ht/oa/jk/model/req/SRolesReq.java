package com.ht.oa.jk.model.req;

import com.ht.oa.jk.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class SRolesReq extends BaseModel implements Serializable {
    private Long roleId;

    private String roleName;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}