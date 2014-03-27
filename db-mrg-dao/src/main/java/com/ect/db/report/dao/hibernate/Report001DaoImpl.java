/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.ViewReport001;
import java.util.List;
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
    public List<Report001> checkDuppActivityInMonth(int userGroupId, int activityId, int month) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     REPORT_001.REPORT_ID, REPORT_001.REPORT_CODE, REPORT_001.REPORT_DESC, REPORT_001.CREATED_DATE, REPORT_001.CREATED_USER, "
                + "                      REPORT_001.CREATED_USER_GROUP, REPORT_001.UPDATED_DATE, REPORT_001.UPDATED_USER, REPORT_001.STRATEGIC_ID, "
                + "                      REPORT_001.SUB_STRATEGIC_ID, REPORT_001.PLAN_ID, REPORT_001.PROJECT_ID, REPORT_001.ACTIVITY_ID, REPORT_001.REMARK, "
                + "                      REPORT_001.APPROVED_USER, REPORT_001.FLOW_STATUS_ID, REPORT_001.APPROVED_DATE, REPORT_001.REJECTED_USER, "
                + "                      REPORT_001.REJECTED_DATE, REPORT_001.REPORT_STATUS, ECT_USER.USER_GROUP_ID, ECT_USER.PROVINCE_ID "
                + "FROM         ECT_USER INNER JOIN "
                + "                      REPORT_001 ON ECT_USER.USER_ID = REPORT_001.CREATED_USER\n"
                + "WHERE   ECT_USER.USER_GROUP_ID = ? "
                + "AND  (REPORT_001.ACTIVITY_ID = ?) "
                + "AND DATEPART(month, CREATED_DATE) = ?");

        List<Report001> list = findNativeQuery(sql.toString(), Report001.class, userGroupId, activityId, month);
        
        return list;

    }
}
