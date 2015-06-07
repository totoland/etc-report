/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.ViewReport001SummaryDetail;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReport001Summary;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository
public class Report001DaoImpl extends BaseDao implements Report001Dao {

    @Override
    public List<ViewReport001> findByStatus(Integer status) {

        return findNativeQuery("SELECT * FROM REPORT_001 WHERE FLOW_STATUS_ID = ? ", ViewReport001.class, status);

    }

    @Override
    public Report001 findByReportId(Integer reportId) {

        return (Report001) findUniqNativeQuery("SELECT * FROM REPORT_001 WHERE REPORT_ID = ? ", Report001.class, reportId);

    }

    @Override
    public List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, Date month) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
        sdf.setCalendar(calendar);
        String date = sdf.format(month);

        if (date.startsWith("0")) {
            date = date.replaceFirst("0", "");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     REPORT_001.REPORT_ID, REPORT_001.REPORT_CODE, REPORT_001.REPORT_DESC, REPORT_001.CREATED_DATE, REPORT_001.CREATED_USER, "
                + "                      REPORT_001.CREATED_USER_GROUP, REPORT_001.UPDATED_DATE, REPORT_001.UPDATED_USER, REPORT_001.STRATEGIC_ID, "
                + "                      REPORT_001.SUB_STRATEGIC_ID, REPORT_001.PLAN_ID, REPORT_001.PROJECT_ID, REPORT_001.ACTIVITY_ID, REPORT_001.REMARK, "
                + "                      REPORT_001.APPROVED_USER, REPORT_001.FLOW_STATUS_ID, REPORT_001.APPROVED_DATE, REPORT_001.REJECTED_USER, "
                + "                      REPORT_001.REJECTED_DATE, REPORT_001.REPORT_STATUS, ECT_USER.USER_GROUP_ID, ECT_USER.PROVINCE_ID "
                + "FROM         ECT_USER INNER JOIN "
                + "                      REPORT_001 ON ECT_USER.USER_ID = REPORT_001.CREATED_USER "
                + "WHERE   ECT_USER.USER_GROUP_ID = ? "
                + "AND  (REPORT_001.ACTIVITY_ID = ?) "
                + "AND (CONVERT(NVARCHAR(2),DATEPART(month,CREATED_DATE)) + '/' +CONVERT(NVARCHAR(4),DATEPART(year,CREATED_DATE))) = ? ");

        List<Report001> list = findNativeQuery(sql.toString(), Report001.class, userGroupId, activityId, date);

        return list;

    }

    @Override
    public List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, String month, String year) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     REPORT_001.REPORT_ID, REPORT_001.REPORT_CODE, REPORT_001.REPORT_DESC, REPORT_001.CREATED_DATE, REPORT_001.CREATED_USER, "
                + "                      REPORT_001.CREATED_USER_GROUP, REPORT_001.UPDATED_DATE, REPORT_001.UPDATED_USER, REPORT_001.STRATEGIC_ID, "
                + "                      REPORT_001.SUB_STRATEGIC_ID, REPORT_001.PLAN_ID, REPORT_001.PROJECT_ID, REPORT_001.ACTIVITY_ID, REPORT_001.REMARK, "
                + "                      REPORT_001.APPROVED_USER, REPORT_001.FLOW_STATUS_ID, REPORT_001.APPROVED_DATE, REPORT_001.REJECTED_USER, "
                + "                      REPORT_001.REJECTED_DATE, REPORT_001.REPORT_STATUS, ECT_USER.USER_GROUP_ID, ECT_USER.PROVINCE_ID,REPORT_001.REPORT_MONTH,REPORT_001.REPORT_YEAR  "
                + "FROM         ECT_USER INNER JOIN "
                + "                      REPORT_001 ON ECT_USER.USER_ID = REPORT_001.CREATED_USER "
                + "WHERE   ECT_USER.USER_GROUP_ID = ? "
                + "AND  (REPORT_001.ACTIVITY_ID = ?) "
                + "AND REPORT_001.REPORT_MONTH = ? AND REPORT_001.REPORT_YEAR = ? ");

        List<Report001> list = findNativeQuery(sql.toString(), Report001.class, userGroupId, activityId, month, year);

        return list;

    }

    @Override
    public List<ViewReport001Summary> findByCriteria(ReportCriteria reportCriteria) {
        String SQL_REPORT_ORDER = "SELECT ROW_NUMBER() OVER (ORDER BY USER_GROUP_NAME ASC) AS ROW_NO, X.* FROM ( SELECT * FROM VIEW_REPORT001 {0}) X";

        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");

        List<Object> listValue = new ArrayList();

        if (reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()) {
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
//        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
//            listValue.add(reportCriteria.getYear());
//            sql.append(" AND REPORT_YEAR = ? ");
//        }
        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
            if (reportCriteria.isFiscalYear()) {
                sql.append(" AND " + toFiscalYear(reportCriteria.getYear()));
            } else {
                listValue.add(reportCriteria.getYear());
                sql.append(" AND REPORT_YEAR = ? ");
            }
        }
        if (reportCriteria.getGroupIds() != null && !reportCriteria.getGroupIds().isEmpty()) {
            //listValue.add(toStringArray(reportCriteria.getGroupIds()));
            sql.append(" AND USER_GROUP_ID in (").append(toStringArray(reportCriteria.getGroupIds())).append(") ");
        }
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        if (reportCriteria.getStrategic() != null && !reportCriteria.getStrategic().isEmpty()
                && !reportCriteria.getStrategic().equals("-1")) {
            listValue.add(reportCriteria.getStrategic());
            sql.append(" AND STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getSubStrategic() != null && !reportCriteria.getSubStrategic().isEmpty()
                && !reportCriteria.getSubStrategic().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND SUB_STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getPlan() != null && !reportCriteria.getPlan().isEmpty()
                && !reportCriteria.getPlan().equals("-1")) {
            listValue.add(reportCriteria.getPlan());
            sql.append(" AND PLAN_ID = ? ");
        }
        if (reportCriteria.getProject() != null && !reportCriteria.getProject().isEmpty()
                && !reportCriteria.getProject().equals("-1")) {
            listValue.add(reportCriteria.getProject());
            sql.append(" AND PROJECT_ID = ? ");
        }
        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");

        MessageFormat messageFormat = new MessageFormat(SQL_REPORT_ORDER);
        String SQL = messageFormat.format(SQL_REPORT_ORDER, sql.toString());
        SQL = genSQLPaggin(SQL, reportCriteria.getStartRow(), reportCriteria.getMaxRow());

        logger.trace("messageFormat.toString() : " + SQL);

        List<ViewReport001Summary> list = findNativeQuery(SQL, ViewReport001Summary.class, listValue);

        return list;
    }

    @Override
    public Integer countCriteria(ReportCriteria reportCriteria) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM VIEW_REPORT001 WHERE 1 = 1 ");

        List<Object> listValue = new ArrayList();

        if (reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()) {
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
//        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
//            listValue.add(reportCriteria.getYear());
//            sql.append(" AND REPORT_YEAR = ? ");
//        }
        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
            if (reportCriteria.isFiscalYear()) {
                sql.append(" AND " + toFiscalYear(reportCriteria.getYear()));
            } else {
                listValue.add(reportCriteria.getYear());
                sql.append(" AND REPORT_YEAR = ? ");
            }
        }
        if (reportCriteria.getGroupIds() != null && !reportCriteria.getGroupIds().isEmpty()) {
            //listValue.add(toStringArray(reportCriteria.getGroupIds()));
            sql.append(" AND USER_GROUP_ID in (").append(toStringArray(reportCriteria.getGroupIds())).append(") ");
        }
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        if (reportCriteria.getStrategic() != null && !reportCriteria.getStrategic().isEmpty()
                && !reportCriteria.getStrategic().equals("-1")) {
            listValue.add(reportCriteria.getStrategic());
            sql.append(" AND STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getSubStrategic() != null && !reportCriteria.getSubStrategic().isEmpty()
                && !reportCriteria.getSubStrategic().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND SUB_STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getPlan() != null && !reportCriteria.getPlan().isEmpty()
                && !reportCriteria.getPlan().equals("-1")) {
            listValue.add(reportCriteria.getPlan());
            sql.append(" AND PLAN_ID = ? ");
        }
        if (reportCriteria.getProject() != null && !reportCriteria.getProject().isEmpty()
                && !reportCriteria.getProject().equals("-1")) {
            listValue.add(reportCriteria.getProject());
            sql.append(" AND PROJECT_ID = ? ");
        }

        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");

        logger.trace(sql.toString());
        logger.trace("listValue : " + listValue);

        return countNativeQuery(sql.toString(), listValue);
    }

    protected String toStringArray(List<String> list) {

        String result = "";

        for (String r : list) {
            result += "," + r;
        }

        return result.replaceFirst(",", "");
    }

    @Override
    public List<ViewReport001SummaryDetail> findReport001DetailByCriteria(ReportCriteria reportCriteria) {
        String SQL_REPORT_ORDER = " SELECT X.* "
                + ",ISNULL(BUDGET_SET,0) AS BUDGET_SET,ISNULL(BUDGET_REAL,0) AS BUDGET_REAL "
                + "FROM ( "
                + " "
                + "SELECT ROW_NUMBER() OVER (ORDER BY STRATEGIC_ID, "
                + "PROJECT_ID, "
                + "ACTIVITY_ID ASC) AS ROW_NO, "
                + "STRATEGIC_ID       , "
                + "PROJECT_ID       , "
                + "ACTIVITY_ID       , "
                + "STRATEGIC_NAME       , "
                + "PROJECT_NAME       , "
                + "ACTIVITY_NAME    , "
                + "MAX(REPORT_MONTH_YEAR) AS REPORT_MONTH_YEAR "
                + "FROM VIEW_REPORT001 {0} "
                + "GROUP BY STRATEGIC_ID       , "
                + "PROJECT_ID       , "
                + "STRATEGIC_NAME       , "
                + "SUB_STRATEGIC_NAME       , "
                + "PROJECT_NAME       , "
                + "ACTIVITY_ID       , "
                + "ACTIVITY_NAME	 "
                + " "
                + ") X "
                + "INNER JOIN VIEW_REPORT001 ON X.ACTIVITY_ID = VIEW_REPORT001.ACTIVITY_ID "
                + "AND X.REPORT_MONTH_YEAR = VIEW_REPORT001.REPORT_MONTH_YEAR "
                + "ORDER BY STRATEGIC_ID, "
                + "PROJECT_ID, "
                + "ACTIVITY_ID ASC ";

        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");

        List<Object> listValue = new ArrayList();

        if (reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()) {
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
            if (reportCriteria.isFiscalYear()) {
                sql.append(" AND ").append(toFiscalYear(reportCriteria.getYear()));
            } else {
                listValue.add(reportCriteria.getYear());
                sql.append(" AND REPORT_YEAR = ? ");
            }
        }
        if (reportCriteria.getGroupIds() != null && !reportCriteria.getGroupIds().isEmpty()) {
            //listValue.add(toStringArray(reportCriteria.getGroupIds()));
            sql.append(" AND USER_GROUP_ID in (").append(toStringArray(reportCriteria.getGroupIds())).append(") ");
        }
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        if (reportCriteria.getStrategic() != null && !reportCriteria.getStrategic().isEmpty()
                && !reportCriteria.getStrategic().equals("-1")) {
            listValue.add(reportCriteria.getStrategic());
            sql.append(" AND STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getSubStrategic() != null && !reportCriteria.getSubStrategic().isEmpty()
                && !reportCriteria.getSubStrategic().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND SUB_STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getPlan() != null && !reportCriteria.getPlan().isEmpty()
                && !reportCriteria.getPlan().equals("-1")) {
            listValue.add(reportCriteria.getPlan());
            sql.append(" AND PLAN_ID = ? ");
        }
        if (reportCriteria.getProject() != null && !reportCriteria.getProject().isEmpty()
                && !reportCriteria.getProject().equals("-1")) {
            listValue.add(reportCriteria.getProject());
            sql.append(" AND PROJECT_ID = ? ");
        }
        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");

        MessageFormat messageFormat = new MessageFormat(SQL_REPORT_ORDER);
        String SQL = messageFormat.format(SQL_REPORT_ORDER, sql.toString());

        logger.trace("messageFormat.toString() : " + SQL);

        List<ViewReport001SummaryDetail> list = findNativeQuery(SQL, ViewReport001SummaryDetail.class, listValue);

        return list;
    }

    @Override
    public Integer countSummaryDetailCriteria(ReportCriteria reportCriteria) {
        String REPORT_DETAIL = "SELECT COUNT(1) FROM ( SELECT STRATEGIC_ID "
                + "      ,PROJECT_ID "
                + "      ,ACTIVITY_ID "
                + "      ,STRATEGIC_NAME "
                + "      ,PROJECT_NAME "
                + "      ,ACTIVITY_NAME "
                + "      ,SUM(BUDGET_SET) AS BUDGET_SET "
                + "      ,SUM(BUDGET_REAL) AS BUDGET_REAL "
                + "  FROM VIEW_REPORT001 {0} "
                + "  GROUP BY STRATEGIC_ID "
                + "      ,PROJECT_ID "
                + "      ,STRATEGIC_NAME "
                + "      ,SUB_STRATEGIC_NAME "
                + "      ,PROJECT_NAME "
                + "      ,ACTIVITY_ID "
                + "      ,ACTIVITY_NAME ) X";

        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1 = 1 ");

        List<Object> listValue = new ArrayList();

        if (reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()) {
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
        if (reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()) {
            listValue.add(reportCriteria.getYear());
            sql.append(" AND REPORT_YEAR = ? ");
        }
        if (reportCriteria.getGroupIds() != null && !reportCriteria.getGroupIds().isEmpty()) {
            //listValue.add(toStringArray(reportCriteria.getGroupIds()));
            sql.append(" AND USER_GROUP_ID in (").append(toStringArray(reportCriteria.getGroupIds())).append(") ");
        }
        if (reportCriteria.getStatus() != null && !reportCriteria.getStatus().isEmpty()) {
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        if (reportCriteria.getStrategic() != null && !reportCriteria.getStrategic().isEmpty()
                && !reportCriteria.getStrategic().equals("-1")) {
            listValue.add(reportCriteria.getStrategic());
            sql.append(" AND STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getSubStrategic() != null && !reportCriteria.getSubStrategic().isEmpty()
                && !reportCriteria.getSubStrategic().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND SUB_STRATEGIC_ID = ? ");
        }
        if (reportCriteria.getPlan() != null && !reportCriteria.getPlan().isEmpty()
                && !reportCriteria.getPlan().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND PLAN_ID = ? ");
        }
        if (reportCriteria.getProject() != null && !reportCriteria.getProject().isEmpty()
                && !reportCriteria.getProject().equals("-1")) {
            listValue.add(reportCriteria.getSubStrategic());
            sql.append(" AND PROJECT_ID = ? ");
        }

        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");

        MessageFormat messageFormat = new MessageFormat(REPORT_DETAIL);
        String SQL = messageFormat.format(REPORT_DETAIL, sql.toString());

        logger.trace(SQL.toString());
        logger.trace("listValue : {}" + listValue);

        return countNativeQuery(SQL, listValue);
    }

    private String toFiscalYear(String year) {
        int selectYear = Integer.parseInt(year) - 543;
        Integer fyear = selectYear - 1;

        return  "( "
                + " REPORT_MONTH_YEAR "
                + " BETWEEN   "
                + "	CAST('"+fyear+"' + RIGHT('0' + '10',2) + RIGHT('0' + '1', 2) AS DATETIME) AND CAST('"+selectYear+"' +RIGHT('0' + '9', 2) + RIGHT('0' + '30', 2)     AS DATETIME)  "
                + " ) ";
    }
}
