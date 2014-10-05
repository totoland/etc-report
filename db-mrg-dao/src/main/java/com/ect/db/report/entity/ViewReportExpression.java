/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReportExpression extends DomainEntity implements Serializable{
    private static final long serialVersionUID = -1326600650971735742L;
    
    @Id
    @Basic(optional = false)
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

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getAllamount() {
        return allamount;
    }

    public void setAllamount(Integer allamount) {
        this.allamount = allamount;
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

    @Override
    public String toString() {
        return "ViewReportExpression{" + "institution=" + institution + ", allamount=" + allamount + ", atCenter=" + atCenter + ", atEctProvince=" + atEctProvince + ", ectResolve=" + ectResolve + ", analystRemain=" + analystRemain + ", offerEct=" + offerEct + ", accessCommittee=" + accessCommittee + ", onAgenda=" + onAgenda + '}';
    }
}
