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
@Table(name = "REPORT_012_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report012Detail.findAll", query = "SELECT r FROM Report012Detail r")})
public class Report012Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -5348765416920133019L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "INSTITUTION")
    private String institution;
    @Column(name = "ALL_AMOUNT")
    private Integer allamount;
    @Column(name = "AT_CENTER")
    private Integer atCenter;
    @Column(name = "AT_ECT_PROVINCE")
    private Integer atEctProvince;
    @Column(name = "ECT_RESOLVE")
    private Integer ectResolve;
    @Column(name = "ANALYST_REMAIN")
    private Integer analystRemain;
    @Column(name = "OFFER_ECT")
    private Integer offerEct;
    @Column(name = "ACCESS_COMMITTEE")
    private Integer accessCommittee;
    @Column(name = "ON_AGENDA")
    private Integer onAgenda;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report012 reportId;

    public Report012Detail() {
    }

    public Report012Detail(Integer reportDetailId) {
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

    public Integer getAtCenter() {
        return atCenter;
    }

    public void setAtCenter(Integer atCenter) {
        this.atCenter = atCenter;
    }

    public Integer getAtEctProvince() {
        return atEctProvince;
    }

    public void setAtEctProvince(Integer atEctProvince) {
        this.atEctProvince = atEctProvince;
    }

    public Integer getEctResolve() {
        return ectResolve;
    }

    public void setEctResolve(Integer ectResolve) {
        this.ectResolve = ectResolve;
    }

    public Integer getAnalystRemain() {
        return analystRemain;
    }

    public void setAnalystRemain(Integer analystRemain) {
        this.analystRemain = analystRemain;
    }

    public Integer getOfferEct() {
        return offerEct;
    }

    public void setOfferEct(Integer offerEct) {
        this.offerEct = offerEct;
    }

    public Integer getAccessCommittee() {
        return accessCommittee;
    }

    public void setAccessCommittee(Integer accessCommittee) {
        this.accessCommittee = accessCommittee;
    }

    public Integer getOnAgenda() {
        return onAgenda;
    }

    public void setOnAgenda(Integer onAgenda) {
        this.onAgenda = onAgenda;
    }

    public Report012 getReportId() {
        return reportId;
    }

    public void setReportId(Report012 reportId) {
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
        if (!(object instanceof Report012Detail)) {
            return false;
        }
        Report012Detail other = (Report012Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report012Detail{" + "reportDetailId=" + reportDetailId + ", institution=" + institution + ", allamount=" + allamount + ", atCenter=" + atCenter + ", atEctProvince=" + atEctProvince + ", ectResolve=" + ectResolve + ", analystRemain=" + analystRemain + ", offerEct=" + offerEct + ", accessCommittee=" + accessCommittee + ", onAgenda=" + onAgenda + '}';
    }
    
    /**
     * @return the allamount
     */
    public Integer getAllamount() {
        return allamount;
    }

    /**
     * @param allamount the allamount to set
     */
    public void setAllamount(Integer allamount) {
        this.allamount = allamount;
    }
    
}
