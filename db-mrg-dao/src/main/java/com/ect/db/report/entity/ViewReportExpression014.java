/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReportExpression014 extends ViewReportExpression{
    private static final long serialVersionUID = -4251390170483936718L;
    
    @Column(name = "SEND_REQUEST")
    private Integer sendRequest;

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
        return super.toString()+" ViewReportExpression014{" + "sendRequest=" + sendRequest + '}';
    }
}
