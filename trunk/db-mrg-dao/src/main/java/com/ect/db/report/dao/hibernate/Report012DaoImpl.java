/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.dao.Report012Dao;
import com.ect.db.report.entity.Report012;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report012Dao")
public class Report012DaoImpl extends BaseDao implements Report012Dao{

    @Override
    public Report012 findByReportId(Integer paramReportId) {
        return (Report012) findUniqNativeQuery("SELECT * FROM REPORT_012 WHERE REPORT_ID = ? ", Report012.class, paramReportId);
    }
    
}
