/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
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
@Table(name = "ECT_ACTIVITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EctActivity.findAll", query = "SELECT e FROM EctActivity e")})
public class EctActivity extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -260629771027284729L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    @Column(name = "ACTIVITY_NAME")
    private String activityName;
    @Column(name = "ACTIVITY_DESC")
    private String activityDesc;
    @Column(name = "PROJECT_ID")
    private Integer projectId;

    public EctActivity() {
    }

    public EctActivity(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityId != null ? activityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EctActivity)) {
            return false;
        }
        EctActivity other = (EctActivity) object;
        if ((this.activityId == null && other.activityId != null) || (this.activityId != null && !this.activityId.equals(other.activityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EctActivity{" + "activityId=" + activityId + ", activityName=" + activityName + ", activityDesc=" + activityDesc + ", projectId=" + projectId + '}';
    }
    
}
