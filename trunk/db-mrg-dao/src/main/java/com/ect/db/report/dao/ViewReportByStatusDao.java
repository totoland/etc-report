/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportStatus;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface ViewReportByStatusDao {
    
    List<ViewReportStatus> findReportByStatus(Integer flowStatus);
    
    Integer updateReportStatus(String reportName,Integer reportId,Integer flowStatusId,Integer approvedUser);
    
    Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark);
    
    List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria);
    
    List<ViewReportStatus> findReportByStatus(Integer flowStatus,Integer reportStatus);
    
}
