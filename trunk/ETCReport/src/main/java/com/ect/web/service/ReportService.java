/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReportStatus;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface ReportService {
    
    List<ViewReportStatus>findReportByStatus(Integer flowStatusId);
    
    Integer updateReportFlowStatus(String reportName,Integer reportId,Integer flowStatusId,Integer approvedUser);
  
    Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark);
    
    List<ViewReport001> findByStatus(Integer status);

    ViewReport001 findByReportId(Integer reportId);
}
