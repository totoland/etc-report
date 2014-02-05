/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report007Dao;
import com.ect.db.report.entity.Report007;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report007Dao")
public class Report007DaoImpl extends BaseDao implements Report007Dao{

    @Override
    public Report007 findByReportId(Integer reportId) {
        return (Report007) findUniqNativeQuery("SELECT * FROM REPORT_007 WHERE REPORT_ID = ? ", Report007.class, reportId);
    }
    
}
