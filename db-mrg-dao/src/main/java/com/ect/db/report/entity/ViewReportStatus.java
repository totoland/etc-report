/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
        this.documentNo = loadMap().get(this.reportCode)+"-"+userGroupId+"-"+this.reportId+"-"+this.reportMonth+"-"+this.reportYear;
        return documentNo;
    }

    @Transient
    private Map<String,String> map;
    
    private Map<String,String> loadMap(){
        
        if(map == null){
            map = new HashMap();
        }
        
        map.put("REPORT_001", "1.1");
        map.put("REPORT_002", "1.2");
        map.put("REPORT_003", "1.3");
        map.put("REPORT_004", "2.1");
        map.put("REPORT_005", "2.2");
        map.put("REPORT_006", "2.3");
        map.put("REPORT_007", "3.1");
        map.put("REPORT_008", "3.2");
        map.put("REPORT_009", "3.3");
        map.put("REPORT_010", "3.4");
        map.put("REPORT_011", "4.1");
        map.put("REPORT_012", "4.2");
        map.put("REPORT_013", "4.3");
        map.put("REPORT_014", "4.4");
        map.put("REPORT_015", "4.5");
        map.put("REPORT_016", "4.6");
        map.put("REPORT_017", "4.7");
        map.put("REPORT_018", "5.1");
        map.put("REPORT_019", "5.2");
        map.put("REPORT_020", "6");
        map.put("REPORT_021", "7");
        map.put("REPORT_022", "8");
        map.put("REPORT_023", "9");
        
        return map;
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

    /**
     * @return the createdUserFullName
     */
    public String getCreatedUserFullName() {
        return createdUserFullName;
    }

    /**
     * @param createdUserFullName the createdUserFullName to set
     */
    public void setCreatedUserFullName(String createdUserFullName) {
        this.createdUserFullName = createdUserFullName;
    }

    /**
     * @return the updatedUserFullName
     */
    public String getUpdatedUserFullName() {
        return updatedUserFullName;
    }

    /**
     * @param updatedUserFullName the updatedUserFullName to set
     */
    public void setUpdatedUserFullName(String updatedUserFullName) {
        this.updatedUserFullName = updatedUserFullName;
    }

    /**
     * @return the approvedUserFullName
     */
    public String getApprovedUserFullName() {
        return approvedUserFullName;
    }

    /**
     * @param approvedUserFullName the approvedUserFullName to set
     */
    public void setApprovedUserFullName(String approvedUserFullName) {
        this.approvedUserFullName = approvedUserFullName;
    }

    /**
     * @return the rejectedUserFullName
     */
    public String getRejectedUserFullName() {
        return rejectedUserFullName;
    }

    /**
     * @param rejectedUserFullName the rejectedUserFullName to set
     */
    public void setRejectedUserFullName(String rejectedUserFullName) {
        this.rejectedUserFullName = rejectedUserFullName;
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

    /**
     * @return the userGroupId
     */
    public Integer getUserGroupId() {
        return userGroupId;
    }

    /**
     * @param userGroupId the userGroupId to set
     */
    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * @return the userGroupLvl
     */
    public Integer getUserGroupLvl() {
        return userGroupLvl;
    }

    /**
     * @param userGroupLvl the userGroupLvl to set
     */
    public void setUserGroupLvl(Integer userGroupLvl) {
        this.userGroupLvl = userGroupLvl;
    }
    
    /**
     * @return the reportMonth
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth the reportMonth to set
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return the reportYear
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear the reportYear to set
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return the userGroupName
     */
    public String getUserGroupName() {
        return userGroupName;
    }

    /**
     * @param userGroupName the userGroupName to set
     */
    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    @Override
    public String toString() {
        return "ViewReportStatus{" + "id=" + id + ", reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", remark=" + remark + ", approvedDate=" + approvedDate + ", approvedUser=" + approvedUser + ", approvedUserName=" + approvedUserName + ", createdUserName=" + createdUserName + ", updatedUserName=" + updatedUserName + ", flowStatusId=" + flowStatusId + ", flowStatusName=" + flowStatusName + ", rejectedDate=" + rejectedDate + ", rejectedUser=" + rejectedUser + ", rejectedUserName=" + rejectedUserName + ", createdUserFullName=" + createdUserFullName + ", updatedUserFullName=" + updatedUserFullName + ", approvedUserFullName=" + approvedUserFullName + ", rejectedUserFullName=" + rejectedUserFullName + ", reportStatus=" + reportStatus + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName + ", userGroupLvl=" + userGroupLvl + ", reportMonth=" + reportMonth + ", reportYear=" + reportYear + ", documentNo=" + documentNo + '}';
    }
    
}
