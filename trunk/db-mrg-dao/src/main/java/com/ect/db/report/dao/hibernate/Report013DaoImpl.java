/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report013Dao;
import com.ect.db.report.entity.Report013;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report013Dao")
public class Report013DaoImpl extends BaseDao implements Report013Dao{

    @Override
    public Report013 findByReportId(Integer paramReportId) {
        return (Report013) findUniqNativeQuery("SELECT * FROM REPORT_013 WHERE REPORT_ID = ? ", Report013.class, paramReportId);
    }
    
}
