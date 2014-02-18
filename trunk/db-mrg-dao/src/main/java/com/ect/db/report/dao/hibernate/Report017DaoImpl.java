/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report017Dao;
import com.ect.db.report.entity.Report017;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report017Dao")
public class Report017DaoImpl extends BaseDao implements Report017Dao{

    @Override
    public Report017 findByReportId(Integer paramReportId) {
        return (Report017) findUniqNativeQuery("SELECT * FROM REPORT_017 WHERE REPORT_ID = ? ", Report017.class, paramReportId);
    }
    
}
