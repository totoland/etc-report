/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.ect.db.common.dao.GennericDao;
import com.ect.db.entity.EctProvince;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/config/BeanLocations.xml"
    })
public class Test {

    @Autowired
    GennericDao<EctProvince>gennericDao;
    
    @Before
    public void init() throws Exception {
        // Log4J junit configuration.
        System.out.println("init");
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }

    @org.junit.Test
    public void main() {
        
        System.out.println("gennericDao : "+gennericDao.findAll(EctProvince.class));
        
    }
}
