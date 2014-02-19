/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report018Dao;
import com.ect.db.report.entity.Report018;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report018Dao")
public class Report018DaoImpl extends BaseDao implements Report018Dao{

    @Override
    public Report018 findByReportId(Integer paramReportId) {
        return (Report018) findUniqNativeQuery("SELECT * FROM REPORT_018 WHERE REPORT_ID = ? ", Report018.class, paramReportId);
    }
    
}
