/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report014Dao;
import com.ect.db.report.entity.Report014;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report014Dao")
public class Report014DaoImpl extends BaseDao implements Report014Dao{

    @Override
    public Report014 findByReportId(Integer paramReportId) {
        return (Report014) findUniqNativeQuery("SELECT * FROM REPORT_014 WHERE REPORT_ID = ? ", Report014.class, paramReportId);
    }
    
}
