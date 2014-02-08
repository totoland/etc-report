/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report008Dao;
import com.ect.db.report.entity.Report008;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report008Dao")
public class Report008DaoImpl extends BaseDao implements Report008Dao{

    @Override
    public Report008 findByReportId(Integer paramReportId) {
        return (Report008) findUniqNativeQuery("SELECT * FROM REPORT_008 WHERE REPORT_ID = ? ", Report008.class, paramReportId);
    }
    
}
