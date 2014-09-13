/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.entity.Report003;
import com.ect.db.report.entity.Report004;
import com.ect.db.report.entity.Report005;
import com.ect.db.report.entity.Report006;
import com.ect.db.report.entity.Report007;
import com.ect.db.report.entity.Report008;
import com.ect.db.report.entity.Report009;
import com.ect.db.report.entity.Report010;
import com.ect.db.report.entity.Report011;
import com.ect.db.report.entity.Report012;
import com.ect.db.report.entity.Report013;
import com.ect.db.report.entity.Report014;
import com.ect.db.report.entity.Report015;
import com.ect.db.report.entity.Report016;
import com.ect.db.report.entity.Report017;
import com.ect.db.report.entity.Report018;
import com.ect.db.report.entity.Report019;
import com.ect.db.report.entity.Report020;
import com.ect.db.report.entity.Report021;
import com.ect.db.report.entity.Report022;
import com.ect.db.report.entity.Report023;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReport001Summary;
import com.ect.db.report.entity.ViewReportStatus;
import java.util.Date;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface ReportService {

    List<ViewReportStatus> findReportByStatus(Integer flowStatusId);

    Integer updateReportStatusApprove(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser);

    Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark);

    List<ViewReport001> findByStatus(Integer status);

    Report001 findByReport001ById(Integer reportId);

    List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria);
    
    List<ViewReportStatus> findReportByCriteria(ReportCriteria reportCriteria);

    Report002 findByReport002ById(Integer paramReportId);

    List<ViewReportStatus> findReportByStatus(Integer flowStatusId, Integer reportStatus);

    Report003 findByReport003ById(Integer paramReportId);

    Report004 findByReport004ById(Integer paramReportId);

    Integer countByCriteria(ReportCriteria reportCriteria);
    
    Integer countReportByCriteria(ReportCriteria reportCriteria);

    Report005 findByReport005ById(Integer paramReportId);

    Report006 findByReport006ById(Integer paramReportId);

    Report007 findByReport007ById(Integer paramReportId);    

    Report008 findByReport008ById(Integer paramReportId);

    Report009 findByReport009ById(Integer paramReportId);

    Report010 findByReport010ById(Integer paramReportId);

    Report011 findByReport011ById(Integer paramReportId);
    
    Report012 findByReport012ById(Integer paramReportId);
    
    Report013 findByReport013ById(Integer paramReportId);
    
    Report014 findByReport014ById(Integer paramReportId);
    
    Report015 findByReport015ById(Integer paramReportId);
    
    Report016 findByReport016ById(Integer paramReportId);
    
    Report017 findByReport017ById(Integer paramReportId);
    
    Report018 findByReport018ById(Integer paramReportId);
    
    Report019 findByReport019ById(Integer paramReportId);
    
    Report020 findByReport020ById(Integer paramReportId);
    
    Report021 findByReport021ById(Integer paramReportId);
    
    Report022 findByReport022ById(Integer paramReportId);
    
    Report023 findByReport023ById(Integer paramReportId);
    
    List<Report001> checkDuppActivityInMonth(Integer userGroupId,Integer activityId, Date month);

    List<Report002> checkDuppActivityInMonthReport002(Integer userGroupId, Integer activityId, int i);
    
    Integer deleteReport(String reportName, Integer reportId);
    
    List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, String month,String year);
    
    boolean checkDuppReportInMonth(int userGroupId, String reportName, String month,String year);
    
    List<ViewReport001Summary> findReport001ByCriteria(ReportCriteria reportCriteria);
    
    Integer countReport001ByCriteria(ReportCriteria reportCriteria);
}
