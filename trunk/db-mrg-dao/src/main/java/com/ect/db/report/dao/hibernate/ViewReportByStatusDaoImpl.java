/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

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
    private static String SQL_REPORT_001 = "SELECT   ROW_NUMBER() OVER(ORDER BY REPORT_ID) AS ROW_NO ,   REPORT_001.REPORT_ID, REPORT_001.REPORT_CODE, REPORT_001.REPORT_DESC, REPORT_001.CREATED_DATE, REPORT_001.CREATED_USER,  "
            + "                      REPORT_001.UPDATED_DATE, REPORT_001.UPDATED_USER, REPORT_001.REMARK, REPORT_001.APPROVED_USER, REPORT_001.FLOW_STATUS_ID,  "
            + "                      ECT_USER.USERNAME AS CREATED_USER_NAME, ECT_FLOW_STATUS.FLOW_STATUS_NAME, ECT_USER_1.USERNAME AS UPDATED_USER_NAME,  "
            + "                      ECT_USER_2.USERNAME AS APPROVED_USER_NAME, REPORT_001.APPROVED_DATE, ECT_USER_3.USERNAME AS REJECTED_USER_NAME, REPORT_001.REJECTED_USER,  "
            + "                      REPORT_001.REJECTED_DATE "
            + "   FROM         REPORT_001 INNER JOIN "
            + "                      ECT_USER ON REPORT_001.CREATED_USER = ECT_USER.USER_ID INNER JOIN "
            + "                      ECT_FLOW_STATUS ON REPORT_001.FLOW_STATUS_ID = ECT_FLOW_STATUS.FLOW_STATUS_ID LEFT OUTER JOIN "
            + "                      ECT_USER AS ECT_USER_3 ON REPORT_001.REJECTED_USER = ECT_USER_3.USER_ID LEFT OUTER JOIN "
            + "                      ECT_USER AS ECT_USER_2 ON REPORT_001.APPROVED_USER = ECT_USER_2.USER_ID LEFT OUTER JOIN "
            + "                      ECT_USER AS ECT_USER_1 ON REPORT_001.UPDATED_USER = ECT_USER_1.USER_ID";

    @Override
    public List<ViewReportStatus> findReportByStatus(Integer flowStatus) {

        String sql = SQL_REPORT_001 + " WHERE ECT_FLOW_STATUS.FLOW_STATUS_ID = ?";

        return super.findNativeQuery(sql, ViewReportStatus.class, flowStatus);
    }

    @Override
    public Integer updateReportStatus(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser) {

        String sql = "UPDATE " + reportName + " SET FLOW_STATUS_ID = ?, APPROVED_DATE = ? , APPROVED_USER = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, reportId);

    }
    
    public Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark) {

        String sql = "UPDATE " + reportName + " SET FLOW_STATUS_ID = ?, REJECTED_DATE = ? , REJECTED_USER = ? ,REMARK = ? WHERE REPORT_ID = ? ";

        logger.trace("updateReportStatus SQL : {}", sql);

        return updateNativeQuery(sql, flowStatusId, new Date(), approvedUser, remark, reportId);

    }
}
