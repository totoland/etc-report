/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jirawat.l
 */
@Entity
@Table(name = "REPORT_022")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report022.findAll", query = "SELECT r FROM Report022 r")})
public class Report022 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Column(name = "REPORT_CODE")
    private String reportCode;
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
    @Column(name = "FLOW_STATUS_ID")
    private Integer flowStatusId;
    @Column(name = "REPORT_STATUS")
    private Integer reportStatus;
    @Lob
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "REJECTED_USER")
    private Integer rejectedUser;
    @Column(name = "REJECTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectedDate;
    @Column(name = "APPROVED_USER")
    private Integer approvedUser;
    @Column(name = "APPROVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;
    @OneToMany(mappedBy = "reportId")
    private List<Report022Detail> report022DetailList;

    public Report022() {
    }

    public Report022(Integer reportId) {
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

    public Integer getCreatedUserGroup() {
        return createdUserGroup;
    }

    public void setCreatedUserGroup(Integer createdUserGroup) {
        this.createdUserGroup = createdUserGroup;
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

    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getApprovedUser() {
        return approvedUser;
    }

    public void setApprovedUser(Integer approvedUser) {
        this.approvedUser = approvedUser;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    @XmlTransient
    public List<Report022Detail> getReport022DetailList() {
        return report022DetailList;
    }

    public void setReport022DetailList(List<Report022Detail> report022DetailList) {
        this.report022DetailList = report022DetailList;
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
        if (!(object instanceof Report022)) {
            return false;
        }
        Report022 other = (Report022) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.report.entity.Report022[ reportId=" + reportId + " ]";
    }
    
}
