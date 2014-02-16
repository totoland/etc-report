/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report015Dao;
import com.ect.db.report.entity.Report015;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report015Dao")
public class Report015DaoImpl extends BaseDao implements Report015Dao{

    @Override
    public Report015 findByReportId(Integer paramReportId) {
        return (Report015) findUniqNativeQuery("SELECT * FROM REPORT_015 WHERE REPORT_ID = ? ", Report015.class, paramReportId);
    }
    
}
