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
@Table(name = "REPORT_017_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report017Detail.findAll", query = "SELECT r FROM Report017Detail r")})
public class Report017Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 856462751346894370L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "INSTITUTION")
    private String institution;
    @Column(name = "ALL_STORY")
    private Integer allStory;
    @Column(name = "RED_CARD")
    private Integer redCard;
    @Column(name = "YELLOW_CARD")
    private Integer yellowCard;
    @Column(name = "YELLOW_CARD_CRIMINAL_CASE")
    private Integer yellowCardCriminalCase;
    @Column(name = "RESET_COUNTER")
    private Integer resetCounter;
    @Column(name = "CRIMINAL_CASE")
    private Integer criminalCase;
    @Column(name = "ADDING")
    private Integer adding;
    @Column(name = "REQUEST_RECEIVED")
    private Integer requestReceived;
    @Column(name = "REQUEST_NO_RECEIVED")
    private Integer requestNoReceived;
    @Column(name = "WITHDRAWN_REQUEST")
    private Integer withdrawnRequest;
    @Column(name = "ECT")
    private Integer ect;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report017 reportId;

    public Report017Detail() {
    }

    public Report017Detail(Integer reportDetailId) {
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

    public Integer getAllStory() {
        return allStory;
    }

    public void setAllStory(Integer allStory) {
        this.allStory = allStory;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }

    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
    }

    public Integer getYellowCardCriminalCase() {
        return yellowCardCriminalCase;
    }

    public void setYellowCardCriminalCase(Integer yellowCardCriminalCase) {
        this.yellowCardCriminalCase = yellowCardCriminalCase;
    }

    public Integer getResetCounter() {
        return resetCounter;
    }

    public void setResetCounter(Integer resetCounter) {
        this.resetCounter = resetCounter;
    }

    public Integer getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(Integer criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Integer getAdding() {
        return adding;
    }

    public void setAdding(Integer adding) {
        this.adding = adding;
    }

    public Integer getRequestReceived() {
        return requestReceived;
    }

    public void setRequestReceived(Integer requestReceived) {
        this.requestReceived = requestReceived;
    }

    public Integer getRequestNoReceived() {
        return requestNoReceived;
    }

    public void setRequestNoReceived(Integer requestNoReceived) {
        this.requestNoReceived = requestNoReceived;
    }

    public Integer getWithdrawnRequest() {
        return withdrawnRequest;
    }

    public void setWithdrawnRequest(Integer withdrawnRequest) {
        this.withdrawnRequest = withdrawnRequest;
    }

    public Integer getEct() {
        return ect;
    }

    public void setEct(Integer ect) {
        this.ect = ect;
    }

    public Report017 getReportId() {
        return reportId;
    }

    public void setReportId(Report017 reportId) {
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
        if (!(object instanceof Report017Detail)) {
            return false;
        }
        Report017Detail other = (Report017Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report017Detail{" + "reportDetailId=" + reportDetailId + ", institution=" + institution + ", allStory=" + allStory + ", redCard=" + redCard + ", yellowCard=" + yellowCard + ", yellowCardCriminalCase=" + yellowCardCriminalCase + ", resetCounter=" + resetCounter + ", criminalCase=" + criminalCase + ", adding=" + adding + ", requestReceived=" + requestReceived + ", requestNoReceived=" + requestNoReceived + ", withdrawnRequest=" + withdrawnRequest + ", ect=" + ect + '}';
    }
    
}
