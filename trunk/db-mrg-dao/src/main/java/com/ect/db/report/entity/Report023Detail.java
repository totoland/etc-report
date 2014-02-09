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
@Table(name = "REPORT_023_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report023Detail.findAll", query = "SELECT r FROM Report023Detail r")})
public class Report023Detail extends DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORT_DETAIL_ID")
    private Integer reportDetailId;
    @Column(name = "CASES")
    private String cases;
    @Column(name = "OLD_CASES_QUOTED")
    private Integer oldCasesQuoted;
    @Column(name = "NEW_CASES_QUOTED")
    private Integer newCasesQuoted;
    @Column(name = "SUM_CASES")
    private Integer sumCases;
    @Column(name = "ADMIN_COURT")
    private Integer adminCourt;
    @Column(name = "CRIMINAL_CASES")
    private Integer criminalCases;
    @Column(name = "CIVIL_CASES")
    private Integer civilCases;
    @Column(name = "RULING_CASES")
    private Integer rulingCases;
    @Column(name = "RHETORIC_WAITING")
    private Integer rhetoricWaiting;
    @Column(name = "PROGRESS")
    private Integer progress;
    @Column(name = "INVESTIGATOR")
    private Integer investigator;
    @Column(name = "NACC")
    private Integer nacc;
    @Column(name = "ATTORNEY")
    private Integer attorney;
    @Column(name = "PROSECUTORS")
    private Integer prosecutors;
    @Column(name = "CIVIL_CASES_2")
    private Integer civil_cases2;
    @Column(name = "APPEALS")
    private Integer appeals;
    @Column(name = "THESSALONIKI")
    private Integer thessaloniki;
    @Column(name = "TERMINATES")
    private Integer terminates;
    @Column(name = "PENDING")
    private Integer pending;
    @Column(name = "ATTORNEY_2")
    private Integer attorney2;
    @Column(name = "PROSECUTORS_2")
    private Integer prosecutors2;
    @Column(name = "APPEALS_2")
    private Integer appeals2;
    @Column(name = "THESSALONIKI_2")
    private Integer thessaloniki2;
    @Column(name = "EXECUTION")
    private Integer execution;
    @Column(name = "TERMINATES_2")
    private Integer terminates2;
    @Column(name = "PROGRESS_2")
    private Integer progress2;
    @Column(name = "PROVINCE")
    private Integer province;
    @Column(name = "SUPREME")
    private Integer supreme;
    @Column(name = "TERMINATES_3")
    private Integer terminates3;
    @Column(name = "CONSIDERING")
    private Integer considering;
    @Column(name = "TERMINATES_4")
    private Integer terminates4;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private Report023 reportId;

    public Report023Detail() {
    }

    public Report023Detail(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public Integer getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Integer reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public Integer getOldCasesQuoted() {
        return oldCasesQuoted;
    }

    public void setOldCasesQuoted(Integer oldCasesQuoted) {
        this.oldCasesQuoted = oldCasesQuoted;
    }

    public Integer getNewCasesQuoted() {
        return newCasesQuoted;
    }

    public void setNewCasesQuoted(Integer newCasesQuoted) {
        this.newCasesQuoted = newCasesQuoted;
    }

    public Integer getSumCases() {
        return sumCases;
    }

    public void setSumCases(Integer sumCases) {
        this.sumCases = sumCases;
    }

    public Integer getAdminCourt() {
        return adminCourt;
    }

    public void setAdminCourt(Integer adminCourt) {
        this.adminCourt = adminCourt;
    }

    public Integer getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(Integer criminalCases) {
        this.criminalCases = criminalCases;
    }

    public Integer getCivilCases() {
        return civilCases;
    }

    public void setCivilCases(Integer civilCases) {
        this.civilCases = civilCases;
    }

    public Integer getRulingCases() {
        return rulingCases;
    }

    public void setRulingCases(Integer rulingCases) {
        this.rulingCases = rulingCases;
    }

    public Integer getRhetoricWaiting() {
        return rhetoricWaiting;
    }

    public void setRhetoricWaiting(Integer rhetoricWaiting) {
        this.rhetoricWaiting = rhetoricWaiting;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getInvestigator() {
        return investigator;
    }

    public void setInvestigator(Integer investigator) {
        this.investigator = investigator;
    }

    public Integer getNacc() {
        return nacc;
    }

    public void setNacc(Integer nacc) {
        this.nacc = nacc;
    }

    public Integer getAttorney() {
        return attorney;
    }

    public void setAttorney(Integer attorney) {
        this.attorney = attorney;
    }

    public Integer getProsecutors() {
        return prosecutors;
    }

    public void setProsecutors(Integer prosecutors) {
        this.prosecutors = prosecutors;
    }

    public Integer getAppeals() {
        return appeals;
    }

    public void setAppeals(Integer appeals) {
        this.appeals = appeals;
    }

    public Integer getThessaloniki() {
        return thessaloniki;
    }

    public void setThessaloniki(Integer thessaloniki) {
        this.thessaloniki = thessaloniki;
    }

    public Integer getTerminates() {
        return terminates;
    }

    public void setTerminates(Integer terminates) {
        this.terminates = terminates;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getAttorney2() {
        return attorney2;
    }

    public void setAttorney2(Integer attorney2) {
        this.attorney2 = attorney2;
    }

    public Integer getProsecutors2() {
        return prosecutors2;
    }

    public void setProsecutors2(Integer prosecutors2) {
        this.prosecutors2 = prosecutors2;
    }

    public Integer getAppeals2() {
        return appeals2;
    }

    public void setAppeals2(Integer appeals2) {
        this.appeals2 = appeals2;
    }

    public Integer getExecution() {
        return execution;
    }

    public void setExecution(Integer execution) {
        this.execution = execution;
    }

    public Integer getTerminates2() {
        return terminates2;
    }

    public void setTerminates2(Integer terminates2) {
        this.terminates2 = terminates2;
    }

    public Integer getProgress2() {
        return progress2;
    }

    public void setProgress2(Integer progress2) {
        this.progress2 = progress2;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getSupreme() {
        return supreme;
    }

    public void setSupreme(Integer supreme) {
        this.supreme = supreme;
    }

    public Integer getTerminates3() {
        return terminates3;
    }

    public void setTerminates3(Integer terminates3) {
        this.terminates3 = terminates3;
    }

    public Integer getConsidering() {
        return considering;
    }

    public void setConsidering(Integer considering) {
        this.considering = considering;
    }

    public Integer getTerminates4() {
        return terminates4;
    }

    public void setTerminates4(Integer terminates4) {
        this.terminates4 = terminates4;
    }

    public Report023 getReportId() {
        return reportId;
    }

    public void setReportId(Report023 reportId) {
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
        if (!(object instanceof Report023Detail)) {
            return false;
        }
        Report023Detail other = (Report023Detail) object;
        if ((this.reportDetailId == null && other.reportDetailId != null) || (this.reportDetailId != null && !this.reportDetailId.equals(other.reportDetailId))) {
            return false;
        }
        return true;
    }

    public Integer getCivil_cases2() {
        return civil_cases2;
    }

    public void setCivil_cases2(Integer civil_cases2) {
        this.civil_cases2 = civil_cases2;
    }

    public Integer getThessaloniki2() {
        return thessaloniki2;
    }

    public void setThessaloniki2(Integer thessaloniki2) {
        this.thessaloniki2 = thessaloniki2;
    }

    @Override
    public String toString() {
        return "Report023Detail{" + "reportDetailId=" + reportDetailId + ", cases=" + cases + ", oldCasesQuoted=" + oldCasesQuoted + ", newCasesQuoted=" + newCasesQuoted + ", sumCases=" + sumCases + ", adminCourt=" + adminCourt + ", criminalCases=" + criminalCases + ", civilCases=" + civilCases + ", rulingCases=" + rulingCases + ", rhetoricWaiting=" + rhetoricWaiting + ", progress=" + progress + ", investigator=" + investigator + ", nacc=" + nacc + ", attorney=" + attorney + ", prosecutors=" + prosecutors + ", civil_cases2=" + civil_cases2 + ", appeals=" + appeals + ", thessaloniki=" + thessaloniki + ", terminates=" + terminates + ", pending=" + pending + ", attorney2=" + attorney2 + ", prosecutors2=" + prosecutors2 + ", appeals2=" + appeals2 + ", thessaloniki2=" + thessaloniki2 + ", execution=" + execution + ", terminates2=" + terminates2 + ", progress2=" + progress2 + ", province=" + province + ", supreme=" + supreme + ", terminates3=" + terminates3 + ", considering=" + considering + ", terminates4=" + terminates4 + ", reportId=" + reportId + '}';
    }
}
