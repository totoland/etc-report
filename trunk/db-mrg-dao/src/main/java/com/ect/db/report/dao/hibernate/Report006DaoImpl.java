/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report006Dao;
import com.ect.db.report.entity.Report006;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report006Dao")
public class Report006DaoImpl extends BaseDao implements Report006Dao{

    @Override
    public Report006 findByReportId(Integer reportId) {
        return (Report006) findUniqNativeQuery("SELECT * FROM REPORT_006 WHERE REPORT_ID = ? ", Report006.class, reportId);
    }
    
}
