/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.entity;

import com.ect.db.domain.entity.DomainEntity;
import java.io.Serializable;
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
 * @author Jirawat.l
 */
@Entity
@Table(name = "REPORT_022_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report022Detail.findAll", query = "SELECT r FROM Report022Detail r")})
public class Report022Detail  extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "ORDER_NO")
    private Integer orderNo;
    @Column(name = "NOTICE")
    private String notice;
    @Column(name = "EDIT")
    private String edit;
    @Column(name = "REDRAFT")
    private String redraft;
    @Column(name = "RESULT")
    private String result;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report022 reportId;

    public Report022Detail() {
    }

    public Report022Detail(Integer reportDetailId) {
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getRedraft() {
        return redraft;
    }

    public void setRedraft(String redraft) {
        this.redraft = redraft;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Report022 getReportId() {
        return reportId;
    }

    public void setReportId(Report022 reportId) {
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
        if (!(object instanceof Report022Detail)) {
            return false;
        }
        Report022Detail other = (Report022Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report022Detail{" + "reportDetailId=" + reportDetailId + ", orderNo=" + orderNo + ", notice=" + notice + ", edit=" + edit + ", redraft=" + redraft + ", result=" + result + ", reportId=" + reportId + '}';
    }

   
}
