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
@Table(name = "ECT_PROVINCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctProvince.findAll", query = "SELECT e FROM EctProvince e")})
public class EctProvince extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;
    @Column(name = "PROVINCE_CODE")
    private String provinceCode;
    @Column(name = "PROVINCE_NAME")
    private String provinceName;
    @Column(name = "GEO_ID")
    private Integer geoId;

    public EctProvince() {
    }

    public EctProvince(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provinceId != null ? provinceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctProvince)) {
            return false;
        }
        EctProvince other = (EctProvince) object;
        if ((this.provinceId == null && other.provinceId != null) || (this.provinceId != null && !this.provinceId.equals(other.provinceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toJson();
    }
    
}
