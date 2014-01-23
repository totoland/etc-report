/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao.hibernate;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.EctConf;
import com.ect.db.report.entity.ReportName;
import com.ect.db.report.dao.ReportNameDao;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("reportNameDao")
public class ReportNameDaoImpl extends BaseDao implements ReportNameDao, Serializable{
    private static final long serialVersionUID = -3781001562633705978L;
    private static final Logger logger = LoggerFactory.getLogger(ReportNameDaoImpl.class);
            
    @Override
    public List<ReportName> getAllReportName(){
        
        return getHibernateTemplate().loadAll(ReportName.class);
        
    }
    
}
