/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author totoland
 */
@Entity
public class ViewReport001 extends DomainEntity implements Serializable{
    private static final long serialVersionUID = 5310115700864566904L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "REPORT_ID",nullable = true)
    private Integer reportId;
    @Column(name = "REPORT_CODE")
    private String reportCode;
    @Lob
    @Column(name = "REPORT_DESC")
    private String reportDesc;
    @Column(name = "CREATED_DATE",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_USER",updatable = false)
    private Integer createdUser;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "STRATEGIC_ID")
    private Integer strategicId;
    @Column(name = "SUB_STRATEGIC_ID")
    private Integer subStrategicId;
    @Column(name = "PLAN_ID")
    private Integer planId;
    @Column(name = "PROJECT_ID")
    private Integer projectId;
    @Lob
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "APPROVED_USER")
    private Integer approvedUser;
    @Column(name = "FLOW_STATUS_ID")
    private Integer flowStatusId;

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

    public Integer getStrategicId() {
        return strategicId;
    }

    public void setStrategicId(Integer strategicId) {
        this.strategicId = strategicId;
    }

    public Integer getSubStrategicId() {
        return subStrategicId;
    }

    public void setSubStrategicId(Integer subStrategicId) {
        this.subStrategicId = subStrategicId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getApprovedUser() {
        return approvedUser;
    }

    public void setApprovedUser(Integer approvedUser) {
        this.approvedUser = approvedUser;
    }

    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }
    
    @Override
    public String toString() {
        return "ViewReport001{" + "reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", strategicId=" + strategicId + ", subStrategicId=" + subStrategicId + ", planId=" + planId + ", projectId=" + projectId + ", remark=" + remark + ", approvedUser=" + approvedUser + ", flowStatusId=" + flowStatusId + '}';
    }
}
