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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "REPORT_NAME")
@NamedQueries({
    @NamedQuery(name = "ReportName.findAll", query = "SELECT r FROM ReportName r")})
public class ReportName extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static enum ReportCode{

        REPORT_001("REPORT_001");

        protected String code;
        
        private ReportCode(String code) {
            this.code = code;
        }

        /**
         * @return the reportName
         */
        public String getCode() {
            return code;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_ID")
    private Integer reportId;
    @Column(name = "REPORT_CODE")
    private String reportCode;
    @Column(name = "REPORT_NAME")
    private String reportName;
    @Lob
    @Column(name = "REPORT_DESC")
    private String reportDesc;
    @Column(name = "REPORT_DETAIL_CODE")
    private String reportDetailCode;
    @Column(name = "REPORT_TYPE")
    private Integer reportType;
    @Column(name = "REPORT_URL")
    private String reportUrl;

    public ReportName() {
    }

    public ReportName(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportDetailCode() {
        return reportDetailCode;
    }

    public void setReportDetailCode(String reportDetailCode) {
        this.reportDetailCode = reportDetailCode;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportName)) {
            return false;
        }
        ReportName other = (ReportName) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReportName{" + "reportId=" + reportId + ", reportCode=" + reportCode + ", reportName=" + reportName + ", reportDesc=" + reportDesc + ", reportDetailCode=" + reportDetailCode + ", reportType=" + reportType + ", reportUrl=" + reportUrl + '}';
    }
    
    /**
     * @return the reportUrl
     */
    public String getReportUrl() {
        return reportUrl;
    }

    /**
     * @param reportUrl the reportUrl to set
     */
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }
}
