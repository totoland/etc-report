/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report003Dao;
import com.ect.db.report.entity.Report003;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report003Dao")
public class Report003DaoImpl extends BaseDao implements Report003Dao{
    
    @Override
    public Report003 findByReportId(Integer reportId) {
    
        return (Report003) findUniqNativeQuery("SELECT * FROM REPORT_003 WHERE REPORT_ID = ? ", Report003.class, reportId);
        
    }
    
}
