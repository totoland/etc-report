/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report019Dao;
import com.ect.db.report.entity.Report019;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report019Dao")
public class Report019DaoImpl extends BaseDao implements Report019Dao{

    @Override
    public Report019 findByReportId(Integer paramReportId) {
        return (Report019) findUniqNativeQuery("SELECT * FROM REPORT_019 WHERE REPORT_ID = ? ", Report019.class, paramReportId);
    }
    
}
