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
@Table(name = "REPORT_003_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report003Detail.findAll", query = "SELECT r FROM Report003Detail r")})
public class Report003Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 5767733392586811816L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "DOC_RECEIVE")
    private String docReceive;
    @Column(name = "DOC_RECEIVE_AMOUNT")
    private Integer docReceiveAmount;
    @Column(name = "DOC_SEND")
    private String docSend;
    @Column(name = "DOC_SEND_AMOUNT")
    private Integer docSendAmount;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report003 reportId;

    public Report003Detail() {
    }

    public Report003Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getDocReceive() {
        return docReceive;
    }

    public void setDocReceive(String docReceive) {
        this.docReceive = docReceive;
    }

    public Integer getDocReceiveAmount() {
        return docReceiveAmount;
    }

    public void setDocReceiveAmount(Integer docReceiveAmount) {
        this.docReceiveAmount = docReceiveAmount;
    }

    public String getDocSend() {
        return docSend;
    }

    public void setDocSend(String docSend) {
        this.docSend = docSend;
    }

    public Integer getDocSendAmount() {
        return docSendAmount;
    }

    public void setDocSendAmount(Integer docSendAmount) {
        this.docSendAmount = docSendAmount;
    }

    public Report003 getReportId() {
        return reportId;
    }

    public void setReportId(Report003 reportId) {
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
        if (!(object instanceof Report003Detail)) {
            return false;
        }
        Report003Detail other = (Report003Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report003Detail{" + "reportDetailId=" + reportDetailId + ", docReceive=" + docReceive + ", docReceiveAmount=" + docReceiveAmount + ", docSend=" + docSend + ", docSendAmount=" + docSendAmount + '}';
    }
    
}
