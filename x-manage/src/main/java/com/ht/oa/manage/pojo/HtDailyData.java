package com.ht.oa.manage.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ht.oa.manage.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class HtDailyData {

    @ExcelProperty(value = "日期")
    private String dailyDate;
    @ExcelProperty(value = "人员姓名")
    private String userName;
    @ExcelProperty(value = "计划工作内容")
    private String planContent;
    @ExcelProperty(value = "是否完成")
    private String isComplete;
    @ExcelProperty(value = "完成工作内容")
    private String completeContent;
    @ExcelProperty(value = "遇到的问题")
    private String problem;

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}