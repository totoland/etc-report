/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.common.dao.hibernate;

import com.ect.db.common.dao.EctConfDao;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.EctConf;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("ectConfDao")
public class EctConfDaoImpl extends BaseDao implements EctConfDao, Serializable{
    
    private static Logger logger = LoggerFactory.getLogger(EctConfDaoImpl.class);
    private static final long serialVersionUID = 4323443395907871244L;
    
    public EctConfManager allConf;

    public EctConfDaoImpl() {
        logger.info("Init Class IslConf...");
    }

    @Override
    public EctConf getConf(String confName) {
        EctConf q = (EctConf)findUniqNativeQuery("SELECT * FROM Ect_Conf where CONF_NAME = ?", EctConf.class,confName);
        return q;
    }

    @Override
    public String getConfValue(String confName) {
        EctConf q = (EctConf)findUniqNativeQuery("SELECT * FROM Ect_Conf where CONF_NAME = ?", EctConf.class,confName);
        return q.getConfValue();
    }

    @Override
    public EctConfManager getAllConf() {

        logger.debug("Get All Conf...");

        String sql = "SELECT * FROM Ect_Conf";
        List<EctConf> q = findNativeQuery(sql, EctConf.class);

        allConf = new EctConfManager();
        allConf.addAll(q);

        return allConf;
    }
    
}
