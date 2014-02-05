/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report023Dao;
import com.ect.db.report.entity.Report023;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report023Dao")
public class Report023DaoImpl extends BaseDao implements Report023Dao{

    @Override
    public Report023 findByReportId(Integer reportId) {
        return (Report023) findUniqNativeQuery("SELECT * FROM REPORT_023 WHERE REPORT_ID = ? ", Report023.class, reportId);
    }
    
}
