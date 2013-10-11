/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.ect.db.common.dao.CommonDao;
import com.ect.db.common.entity.DropDownList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
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

    private static Logger logger = Logger.getLogger(Test.class);
    @Autowired
    CommonDao commonDao;
    
    @Before
    public void init() throws Exception {
        // Log4J junit configuration.
        System.out.println("init");
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }

    @org.junit.Test
    public void main() {
        System.out.println("Test!!");

        System.out.println("spring hibernate!!");
        
        DropDownList ddls = new DropDownList();
        ddls.setSchema("SYS");
        ddls.setTableName("SYSTABLES");
        ddls.setName("TABLENAME");
        ddls.setValue("TABLEID");
        
        List<DropDownList>dropDownLists = commonDao.getDropdownList(ddls);
        
        for(DropDownList ddl : dropDownLists){
            System.out.println(ddl.toString());
        }
    }
}
