/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.ViewReport001;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository
public class Report001DaoImpl extends BaseDao implements Report001Dao{

    @Override
    public List<ViewReport001> findByStatus(Integer status) {
        
        return findNativeQuery("SELECT * FROM REPORT_001 WHERE FLOW_STATUS_ID = ? ", ViewReport001.class, status);
        
    }
    
    @Override
    public Report001 findByReportId(Integer reportId) {
    
        return (Report001) findUniqNativeQuery("SELECT * FROM REPORT_001 WHERE REPORT_ID = ? ", Report001.class, reportId);
        
    }
    
    
    
}
