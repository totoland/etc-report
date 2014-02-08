/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report009Dao;
import com.ect.db.report.entity.Report009;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report009Dao")
public class Report009DaoImpl extends BaseDao implements Report009Dao{

    @Override
    public Report009 findByReportId(Integer paramReportId) {
        return (Report009) findUniqNativeQuery("SELECT * FROM REPORT_009 WHERE REPORT_ID = ? ", Report009.class, paramReportId);
    }
    
}
