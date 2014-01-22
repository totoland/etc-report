/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_SUB_STRATEGIC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctSubStrategic.findAll", query = "SELECT e FROM EctSubStrategic e")})
public class EctSubStrategic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SUB_STRATEGIC_ID")
    private Integer subStrategicId;
    @Column(name = "SUB_STRATEGIC_NAME")
    private String subStrategicName;
    @Column(name = "SUB_STRATEGIC_DESC")
    private String subStrategicDesc;
    @Column(name = "STRATEGIC_ID")
    private Integer strategicId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ectSubStrategic1")
    private EctSubStrategic ectSubStrategic;
    @JoinColumn(name = "SUB_STRATEGIC_ID", referencedColumnName = "SUB_STRATEGIC_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private EctSubStrategic ectSubStrategic1;

    public EctSubStrategic() {
    }

    public EctSubStrategic(Integer subStrategicId) {
        this.subStrategicId = subStrategicId;
    }

    public Integer getSubStrategicId() {
        return subStrategicId;
    }

    public void setSubStrategicId(Integer subStrategicId) {
        this.subStrategicId = subStrategicId;
    }

    public String getSubStrategicName() {
        return subStrategicName;
    }

    public void setSubStrategicName(String subStrategicName) {
        this.subStrategicName = subStrategicName;
    }

    public String getSubStrategicDesc() {
        return subStrategicDesc;
    }

    public void setSubStrategicDesc(String subStrategicDesc) {
        this.subStrategicDesc = subStrategicDesc;
    }

    public Integer getStrategicId() {
        return strategicId;
    }

    public void setStrategicId(Integer strategicId) {
        this.strategicId = strategicId;
    }

    public EctSubStrategic getEctSubStrategic() {
        return ectSubStrategic;
    }

    public void setEctSubStrategic(EctSubStrategic ectSubStrategic) {
        this.ectSubStrategic = ectSubStrategic;
    }

    public EctSubStrategic getEctSubStrategic1() {
        return ectSubStrategic1;
    }

    public void setEctSubStrategic1(EctSubStrategic ectSubStrategic1) {
        this.ectSubStrategic1 = ectSubStrategic1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subStrategicId != null ? subStrategicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctSubStrategic)) {
            return false;
        }
        EctSubStrategic other = (EctSubStrategic) object;
        if ((this.subStrategicId == null && other.subStrategicId != null) || (this.subStrategicId != null && !this.subStrategicId.equals(other.subStrategicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EctSubStrategic{" + "subStrategicId=" + subStrategicId + ", subStrategicName=" + subStrategicName + ", subStrategicDesc=" + subStrategicDesc + ", strategicId=" + strategicId + ", ectSubStrategic=" + ectSubStrategic + ", ectSubStrategic1=" + ectSubStrategic1 + '}';
    }
    
}
