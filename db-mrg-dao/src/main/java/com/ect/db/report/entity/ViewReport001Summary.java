/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReport001Summary extends DomainEntity implements Serializable{
    private static final long serialVersionUID = 6808475191469188934L;
    
    @Id
    @Column(name ="ROW_NO")
    protected Integer id;
    @Column(name = "REPORT_ID",nullable = true)
    protected Integer reportId;
    @Column(name = "REPORT_CODE")
    protected String reportCode;
    @Lob
    @Column(name = "REPORT_DESC")
    protected String reportDesc;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;
    @Column(name = "CREATED_USER")
    protected Integer createdUser;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;
    @Column(name = "UPDATED_USER")
    protected Integer updatedUser;
    @Column(name = "REMARK")
    protected String remark;
    @Column(name = "APPROVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date approvedDate;
    @Column(name ="APPROVED_USER")
    protected Integer approvedUser;
    @Column(name ="APPROVED_USER_NAME")
    protected String approvedUserName;
    @Column(name ="CREATED_USER_NAME")
    protected String createdUserName;
    @Column(name ="UPDATED_USER_NAME")
    protected String updatedUserName;
    @Column(name = "FLOW_STATUS_ID")
    protected Integer flowStatusId;
    @Column(name = "FLOW_STATUS_NAME")
    protected String flowStatusName;
    @Column(name ="REJECTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date rejectedDate;
    @Column(name ="REJECTED_USER")
    protected Integer rejectedUser;
    @Column(name ="REJECTED_USER_NAME")
    protected String rejectedUserName;
    @Column(name ="CREATED_USER_FULL_NAME")
    private String createdUserFullName;
    @Column(name ="UPDATED_USER_FULL_NAME")
    private String updatedUserFullName;
    @Column(name ="APPROVED_USER_FULL_NAME")
    private String approvedUserFullName;
    @Column(name ="REJECTED_USER_FULL_NAME")
    private String rejectedUserFullName;
    @Column(name ="REPORT_STATUS")
    private Integer reportStatus;
    @Column(name ="USER_GROUP_ID")
    private Integer userGroupId;
    @Column(name ="USER_GROUP_NAME")
    private String userGroupName;
    @Column(name = "USER_GROUP_LVL")
    private Integer userGroupLvl;
    @Column(name = "REPORT_MONTH",updatable = false)
    private String reportMonth;
    @Column(name = "REPORT_YEAR",updatable = false)
    private String reportYear;
    @Column(name = "REPORT_DETAIL_ID", nullable = true)
    private Long reportDetailId;
    @Column(name = "DEP_ID")
    private Long depId;
    @Column(name = "DEP_NAME")
    private String depName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BUDGET_SET")
    private BigDecimal budgetSet;
    @Column(name = "BUDGET_REAL")
    private BigDecimal budgetReal;
    @Column(name = "ACTIVITY_TYPE")
    private String activityType;
    @Column(name = "ACTIVITY_AMOUNT")
    private Long activityAmount;
    @Column(name = "WORK_DETAIL")
    private String workDetail;
    @Column(name = "GOAL_TYPE")
    private String goalType;
    @Basic(optional = false)
    @Column(name = "GOAL_AMOUNT")
    private Long goalAmount;
    @Column(name = "GOAL_RESULT")
    private Long goalResult;
    @Column(name = "RESULT_TYPE")
    private String resultType;
    @Column(name = "RESULT_AMOUNT")
    private String resultAmount;
    @Column(name = "PRACTICE_RESULT")
    private String practiceResult;
    @Column(name = "IS_PASS")
    private Boolean isPass;
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    @Column(name = "ACTIVITY_NAME")
    private String activityName;
    
    @Transient
    private String result;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Integer getApprovedUser() {
        return approvedUser;
    }

    public void setApprovedUser(Integer approvedUser) {
        this.approvedUser = approvedUser;
    }

    public String getApprovedUserName() {
        return approvedUserName;
    }

    public void setApprovedUserName(String approvedUserName) {
        this.approvedUserName = approvedUserName;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    public String getFlowStatusName() {
        return flowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public Integer getRejectedUser() {
        return rejectedUser;
    }

    public void setRejectedUser(Integer rejectedUser) {
        this.rejectedUser = rejectedUser;
    }

    public String getRejectedUserName() {
        return rejectedUserName;
    }

    public void setRejectedUserName(String rejectedUserName) {
        this.rejectedUserName = rejectedUserName;
    }

    public String getCreatedUserFullName() {
        return createdUserFullName;
    }

    public void setCreatedUserFullName(String createdUserFullName) {
        this.createdUserFullName = createdUserFullName;
    }

    public String getUpdatedUserFullName() {
        return updatedUserFullName;
    }

    public void setUpdatedUserFullName(String updatedUserFullName) {
        this.updatedUserFullName = updatedUserFullName;
    }

    public String getApprovedUserFullName() {
        return approvedUserFullName;
    }

    public void setApprovedUserFullName(String approvedUserFullName) {
        this.approvedUserFullName = approvedUserFullName;
    }

    public String getRejectedUserFullName() {
        return rejectedUserFullName;
    }

    public void setRejectedUserFullName(String rejectedUserFullName) {
        this.rejectedUserFullName = rejectedUserFullName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public Integer getUserGroupLvl() {
        return userGroupLvl;
    }

    public void setUserGroupLvl(Integer userGroupLvl) {
        this.userGroupLvl = userGroupLvl;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    public String getReportYear() {
        return reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    public Long getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Long reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public BigDecimal getBudgetSet() {
        return budgetSet;
    }

    public void setBudgetSet(BigDecimal budgetSet) {
        this.budgetSet = budgetSet;
    }

    public BigDecimal getBudgetReal() {
        return budgetReal;
    }

    public void setBudgetReal(BigDecimal budgetReal) {
        this.budgetReal = budgetReal;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Long getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(Long activityAmount) {
        this.activityAmount = activityAmount;
    }

    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public Long getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Long goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Long getGoalResult() {
        return goalResult;
    }

    public void setGoalResult(Long goalResult) {
        this.goalResult = goalResult;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultAmount() {
        return resultAmount;
    }

    public void setResultAmount(String resultAmount) {
        this.resultAmount = resultAmount;
    }

    public String getPracticeResult() {
        return practiceResult;
    }

    public void setPracticeResult(String practiceResult) {
        this.practiceResult = practiceResult;
    }

    public Boolean isIsPass() {
        return isPass;
    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
    }

    /**
     * @return the activityId
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * @param activityId the activityId to set
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * @return the activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName the activityName to set
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public String toString() {
        return "ViewReport001Summary{" + "id=" + id + ", reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", remark=" + remark + ", approvedDate=" + approvedDate + ", approvedUser=" + approvedUser + ", approvedUserName=" + approvedUserName + ", createdUserName=" + createdUserName + ", updatedUserName=" + updatedUserName + ", flowStatusId=" + flowStatusId + ", flowStatusName=" + flowStatusName + ", rejectedDate=" + rejectedDate + ", rejectedUser=" + rejectedUser + ", rejectedUserName=" + rejectedUserName + ", createdUserFullName=" + createdUserFullName + ", updatedUserFullName=" + updatedUserFullName + ", approvedUserFullName=" + approvedUserFullName + ", rejectedUserFullName=" + rejectedUserFullName + ", reportStatus=" + reportStatus + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName + ", userGroupLvl=" + userGroupLvl + ", reportMonth=" + reportMonth + ", reportYear=" + reportYear + ", reportDetailId=" + reportDetailId + ", depId=" + depId + ", depName=" + depName + ", budgetSet=" + budgetSet + ", budgetReal=" + budgetReal + ", activityType=" + activityType + ", activityAmount=" + activityAmount + ", workDetail=" + workDetail + ", goalType=" + goalType + ", goalAmount=" + goalAmount + ", goalResult=" + goalResult + ", resultType=" + resultType + ", resultAmount=" + resultAmount + ", practiceResult=" + practiceResult + ", isPass=" + isPass + ", activityId=" + activityId + ", activityName=" + activityName + '}';
    }

    /**
     * @return the result
     */
    public String getResult() {
        
        if (isPass) {
            result = "ผ่าน";
        } else {
            result = "ไม่ผ่าน";
        }
        
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
}
