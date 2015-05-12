/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.ViewReportExpressionDao;
import com.ect.db.report.entity.ViewReportExpression;
import com.ect.db.report.entity.ViewReportExpression014;
import com.ect.db.report.entity.ViewReportExpression017;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Totoland
 */
@Repository
public class ViewReportExpressionDaoImpl extends BaseDao implements ViewReportExpressionDao {

    @Override
    public List<ViewReportExpression> findByCriteria(ReportCriteria criteria) {

        String SQL = "SELECT        INSTITUTION, ISNULL(SUM(SUM_ALL_AMOUNT), 0) AS ALL_AMOUNT, ISNULL(SUM(SUM_AT_CENTER), 0) AS AT_CENTER, ISNULL(SUM(SUM_AT_ECT_PROVINCE), 0) AS AT_ECT_PROVINCE,  "
                + "                         ISNULL(SUM(SUM_ECT_RESOLVE), 0) AS ECT_RESOLVE, ISNULL(SUM(SUM_ANALYST_REMAIN), 0) AS ANALYST_REMAIN, ISNULL(SUM(SUM_OFFER_ECT), 0) AS OFFER_ECT,  "
                + "                         ISNULL(SUM(SUM_ACCESS_COMMITTEE), 0) AS ACCESS_COMMITTEE, ISNULL(SUM(SUM_ON_AGENDA), 0) AS ON_AGENDA "
                + "FROM            ( "
                + "SELECT     REPORT_011_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_011_DETAIL.ALL_AMOUNT), 0) AS SUM_ALL_AMOUNT, ISNULL(SUM(REPORT_011_DETAIL.AT_CENTER),  "
                + "                      0) AS SUM_AT_CENTER, ISNULL(SUM(REPORT_011_DETAIL.AT_ECT_PROVINCE), 0) AS SUM_AT_ECT_PROVINCE,  "
                + "                      ISNULL(SUM(REPORT_011_DETAIL.ECT_RESOLVE), 0) AS SUM_ECT_RESOLVE, ISNULL(SUM(REPORT_011_DETAIL.ANALYST_REMAIN), 0)  "
                + "                      AS SUM_ANALYST_REMAIN, ISNULL(SUM(REPORT_011_DETAIL.OFFER_ECT), 0) AS SUM_OFFER_ECT, ISNULL(SUM(REPORT_011_DETAIL.ACCESS_COMMITTEE), 0)  "
                + "                      AS SUM_ACCESS_COMMITTEE, ISNULL(SUM(REPORT_011_DETAIL.ON_AGENDA), 0) AS SUM_ON_AGENDA "
                + "FROM         REPORT_011_DETAIL INNER JOIN "
                + "                      REPORT_011 ON REPORT_011_DETAIL.REPORT_ID = REPORT_011.REPORT_ID "
                + "WHERE REPORT_011.REPORT_YEAR = ? AND REPORT_011.REPORT_MONTH = ? "
                + "GROUP BY REPORT_011_DETAIL.INSTITUTION "
                + "UNION ALL "
                + "SELECT     REPORT_012_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_012_DETAIL.ALL_AMOUNT), 0) AS SUM_ALL_AMOUNT, ISNULL(SUM(REPORT_012_DETAIL.AT_CENTER),  "
                + "                      0) AS SUM_AT_CENTER, ISNULL(SUM(REPORT_012_DETAIL.AT_ECT_PROVINCE), 0) AS SUM_AT_ECT_PROVINCE,  "
                + "                      ISNULL(SUM(REPORT_012_DETAIL.ECT_RESOLVE), 0) AS SUM_ECT_RESOLVE, ISNULL(SUM(REPORT_012_DETAIL.ANALYST_REMAIN), 0)  "
                + "                      AS SUM_ANALYST_REMAIN, ISNULL(SUM(REPORT_012_DETAIL.OFFER_ECT), 0) AS SUM_OFFER_ECT, ISNULL(SUM(REPORT_012_DETAIL.ACCESS_COMMITTEE), 0)  "
                + "                      AS SUM_ACCESS_COMMITTEE, ISNULL(SUM(REPORT_012_DETAIL.ON_AGENDA), 0) AS SUM_ON_AGENDA "
                + "FROM         REPORT_012_DETAIL INNER JOIN "
                + "                      REPORT_012 ON REPORT_012_DETAIL.REPORT_ID = REPORT_012.REPORT_ID "
                + "WHERE REPORT_012.REPORT_YEAR = ? AND REPORT_012.REPORT_MONTH = ? "
                + "GROUP BY REPORT_012_DETAIL.INSTITUTION "
                + "UNION ALL "
                + "SELECT     REPORT_014_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_014_DETAIL.ALL_AMOUNT), 0) AS SUM_ALL_AMOUNT, ISNULL(SUM(REPORT_014_DETAIL.AT_CENTER),  "
                + "                      0) AS SUM_AT_CENTER, ISNULL(SUM(REPORT_014_DETAIL.AT_ECT_PROVINCE), 0) AS SUM_AT_ECT_PROVINCE,  "
                + "                      ISNULL(SUM(REPORT_014_DETAIL.ECT_RESOLVE), 0) AS SUM_ECT_RESOLVE, ISNULL(SUM(REPORT_014_DETAIL.ANALYST_REMAIN), 0)  "
                + "                      AS SUM_ANALYST_REMAIN, ISNULL(SUM(REPORT_014_DETAIL.OFFER_ECT), 0) AS SUM_OFFER_ECT, ISNULL(SUM(REPORT_014_DETAIL.ACCESS_COMMITTEE), 0)  "
                + "                      AS SUM_ACCESS_COMMITTEE, ISNULL(SUM(REPORT_014_DETAIL.ON_AGENDA), 0) AS SUM_ON_AGENDA "
                + "FROM         REPORT_014_DETAIL INNER JOIN "
                + "                      REPORT_014 ON REPORT_014_DETAIL.REPORT_ID = REPORT_014.REPORT_ID "
                + "WHERE REPORT_014.REPORT_YEAR = ? AND REPORT_014.REPORT_MONTH = ? "
                + "GROUP BY REPORT_014_DETAIL.INSTITUTION "
                + " "
                + " "
                + ") AS X "
                + "GROUP BY INSTITUTION";

        return findNativeQuery(SQL, ViewReportExpression.class, criteria.getYear(), criteria.getMonth(), criteria.getYear(), criteria.getMonth(), criteria.getYear(), criteria.getMonth());
    }

