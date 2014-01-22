/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.Report001;
import com.ect.db.entity.Report002;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.dao.Report002Dao;
import com.ect.db.report.dao.ViewReportByStatusDao;
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
public class ReportServiceImpl implements ReportService{
    
    @Autowired
    ViewReportByStatusDao viewReportByStatusDao;
    
    @Autowired
    Report001Dao report001Dao;
    
    @Autowired
    Report002Dao report002Dao;
    
    @Override
    public List<ViewReportStatus>findReportByStatus(Integer flowStatusId){
        return viewReportByStatusDao.findReportByStatus(flowStatusId);
    }
    
    @Override
    public Integer updateReportFlowStatus(String reportName,Integer reportId,Integer flowStatusId,Integer approvedUser){
        return viewReportByStatusDao.updateReportStatus(reportName, reportId, flowStatusId,approvedUser);
    }
    
    @Override
    public Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark){
         return viewReportByStatusDao.updateReportStatusReject(reportName, reportId, flowStatusId,approvedUser,remark);
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
    public List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria){
        return viewReportByStatusDao.findByCriteria(reportCriteria);
    }

    @Override
    public Report002 findByReport002ById(Integer paramReportId) {
        return report002Dao.findByReportId(paramReportId);
    }
    
}
