/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.dao.Report002Dao;
import com.ect.db.report.entity.ViewReport002;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Repository("report002Dao")
public class Report002DaoImpl extends BaseDao implements Report002Dao, Serializable{
    private static final long serialVersionUID = 6217871650920942205L;
    
    @Override
    public List<ViewReport002> findByStatus(Integer status) {
        
        return findNativeQuery("SELECT * FROM REPORT_002 WHERE FLOW_STATUS_ID = ? ", ViewReport002.class, status);
        
    }
    
    @Override
    public Report002 findByReportId(Integer reportId) {
    
        return (Report002) findUniqNativeQuery("SELECT * FROM REPORT_002 WHERE REPORT_ID = ? ", Report002.class, reportId);
        
    }
    
    @Override
    public List<Report002> checkDuppActivityInMonth002(Integer userGroupId, Integer activityId, int month){
    
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
        sdf.setCalendar(calendar);
        String date = sdf.format(new Date());
                
        if(date.startsWith("0")){
            date = date.replaceFirst("0", "");
        }
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     REPORT_002.REPORT_ID, REPORT_002.REPORT_CODE, REPORT_002.REPORT_DESC, REPORT_002.CREATED_DATE, REPORT_002.CREATED_USER, "
                + "                      REPORT_002.CREATED_USER_GROUP, REPORT_002.UPDATED_DATE, REPORT_002.UPDATED_USER, REPORT_002.STRATEGIC_ID, "
                + "                      REPORT_002.SUB_STRATEGIC_ID, REPORT_002.PLAN_ID, REPORT_002.PROJECT_ID, REPORT_002.ACTIVITY_ID, REPORT_002.REMARK, "
                + "                      REPORT_002.APPROVED_USER, REPORT_002.FLOW_STATUS_ID, REPORT_002.APPROVED_DATE, REPORT_002.REJECTED_USER, "
                + "                      REPORT_002.REJECTED_DATE, REPORT_002.REPORT_STATUS, ECT_USER.USER_GROUP_ID, ECT_USER.PROVINCE_ID "
                + "FROM         ECT_USER INNER JOIN "
                + "                      REPORT_002 ON ECT_USER.USER_ID = REPORT_002.CREATED_USER "
                + "WHERE   ECT_USER.USER_GROUP_ID = ? "
                + "AND  (REPORT_002.ACTIVITY_ID = ?) "
                + "AND (CONVERT(NVARCHAR(2),DATEPART(month,CREATED_DATE)) + '/' +CONVERT(NVARCHAR(4),DATEPART(year,CREATED_DATE))) = ? ");

        List<Report002> list = findNativeQuery(sql.toString(), Report002.class, userGroupId, activityId, month);
        
        return list;
        
    }
}
