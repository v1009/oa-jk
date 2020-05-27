package com.ht.oa.jk.model;

import java.io.Serializable;
import java.util.Date;

public class HtDaily extends BaseModel implements Serializable {
    private Long id;

    private Date dailyDate;

    private Integer dailyDateNum;

    private String planContent;

    private Integer isComplete;

    private String completeContent;

    private String problem;

    private Date insertTime;

    private Date lastTime;

    private Integer status;

    private Long userId;

    private String userName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    public Integer getDailyDateNum() {
        return dailyDateNum;
    }

    public void setDailyDateNum(Integer dailyDateNum) {
        this.dailyDateNum = dailyDateNum;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent == null ? null : planContent.trim();
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getCompleteContent() {
        return completeContent;
    }

    public void setCompleteContent(String completeContent) {
        this.completeContent = completeContent == null ? null : completeContent.trim();
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem == null ? null : problem.trim();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
        HtDaily other = (HtDaily) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDailyDate() == null ? other.getDailyDate() == null : this.getDailyDate().equals(other.getDailyDate()))
            && (this.getDailyDateNum() == null ? other.getDailyDateNum() == null : this.getDailyDateNum().equals(other.getDailyDateNum()))
            && (this.getPlanContent() == null ? other.getPlanContent() == null : this.getPlanContent().equals(other.getPlanContent()))
            && (this.getIsComplete() == null ? other.getIsComplete() == null : this.getIsComplete().equals(other.getIsComplete()))
            && (this.getCompleteContent() == null ? other.getCompleteContent() == null : this.getCompleteContent().equals(other.getCompleteContent()))
            && (this.getProblem() == null ? other.getProblem() == null : this.getProblem().equals(other.getProblem()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDailyDate() == null) ? 0 : getDailyDate().hashCode());
        result = prime * result + ((getDailyDateNum() == null) ? 0 : getDailyDateNum().hashCode());
        result = prime * result + ((getPlanContent() == null) ? 0 : getPlanContent().hashCode());
        result = prime * result + ((getIsComplete() == null) ? 0 : getIsComplete().hashCode());
        result = prime * result + ((getCompleteContent() == null) ? 0 : getCompleteContent().hashCode());
        result = prime * result + ((getProblem() == null) ? 0 : getProblem().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        return result;
    }
}