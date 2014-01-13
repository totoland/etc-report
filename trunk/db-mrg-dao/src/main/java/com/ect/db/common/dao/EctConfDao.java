/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.common.dao;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.EctConf;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface EctConfDao {
    
    public EctConf getConf(String confName);

    public String getConfValue(String confName);

    public EctConfManager getAllConf();
}
