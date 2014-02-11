/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report011Dao;
import com.ect.db.report.entity.Report011;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report011Dao")
public class Report011DaoImpl extends BaseDao implements Report011Dao{

    @Override
    public Report011 findByReportId(Integer paramReportId) {
        return (Report011) findUniqNativeQuery("SELECT * FROM REPORT_011 WHERE REPORT_ID = ? ", Report011.class, paramReportId);
    }
    
}
