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
@Table(name = "REPORT_015_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report015Detail.findAll", query = "SELECT r FROM Report015Detail r")})
public class Report015Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 460288749444689603L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "INSTITUTION")
    private String institution;
    @Column(name = "DEP_NAME")
    private String depName;
    @Column(name = "ELECTION_DAY")
    private String electionDay;
    @Column(name = "MEETING_TIME")
    private Integer meetingTime;
    @Column(name = "MEETING_DATE")
    private String meetingDate;
    @Column(name = "CONCLUSION_MEETING")
    private String conclusionMeeting;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "ST_AMOUNT")
    private Integer stAmount;
    @Column(name = "PT_AMOUNT")
    private Integer ptAmount;
    @Column(name = "SUM_AMOUNT")
    private Integer sumAmount;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report015 reportId;

    public Report015Detail() {
    }

    public Report015Detail(Integer reportDetailId) {
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

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getElectionDay() {
        return electionDay;
    }

    public void setElectionDay(String electionDay) {
        this.electionDay = electionDay;
    }

    public Integer getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Integer meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getConclusionMeeting() {
        return conclusionMeeting;
    }

    public void setConclusionMeeting(String conclusionMeeting) {
        this.conclusionMeeting = conclusionMeeting;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStAmount() {
        return stAmount;
    }

    public void setStAmount(Integer stAmount) {
        this.stAmount = stAmount;
    }

    public Integer getPtAmount() {
        return ptAmount;
    }

    public void setPtAmount(Integer ptAmount) {
        this.ptAmount = ptAmount;
    }

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public Report015 getReportId() {
        return reportId;
    }

    public void setReportId(Report015 reportId) {
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
        if (!(object instanceof Report015Detail)) {
            return false;
        }
        Report015Detail other = (Report015Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report015Detail{" + "reportDetailId=" + reportDetailId + ", institution=" + institution + ", depName=" + depName + ", electionDay=" + electionDay + ", meetingTime=" + meetingTime + ", meetingDate=" + meetingDate + ", conclusionMeeting=" + conclusionMeeting + ", fullName=" + fullName + ", stAmount=" + stAmount + ", ptAmount=" + ptAmount + ", sumAmount=" + sumAmount + '}';
    }
    
}
