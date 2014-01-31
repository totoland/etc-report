/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report005Dao;
import com.ect.db.report.entity.Report005;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report005Dao")
public class Report005DaoImpl extends BaseDao implements Report005Dao{

    @Override
    public Report005 findByReportId(Integer reportId) {
    
        return (Report005) findUniqNativeQuery("SELECT * FROM REPORT_005 WHERE REPORT_ID = ? ", Report005.class, reportId);
        
    }
}
