/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_001_DETAIL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"REPORT_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report001Detail.findAll", query = "SELECT r FROM Report001Detail r")})
public class Report001Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID", nullable = false)
    private Integer reportDetailId;
    @Column(name = "REPORT_CODE", length = 50)
    private String reportCode;
    @Column(name = "DEP_ID")
    private Integer depId;
    @Column(name = "DEP_NAME")
    private String depName;
    @Column(name = "ACTIVITY_NAME")
    private String activityName;
    @Column(name = "ACTIVITY_TYPE", length = 2147483647)
    private String activityType;
    @Column(name = "ACTIVITY_AMOUNT")
    private Integer activityAmount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BUDGET_SET", precision = 18, scale = 2)
    private BigDecimal budgetSet;
    @Column(name = "BUDGET_REAL", precision = 18, scale = 2)
    private BigDecimal budgetReal;
    @Column(name = "IS_PASS")
    private Boolean isPass;
    @OneToMany(mappedBy = "reportCode")
    private List<Report001> report001List;

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

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    @XmlTransient
    public List<Report001> getReport001List() {
        return report001List;
    }

    public void setReport001List(List<Report001> report001List) {
        this.report001List = report001List;
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

    /**
     * @return the depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName the depName to set
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }
    
}
