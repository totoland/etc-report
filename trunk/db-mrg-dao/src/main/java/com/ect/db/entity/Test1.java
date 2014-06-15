/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "TEST1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test1.findAll", query = "SELECT t FROM Test1 t")})
public class Test1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TEST_ID")
    private Integer testId;
    @Column(name = "R_ID")
    private Integer rId;
    @Lob
    @Column(name = "DETAIL")
    private String detail;
    @OneToMany(mappedBy = "instanceId")
    private Collection<EctConf> ectConfCollection;

    public Test1() {
    }

    public Test1(Integer testId) {
        this.testId = testId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getRId() {
        return rId;
    }

    public void setRId(Integer rId) {
        this.rId = rId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @XmlTransient
    public Collection<EctConf> getEctConfCollection() {
        return ectConfCollection;
    }

    public void setEctConfCollection(Collection<EctConf> ectConfCollection) {
        this.ectConfCollection = ectConfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testId != null ? testId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test1)) {
            return false;
        }
        Test1 other = (Test1) object;
        if ((this.testId == null && other.testId != null) || (this.testId != null && !this.testId.equals(other.testId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.Test1[ testId=" + testId + " ]";
    }
    
}
