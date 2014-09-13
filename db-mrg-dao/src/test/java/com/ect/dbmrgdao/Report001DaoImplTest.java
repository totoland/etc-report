/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.dbmrgdao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.common.dao.GennericDao;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.Report001;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class Report001DaoImplTest {
    
    @Autowired
    GennericDao<Report001> gennericDao;
    
    @Autowired
    Report001Dao report001Dao;
    
    @org.junit.Test
    public void testReport001Query(){
    
        System.out.println("testReport001Query...");
        
        List<String>status = new ArrayList<>();
        
        List<Integer>statusInt = Arrays.asList(103, 109, 104, 105, 106, 107, 108, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 39, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 1, 3, 65, 95, 79, 96, 91, 89, 80, 90, 86, 78, 97, 99, 100, 93, 92, 98, 88, 94, 102, 87, 81, 82, 83, 84, 85);
        
        for(int i=0;i<statusInt.size();i++){
            status.add(String.valueOf(statusInt.get(i)));
        }
        
        System.out.println(report001Dao.countCriteria(new ReportCriteria(null,null,null,status)));
        
    }
    
}
