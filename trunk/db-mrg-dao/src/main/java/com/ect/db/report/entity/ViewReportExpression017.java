/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReportExpression017 extends DomainEntity{
    private static final long serialVersionUID = -3627300859531104936L;
    
    @Id
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.institution);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewReportExpression017 other = (ViewReportExpression017) obj;
        if (!Objects.equals(this.institution, other.institution)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ViewReportExpression017{" + "institution=" + institution + ", allStory=" + allStory + ", redCard=" + redCard + ", yellowCard=" + yellowCard + ", yellowCardCriminalCase=" + yellowCardCriminalCase + ", resetCounter=" + resetCounter + ", criminalCase=" + criminalCase + ", adding=" + adding + ", requestReceived=" + requestReceived + ", requestNoReceived=" + requestNoReceived + ", withdrawnRequest=" + withdrawnRequest + ", ect=" + ect + '}';
    }
}
