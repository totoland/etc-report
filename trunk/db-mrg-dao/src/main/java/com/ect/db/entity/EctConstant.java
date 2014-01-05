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
@Table(name = "ECT_CONSTANT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctConstant.findAll", query = "SELECT e FROM EctConstant e")})
public class EctConstant extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONS_ID")
    private Integer consId;
    @Column(name = "CONS_NAME")
    private String consName;
    @Column(name = "CONS_VALUE")
    private String consValue;
    @Column(name = "CONS_KEY")
    private String consKey;
    @Column(name = "CONS_SORT")
    private Integer consSort;

    public EctConstant() {
    }

    public EctConstant(Integer consId) {
        this.consId = consId;
    }

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getConsValue() {
        return consValue;
    }

    public void setConsValue(String consValue) {
        this.consValue = consValue;
    }

    public String getConsKey() {
        return consKey;
    }

    public void setConsKey(String consKey) {
        this.consKey = consKey;
    }

    public Integer getConsSort() {
        return consSort;
    }

    public void setConsSort(Integer consSort) {
        this.consSort = consSort;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consId != null ? consId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctConstant)) {
            return false;
        }
        EctConstant other = (EctConstant) object;
        if ((this.consId == null && other.consId != null) || (this.consId != null && !this.consId.equals(other.consId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toJson();
    }
    
}
