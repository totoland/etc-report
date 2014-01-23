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
@Table(name = "REPORT_004_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report004Detail.findAll", query = "SELECT r FROM Report004Detail r")})
public class Report004Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -8234617210544028581L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name ="DLA_TYPE")
    private String dlaType;
    @Column(name = "FULL_TERM")
    private Integer fullTerm;
    @Column(name = "ELECTION_BEFORE_ANNOUNCEMENT")
    private Integer electionBeforeAnnouncement;
    @Column(name = "ELECTION_FILL_VACANCY")
    private Integer electionFillVacancy;
    @Column(name = "AMOUNT_S_TH")
    private Integer amountSTh;
    @Column(name = "AMOUNT_PH_TH")
    private Integer amountPhTh;
    @Column(name = "ELECTION_EARLIER_LAST_MONTH_S_TH")
    private Integer electionEarlierLastMonthSTh;
    @Column(name = "ELECTION_EARLIER_LAST_MONTH_PH_TH")
    private Integer electionEarlierLastMonthPhTh;
    @Column(name = "ELECTION_EARLIER_CUR_MONTH_S_TH")
    private Integer electionEarlierCurMonthSTh;
    @Column(name = "ELECTION_EARLIER_CUR_MONTH_PH_TH")
    private Integer electionEarlierCurMonthPhTh;
    @Column(name = "ELECTION_EARLIER_AMOUNT_S_TH")
    private Integer electionEarlierAmountSTh;
    @Column(name = "ELECTION_EARLIER_AMOUNT_PH_TH")
    private Integer electionEarlierAmountPhTh;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report004 reportId;

    public Report004Detail() {
    }

    public Report004Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getFullTerm() {
        return fullTerm;
    }

    public void setFullTerm(Integer fullTerm) {
        this.fullTerm = fullTerm;
    }

    public Integer getElectionBeforeAnnouncement() {
        return electionBeforeAnnouncement;
    }

    public void setElectionBeforeAnnouncement(Integer electionBeforeAnnouncement) {
        this.electionBeforeAnnouncement = electionBeforeAnnouncement;
    }

    public Integer getElectionFillVacancy() {
        return electionFillVacancy;
    }

    public void setElectionFillVacancy(Integer electionFillVacancy) {
        this.electionFillVacancy = electionFillVacancy;
    }

    public Integer getAmountSTh() {
        return amountSTh;
    }

    public void setAmountSTh(Integer amountSTh) {
        this.amountSTh = amountSTh;
    }

    public Integer getAmountPhTh() {
        return amountPhTh;
    }

    public void setAmountPhTh(Integer amountPhTh) {
        this.amountPhTh = amountPhTh;
    }

    public Integer getElectionEarlierLastMonthSTh() {
        return electionEarlierLastMonthSTh;
    }

    public void setElectionEarlierLastMonthSTh(Integer electionEarlierLastMonthSTh) {
        this.electionEarlierLastMonthSTh = electionEarlierLastMonthSTh;
    }

    public Integer getElectionEarlierLastMonthPhTh() {
        return electionEarlierLastMonthPhTh;
    }

    public void setElectionEarlierLastMonthPhTh(Integer electionEarlierLastMonthPhTh) {
        this.electionEarlierLastMonthPhTh = electionEarlierLastMonthPhTh;
    }

    public Integer getElectionEarlierCurMonthSTh() {
        return electionEarlierCurMonthSTh;
    }

    public void setElectionEarlierCurMonthSTh(Integer electionEarlierCurMonthSTh) {
        this.electionEarlierCurMonthSTh = electionEarlierCurMonthSTh;
    }

    public Integer getElectionEarlierCurMonthPhTh() {
        return electionEarlierCurMonthPhTh;
    }

    public void setElectionEarlierCurMonthPhTh(Integer electionEarlierCurMonthPhTh) {
        this.electionEarlierCurMonthPhTh = electionEarlierCurMonthPhTh;
    }

    public Integer getElectionEarlierAmountSTh() {
        return electionEarlierAmountSTh;
    }

    public void setElectionEarlierAmountSTh(Integer electionEarlierAmountSTh) {
        this.electionEarlierAmountSTh = electionEarlierAmountSTh;
    }

    public Integer getElectionEarlierAmountPhTh() {
        return electionEarlierAmountPhTh;
    }

    public void setElectionEarlierAmountPhTh(Integer electionEarlierAmountPhTh) {
        this.electionEarlierAmountPhTh = electionEarlierAmountPhTh;
    }

    public Report004 getReportId() {
        return reportId;
    }

    public void setReportId(Report004 reportId) {
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
        if (!(object instanceof Report004Detail)) {
            return false;
        }
        Report004Detail other = (Report004Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    /**
     * @return the dlaType
     */
    public String getDlaType() {
        return dlaType;
    }

    /**
     * @param dlaType the dlaType to set
     */
    public void setDlaType(String dlaType) {
        this.dlaType = dlaType;
    }

    @Override
    public String toString() {
        return "Report004Detail{" + "reportDetailId=" + reportDetailId + ", dlaType=" + dlaType + ", fullTerm=" + fullTerm + ", electionBeforeAnnouncement=" + electionBeforeAnnouncement + ", electionFillVacancy=" + electionFillVacancy + ", amountSTh=" + amountSTh + ", amountPhTh=" + amountPhTh + ", electionEarlierLastMonthSTh=" + electionEarlierLastMonthSTh + ", electionEarlierLastMonthPhTh=" + electionEarlierLastMonthPhTh + ", electionEarlierCurMonthSTh=" + electionEarlierCurMonthSTh + ", electionEarlierCurMonthPhTh=" + electionEarlierCurMonthPhTh + ", electionEarlierAmountSTh=" + electionEarlierAmountSTh + ", electionEarlierAmountPhTh=" + electionEarlierAmountPhTh + '}';
    }
    
}
