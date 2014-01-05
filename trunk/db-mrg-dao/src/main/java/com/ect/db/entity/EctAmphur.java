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

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "ECT_AMPHUR")
@NamedQueries({
    @NamedQuery(name = "EctAmphur.findAll", query = "SELECT e FROM EctAmphur e")})
public class EctAmphur extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AMPHUR_ID")
    private Integer amphurId;
    @Column(name = "AMPHUR_CODE")
    private String amphurCode;
    @Column(name = "AMPHUR_NAME")
    private String amphurName;
    @Column(name = "GEO_ID")
    private Integer geoId;
    @Column(name = "PROVINCE_ID")
    private Integer provinceId;

    public EctAmphur() {
    }

    public EctAmphur(Integer amphurId) {
        this.amphurId = amphurId;
    }

    public Integer getAmphurId() {
        return amphurId;
    }

    public void setAmphurId(Integer amphurId) {
        this.amphurId = amphurId;
    }

    public String getAmphurCode() {
        return amphurCode;
    }

    public void setAmphurCode(String amphurCode) {
        this.amphurCode = amphurCode;
    }

    public String getAmphurName() {
        return amphurName;
    }

    public void setAmphurName(String amphurName) {
        this.amphurName = amphurName;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amphurId != null ? amphurId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctAmphur)) {
            return false;
        }
        EctAmphur other = (EctAmphur) object;
        if ((this.amphurId == null && other.amphurId != null) || (this.amphurId != null && !this.amphurId.equals(other.amphurId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toJson();
    }
    
}
