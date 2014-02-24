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
@Table(name = "ECT_GROUP_LVL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctGroupLvl.findAll", query = "SELECT e FROM EctGroupLvl e"),
    @NamedQuery(name = "EctGroupLvl.findByGroupLvlId", query = "SELECT e FROM EctGroupLvl e WHERE e.groupLvlId = :groupLvlId"),
    @NamedQuery(name = "EctGroupLvl.findByGroupLvlName", query = "SELECT e FROM EctGroupLvl e WHERE e.groupLvlName = :groupLvlName"),
    @NamedQuery(name = "EctGroupLvl.findByGroupLvlDesc", query = "SELECT e FROM EctGroupLvl e WHERE e.groupLvlDesc = :groupLvlDesc")})
public class EctGroupLvl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUP_LVL_ID")
    private Integer groupLvlId;
    @Column(name = "GROUP_LVL_NAME")
    private String groupLvlName;
    @Column(name = "GROUP_LVL_DESC")
    private String groupLvlDesc;

    public EctGroupLvl() {
    }

    public EctGroupLvl(Integer groupLvlId) {
        this.groupLvlId = groupLvlId;
    }

    public Integer getGroupLvlId() {
        return groupLvlId;
    }

    public void setGroupLvlId(Integer groupLvlId) {
        this.groupLvlId = groupLvlId;
    }

    public String getGroupLvlName() {
        return groupLvlName;
    }

    public void setGroupLvlName(String groupLvlName) {
        this.groupLvlName = groupLvlName;
    }

    public String getGroupLvlDesc() {
        return groupLvlDesc;
    }

    public void setGroupLvlDesc(String groupLvlDesc) {
        this.groupLvlDesc = groupLvlDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupLvlId != null ? groupLvlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctGroupLvl)) {
            return false;
        }
        EctGroupLvl other = (EctGroupLvl) object;
        if ((this.groupLvlId == null && other.groupLvlId != null) || (this.groupLvlId != null && !this.groupLvlId.equals(other.groupLvlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EctGroupLvl{" + "groupLvlId=" + groupLvlId + ", groupLvlName=" + groupLvlName + ", groupLvlDesc=" + groupLvlDesc + '}';
    }
    
    public static enum GroupLevel {

        SYSTEM_ADMIN(0),
        CENTER(1),
        LEAD(2),
        HEAD(3), 
        OPERATOR(4);
        
        private int level = 0;
        
        GroupLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
    
}
