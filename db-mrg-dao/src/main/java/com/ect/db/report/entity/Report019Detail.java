/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "REPORT_019_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report019Detail.findAll", query = "SELECT r FROM Report019Detail r")})
public class Report019Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -181302870124468078L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "VERIFY_ELECTION")
    private String verifyElection;
    @Column(name = "VERIFY_ELECTION_AMOUNT")
    private Integer verifyElectionAmount;
    @Column(name = "FULL_TERM")
    private Integer fullTerm;
    @Column(name = "NEW_ELECTION")
    private Integer newElection;
    @Column(name = "REPLACE_EMPLY_POSITION")
    private Integer replaceEmplyPosition;
    @Column(name = "REPORT_LAAS")
    private Integer reportLaas;
    @Column(name = "NO_REPORT_LAAS")
    private Integer noReportLaas;
    @Column(name = "EXTEND_RESULT")
    private Integer extendResult;
    @Column(name = "NO_EXTEND_RESULT")
    private Integer noExtendResult;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report019 reportId;

    public Report019Detail() {
    }

    public Report019Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getVerifyElection() {
        return verifyElection;
    }

    public void setVerifyElection(String verifyElection) {
        this.verifyElection = verifyElection;
    }

    public Integer getVerifyElectionAmount() {
        return verifyElectionAmount;
    }

    public void setVerifyElectionAmount(Integer verifyElectionAmount) {
        this.verifyElectionAmount = verifyElectionAmount;
    }

    public Integer getFullTerm() {
        return fullTerm;
    }

    public void setFullTerm(Integer fullTerm) {
        this.fullTerm = fullTerm;
    }

    public Integer getNewElection() {
        return newElection;
    }

    public void setNewElection(Integer newElection) {
        this.newElection = newElection;
    }

    public Integer getReplaceEmplyPosition() {
        return replaceEmplyPosition;
    }

    public void setReplaceEmplyPosition(Integer replaceEmplyPosition) {
        this.replaceEmplyPosition = replaceEmplyPosition;
    }

    public Integer getReportLaas() {
        return reportLaas;
    }

    public void setReportLaas(Integer reportLaas) {
        this.reportLaas = reportLaas;
    }

    public Integer getNoReportLaas() {
        return noReportLaas;
    }

    public void setNoReportLaas(Integer noReportLaas) {
        this.noReportLaas = noReportLaas;
    }

    public Integer getExtendResult() {
        return extendResult;
    }

    public void setExtendResult(Integer extendResult) {
        this.extendResult = extendResult;
    }

    public Integer getNoExtendResult() {
        return noExtendResult;
    }

    public void setNoExtendResult(Integer noExtendResult) {
        this.noExtendResult = noExtendResult;
    }

    public Report019 getReportId() {
        return reportId;
    }

    public void setReportId(Report019 reportId) {
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
        if (!(object instanceof Report019Detail)) {
            return false;
        }
        Report019Detail other = (Report019Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report019Detail{" + "reportDetailId=" + reportDetailId + ", verifyElection=" + verifyElection + ", verifyElectionAmount=" + verifyElectionAmount + ", fullTerm=" + fullTerm + ", newElection=" + newElection + ", replaceEmplyPosition=" + replaceEmplyPosition + ", reportLaas=" + reportLaas + ", noReportLaas=" + noReportLaas + ", extendResult=" + extendResult + ", noExtendResult=" + noExtendResult + '}';
    }
    
}
