/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_012")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report012.findAll", query = "SELECT r FROM Report012 r")})
public class Report012 extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -6495687765725061233L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "reportId",cascade = CascadeType.ALL,targetEntity = Report012Detail.class,fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Report012Detail> report012DetailList;

    public Report012() {
    }

    public Report012(Integer reportId) {
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
    public List<Report012Detail> getReport012DetailList() {
        return report012DetailList;
    }

    public void setReport012DetailList(List<Report012Detail> report012DetailList) {
        this.report012DetailList = report012DetailList;
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
        if (!(object instanceof Report012)) {
            return false;
        }
        Report012 other = (Report012) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report012{" + "reportId=" + reportId + ", reportCode=" + reportCode + ", reportDesc=" + reportDesc + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", createdUserGroup=" + createdUserGroup + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", flowStatusId=" + flowStatusId + ", reportStatus=" + reportStatus + ", remark=" + remark + ", rejectedUser=" + rejectedUser + ", rejectedDate=" + rejectedDate + ", approvedUser=" + approvedUser + ", approvedDate=" + approvedDate + ", report012DetailList=" + report012DetailList + '}';
    }
    
}
