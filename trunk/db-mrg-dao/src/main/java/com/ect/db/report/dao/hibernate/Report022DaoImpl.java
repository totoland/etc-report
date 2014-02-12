/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report022Dao;
import com.ect.db.report.entity.Report022;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report022Dao")
public class Report022DaoImpl extends BaseDao implements Report022Dao{

    @Override
    public Report022 findByReportId(Integer reportId) {
        return (Report022) findUniqNativeQuery("SELECT * FROM REPORT_022 WHERE REPORT_ID = ? ", Report022.class, reportId);
    }
    
}
