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
@Table(name = "ECT_PERMISSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctPermission.findAll", query = "SELECT e FROM EctPermission e")})
public class EctPermission extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_PERMISSION_ID")
    private Integer rolePermissionId;
    @Column(name = "ROLE_GROUP_ID")
    private Integer roleGroupId;
    @Column(name = "ROLE_SCREEN_ID")
    private Integer roleScreenId;
    @Column(name = "ROLE_ACTION_ID")
    private Integer roleActionId;

    public EctPermission() {
    }

    public EctPermission(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Integer roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public Integer getRoleScreenId() {
        return roleScreenId;
    }

    public void setRoleScreenId(Integer roleScreenId) {
        this.roleScreenId = roleScreenId;
    }

    public Integer getRoleActionId() {
        return roleActionId;
    }

    public void setRoleActionId(Integer roleActionId) {
        this.roleActionId = roleActionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermissionId != null ? rolePermissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctPermission)) {
            return false;
        }
        EctPermission other = (EctPermission) object;
        if ((this.rolePermissionId == null && other.rolePermissionId != null) || (this.rolePermissionId != null && !this.rolePermissionId.equals(other.rolePermissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctPermission[ rolePermissionId=" + rolePermissionId + " ]";
    }
    
}
