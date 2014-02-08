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
@Table(name = "REPORT_010_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report010Detail.findAll", query = "SELECT r FROM Report010Detail r")})
public class Report010Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "POLITICAL_PARTY")
    private String politicalParty;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "ACCEPTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedDate;
    @Column(name = "CASE_IN")
    private Integer caseIn;
    @Column(name = "CASE_OUT")
    private Integer caseOut;
    @Column(name = "CASE_DISSOLVE_PARLIAMENT")
    private Integer caseDissolveParliament;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report010 reportId;

    public Report010Detail() {
    }

    public Report010Detail(Integer reportDetailId) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Integer getCaseIn() {
        return caseIn;
    }

    public void setCaseIn(Integer caseIn) {
        this.caseIn = caseIn;
    }

    public Integer getCaseOut() {
        return caseOut;
    }

    public void setCaseOut(Integer caseOut) {
        this.caseOut = caseOut;
    }

    public Integer getCaseDissolveParliament() {
        return caseDissolveParliament;
    }

    public void setCaseDissolveParliament(Integer caseDissolveParliament) {
        this.caseDissolveParliament = caseDissolveParliament;
    }

    public Report010 getReportId() {
        return reportId;
    }

    public void setReportId(Report010 reportId) {
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
        if (!(object instanceof Report010Detail)) {
            return false;
        }
        Report010Detail other = (Report010Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report010Detail{" + "reportDetailId=" + reportDetailId + ", politicalParty=" + politicalParty + ", position=" + position + ", acceptedDate=" + acceptedDate + ", caseIn=" + caseIn + ", caseOut=" + caseOut + ", caseDissolveParliament=" + caseDissolveParliament + '}';
    }
    
}
