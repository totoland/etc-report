/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.dbmrgdao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.dao.ViewReportExpressionDao;
import java.util.List;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/config/BeanLocations.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ViewReportExpressionDaoImplTest {
    @Autowired
    ViewReportExpressionDao viewReportExpressionDao;
    
    @org.junit.Test
    public void testReport(){
        List list = viewReportExpressionDao.findByCriteria(new ReportCriteria("07", "2557", null, null));
        
        Assert.assertNotNull(list);
        
        System.out.println("list : "+list);
        
        
    }
}
