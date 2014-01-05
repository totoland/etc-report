/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctTask.findAll", query = "SELECT e FROM EctTask e")})
public class EctTask extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "DOC_NO")
    private String docNo;
    @Column(name = "FLOW_STATUS_ID")
    private Integer flowStatusId;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CREATED_USER")
    private Integer createdUser;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "UPDATED_USER")
    private Integer updatedUser;

    public EctTask() {
    }

    public EctTask(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Integer getFlowStatusId() {
        return flowStatusId;
    }

    public void setFlowStatusId(Integer flowStatusId) {
        this.flowStatusId = flowStatusId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctTask)) {
            return false;
        }
        EctTask other = (EctTask) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctTask[ taskId=" + taskId + " ]";
    }
    
}
