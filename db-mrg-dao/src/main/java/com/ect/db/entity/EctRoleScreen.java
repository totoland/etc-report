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
@Table(name = "ECT_ROLE_SCREEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctRoleScreen.findAll", query = "SELECT e FROM EctRoleScreen e")})
public class EctRoleScreen extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_SCREEN_ID")
    private Integer roleScreenId;
    @Column(name = "ROLE_SCREEN_NAME")
    private String roleScreenName;
    @Column(name = "ROLE_SCREEN_URL")
    private String roleScreenUrl;
    @Column(name = "ROLE_SCREEN_LABEL")
    private String roleScreenLabel;
    @Lob
    @Column(name = "ROLE_SCREEN_DESC")
    private String roleScreenDesc;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public EctRoleScreen() {
    }

    public EctRoleScreen(Integer roleScreenId) {
        this.roleScreenId = roleScreenId;
    }

    public Integer getRoleScreenId() {
        return roleScreenId;
    }

    public void setRoleScreenId(Integer roleScreenId) {
        this.roleScreenId = roleScreenId;
    }

    public String getRoleScreenName() {
        return roleScreenName;
    }

    public void setRoleScreenName(String roleScreenName) {
        this.roleScreenName = roleScreenName;
    }

    public String getRoleScreenUrl() {
        return roleScreenUrl;
    }

    public void setRoleScreenUrl(String roleScreenUrl) {
        this.roleScreenUrl = roleScreenUrl;
    }

    public String getRoleScreenLabel() {
        return roleScreenLabel;
    }

    public void setRoleScreenLabel(String roleScreenLabel) {
        this.roleScreenLabel = roleScreenLabel;
    }

    public String getRoleScreenDesc() {
        return roleScreenDesc;
    }

    public void setRoleScreenDesc(String roleScreenDesc) {
        this.roleScreenDesc = roleScreenDesc;
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
        hash += (roleScreenId != null ? roleScreenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctRoleScreen)) {
            return false;
        }
        EctRoleScreen other = (EctRoleScreen) object;
        if ((this.roleScreenId == null && other.roleScreenId != null) || (this.roleScreenId != null && !this.roleScreenId.equals(other.roleScreenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctRoleScreen[ roleScreenId=" + roleScreenId + " ]";
    }
    
}
