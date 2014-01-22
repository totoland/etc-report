/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.dao.BaseDao;
import com.ect.db.entity.Report002;
import com.ect.db.report.dao.Report002Dao;
import com.ect.db.report.entity.ViewReport002;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("report002Dao")
public class Report002DaoImpl extends BaseDao implements Report002Dao, Serializable{
    private static final long serialVersionUID = 6217871650920942205L;
    
    @Override
    public List<ViewReport002> findByStatus(Integer status) {
        
        return findNativeQuery("SELECT * FROM REPORT_002 WHERE FLOW_STATUS_ID = ? ", ViewReport002.class, status);
        
    }
    
    @Override
    public Report002 findByReportId(Integer reportId) {
    
        return (Report002) findUniqNativeQuery("SELECT * FROM REPORT_002 WHERE REPORT_ID = ? ", Report002.class, reportId);
        
    }
}
