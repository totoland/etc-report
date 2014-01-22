/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author totoland
 */
@Entity
public class ViewReport002 extends DomainEntity{
    private static final long serialVersionUID = -1033706928211997379L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Column(name = "REPORT_CODE")
    private String reportCode;
    @Lob
    @Column(name = "REPORT_DESC")
    private String reportDesc;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_USER")
    private Integer createdUser;
    @Column(name = "CREATED_USER_GROUP")
    private Integer createdUserGroup;
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
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    @Lob
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "APPROVED_USER")
    private Integer approvedUser;
    @Column(name = "FLOW_STATUS_ID")
    private Integer flowStatusId;
    @Column(name = "REJECTED_USER")
    private Integer rejectedUser;
    @Column(name = "REJECTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectedDate;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.reportId != null ? this.reportId.hashCode() : 0);
        hash = 97 * hash + (this.reportCode != null ? this.reportCode.hashCode() : 0);
        hash = 97 * hash + (this.reportDesc != null ? this.reportDesc.hashCode() : 0);
        hash = 97 * hash + (this.createdDate != null ? this.createdDate.hashCode() : 0);
        hash = 97 * hash + (this.createdUser != null ? this.createdUser.hashCode() : 0);
        hash = 97 * hash + (this.createdUserGroup != null ? this.createdUserGroup.hashCode() : 0);
        hash = 97 * hash + (this.updatedDate != null ? this.updatedDate.hashCode() : 0);
        hash = 97 * hash + (this.updatedUser != null ? this.updatedUser.hashCode() : 0);
        hash = 97 * hash + (this.strategicId != null ? this.strategicId.hashCode() : 0);
        hash = 97 * hash + (this.subStrategicId != null ? this.subStrategicId.hashCode() : 0);
        hash = 97 * hash + (this.planId != null ? this.planId.hashCode() : 0);
        hash = 97 * hash + (this.projectId != null ? this.projectId.hashCode() : 0);
        hash = 97 * hash + (this.activityId != null ? this.activityId.hashCode() : 0);
        hash = 97 * hash + (this.remark != null ? this.remark.hashCode() : 0);
        hash = 97 * hash + (this.approvedUser != null ? this.approvedUser.hashCode() : 0);
        hash = 97 * hash + (this.flowStatusId != null ? this.flowStatusId.hashCode() : 0);
        hash = 97 * hash + (this.rejectedUser != null ? this.rejectedUser.hashCode() : 0);
        hash = 97 * hash + (this.rejectedDate != null ? this.rejectedDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewReport002 other = (ViewReport002) obj;
        if (this.reportId != other.reportId && (this.reportId == null || !this.reportId.equals(other.reportId))) {
            return false;
        }
        if ((this.reportCode == null) ? (other.reportCode != null) : !this.reportCode.equals(other.reportCode)) {
            return false;
        }
        if ((this.reportDesc == null) ? (other.reportDesc != null) : !this.reportDesc.equals(other.reportDesc)) {
            return false;
        }
        if (this.createdDate != other.createdDate && (this.createdDate == null || !this.createdDate.equals(other.createdDate))) {
            return false;
        }
        if (this.createdUser != other.createdUser && (this.createdUser == null || !this.createdUser.equals(other.createdUser))) {
            return false;
        }
        if (this.createdUserGroup != other.createdUserGroup && (this.createdUserGroup == null || !this.createdUserGroup.equals(other.createdUserGroup))) {
            return false;
        }
        if (this.updatedDate != other.updatedDate && (this.updatedDate == null || !this.updatedDate.equals(other.updatedDate))) {
            return false;
        }
        if (this.updatedUser != other.updatedUser && (this.updatedUser == null || !this.updatedUser.equals(other.updatedUser))) {
            return false;
        }
        if (this.strategicId != other.strategicId && (this.strategicId == null || !this.strategicId.equals(other.strategicId))) {
            return false;
        }
        if (this.subStrategicId != other.subStrategicId && (this.subStrategicId == null || !this.subStrategicId.equals(other.subStrategicId))) {
            return false;
        }
        if (this.planId != other.planId && (this.planId == null || !this.planId.equals(other.planId))) {
            return false;
        }
        if (this.projectId != other.projectId && (this.projectId == null || !this.projectId.equals(other.projectId))) {
            return false;
        }
        if (this.activityId != other.activityId && (this.activityId == null || !this.activityId.equals(other.activityId))) {
            return false;
        }
        if ((this.remark == null) ? (other.remark != null) : !this.remark.equals(other.remark)) {
            return false;
        }
        if (this.approvedUser != other.approvedUser && (this.approvedUser == null || !this.approvedUser.equals(other.approvedUser))) {
            return false;
        }
        if (this.flowStatusId != other.flowStatusId && (this.flowStatusId == null || !this.flowStatusId.equals(other.flowStatusId))) {
            return false;
        }
        if (this.rejectedUser != other.rejectedUser && (this.rejectedUser == null || !this.rejectedUser.equals(other.rejectedUser))) {
            return false;
        }
        if (this.rejectedDate != other.rejectedDate && (this.rejectedDate == null || !this.rejectedDate.equals(other.rejectedDate))) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the reportId
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * @return the reportCode
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * @param reportCode the reportCode to set
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * @return the reportDesc
     */
    public String getReportDesc() {
        return reportDesc;
    }

    /**
     * @param reportDesc the reportDesc to set
     */
    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the createdUser
     */
    public Integer getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
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

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return the updatedUser
     */
    public Integer getUpdatedUser() {
        return updatedUser;
    }

    /**
     * @param updatedUser the updatedUser to set
     */
    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * @return the strategicId
     */
    public Integer getStrategicId() {
        return strategicId;
    }

    /**
     * @param strategicId the strategicId to set
     */
    public void setStrategicId(Integer strategicId) {
        this.strategicId = strategicId;
    }

    /**
     * @return the subStrategicId
     */
    public Integer getSubStrategicId() {
        return subStrategicId;
    }

    /**
     * @param subStrategicId the subStrategicId to set
     */
    public void setSubStrategicId(Integer subStrategicId) {
        this.subStrategicId = subStrategicId;
    }

    /**
     * @return the planId
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the approvedUser
     */
    public Integer getApprovedUser() {
        return approvedUser;
    }

    /**
     * @param approvedUser the approvedUser to set
     */
    public void setApprovedUser(Integer approvedUser) {
        this.approvedUser = approvedUser;
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
     * @return the rejectedUser
     */
    public Integer getRejectedUser() {
        return rejectedUser;
    }

    /**
     * @param rejectedUser the rejectedUser to set
     */
    public void setRejectedUser(Integer rejectedUser) {
        this.rejectedUser = rejectedUser;
    }

    /**
     * @return the rejectedDate
     */
    public Date getRejectedDate() {
        return rejectedDate;
    }

    /**
     * @param rejectedDate the rejectedDate to set
     */
    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    @Override
    public String toString() {
        return "ViewReport002{" + "reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", createdUserGroup=" + createdUserGroup + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", strategicId=" + strategicId + ", subStrategicId=" + subStrategicId + ", planId=" + planId + ", projectId=" + projectId + ", activityId=" + activityId + ", remark=" + remark + ", approvedUser=" + approvedUser + ", flowStatusId=" + flowStatusId + ", rejectedUser=" + rejectedUser + ", rejectedDate=" + rejectedDate + '}';
    }
    
}
