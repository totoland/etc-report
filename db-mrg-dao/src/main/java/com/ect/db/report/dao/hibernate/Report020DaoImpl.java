/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report020Dao;
import com.ect.db.report.entity.Report020;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report020Dao")
public class Report020DaoImpl extends BaseDao implements Report020Dao {

    @Override
    public Report020 findByReportId(Integer reportId) {
        return (Report020) findUniqNativeQuery("SELECT * FROM REPORT_020 WHERE REPORT_ID = ? ", Report020.class, reportId);
    }
}
