/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_FLOW_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctFlowStatus.findAll", query = "SELECT e FROM EctFlowStatus e")})
public class EctFlowStatus extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FLOW_STATUS_ID")
    private Integer flowStatusId;
    @Column(name = "FLOW_STATUS_NAME")
    private String flowStatusName;
    @Lob
    @Column(name = "FLOW_STATUS_DESC")
    private String flowStatusDesc;

    public EctFlowStatus() {
    }

    public EctFlowStatus(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    public String getFlowStatusName() {
        return flowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName;
    }

    public String getFlowStatusDesc() {
        return flowStatusDesc;
    }

    public void setFlowStatusDesc(String flowStatusDesc) {
        this.flowStatusDesc = flowStatusDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flowStatusId != null ? flowStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctFlowStatus)) {
            return false;
        }
        EctFlowStatus other = (EctFlowStatus) object;
        if ((this.flowStatusId == null && other.flowStatusId != null) || (this.flowStatusId != null && !this.flowStatusId.equals(other.flowStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toJson();
    }

    public static enum FlowStatus {

        DRAFF(0,"DRAFF"),
        STEP_1(101,"STEP_1"),
        STEP_2(102,"STEP_2"),
        STEP_3(103,"STEP_3"), 
        APPROVED(200,"APPROVED"), 
        REJECT(-1,"REJECT");
        
        private int status = 0;
        protected String name = "";
        
        FlowStatus(int status,String name) {
            this.status = status;
            this.name = name;
        }

        /**
         * @return the status
         */
        public int getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(int status) {
            this.status = status;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static enum ReportStatus {

        WAIT(100,"WAIT"),
        APPROVE(200,"APPROVE"), 
        REJECTE(401,"REJECT");
        
        private int status = 0;
        protected String name = "";
        
        ReportStatus(int status,String name) {
            this.status = status;
            this.name = name;
        }

        /**
         * @return the status
         */
        public int getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(int status) {
            this.status = status;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
