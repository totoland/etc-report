/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Totoland
 */
@Entity
public class ViewReport001SummaryDetail extends DomainEntity implements Serializable{
    private static final long serialVersionUID = 9003724458533469639L;
    
    @Id
    @Column(name ="ROW_NO")
    protected Integer id;
    @Column(name ="STRATEGIC_ID")
    private Integer strategicId;
    @Column(name = "STRATEGIC_NAME")
    private String strategicName;
    @Column(name ="PROJECT_ID")
    private Integer projectId;
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;
    @Column(name = "ACTIVITY_NAME")
    private String activityName;
    @Column(name = "BUDGET_SET")
    private BigDecimal budgetSet;
    @Column(name = "BUDGET_REAL")
    private BigDecimal budgetReal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public BigDecimal getBudgetSet() {
        return budgetSet;
    }

    public void setBudgetSet(BigDecimal budgetSet) {
        this.budgetSet = budgetSet;
    }

    public BigDecimal getBudgetReal() {
        return budgetReal;
    }

    public void setBudgetReal(BigDecimal budgetReal) {
        this.budgetReal = budgetReal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.strategicId);
        hash = 83 * hash + Objects.hashCode(this.strategicName);
        hash = 83 * hash + Objects.hashCode(this.projectId);
        hash = 83 * hash + Objects.hashCode(this.projectName);
        hash = 83 * hash + Objects.hashCode(this.activityId);
        hash = 83 * hash + Objects.hashCode(this.activityName);
        hash = 83 * hash + Objects.hashCode(this.budgetSet);
        hash = 83 * hash + Objects.hashCode(this.budgetReal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewReport001SummaryDetail other = (ViewReport001SummaryDetail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.strategicId, other.strategicId)) {
            return false;
        }
        if (!Objects.equals(this.strategicName, other.strategicName)) {
            return false;
        }
        if (!Objects.equals(this.projectId, other.projectId)) {
            return false;
        }
        if (!Objects.equals(this.projectName, other.projectName)) {
            return false;
        }
        if (!Objects.equals(this.activityId, other.activityId)) {
            return false;
        }
        if (!Objects.equals(this.activityName, other.activityName)) {
            return false;
        }
        if (!Objects.equals(this.budgetSet, other.budgetSet)) {
            return false;
        }
        if (!Objects.equals(this.budgetReal, other.budgetReal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ViewReport001SummaryDetail{" + "id=" + id + ", strategicId=" + strategicId + ", strategicName=" + strategicName + ", projectId=" + projectId + ", projectName=" + projectName + ", activityId=" + activityId + ", activityName=" + activityName + ", budgetSet=" + budgetSet + ", budgetReal=" + budgetReal + '}';
    }
}
