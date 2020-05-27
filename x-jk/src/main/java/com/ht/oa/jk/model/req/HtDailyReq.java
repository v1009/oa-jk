package com.ht.oa.jk.model.req;

import com.ht.oa.jk.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class HtDailyReq extends BaseModel implements Serializable {
    private Long id;

    private Date dailyDate;

    private Integer dailyDateNum;

    private String planContent;

    private String completeContent;

    private Integer isComplete;

    private String problem;

    private Long userId;

    private String userName;

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
        this.planContent = planContent;
    }

    public String getCompleteContent() {
        return completeContent;
    }

    public void setCompleteContent(String completeContent) {
        this.completeContent = completeContent;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
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
        this.userName = userName;
    }
}