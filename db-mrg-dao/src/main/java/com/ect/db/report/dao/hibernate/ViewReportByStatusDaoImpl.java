/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.ViewReportByStatusDao;
import com.ect.db.report.entity.ViewReportStatus;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("viewReportByStatusDao")
public class ViewReportByStatusDaoImpl extends BaseDao implements ViewReportByStatusDao {

    private static final Logger logger = LoggerFactory.getLogger(ViewReportByStatusDaoImpl.class);
    private static String SQL_REPORT_001 = "SELECT * FROM VIEW_REPORT_STATUS";

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatus) {

        String sql = SQL_REPORT_001 + " WHERE FLOW_STATUS_ID = ?";

        return super.findNativeQuery(sql, ViewReportStatus.class, flowStatus);
    }

    @Override
    public Integer updateReportStatus(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser) {

        String sql = "UPDATE " + reportName + " SET FLOW_STATUS_ID = ?, APPROVED_DATE = ? , APPROVED_USER = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, reportId);

    }
    
    @Override
    public Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark) {

        String sql = "UPDATE " + reportName + " SET FLOW_STATUS_ID = ?, REJECTED_DATE = ? , REJECTED_USER = ? ,REMARK = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, remark, reportId);

    }
    
    @Override
    public List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria){
        
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_REPORT_001);
        
        return super.findNativeQuery(sql.toString(), ViewReportStatus.class);
    }
}
