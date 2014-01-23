/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_001")
@NamedQueries({
    @NamedQuery(name = "Report001.findAll", query = "SELECT r FROM Report001 r")})
public class Report001 extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -4203970495584892627L;
     
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
    @Column(name = "CREATED_USER_GROUP")
    private Integer createdUserGroup;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "APPROVED_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date approvedDate;
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
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    @Column(name = "REJECTED_USER")
    private Integer rejectedUser;
    @Column(name = "REJECTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectedDate;
    @Column(name = "REPORT_STATUS")
    private Integer reportStatus;
    
    @OneToMany(mappedBy = "reportId",cascade={CascadeType.ALL},targetEntity = Report001Detail.class,fetch = FetchType.EAGER)
    private List<Report001Detail> report001DetailList;

    public Report001() {
    }

    public Report001(Integer reportId) {
        this.reportId = reportId;
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

    public List<Report001Detail> getReport001DetailList() {
        return report001DetailList;
    }

    public void setReport001DetailList(List<Report001Detail> report001DetailList) {
        this.report001DetailList = report001DetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report001)) {
            return false;
        }
        Report001 other = (Report001) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    /**
     * @return the flowStatusId
     */
    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    /**
     * @param flowStatusId the flowStatusId to set
     */
    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    /**
     * @return the approvedDate
     */
    public Date getApprovedDate() {
        return approvedDate;
    }

    /**
     * @param approvedDate the approvedDate to set
     */
    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getRejectedUser() {
        return rejectedUser;
    }

    public void setRejectedUser(Integer rejectedUser) {
        this.rejectedUser = rejectedUser;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }
    
    /**
     * @return the createdUserGroup
     */
    public Integer getCreatedUserGroup() {
        return createdUserGroup;
    }

    /**
     * @param createdUserGroup the createdUserGroup to set
     */
    public void setCreatedUserGroup(Integer createdUserGroup) {
        this.createdUserGroup = createdUserGroup;
    }

    @Override
    public String toString() {
        return "Report001{" + "reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", createdUserGroup=" + createdUserGroup + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", approvedDate=" + approvedDate + ", strategicId=" + strategicId + ", subStrategicId=" + subStrategicId + ", planId=" + planId + ", projectId=" + projectId + ", remark=" + remark + ", approvedUser=" + approvedUser + ", flowStatusId=" + flowStatusId + ", activityId=" + activityId + ", rejectedUser=" + rejectedUser + ", rejectedDate=" + rejectedDate + ", reportStatus=" + reportStatus + ", report001DetailList=" + report001DetailList + '}';
    }

    /**
     * @return the reportStatus
     */
    public Integer getReportStatus() {
        return reportStatus;
    }

    /**
     * @param reportStatus the reportStatus to set
     */
    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }
    
}
