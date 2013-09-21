/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.ect.test.dao.ContractDao;
import com.ect.test.entity.Contract;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
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

    private static Logger logger = Logger.getLogger(Test.class);
    @Autowired
    ContractDao contractDao;

    @Before
    public void init() throws Exception {
        // Log4J junit configuration.
        System.out.println("init");
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }

    @org.junit.Test
    public void main() {
        logger.info("Test!!");

        logger.info("spring hibernate!!");
        List<Contract> listContract = contractDao.getAllListContract();

        for (Contract contract : listContract) {
            System.out.println(contract.toString());
        }
    }
}
