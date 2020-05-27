package com.ht.oa.jk.model;

import java.io.Serializable;
import java.util.Date;

public class SRoles extends BaseModel implements Serializable {
    private Long roleId;

    private String roleName;

    private Date insertTime;

    private Date lastTime;

    private Integer status;

    private Long addMid;

    private Long updateMid;

    private Long ownerMid;

    private static final long serialVersionUID = 1L;

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAddMid() {
        return addMid;
    }

    public void setAddMid(Long addMid) {
        this.addMid = addMid;
    }

    public Long getUpdateMid() {
        return updateMid;
    }

    public void setUpdateMid(Long updateMid) {
        this.updateMid = updateMid;
    }

    public Long getOwnerMid() {
        return ownerMid;
    }

    public void setOwnerMid(Long ownerMid) {
        this.ownerMid = ownerMid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SRoles other = (SRoles) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddMid() == null ? other.getAddMid() == null : this.getAddMid().equals(other.getAddMid()))
            && (this.getUpdateMid() == null ? other.getUpdateMid() == null : this.getUpdateMid().equals(other.getUpdateMid()))
            && (this.getOwnerMid() == null ? other.getOwnerMid() == null : this.getOwnerMid().equals(other.getOwnerMid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddMid() == null) ? 0 : getAddMid().hashCode());
        result = prime * result + ((getUpdateMid() == null) ? 0 : getUpdateMid().hashCode());
        result = prime * result + ((getOwnerMid() == null) ? 0 : getOwnerMid().hashCode());
        return result;
    }
}