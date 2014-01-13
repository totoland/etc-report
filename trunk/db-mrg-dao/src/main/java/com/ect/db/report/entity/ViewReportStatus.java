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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author totoland
 */
@Entity
public class ViewReportStatus extends DomainEntity implements Serializable{
    private static final long serialVersionUID = -2485952911233303194L;
     
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
    
    @Transient
    protected String documentNo;

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
     * @return the approvedUserName
     */
    public String getApprovedUserName() {
        return approvedUserName;
    }

    /**
     * @param approvedUserName the approvedUserName to set
     */
    public void setApprovedUserName(String approvedUserName) {
        this.approvedUserName = approvedUserName;
    }

    /**
     * @return the createdUserName
     */
    public String getCreatedUserName() {
        return createdUserName;
    }

    /**
     * @param createdUserName the createdUserName to set
     */
    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    /**
     * @return the updatedUserName
     */
    public String getUpdatedUserName() {
        return updatedUserName;
    }

    /**
     * @param updatedUserName the updatedUserName to set
     */
    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
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
     * @return the flowStatusName
     */
    public String getFlowStatusName() {
        return flowStatusName;
    }

    /**
     * @param flowStatusName the flowStatusName to set
     */
    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.reportId != null ? this.reportId.hashCode() : 0);
        hash = 83 * hash + (this.reportCode != null ? this.reportCode.hashCode() : 0);
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
        final ViewReportStatus other = (ViewReportStatus) obj;
        if (this.reportId != other.reportId && (this.reportId == null || !this.reportId.equals(other.reportId))) {
            return false;
        }
        if ((this.reportCode == null) ? (other.reportCode != null) : !this.reportCode.equals(other.reportCode)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the documentNo
     */
    public String getDocumentNo() {
        this.documentNo = this.reportCode.replace("REPORT_", "")+"-"+this.reportId;
        return documentNo;
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
     * @return the rejectedUserName
     */
    public String getRejectedUserName() {
        return rejectedUserName;
    }

    /**
     * @param rejectedUserName the rejectedUserName to set
     */
    public void setRejectedUserName(String rejectedUserName) {
        this.rejectedUserName = rejectedUserName;
    }

    
}
