/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.ViewReportByStatusDao;
import com.ect.db.report.entity.ViewReportStatus;
import java.text.MessageFormat;
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
    private String SQL_REPORT = "SELECT ROW_NUMBER() OVER (ORDER BY CREATED_DATE DESC) AS ROW_NO, X.* FROM ( SELECT * FROM VIEW_REPORT_STATUS ) X";
    private String SQL_REPORT_ORDER = "SELECT ROW_NUMBER() OVER (ORDER BY {0} {1}) AS ROW_NO, X.* FROM ( SELECT * FROM VIEW_REPORT_STATUS ) X";

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatus) {

        String sql = SQL_REPORT + " WHERE FLOW_STATUS_ID = ?";

        return super.findNativeQuery(sql, ViewReportStatus.class, flowStatus);
    }

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatus, Integer reportStatus) {

        String sql = SQL_REPORT + " WHERE FLOW_STATUS_ID = ? AND REPORT_STATUS = ?";

        return super.findNativeQuery(sql, ViewReportStatus.class, flowStatus, reportStatus);
    }

    @Override
    public Integer updateReportStatusApprove(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser) {

        String sql = "UPDATE " + reportName + " SET REPORT_STATUS = 200 ,FLOW_STATUS_ID = ?, APPROVED_DATE = ? , APPROVED_USER = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, reportId);

    }

    @Override
    public Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark) {

        String sql = "UPDATE " + reportName + " SET REPORT_STATUS = 401, FLOW_STATUS_ID = ?, REJECTED_DATE = ? , REJECTED_USER = ? ,REMARK = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, remark, reportId);

    }

    @Override
    public List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria) {

        StringBuilder sql = new StringBuilder();

        if (reportCriteria.getSortField() != null && !reportCriteria.getSortField().isEmpty()) {

            String sortField = reportCriteria.getSortField();
            
            if (sortField.equals("documentNo")) {
                sortField = "REPORT_CODE+CONVERT(NVARCHAR(4),REPORT_ID)";
            }

            MessageFormat messageFormat = new MessageFormat(SQL_REPORT);
            sql.append(messageFormat.format(SQL_REPORT_ORDER, sortField, reportCriteria.getSortOrder()));

        } else {

            sql.append(SQL_REPORT);

        }

        sql.append(" WHERE 1=1");

        if (reportCriteria.getFlowStatus() != null && !reportCriteria.getFlowStatus().isEmpty()) {

            sql.append(" AND FLOW_STATUS_ID = ").append("'").append(reportCriteria.getFlowStatus()).append("'");

        }
        
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {

            sql.append(" AND REPORT_STATUS = ").append(reportCriteria.getStatus());

        }

        if (reportCriteria.getReportCode() != null && !reportCriteria.getReportCode().isEmpty()) {

            sql.append(" AND REPORT_CODE = ").append("'").append(reportCriteria.getReportCode()).append("'");

        }

        return super.findNativePagginQuery(sql.toString(), reportCriteria.getStartRow(), reportCriteria.getMaxRow(), ViewReportStatus.class);
    }

    @Override
    public Integer countByCriteria(ReportCriteria reportCriteria) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM VIEW_REPORT_STATUS");
        sql.append(" WHERE 1=1");

        if (reportCriteria.getFlowStatus() != null && !reportCriteria.getFlowStatus().isEmpty()) {

            sql.append(" AND FLOW_STATUS_ID = ").append("'").append(reportCriteria.getFlowStatus()).append("'");

        }
        
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {

            sql.append(" AND REPORT_STATUS = ").append(reportCriteria.getStatus());

        }

        if (reportCriteria.getReportCode() != null && !reportCriteria.getReportCode().isEmpty()) {

            sql.append(" AND REPORT_CODE = ").append("'").append(reportCriteria.getReportCode()).append("'");

        }

        return super.countNativeQuery(sql.toString());
    }
}