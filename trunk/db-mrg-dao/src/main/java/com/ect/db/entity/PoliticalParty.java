/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import com.ect.db.dao.BaseDao;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "POLITICAL_PARTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoliticalParty.findAll", query = "SELECT p FROM PoliticalParty p")})
public class PoliticalParty extends BaseDao implements Serializable {
    private static final long serialVersionUID = -5521164572894114751L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "POLITICAL_PARTY_ID")
    private Integer politicalPartyId;
    @Column(name = "POLITICAL_PARTY_NAME")
    private String politicalPartyName;

    public PoliticalParty() {
    }

    public PoliticalParty(Integer politicalPartyId) {
        this.politicalPartyId = politicalPartyId;
    }

    public Integer getPoliticalPartyId() {
        return politicalPartyId;
    }

    public void setPoliticalPartyId(Integer politicalPartyId) {
        this.politicalPartyId = politicalPartyId;
    }

    public String getPoliticalPartyName() {
        return politicalPartyName;
    }

    public void setPoliticalPartyName(String politicalPartyName) {
        this.politicalPartyName = politicalPartyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (politicalPartyId != null ? politicalPartyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoliticalParty)) {
            return false;
        }
        PoliticalParty other = (PoliticalParty) object;
        if ((this.politicalPartyId == null && other.politicalPartyId != null) || (this.politicalPartyId != null && !this.politicalPartyId.equals(other.politicalPartyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PoliticalParty{" + "politicalPartyId=" + politicalPartyId + ", politicalPartyName=" + politicalPartyName + '}';
    }
    
}