    @Override
    public List<ViewReportExpression> findReport011ByCriteria(ReportCriteria criteria) {
        List param = new ArrayList();
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT REPORT_011_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_011_DETAIL.ALL_AMOUNT), 0) AS ALL_AMOUNT, ISNULL(SUM(REPORT_011_DETAIL.AT_CENTER),   ");
        SQL.append("                0) AS AT_CENTER, ISNULL(SUM(REPORT_011_DETAIL.AT_ECT_PROVINCE), 0) AS AT_ECT_PROVINCE,   ");
        SQL.append("                ISNULL(SUM(REPORT_011_DETAIL.ECT_RESOLVE), 0) AS ECT_RESOLVE, ISNULL(SUM(REPORT_011_DETAIL.ANALYST_REMAIN), 0)   ");
        SQL.append("                AS ANALYST_REMAIN, ISNULL(SUM(REPORT_011_DETAIL.OFFER_ECT), 0) AS OFFER_ECT, ISNULL(SUM(REPORT_011_DETAIL.ACCESS_COMMITTEE), 0)   ");
        SQL.append("                AS ACCESS_COMMITTEE, ISNULL(SUM(REPORT_011_DETAIL.ON_AGENDA), 0) AS ON_AGENDA  ");
        SQL.append("                FROM REPORT_011_DETAIL INNER JOIN REPORT_011 ON REPORT_011_DETAIL.REPORT_ID = REPORT_011.REPORT_ID  ");
        SQL.append("                WHERE 1=1 AND REPORT_011_DETAIL.INSTITUTION <> '' ");
        if (criteria.getYear() != null) {
            SQL.append("                AND REPORT_011.REPORT_YEAR = ? ");
            param.add(criteria.getYear());
        }
        if (criteria.getMonth()!= null) {
            SQL.append("                AND REPORT_011.REPORT_MONTH = ?  ");
            param.add(criteria.getMonth());
        }
        SQL.append("                GROUP BY REPORT_011_DETAIL.INSTITUTION ");
        
        return findNativeQuery(SQL.toString(), ViewReportExpression.class, param);
    }

    @Override
    public List<ViewReportExpression> findReport012ByCriteria(ReportCriteria criteria) {
        List param = new ArrayList();
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT REPORT_012_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_012_DETAIL.ALL_AMOUNT), 0) AS ALL_AMOUNT, ISNULL(SUM(REPORT_012_DETAIL.AT_CENTER),   ");
        SQL.append("                0) AS AT_CENTER, ISNULL(SUM(REPORT_012_DETAIL.AT_ECT_PROVINCE), 0) AS AT_ECT_PROVINCE,   ");
        SQL.append("                ISNULL(SUM(REPORT_012_DETAIL.ECT_RESOLVE), 0) AS ECT_RESOLVE, ISNULL(SUM(REPORT_012_DETAIL.ANALYST_REMAIN), 0)   ");
        SQL.append("                AS ANALYST_REMAIN, ISNULL(SUM(REPORT_012_DETAIL.OFFER_ECT), 0) AS OFFER_ECT, ISNULL(SUM(REPORT_012_DETAIL.ACCESS_COMMITTEE), 0)   ");
        SQL.append("                AS ACCESS_COMMITTEE, ISNULL(SUM(REPORT_012_DETAIL.ON_AGENDA), 0) AS ON_AGENDA  ");
        SQL.append("                FROM REPORT_012_DETAIL INNER JOIN REPORT_012 ON REPORT_012_DETAIL.REPORT_ID = REPORT_012.REPORT_ID  ");
        SQL.append("                WHERE 1=1 ");
        if (criteria.getYear() != null) {
            SQL.append("                AND REPORT_012.REPORT_YEAR = ? ");
            param.add(criteria.getYear());
        }
        if (criteria.getMonth()!= null) {
            SQL.append("                AND REPORT_012.REPORT_MONTH = ?  ");
            param.add(criteria.getMonth());
        }
        SQL.append("                GROUP BY REPORT_012_DETAIL.INSTITUTION ");

        return findNativeQuery(SQL.toString(), ViewReportExpression.class, param);
    }

    @Override
    public List<ViewReportExpression014> findReport014ByCriteria(ReportCriteria criteria) {
        List param = new ArrayList();
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT REPORT_014_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_014_DETAIL.ALL_AMOUNT), 0) AS ALL_AMOUNT, ISNULL(SUM(REPORT_014_DETAIL.AT_CENTER),   ");
        SQL.append("                0) AS AT_CENTER, ISNULL(SUM(REPORT_014_DETAIL.AT_ECT_PROVINCE), 0) AS AT_ECT_PROVINCE,   ");
        SQL.append("                ISNULL(SUM(REPORT_014_DETAIL.ECT_RESOLVE), 0) AS ECT_RESOLVE, ISNULL(SUM(REPORT_014_DETAIL.ANALYST_REMAIN), 0)   ");
        SQL.append("                AS ANALYST_REMAIN, ISNULL(SUM(REPORT_014_DETAIL.OFFER_ECT), 0) AS OFFER_ECT, ISNULL(SUM(REPORT_014_DETAIL.ACCESS_COMMITTEE), 0)   ");
        SQL.append("                AS ACCESS_COMMITTEE, ISNULL(SUM(REPORT_014_DETAIL.ON_AGENDA), 0) AS ON_AGENDA , ISNULL(SUM(REPORT_014_DETAIL.SEND_REQUEST), 0) AS SEND_REQUEST ");
        SQL.append("                FROM REPORT_014_DETAIL INNER JOIN REPORT_014 ON REPORT_014_DETAIL.REPORT_ID = REPORT_014.REPORT_ID  ");
        SQL.append("                WHERE 1=1 ");
        if (criteria.getYear() != null) {
            SQL.append("                AND REPORT_014.REPORT_YEAR = ? ");
            param.add(criteria.getYear());
        }
        if (criteria.getMonth()!= null) {
            SQL.append("                AND REPORT_014.REPORT_MONTH = ?  ");
            param.add(criteria.getMonth());
        }
        SQL.append("                GROUP BY REPORT_014_DETAIL.INSTITUTION ");

        return findNativeQuery(SQL.toString(), ViewReportExpression014.class, param);
    }

    @Override
    public List<ViewReportExpression017> findReport017ByCriteria(ReportCriteria criteria) {
        List param = new ArrayList();
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT          REPORT_017_DETAIL.INSTITUTION, ISNULL(SUM(REPORT_017_DETAIL.ALL_STORY),0) AS ALL_STORY,  ");
        SQL.append("                ISNULL(SUM(REPORT_017_DETAIL.RED_CARD),0) AS RED_CARD, ISNULL(SUM(REPORT_017_DETAIL.YELLOW_CARD),0) AS YELLOW_CARD,  ");
        SQL.append("				ISNULL(SUM(REPORT_017_DETAIL.YELLOW_CARD_CRIMINAL_CASE),0) AS YELLOW_CARD_CRIMINAL_CASE,ISNULL(SUM(REPORT_017_DETAIL.RESET_COUNTER),0) AS RESET_COUNTER, ");
        SQL.append("				ISNULL(SUM(REPORT_017_DETAIL.CRIMINAL_CASE),0) AS CRIMINAL_CASE, ISNULL(SUM(REPORT_017_DETAIL.ADDING),0) AS ADDING,  ");
        SQL.append("				ISNULL(SUM(REPORT_017_DETAIL.REQUEST_RECEIVED),0) AS REQUEST_RECEIVED,ISNULL(SUM(REPORT_017_DETAIL.REQUEST_NO_RECEIVED),0) AS REQUEST_NO_RECEIVED, ");
        SQL.append("				ISNULL(SUM(REPORT_017_DETAIL.WITHDRAWN_REQUEST),0) AS WITHDRAWN_REQUEST,ISNULL(SUM(REPORT_017_DETAIL.ECT),0) AS ECT ");
        SQL.append("FROM            REPORT_017 INNER JOIN ");
        SQL.append("                         REPORT_017_DETAIL ON REPORT_017.REPORT_ID = REPORT_017_DETAIL.REPORT_ID ");
        if (criteria.getYear() != null) {
            SQL.append("                AND REPORT_017.REPORT_YEAR = ? ");
            param.add(criteria.getYear());
        }
        if (criteria.getMonth()!= null) {
            SQL.append("                AND REPORT_017.REPORT_MONTH = ?  ");
            param.add(criteria.getMonth());
        }
        SQL.append("                GROUP BY REPORT_017_DETAIL.INSTITUTION");

        return findNativeQuery(SQL.toString(), ViewReportExpression017.class, param);
    }

}
