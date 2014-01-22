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
@Table(name = "ECT_STRATEGIC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctStrategic.findAll", query = "SELECT e FROM EctStrategic e")})
public class EctStrategic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STRATEGIC_ID")
    private Integer strategicId;
    @Column(name = "STRATEGIC_NAME")
    private String strategicName;
    @Column(name = "STRATEGIC_DESC")
    private String strategicDesc;

    public EctStrategic() {
    }

    public EctStrategic(Integer strategicId) {
        this.strategicId = strategicId;
    }

    public Integer getStrategicId() {
        return strategicId;
    }

    public void setStrategicId(Integer strategicId) {
        this.strategicId = strategicId;
    }

    public String getStrategicName() {
        return strategicName;
    }

    public void setStrategicName(String strategicName) {
        this.strategicName = strategicName;
    }

    public String getStrategicDesc() {
        return strategicDesc;
    }

    public void setStrategicDesc(String strategicDesc) {
        this.strategicDesc = strategicDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strategicId != null ? strategicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctStrategic)) {
            return false;
        }
        EctStrategic other = (EctStrategic) object;
        if ((this.strategicId == null && other.strategicId != null) || (this.strategicId != null && !this.strategicId.equals(other.strategicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EctStrategic{" + "strategicId=" + strategicId + ", strategicName=" + strategicName + ", strategicDesc=" + strategicDesc + '}';
    }
    
}
