package com.ht.oa.jk.model;

import java.io.Serializable;
import java.util.Date;

public class SRoleResources extends BaseModel implements Serializable {
    private Long id;

    private Long roleId;

    private Long resourceId;

    private Date insertTime;

    private Long addMid;

    private Long ownerMid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Long getAddMid() {
        return addMid;
    }

    public void setAddMid(Long addMid) {
        this.addMid = addMid;
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
        SRoleResources other = (SRoleResources) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getAddMid() == null ? other.getAddMid() == null : this.getAddMid().equals(other.getAddMid()))
            && (this.getOwnerMid() == null ? other.getOwnerMid() == null : this.getOwnerMid().equals(other.getOwnerMid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getAddMid() == null) ? 0 : getAddMid().hashCode());
        result = prime * result + ((getOwnerMid() == null) ? 0 : getOwnerMid().hashCode());
        return result;
    }
}