/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReportExpression014 extends DomainEntity{
    private static final long serialVersionUID = -4251390170483936718L;
    
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
    @Column(name = "SEND_REQUEST")
    private Integer sendRequest;

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
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

    /**
     * @return the atCenter
     */
    public Integer getAtCenter() {
        return atCenter;
    }

    /**
     * @param atCenter the atCenter to set
     */
    public void setAtCenter(Integer atCenter) {
        this.atCenter = atCenter;
    }

    /**
     * @return the atEctProvince
     */
    public Integer getAtEctProvince() {
        return atEctProvince;
    }

    /**
     * @param atEctProvince the atEctProvince to set
     */
    public void setAtEctProvince(Integer atEctProvince) {
        this.atEctProvince = atEctProvince;
    }

    /**
     * @return the ectResolve
     */
    public Integer getEctResolve() {
        return ectResolve;
    }

    /**
     * @param ectResolve the ectResolve to set
     */
    public void setEctResolve(Integer ectResolve) {
        this.ectResolve = ectResolve;
    }

    /**
     * @return the analystRemain
     */
    public Integer getAnalystRemain() {
        return analystRemain;
    }

    /**
     * @param analystRemain the analystRemain to set
     */
    public void setAnalystRemain(Integer analystRemain) {
        this.analystRemain = analystRemain;
    }

    /**
     * @return the offerEct
     */
    public Integer getOfferEct() {
        return offerEct;
    }

    /**
     * @param offerEct the offerEct to set
     */
    public void setOfferEct(Integer offerEct) {
        this.offerEct = offerEct;
    }

    /**
     * @return the accessCommittee
     */
    public Integer getAccessCommittee() {
        return accessCommittee;
    }

    /**
     * @param accessCommittee the accessCommittee to set
     */
    public void setAccessCommittee(Integer accessCommittee) {
        this.accessCommittee = accessCommittee;
    }

    /**
     * @return the onAgenda
     */
    public Integer getOnAgenda() {
        return onAgenda;
    }

    /**
     * @param onAgenda the onAgenda to set
     */
    public void setOnAgenda(Integer onAgenda) {
        this.onAgenda = onAgenda;
    }

    /**
     * @return the sendRequest
     */
    public Integer getSendRequest() {
        return sendRequest;
    }

    /**
     * @param sendRequest the sendRequest to set
     */
    public void setSendRequest(Integer sendRequest) {
        this.sendRequest = sendRequest;
    }
    
    @Override
    public String toString() {
        return "ViewReportExpression014{" + "institution=" + getInstitution() + ", allamount=" + getAllamount() + ", atCenter=" + getAtCenter() + ", atEctProvince=" + getAtEctProvince() + ", ectResolve=" + getEctResolve() + ", analystRemain=" + getAnalystRemain() + ", offerEct=" + getOfferEct() + ", accessCommittee=" + getAccessCommittee() + ", onAgenda=" + getOnAgenda() + ", sendRequest=" + getSendRequest() + '}';
    }
}
