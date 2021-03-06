/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.ViewReport001SummaryDetail;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReport001Summary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface Report001Dao {

    List<ViewReport001> findByStatus(Integer status);

    Report001 findByReportId(Integer reportId);
    
    List<Report001> checkDuppActivityInMonth(int userGroupId,int activityId,Date date);
    
    List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, String month,String year);
    
    List<ViewReport001Summary> findByCriteria(ReportCriteria reportCriteria);
    
    Integer countCriteria(ReportCriteria reportCriteria);

    List<ViewReport001SummaryDetail> findReport001DetailByCriteria(ReportCriteria reportCriteria);
    
    Integer countSummaryDetailCriteria(ReportCriteria reportCriteria);
    
}
