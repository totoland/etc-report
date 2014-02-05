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
@Table(name = "REPORT_007_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report007Detail.findAll", query = "SELECT r FROM Report007Detail r")})
public class Report007Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -5565782556952910968L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "POLITICAL_PARTY_AMOUNT")
    private Integer politicalPartyAmount;
    @Column(name = "STORY_AMOUNT")
    private Integer storyAmount;
    @Column(name = "BRANCH_AMOUNT")
    private Integer branchAmount;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report007 reportId;

    public Report007Detail() {
    }

    public Report007Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPoliticalPartyAmount() {
        return politicalPartyAmount;
    }

    public void setPoliticalPartyAmount(Integer politicalPartyAmount) {
        this.politicalPartyAmount = politicalPartyAmount;
    }

    public Integer getStoryAmount() {
        return storyAmount;
    }

    public void setStoryAmount(Integer storyAmount) {
        this.storyAmount = storyAmount;
    }

    public Integer getBranchAmount() {
        return branchAmount;
    }

    public void setBranchAmount(Integer branchAmount) {
        this.branchAmount = branchAmount;
    }

    public Report007 getReportId() {
        return reportId;
    }

    public void setReportId(Report007 reportId) {
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
        if (!(object instanceof Report007Detail)) {
            return false;
        }
        Report007Detail other = (Report007Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report007Detail{" + "reportDetailId=" + reportDetailId + ", title=" + title + ", politicalPartyAmount=" + politicalPartyAmount + ", storyAmount=" + storyAmount + ", branchAmount=" + branchAmount + '}';
    }
    
}
