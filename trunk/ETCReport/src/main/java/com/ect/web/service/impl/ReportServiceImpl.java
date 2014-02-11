/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.bean.ReportCriteria;
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
import com.ect.db.report.dao.Report023Dao;
import com.ect.db.report.dao.ViewReportByStatusDao;
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
import com.ect.db.report.entity.Report023;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.service.ReportService;
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
    Report023Dao report023Dao;
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
    public Report023 findByReport023ById(Integer paramReportId) {
        return report023Dao.findByReportId(paramReportId);
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
}
