/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.report.dao.ViewReportByStatusDao;
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
    
}
