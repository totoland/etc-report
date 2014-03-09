/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_002_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report002Detail.findAll", query = "SELECT r FROM Report002Detail r")})
public class Report002Detail extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1647283742767079794L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "WORK_DETAIL")
    private String workDetail;
    @Column(name = "OBJECTIVE")
    private String objective;
    @Column(name = "GOAL_AMOUNT")
    private Integer goalAmount;
    @Column(name = "RESULT")
    private String result;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BUDGET")
    private BigDecimal budget;
    @Column(name = "BUGGET_SOURCE")
    private String buggetSource;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report002 reportId;

    public Report002Detail() {
    }

    public Report002Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Integer getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Integer goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getBuggetSource() {
        return buggetSource;
    }

    public void setBuggetSource(String buggetSource) {
        this.buggetSource = buggetSource;
    }

    public Report002 getReportId() {
        return reportId;
    }

    public void setReportId(Report002 reportId) {
        this.reportId = reportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportDetailId != null ? reportDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report002Detail)) {
            return false;
        }
        Report002Detail other = (Report002Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report002Detail{" + "reportDetailId=" + reportDetailId + ", workDetail=" + workDetail + ", objective=" + objective + ", goalAmount=" + goalAmount + ", result=" + result + ", budget=" + budget + ", buggetSource=" + buggetSource +  '}';
    }
    
}
