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
@Table(name = "REPORT_006_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report006Detail.findAll", query = "SELECT r FROM Report006Detail r")})
public class Report006Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 7940120497530675215L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "SUBMIT_MANAGER")
    private Integer submitManager;
    @Column(name = "SUBMIT_PRESIDENT_ECT")
    private Integer submitPresidentEct;
    @Column(name = "SUBMITED")
    private Integer submited;
    @Column(name = "CONCLUSION")
    private Integer conclusion;
    @Column(name = "COMMENT")
    private Integer comment;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report006 reportId;

    public Report006Detail() {
    }

    public Report006Detail(Integer reportDetailId) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSubmitManager() {
        return submitManager;
    }

    public void setSubmitManager(Integer submitManager) {
        this.submitManager = submitManager;
    }

    public Integer getSubmitPresidentEct() {
        return submitPresidentEct;
    }

    public void setSubmitPresidentEct(Integer submitPresidentEct) {
        this.submitPresidentEct = submitPresidentEct;
    }

    public Integer getSubmited() {
        return submited;
    }

    public void setSubmited(Integer submited) {
        this.submited = submited;
    }

    public Integer getConclusion() {
        return conclusion;
    }

    public void setConclusion(Integer conclusion) {
        this.conclusion = conclusion;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Report006 getReportId() {
        return reportId;
    }

    public void setReportId(Report006 reportId) {
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
        if (!(object instanceof Report006Detail)) {
            return false;
        }
        Report006Detail other = (Report006Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report006Detail{" + "reportDetailId=" + reportDetailId + ", title=" + title + ", amount=" + amount + ", submitManager=" + submitManager + ", submitPresidentEct=" + submitPresidentEct + ", submited=" + submited + ", conclusion=" + conclusion + ", comment=" + comment + '}';
    }
    
}
