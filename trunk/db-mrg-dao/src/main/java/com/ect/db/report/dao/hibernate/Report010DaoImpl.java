/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report010Dao;
import com.ect.db.report.entity.Report010;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report010Dao")
public class Report010DaoImpl extends BaseDao implements Report010Dao{

    @Override
    public Report010 findByReportId(Integer paramReportId) {
        return (Report010) findUniqNativeQuery("SELECT * FROM REPORT_010 WHERE REPORT_ID = ? ", Report010.class, paramReportId);
    }
    
}
