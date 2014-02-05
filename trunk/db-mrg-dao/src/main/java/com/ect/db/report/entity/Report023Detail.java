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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jirawat.l
 */
@Entity
@Table(name = "REPORT_023_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report023Detail.findAll", query = "SELECT r FROM Report023Detail r")})
public class Report023Detail extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "CASES")
    private String cases;
    @Column(name = "OLD_CASES_QUOTED")
    private Integer oldCasesQuoted;
    @Column(name = "NEW_CASES_MONTH")
    private Integer newCasesMonth;
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
    private Report023 reportId;

    public Report023Detail() {
    }

    public Report023Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public Integer getOldCasesQuoted() {
        return oldCasesQuoted;
    }

    public void setOldCasesQuoted(Integer oldCasesQuoted) {
        this.oldCasesQuoted = oldCasesQuoted;
    }

    public Integer getNewCasesMonth() {
        return newCasesMonth;
    }

    public void setNewCasesMonth(Integer newCasesMonth) {
        this.newCasesMonth = newCasesMonth;
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

    public Report023 getReportId() {
        return reportId;
    }

    public void setReportId(Report023 reportId) {
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
        if (!(object instanceof Report023Detail)) {
            return false;
        }
        Report023Detail other = (Report023Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report023Detail{" + "reportDetailId=" + reportDetailId + ", cases=" + cases + ", oldCasesQuoted=" + oldCasesQuoted + ", newCasesMonth=" + newCasesMonth + ", electionFillVacancy=" + electionFillVacancy + ", amountSTh=" + amountSTh + ", amountPhTh=" + amountPhTh + ", electionEarlierLastMonthSTh=" + electionEarlierLastMonthSTh + ", electionEarlierLastMonthPhTh=" + electionEarlierLastMonthPhTh + ", electionEarlierCurMonthSTh=" + electionEarlierCurMonthSTh + ", electionEarlierCurMonthPhTh=" + electionEarlierCurMonthPhTh + ", electionEarlierAmountSTh=" + electionEarlierAmountSTh + ", electionEarlierAmountPhTh=" + electionEarlierAmountPhTh + ", reportId=" + reportId + '}';
    }
}
