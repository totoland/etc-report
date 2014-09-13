/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReport001Summary;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
                
        if(date.startsWith("0")){
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
    public List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, String month,String year) {

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

        List<Report001> list = findNativeQuery(sql.toString(), Report001.class, userGroupId, activityId, month,year);
        
        return list;

    }

    @Override
    public List<ViewReport001Summary> findByCriteria(ReportCriteria reportCriteria) {
        String SQL_REPORT_ORDER = "SELECT ROW_NUMBER() OVER (ORDER BY USER_GROUP_NAME ASC) AS ROW_NO, X.* FROM ( SELECT * FROM VIEW_REPORT001 {0}) X";
        
        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");
        
        List<Object>listValue = new ArrayList();
        
        if(reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()){
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
        if(reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()){
            listValue.add(reportCriteria.getYear());
            sql.append(" AND REPORT_YEAR = ? ");
        }
        if(reportCriteria.getGroupIds()!= null && !reportCriteria.getGroupIds().isEmpty()){
            listValue.add(reportCriteria.getGroupIds());
            sql.append(" AND USER_GROUP_ID in (?) ");
        }
        if(reportCriteria.getStatus()!= null && !reportCriteria.getStatus().isEmpty()){
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        
        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");
        
        MessageFormat messageFormat = new MessageFormat(SQL_REPORT_ORDER);
        String SQL = messageFormat.format(SQL_REPORT_ORDER,sql.toString());
        SQL = genSQLPaggin(SQL, reportCriteria.getStartRow(), reportCriteria.getMaxRow());
        
        System.out.println("messageFormat.toString() : "+SQL);
        
        List<ViewReport001Summary> list = findNativeQuery(SQL, ViewReport001Summary.class, listValue);
        
        return list;
    }
    
    @Override
    public Integer countCriteria(ReportCriteria reportCriteria) {        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM VIEW_REPORT001 WHERE 1 = 1 ");
        
        List<Object>listValue = new ArrayList();
        
        if(reportCriteria.getMonth() != null && !reportCriteria.getMonth().isEmpty()){
            listValue.add(reportCriteria.getMonth());
            sql.append(" AND REPORT_MONTH = ? ");
        }
        if(reportCriteria.getYear() != null && !reportCriteria.getYear().isEmpty()){
            listValue.add(reportCriteria.getYear());
            sql.append(" AND REPORT_YEAR = ? ");
        }
        if(reportCriteria.getGroupIds()!= null && !reportCriteria.getGroupIds().isEmpty()){
            listValue.add(reportCriteria.getGroupIds());
            sql.append(" AND USER_GROUP_ID in (?) ");
        }
        if(reportCriteria.getStatus()!= null && !reportCriteria.getStatus().isEmpty()){
            listValue.add(reportCriteria.getStatus());
            sql.append(" AND FLOW_STATUS_ID = ? ");
        }
        
        //TODO: not fillter admin
        sql.append(" AND USER_GROUP_ID <> 109");
                
        System.out.println(sql.toString());
        System.out.println("listValue : "+listValue);
        
        return countNativeQuery(sql.toString(), listValue);
    }
}
