/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report016Dao;
import com.ect.db.report.entity.Report016;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report016Dao")
public class Report016DaoImpl extends BaseDao implements Report016Dao{

    @Override
    public Report016 findByReportId(Integer paramReportId) {
        return (Report016) findUniqNativeQuery("SELECT * FROM REPORT_016 WHERE REPORT_ID = ? ", Report016.class, paramReportId);
    }
    
}
