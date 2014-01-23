/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report004Dao;
import com.ect.db.report.entity.Report004;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report004Dao")
public class report004DaoImpl extends BaseDao implements Report004Dao{

    @Override
    public Report004 findByReportId(Integer reportId) {
    
        return (Report004) findUniqNativeQuery("SELECT * FROM REPORT_004 WHERE REPORT_ID = ? ", Report004.class, reportId);
        
    }
}
