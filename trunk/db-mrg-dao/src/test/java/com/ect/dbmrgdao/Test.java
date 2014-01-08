/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.ect.db.common.dao.GennericDao;
import com.ect.db.entity.Report001;
import com.ect.db.entity.Report001Detail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/config/BeanLocations.xml"
})
@Transactional
public class Test {

    @Autowired
    GennericDao<Report001> gennericDao;

//    @Autowired
//    GennericDao<EctProvince>gennericDao;
    @Before
    public void init() throws Exception {
        // Log4J junit configuration.
        System.out.println("init");
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }

//    @Ignore
//    @org.junit.Test
//    public void main() {
//        
//        System.out.println("gennericDao : "+gennericDao.findAll(EctProvince.class));
//        
//    }
    @org.junit.Test
    @Transactional
    public void save() {

        System.out.println("save");

        Report001 report001 = new Report001();
        //report001.setReportId(1);

        Report001Detail report001Detail = new Report001Detail();
        report001Detail.setActivityAmount(Integer.MIN_VALUE);
        report001Detail.setActivityName("2342");
        report001Detail.setActivityType("2342");
        report001Detail.setBudgetReal(BigDecimal.ZERO);
        report001Detail.setBudgetSet(BigDecimal.ZERO);
        report001Detail.setDepId(Integer.SIZE);
        report001Detail.setDepName("2423432");
        report001Detail.setIsPass(Boolean.TRUE);
        //report001Detail.setReportDetailId(Integer.MIN_VALUE);
        report001Detail.setReportId(report001);

        List<Report001Detail> report001Details = new ArrayList<Report001Detail>();
        report001Details.add(report001Detail);

        report001.setReport001DetailList(report001Details);

        try {
            
            gennericDao.create(report001);
            
            System.out.println("Done!!");
        }catch(Exception ex){
        
            ex.printStackTrace();
            
        }

    }
}
