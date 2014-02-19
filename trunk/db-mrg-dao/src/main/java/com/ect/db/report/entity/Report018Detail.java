/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
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
@Table(name = "REPORT_018_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report018Detail.findAll", query = "SELECT r FROM Report018Detail r")})
public class Report018Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -5425338020951232600L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "ECT_DEP_PROVINCE")
    private Integer ectDepProvince;
    @Column(name = "OOC_AMOUNT")
    private Integer oocAmount;
    @Column(name = "LAAS")
    private String laas;
    @Column(name = "LAAS_AMOUNT")
    private Integer laasAmount;
    @Column(name = "FULL_TERM")
    private Integer fullTerm;
    @Column(name = "BUDGET_FULL_TERM")
    private Integer budgetFullTerm;
    @Column(name = "NEW_ELECTION")
    private Integer newElection;
    @Column(name = "BUDGET_ELECTION")
    private Integer budgetElection;
    @Column(name = "REPLACE_EMPLY_POSITION")
    private Integer replaceEmplyPosition;
    @Column(name = "REPLACE_BUDGET")
    private Integer replaceBudget;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report018 reportId;

    public Report018Detail() {
    }

    public Report018Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getEctDepProvince() {
        return ectDepProvince;
    }

    public void setEctDepProvince(Integer ectDepProvince) {
        this.ectDepProvince = ectDepProvince;
    }

    public Integer getOocAmount() {
        return oocAmount;
    }

    public void setOocAmount(Integer oocAmount) {
        this.oocAmount = oocAmount;
    }

    public String getLaas() {
        return laas;
    }

    public void setLaas(String laas) {
        this.laas = laas;
    }

    public Integer getLaasAmount() {
        return laasAmount;
    }

    public void setLaasAmount(Integer laasAmount) {
        this.laasAmount = laasAmount;
    }

    public Integer getFullTerm() {
        return fullTerm;
    }

    public void setFullTerm(Integer fullTerm) {
        this.fullTerm = fullTerm;
    }

    public Integer getBudgetFullTerm() {
        return budgetFullTerm;
    }

    public void setBudgetFullTerm(Integer budgetFullTerm) {
        this.budgetFullTerm = budgetFullTerm;
    }

    public Integer getNewElection() {
        return newElection;
    }

    public void setNewElection(Integer newElection) {
        this.newElection = newElection;
    }

    public Integer getBudgetElection() {
        return budgetElection;
    }

    public void setBudgetElection(Integer budgetElection) {
        this.budgetElection = budgetElection;
    }

    public Integer getReplaceEmplyPosition() {
        return replaceEmplyPosition;
    }

    public void setReplaceEmplyPosition(Integer replaceEmplyPosition) {
        this.replaceEmplyPosition = replaceEmplyPosition;
    }

    public Integer getReplaceBudget() {
        return replaceBudget;
    }

    public void setReplaceBudget(Integer replaceBudget) {
        this.replaceBudget = replaceBudget;
    }

    public Report018 getReportId() {
        return reportId;
    }

    public void setReportId(Report018 reportId) {
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
        if (!(object instanceof Report018Detail)) {
            return false;
        }
        Report018Detail other = (Report018Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report018Detail{" + "reportDetailId=" + reportDetailId + ", ectDepProvince=" + ectDepProvince + ", oocAmount=" + oocAmount + ", laas=" + laas + ", laasAmount=" + laasAmount + ", fullTerm=" + fullTerm + ", budgetFullTerm=" + budgetFullTerm + ", newElection=" + newElection + ", budgetElection=" + budgetElection + ", replaceEmplyPosition=" + replaceEmplyPosition + ", replaceBudget=" + replaceBudget + '}';
    }
    
}
