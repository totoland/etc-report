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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_008_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report008Detail.findAll", query = "SELECT r FROM Report008Detail r")})
public class Report008Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -1375980029077139635L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "POLITICAL_PARTY")
    private String politicalParty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BUDGET")
    private BigDecimal budget;
    @Column(name = "DISBURSED_PREVIOUS")
    private BigDecimal disbursedPrevious;
    @Column(name = "DISBURSED_MONTH")
    private BigDecimal disbursedMonth;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report008 reportId;

    public Report008Detail() {
    }

    public Report008Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getDisbursedPrevious() {
        return disbursedPrevious;
    }

    public void setDisbursedPrevious(BigDecimal disbursedPrevious) {
        this.disbursedPrevious = disbursedPrevious;
    }

    public BigDecimal getDisbursedMonth() {
        return disbursedMonth;
    }

    public void setDisbursedMonth(BigDecimal disbursedMonth) {
        this.disbursedMonth = disbursedMonth;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Report008 getReportId() {
        return reportId;
    }

    public void setReportId(Report008 reportId) {
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
        if (!(object instanceof Report008Detail)) {
            return false;
        }
        Report008Detail other = (Report008Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report008Detail{" + "reportDetailId=" + reportDetailId + ", politicalParty=" + politicalParty + ", budget=" + budget + ", disbursedPrevious=" + disbursedPrevious + ", disbursedMonth=" + disbursedMonth + ", balance=" + balance + '}';
    }
    
}
