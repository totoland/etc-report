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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_TASK_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctTaskDetail.findAll", query = "SELECT e FROM EctTaskDetail e")})
public class EctTaskDetail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TASK_DETAIL_ID")
    private Integer taskDetailId;
    @Column(name = "TASK_ID")
    private Integer taskId;

    public EctTaskDetail() {
    }

    public EctTaskDetail(Integer taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public Integer getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(Integer taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskDetailId != null ? taskDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctTaskDetail)) {
            return false;
        }
        EctTaskDetail other = (EctTaskDetail) object;
        if ((this.taskDetailId == null && other.taskDetailId != null) || (this.taskDetailId != null && !this.taskDetailId.equals(other.taskDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctTaskDetail[ taskDetailId=" + taskDetailId + " ]";
    }
    
}
