/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_PLAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctPlan.findAll", query = "SELECT e FROM EctPlan e")})
public class EctPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLAN_ID")
    private Integer planId;
    @Column(name = "PLAN_NAME")
    private String planName;
    @Column(name = "PLAN_DESC")
    private String planDesc;
    @Column(name = "SUB_STATEGIC_ID")
    private Integer subStategicId;

    public EctPlan() {
    }

    public EctPlan(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public Integer getSubStategicId() {
        return subStategicId;
    }

    public void setSubStategicId(Integer subStategicId) {
        this.subStategicId = subStategicId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctPlan)) {
            return false;
        }
        EctPlan other = (EctPlan) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EctPlan{" + "planId=" + planId + ", planName=" + planName + ", planDesc=" + planDesc + ", subStategicId=" + subStategicId + '}';
    }
    
}
