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
@Table(name = "ECT_DISTRICT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctDistrict.findAll", query = "SELECT e FROM EctDistrict e")})
public class EctDistrict extends DomainEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DISTRICT_ID")
    private Integer districtId;
    @Column(name = "DISTRICT_CODE")
    private String districtCode;
    @Column(name = "DISTRICT_NAME")
    private String districtName;
    @Column(name = "AMPHUR_ID")
    private Integer amphurId;
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;
    @Column(name = "GEO_ID")
    private Integer geoId;

    public EctDistrict() {
    }

    public EctDistrict(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getAmphurId() {
        return amphurId;
    }

    public void setAmphurId(Integer amphurId) {
        this.amphurId = amphurId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
        hash += (districtId != null ? districtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctDistrict)) {
            return false;
        }
        EctDistrict other = (EctDistrict) object;
        if ((this.districtId == null && other.districtId != null) || (this.districtId != null && !this.districtId.equals(other.districtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ect.db.entity.EctDistrict[ districtId=" + districtId + " ]";
    }
    
}
