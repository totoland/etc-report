/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_001_DETAIL")
@NamedQueries({
    @NamedQuery(name = "Report001Detail.findAll", query = "SELECT r FROM Report001Detail r")})
public class Report001Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 3520311716879656962L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_DETAIL_ID",nullable = true)
    private Integer reportDetailId;
    @Column(name = "DEP_ID")
    private Integer depId;
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
    private Integer activityAmount;
    @Column(name = "WORK_DETAIL")
    private String workDetail;
    @Column(name = "GOAL_TYPE")
    private String goalType;
    @Basic(optional = false)
    @Column(name = "GOAL_AMOUNT")
    private Integer goalAmount;
    @Column(name = "GOAL_RESULT")
    private Integer goalResult;
    @Column(name = "RESULT_TYPE")
    private String resultType;
    @Column(name = "RESULT_AMOUNT")
    private Integer resultAmount;
    @Column(name = "PRACTICE_RESULT")
    private Integer practiceResult;
    @Column(name = "IS_PASS")
    private Boolean isPass;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne(targetEntity=Report001.class,fetch = FetchType.EAGER)
    private Report001 reportId;

    public Report001Detail() {
    }

    public Report001Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(Integer activityAmount) {
        this.activityAmount = activityAmount;
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

    public Boolean getIsPass() {
        return isPass;
    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
    }

    public Report001 getReportId() {
        return reportId;
    }

    public void setReportId(Report001 reportId) {
        this.reportId = reportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportDetailId != null ? reportDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report001Detail)) {
            return false;
        }
        Report001Detail other = (Report001Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
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

    public Integer getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Integer goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Integer getResultAmount() {
        return resultAmount;
    }

    public void setResultAmount(Integer resultAmount) {
        this.resultAmount = resultAmount;
    }

    /**
     * @return the goalResult
     */
    public Integer getGoalResult() {
        return goalResult;
    }

    /**
     * @param goalResult the goalResult to set
     */
    public void setGoalResult(Integer goalResult) {
        this.goalResult = goalResult;
    }

    /**
     * @return the practiceResult
     */
    public Integer getPracticeResult() {
        return practiceResult;
    }

    /**
     * @param practiceResult the practiceResult to set
     */
    public void setPracticeResult(Integer practiceResult) {
        this.practiceResult = practiceResult;
    }

    @Override
    public String toString() {
        return "Report001Detail{" + "reportDetailId=" + reportDetailId + ", depId=" + depId + ", depName=" + depName + ", budgetSet=" + budgetSet + ", budgetReal=" + budgetReal + ", activityType=" + activityType + ", activityAmount=" + activityAmount + ", workDetail=" + workDetail + ", goalType=" + goalType + ", goalAmount=" + goalAmount + ", goalResult=" + goalResult + ", resultType=" + resultType + ", resultAmount=" + resultAmount + ", practiceResult=" + practiceResult + ", isPass=" + isPass + '}';
    }
    
}
