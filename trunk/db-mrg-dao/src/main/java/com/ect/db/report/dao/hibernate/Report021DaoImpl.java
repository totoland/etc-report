/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report021Dao;
import com.ect.db.report.entity.Report021;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report021Dao")
public class Report021DaoImpl extends BaseDao implements Report021Dao {

    @Override
    public Report021 findByReportId(Integer reportId) {
        return (Report021) findUniqNativeQuery("SELECT * FROM REPORT_021 WHERE REPORT_ID = ? ", Report021.class, reportId);
    }
}
