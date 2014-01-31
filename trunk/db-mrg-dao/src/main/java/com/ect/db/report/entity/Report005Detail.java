/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_005_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report005Detail.findAll", query = "SELECT r FROM Report005Detail r")})
public class Report005Detail extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "ELECTED_DAY")
    @Temporal(TemporalType.DATE)
    private Date electedDay;
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;
    @Column(name = "ELECTED_ZONE")
    private String electedZone;
    @Column(name = "VOTER_AMOUNT")
    private Integer voterAmount;
    @Column(name = "VOTED_AMOUNT")
    private Integer votedAmount;
    @Column(name = "VOTE_PERCEN")
    private Integer votePercen;
    @Column(name = "VOIDED_BALLOT_PAPER")
    private Integer voidedBallotPaper;
    @Column(name = "VOIDED_BALLOT_PAPER_PERCEN")
    private Integer voidedBallotPaperPercen;
    @Column(name = "VOTE_NO")
    private Integer voteNo;
    @Column(name = "VOTE_NO_PERCEN")
    private Integer voteNoPercen;
    @Column(name = "ELECTED_TYPE")
    private Integer electedType;
    @Column(name = "NOMINATION_PERIOD")
    private String nominationPeriod;
    @Column(name = "ELECTION")
    private String election;
    @Column(name = "CORPORATE_AMOUNT")
    private Integer corporateAmount;
    @Column(name = "SENATOR_NOMINATION_AMOUNT")
    private Integer senatorNominationAmount;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report005 reportId;

    public Report005Detail() {
    }

    public Report005Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Date getElectedDay() {
        return electedDay;
    }

    public void setElectedDay(Date electedDay) {
        this.electedDay = electedDay;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getElectedZone() {
        return electedZone;
    }

    public void setElectedZone(String electedZone) {
        this.electedZone = electedZone;
    }

    public Integer getVoterAmount() {
        return voterAmount;
    }

    public void setVoterAmount(Integer voterAmount) {
        this.voterAmount = voterAmount;
    }

    public Integer getVotedAmount() {
        return votedAmount;
    }

    public void setVotedAmount(Integer votedAmount) {
        this.votedAmount = votedAmount;
    }

    public Integer getVotePercen() {
        return votePercen;
    }

    public void setVotePercen(Integer votePercen) {
        this.votePercen = votePercen;
    }

    public Integer getVoidedBallotPaper() {
        return voidedBallotPaper;
    }

    public void setVoidedBallotPaper(Integer voidedBallotPaper) {
        this.voidedBallotPaper = voidedBallotPaper;
    }

    public Integer getVoidedBallotPaperPercen() {
        return voidedBallotPaperPercen;
    }

    public void setVoidedBallotPaperPercen(Integer voidedBallotPaperPercen) {
        this.voidedBallotPaperPercen = voidedBallotPaperPercen;
    }

    public Integer getVoteNo() {
        return voteNo;
    }

    public void setVoteNo(Integer voteNo) {
        this.voteNo = voteNo;
    }

    public Integer getVoteNoPercen() {
        return voteNoPercen;
    }

    public void setVoteNoPercen(Integer voteNoPercen) {
        this.voteNoPercen = voteNoPercen;
    }

    public Integer getElectedType() {
        return electedType;
    }

    public void setElectedType(Integer electedType) {
        this.electedType = electedType;
    }

    public String getNominationPeriod() {
        return nominationPeriod;
    }

    public void setNominationPeriod(String nominationPeriod) {
        this.nominationPeriod = nominationPeriod;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public Integer getCorporateAmount() {
        return corporateAmount;
    }

    public void setCorporateAmount(Integer corporateAmount) {
        this.corporateAmount = corporateAmount;
    }

    public Integer getSenatorNominationAmount() {
        return senatorNominationAmount;
    }

    public void setSenatorNominationAmount(Integer senatorNominationAmount) {
        this.senatorNominationAmount = senatorNominationAmount;
    }

    public Report005 getReportId() {
        return reportId;
    }

    public void setReportId(Report005 reportId) {
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
        if (!(object instanceof Report005Detail)) {
            return false;
        }
        Report005Detail other = (Report005Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report005Detail{" + "reportDetailId=" + reportDetailId + ", electedDay=" + electedDay + ", provinceId=" + provinceId + ", electedZone=" + electedZone + ", voterAmount=" + voterAmount + ", votedAmount=" + votedAmount + ", votePercen=" + votePercen + ", voidedBallotPaper=" + voidedBallotPaper + ", voidedBallotPaperPercen=" + voidedBallotPaperPercen + ", voteNo=" + voteNo + ", voteNoPercen=" + voteNoPercen + ", electedType=" + electedType + ", nominationPeriod=" + nominationPeriod + ", election=" + election + ", corporateAmount=" + corporateAmount + ", senatorNominationAmount=" + senatorNominationAmount + '}';
    }
}
