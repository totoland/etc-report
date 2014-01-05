/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report001.findAll", query = "SELECT r FROM Report001 r")})
public class Report001 extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_ID", nullable = false)
    private Integer reportId;
    @Lob
    @Column(name = "REPORT_DESC", length = 2147483647)
    private String reportDesc;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_USER")
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
    @Column(name = "REMARK", length = 2147483647)
    private String remark;
    @Column(name = "APPROVED_USER")
    private Integer approvedUser;
    @JoinColumn(name = "REPORT_CODE", referencedColumnName = "REPORT_CODE")
    @ManyToOne
    private Report001Detail reportCode;

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

    public Report001Detail getReportCode() {
        return reportCode;
    }

    public void setReportCode(Report001Detail reportCode) {
        this.reportCode = reportCode;
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
    
}
