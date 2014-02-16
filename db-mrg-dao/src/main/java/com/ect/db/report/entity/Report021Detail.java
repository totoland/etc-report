/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jirawat.l
 */
@Entity
@Table(name = "REPORT_021_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report021Detail.findAll", query = "SELECT r FROM Report021Detail r")})
public class Report021Detail extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "ORDER_NO")
    private Integer orderNo;
    @Column(name = "INSPECTOR")
    private String inspector;
    @Column(name = "OPERATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationDate;
    @Column(name = "OBTAINED")
    private Integer obtained;
    @Column(name = "RESULT")
    private String result;
    @Column(name = "ADVICE")
    private String advice;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report021 reportId;

    public Report021Detail() {
    }

    public Report021Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Integer getObtained() {
        return obtained;
    }

    public void setObtained(Integer obtained) {
        this.obtained = obtained;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Report021 getReportId() {
        return reportId;
    }

    public void setReportId(Report021 reportId) {
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
        if (!(object instanceof Report021Detail)) {
            return false;
        }
        Report021Detail other = (Report021Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report021Detail{" + "reportDetailId=" + reportDetailId + ", orderNo=" + orderNo + ", inspector=" + inspector + ", operationDate=" + operationDate + ", obtained=" + obtained + ", result=" + result + ", advice=" + advice + '}';
    }
}
