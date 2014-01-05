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
@Table(name = "ECT_ROLE_ACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctRoleAction.findAll", query = "SELECT e FROM EctRoleAction e")})
public class EctRoleAction extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_ACTION_ID")
    private Integer roleActionId;
    @Column(name = "ROLE_ACTION_NAME")
    private String roleActionName;
    @Lob
    @Column(name = "ROLE_ACTION_DESC")
    private String roleActionDesc;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public EctRoleAction() {
    }

    public EctRoleAction(Integer roleActionId) {
        this.roleActionId = roleActionId;
    }

    public Integer getRoleActionId() {
        return roleActionId;
    }

    public void setRoleActionId(Integer roleActionId) {
        this.roleActionId = roleActionId;
    }

    public String getRoleActionName() {
        return roleActionName;
    }

    public void setRoleActionName(String roleActionName) {
        this.roleActionName = roleActionName;
    }

    public String getRoleActionDesc() {
        return roleActionDesc;
    }

    public void setRoleActionDesc(String roleActionDesc) {
        this.roleActionDesc = roleActionDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleActionId != null ? roleActionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctRoleAction)) {
            return false;
        }
        EctRoleAction other = (EctRoleAction) object;
        if ((this.roleActionId == null && other.roleActionId != null) || (this.roleActionId != null && !this.roleActionId.equals(other.roleActionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctRoleAction[ roleActionId=" + roleActionId + " ]";
    }
    
}
