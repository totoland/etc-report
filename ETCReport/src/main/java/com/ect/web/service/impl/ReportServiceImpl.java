/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.ViewReport001SummaryDetail;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.dao.Report002Dao;
import com.ect.db.report.dao.Report003Dao;
import com.ect.db.report.dao.Report004Dao;
import com.ect.db.report.dao.Report005Dao;
import com.ect.db.report.dao.Report006Dao;
import com.ect.db.report.dao.Report007Dao;
import com.ect.db.report.dao.Report008Dao;
import com.ect.db.report.dao.Report009Dao;
import com.ect.db.report.dao.Report010Dao;
import com.ect.db.report.dao.Report011Dao;
import com.ect.db.report.dao.Report012Dao;
import com.ect.db.report.dao.Report013Dao;
import com.ect.db.report.dao.Report014Dao;
import com.ect.db.report.dao.Report015Dao;
import com.ect.db.report.dao.Report016Dao;
import com.ect.db.report.dao.Report017Dao;
import com.ect.db.report.dao.Report018Dao;
import com.ect.db.report.dao.Report019Dao;
import com.ect.db.report.dao.Report020Dao;
import com.ect.db.report.dao.Report021Dao;
import com.ect.db.report.dao.Report022Dao;
import com.ect.db.report.dao.Report023Dao;
import com.ect.db.report.dao.ViewReportByStatusDao;
import com.ect.db.report.dao.ViewReportExpressionDao;
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
import com.ect.db.report.entity.ViewReportExpression;
import com.ect.db.report.entity.ViewReportExpression014;
import com.ect.db.report.entity.ViewReportExpression017;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.service.ReportService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ViewReportByStatusDao viewReportByStatusDao;
    @Autowired
    ViewReportByStatusDao viewReportByStatusCenterDaoImpl;
    @Autowired
    Report001Dao report001Dao;
    @Autowired
    Report002Dao report002Dao;
    @Autowired
    Report003Dao report003Dao;
    @Autowired
    Report004Dao report004Dao;
    @Autowired
    Report005Dao report005Dao;
    @Autowired
    Report006Dao report006Dao;
    @Autowired
    Report007Dao report007Dao;
    @Autowired
    Report008Dao report008Dao;
    @Autowired
    Report009Dao report009Dao;
    @Autowired
    Report010Dao report010Dao;
    @Autowired
    Report011Dao report011Dao;
    @Autowired
    Report012Dao report012Dao;
    @Autowired
    Report013Dao report013Dao;
    @Autowired
    Report014Dao report014Dao;
    @Autowired
    Report015Dao report015Dao;
    @Autowired
    Report016Dao report016Dao;
    @Autowired
    Report017Dao report017Dao;
    @Autowired
    Report018Dao report018Dao;
    @Autowired
    Report019Dao report019Dao;
    @Autowired
    Report020Dao report020Dao;
    @Autowired
    Report021Dao report021Dao;
    @Autowired
    Report022Dao report022Dao;
    @Autowired
    Report023Dao report023Dao;
    @Autowired
    ViewReportExpressionDao viewReportExpressionDao;

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatusId) {
        return viewReportByStatusDao.findReportByStatus(flowStatusId);
    }

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatusId, Integer reportStatus) {
        return viewReportByStatusDao.findReportByStatus(flowStatusId, reportStatus);
    }

    @Override
    public Integer updateReportStatusApprove(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser) {
        return viewReportByStatusDao.updateReportStatusApprove(reportName, reportId, flowStatusId, approvedUser);
    }

    @Override
    public Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark) {
        return viewReportByStatusDao.updateReportStatusReject(reportName, reportId, flowStatusId, approvedUser, remark);
    }

    @Override
    public List<ViewReport001> findByStatus(Integer status) {
        return report001Dao.findByStatus(status);
    }

    @Override
    public Report001 findByReport001ById(Integer reportId) {
        return report001Dao.findByReportId(reportId);
    }

    @Override
    public List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria) {
        return viewReportByStatusDao.findByCriteria(reportCriteria);
    }

    @Override
    public Report002 findByReport002ById(Integer paramReportId) {
        return report002Dao.findByReportId(paramReportId);
    }

    @Override
    public Report003 findByReport003ById(Integer paramReportId) {
        return report003Dao.findByReportId(paramReportId);
    }

    @Override
    public Report004 findByReport004ById(Integer paramReportId) {
        return report004Dao.findByReportId(paramReportId);
    }

    @Override
    public Integer countByCriteria(ReportCriteria reportCriteria) {
        return viewReportByStatusDao.countByCriteria(reportCriteria);
    }

    @Override
    public Report005 findByReport005ById(Integer paramReportId) {
        return report005Dao.findByReportId(paramReportId);
    }

    @Override
    public Report006 findByReport006ById(Integer paramReportId) {
        return report006Dao.findByReportId(paramReportId);
    }

    @Override
    public Report007 findByReport007ById(Integer paramReportId) {
        return report007Dao.findByReportId(paramReportId);
    }

    @Override
    public Report008 findByReport008ById(Integer paramReportId) {
        return report008Dao.findByReportId(paramReportId);
    }

    @Override
    public Report009 findByReport009ById(Integer paramReportId) {
        return report009Dao.findByReportId(paramReportId);
    }

    @Override
    public Report010 findByReport010ById(Integer paramReportId) {
        return report010Dao.findByReportId(paramReportId);
    }

    @Override
    public Report011 findByReport011ById(Integer paramReportId) {
        return report011Dao.findByReportId(paramReportId);
    }

    @Override
    public Report012 findByReport012ById(Integer paramReportId) {
        return report012Dao.findByReportId(paramReportId);
    }

    @Override
    public Report013 findByReport013ById(Integer paramReportId) {
        return report013Dao.findByReportId(paramReportId);
    }

    @Override
    public Report014 findByReport014ById(Integer paramReportId) {
        return report014Dao.findByReportId(paramReportId);
    }

    @Override
    public Report015 findByReport015ById(Integer paramReportId) {
        return report015Dao.findByReportId(paramReportId);
    }

    @Override
    public Report016 findByReport016ById(Integer paramReportId) {
        return report016Dao.findByReportId(paramReportId);
    }

    @Override
    public Report017 findByReport017ById(Integer paramReportId) {
        return report017Dao.findByReportId(paramReportId);
    }

    @Override
    public Report018 findByReport018ById(Integer paramReportId) {
        return report018Dao.findByReportId(paramReportId);
    }

    @Override
    public Report019 findByReport019ById(Integer paramReportId) {
        return report019Dao.findByReportId(paramReportId);
    }

    @Override
    public Report020 findByReport020ById(Integer paramReportId) {
        return report020Dao.findByReportId(paramReportId);
    }

    @Override
    public Report021 findByReport021ById(Integer paramReportId) {
        return report021Dao.findByReportId(paramReportId);
    }

    @Override
    public Report022 findByReport022ById(Integer paramReportId) {
        return report022Dao.findByReportId(paramReportId);
    }

    @Override
    public Report023 findByReport023ById(Integer paramReportId) {
        return report023Dao.findByReportId(paramReportId);
    }

    @Override
    public List<Report001> checkDuppActivityInMonth(Integer userGroupId, Integer activityId, Date month) {
        return report001Dao.checkDuppActivityInMonth(userGroupId, activityId, month);
    }

    @Override
    public List<Report002> checkDuppActivityInMonthReport002(Integer userGroupId, Integer activityId, int month) {
        return report002Dao.checkDuppActivityInMonth002(userGroupId, activityId, month);
    }
    
    @Override
    public Integer deleteReport(String reportName, Integer reportId){
        return viewReportByStatusDao.deleteReport(reportName, reportId);
    }

    @Override
    public List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, String month,String year) {
        return report001Dao.checkDuppActivityInMonth(userGroupId, activityId, month,year);
    }

    @Override
    public boolean checkDuppReportInMonth(int userGroupId, String reportName, String month,String year) {
        return viewReportByStatusDao.checkDuppReportInMonth(userGroupId, reportName, month,year);
    }

    @Override
    public List<ViewReportStatus> findReportByCriteria(ReportCriteria reportCriteria) {
        return viewReportByStatusCenterDaoImpl.findByCriteria(reportCriteria);
    }

    @Override
    public Integer countReportByCriteria(ReportCriteria reportCriteria) {
        return viewReportByStatusCenterDaoImpl.countByCriteria(reportCriteria);
    }

    @Override
    public List<ViewReport001Summary> findReport001ByCriteria(ReportCriteria reportCriteria) {
        return report001Dao.findByCriteria(reportCriteria);
    }

    @Override
    public Integer countReport001ByCriteria(ReportCriteria reportCriteria) {
        return report001Dao.countCriteria(reportCriteria);
    }
    
    @Override
    public List<ViewReportExpression> findReportExpressionByCriteria(ReportCriteria criteria){
        return viewReportExpressionDao.findByCriteria(criteria);
    }

    @Override
    public List<ViewReportExpression> findReportExpression011ByCriteria(ReportCriteria criteria) {
        return viewReportExpressionDao.findReport011ByCriteria(criteria);
    }

    @Override
    public List<ViewReportExpression> findReportExpression012ByCriteria(ReportCriteria reportCriteria) {
        return viewReportExpressionDao.findReport012ByCriteria(reportCriteria);
    }

    @Override
    public List<ViewReportExpression014> findReportExpression014ByCriteria(ReportCriteria reportCriteria) {
        return viewReportExpressionDao.findReport014ByCriteria(reportCriteria);
    }

    @Override
    public List<ViewReportExpression017> findReportExpression017ByCriteria(ReportCriteria reportCriteria) {
        return viewReportExpressionDao.findReport017ByCriteria(reportCriteria);
    }

    @Override
    public List<ViewReport001SummaryDetail> findReport001DetailByCriteria(ReportCriteria reportCriteria) {
        return report001Dao.findReport001DetailByCriteria(reportCriteria);
    }

    @Override
    public Integer countSummaryDetailCriteria(ReportCriteria reportCriteria) {
        return report001Dao.countSummaryDetailCriteria(reportCriteria);
    }
}
