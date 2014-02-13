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
@Table(name = "REPORT_013_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report013Detail.findAll", query = "SELECT r FROM Report013Detail r")})
public class Report013Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 7031512176036482731L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "INSTITUTION")
    private String institution;
    @Column(name = "DECISION_TO_PREPARE")
    private Integer decisionToPrepare;
    @Column(name = "ON_PROCESS")
    private Integer onProcess;
    @Column(name = "COMPLETE")
    private Integer complete;
    @Column(name = "ECT_SIGNED_ONPROCESS")
    private Integer ectSignedOnprocess;
    @Column(name = "ECT_SIGNED_COMPLEE")
    private Integer ectSignedComplee;
    @Column(name = "SENDED")
    private Integer sended;
    @Column(name = "NO_SEND")
    private Integer noSend;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report013 reportId;

    public Report013Detail() {
    }

    public Report013Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getDecisionToPrepare() {
        return decisionToPrepare;
    }

    public void setDecisionToPrepare(Integer decisionToPrepare) {
        this.decisionToPrepare = decisionToPrepare;
    }

    public Integer getOnProcess() {
        return onProcess;
    }

    public void setOnProcess(Integer onProcess) {
        this.onProcess = onProcess;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getEctSignedOnprocess() {
        return ectSignedOnprocess;
    }

    public void setEctSignedOnprocess(Integer ectSignedOnprocess) {
        this.ectSignedOnprocess = ectSignedOnprocess;
    }

    public Integer getEctSignedComplee() {
        return ectSignedComplee;
    }

    public void setEctSignedComplee(Integer ectSignedComplee) {
        this.ectSignedComplee = ectSignedComplee;
    }

    public Integer getSended() {
        return sended;
    }

    public void setSended(Integer sended) {
        this.sended = sended;
    }

    public Integer getNoSend() {
        return noSend;
    }

    public void setNoSend(Integer noSend) {
        this.noSend = noSend;
    }

    public Report013 getReportId() {
        return reportId;
    }

    public void setReportId(Report013 reportId) {
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
        if (!(object instanceof Report013Detail)) {
            return false;
        }
        Report013Detail other = (Report013Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report013Detail{" + "reportDetailId=" + reportDetailId + ", institution=" + institution + ", decisionToPrepare=" + decisionToPrepare + ", onProcess=" + onProcess + ", complete=" + complete + ", ectSignedOnprocess=" + ectSignedOnprocess + ", ectSignedComplee=" + ectSignedComplee + ", sended=" + sended + ", noSend=" + noSend + '}';
    }
    
}
