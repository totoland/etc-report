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
@Table(name = "ECT_USER_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctUserGroup.findAll", query = "SELECT e FROM EctUserGroup e")})
public class EctUserGroup extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_GROUP_ID")
    private Integer userGroupId;
    @Column(name = "USER_GROUP_NAME")
    private String userGroupName;
    @Lob
    @Column(name = "USER_GROUP_DESC")
    private String userGroupDesc;
    @Column(name = "USER_GROUP_LVL")
    private Integer userGroupLvl;

    public EctUserGroup() {
    }

    public EctUserGroup(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupDesc() {
        return userGroupDesc;
    }

    public void setUserGroupDesc(String userGroupDesc) {
        this.userGroupDesc = userGroupDesc;
    }

    public Integer getUserGroupLvl() {
        return userGroupLvl;
    }

    public void setUserGroupLvl(Integer userGroupLvl) {
        this.userGroupLvl = userGroupLvl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupId != null ? userGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctUserGroup)) {
            return false;
        }
        EctUserGroup other = (EctUserGroup) object;
        if ((this.userGroupId == null && other.userGroupId != null) || (this.userGroupId != null && !this.userGroupId.equals(other.userGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctUserGroup[ userGroupId=" + userGroupId + " ]";
    }
    
}
