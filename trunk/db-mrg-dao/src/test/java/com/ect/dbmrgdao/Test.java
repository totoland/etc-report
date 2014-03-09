/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.bean.ReportCriteria;
import com.ect.db.bean.UserCriteria;
import com.ect.db.common.dao.EctConfDao;
import com.ect.db.common.dao.GennericDao;
import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.EctProvince;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report001Detail;
import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.dao.Report002Dao;
import com.ect.db.report.dao.Report004Dao;
import com.ect.db.report.dao.Report006Dao;
import com.ect.db.report.dao.Report023Dao;
import com.ect.db.report.dao.ViewReportByStatusDao;
import com.ect.db.report.entity.Report003;
import com.ect.db.report.entity.Report003Detail;
import com.ect.db.user.dao.UserDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
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
public class Test {

    @Autowired
    GennericDao<Report003> gennericDao;
    @Autowired
    AuthenDao authenDao;
    @Autowired
    Report001Dao report001Dao;
    @Autowired
    Report023Dao report023Dao;
    @Autowired
    EctConfDao ectConfDao;
    @Autowired
    EctConfManager ectConfManager;
    @Autowired
    ViewReportByStatusDao viewReportByStatusDao;
    @Autowired
    Report002Dao report002Dao;
    @Autowired
    Report004Dao report004Dao;
//    @Autowired
//    GennericDao<EctProvince> gennericDao;
    @Autowired
    Report006Dao report006Dao;
    @Autowired
    UserDao userDao;
    
    @Before
    public void init() throws Exception {
        // Log4J junit configuration.
        System.out.println("init");
        //PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }
    
    @org.junit.Test
    public void testSearchByUserName(){
    
        System.out.println(userDao.searchByUserName("administrator"));
        
    }
    
    @Ignore
    @org.junit.Test
    public void testReport003(){
        
        Report003 report003 = new Report003();
        report003.setReportId(6);
        
        List<Report003Detail>report003Details = new ArrayList<Report003Detail>();

        Report003Detail report003Detail1 = new Report003Detail();
        report003Detail1.setDocReceive("หนังสือรับภายใน");
        report003Detail1.setDocSend("หนังสือส่งภายใน");
        report003Detail1.setDocSendAmount(0);
        report003Detail1.setDocReceiveAmount(0);
        report003Detail1.setReportId(report003);
        
        Report003Detail report003Detail2 = new Report003Detail();
        report003Detail2.setDocReceive("หนังสือรับภายใน (ชั้นความลับ)");
        report003Detail2.setDocSend("หนังสือส่งภายใน (ชั้นความลับ)");
        report003Detail2.setDocSendAmount(0);
        report003Detail2.setDocReceiveAmount(0);
        report003Detail2.setReportId(report003);

        Report003Detail report003Detail3 = new Report003Detail();
        report003Detail3.setDocReceive("หนังสือรับภายนอก");
        report003Detail3.setDocSend("หนังสือส่งภายนอก");
        report003Detail3.setDocSendAmount(0);
        report003Detail3.setDocReceiveAmount(0);
        report003Detail3.setReportId(report003);
        
        Report003Detail report003Detail4 = new Report003Detail();
        report003Detail4.setDocReceive("หนังสือรับภายนอก (ชั้นความลับ)");
        report003Detail4.setDocSend("หนังสือส่งภายนอก (ชั้นความลับ)");
        report003Detail4.setDocSendAmount(0);
        report003Detail4.setDocReceiveAmount(0);
        report003Detail4.setReportId(report003);
        
        Report003Detail report003Detail5 = new Report003Detail();
        report003Detail5.setDocReceive("เรื่องร้องเรียน ร้องขอความเป็นธรรม");
        report003Detail5.setDocSendAmount(0);
        report003Detail5.setDocReceiveAmount(0);
        report003Detail5.setReportId(report003);
        
        Report003Detail report003Detail6 = new Report003Detail();
        report003Detail6.setDocReceive("สำนวนร้องคัดค้าน");
        report003Detail6.setDocSendAmount(0);
        report003Detail6.setDocReceiveAmount(0);
        report003Detail6.setReportId(report003);
        
        Report003Detail report003Detail7 = new Report003Detail();
        report003Detail7.setDocReceive("อื่นๆ");
        report003Detail7.setDocSendAmount(0);
        report003Detail7.setDocReceiveAmount(0);
        report003Detail7.setReportId(report003);

        report003Details.add(report003Detail1);
        report003Details.add(report003Detail2);
        report003Details.add(report003Detail3);
        report003Details.add(report003Detail4);
        report003Details.add(report003Detail5);
        report003Details.add(report003Detail6);
        report003Details.add(report003Detail7);
        
        report003.setReport003DetailList(report003Details);
        
        gennericDao.create(report003);
        
    }

    @Ignore
    @org.junit.Test
    public void testUser(){
        UserCriteria criteria = new UserCriteria();
        criteria.setUserName("เจ้า");
        System.out.println(userDao.searchByUserCriteria(criteria));
    }
    
    @Ignore
    @org.junit.Test
    public void testReport006() {

        System.out.println(report006Dao.findByReportId(1));

    }

    @Ignore
    @org.junit.Test
    public void testReport023() {
        System.out.println(report023Dao.findByReportId(1));
    }

    @Ignore
    @org.junit.Test
    public void testReport004() {

        System.out.println((report004Dao.findByReportId(1)));

    }

    @Ignore
    @org.junit.Test
    public void testReport002() {

        System.out.println((viewReportByStatusDao.findByCriteria(new ReportCriteria())));

    }

    @Ignore
    @org.junit.Test
    public void testReport001() {
        //System.out.println(((Report001)gennericDao.find(33, Report001.class)));
    }

    @Ignore
    @org.junit.Test
    public void testEctConfManager() {

        System.out.println("##########################");

        //viewReportByStatusDao.updateReportStatus("REPORT_001", 20, FlowStatus.STEP_2.getStatus());

        System.out.println(viewReportByStatusDao.findReportByStatus(FlowStatus.STEP_1.getStatus()));
    }

    @Ignore
    @org.junit.Test
    public void testFindStatus() {
        System.out.println("testFindStatus!!");

        System.out.print(ectConfDao.getAllConf());

    }

    @Ignore
    @org.junit.Test
    public void testLogin() {
        System.out.println(authenDao.loginUser("operator", "NpVJEPbt5HfDHM8PkFkVuQ=="));
    }

    @Ignore
    @org.junit.Test
    @Transactional
    public void save() {

        System.out.println("save");

        Report001 report001 = new Report001();
        //report001.setReportId(1);

        Report001Detail report001Detail = new Report001Detail();
        report001Detail.setActivityAmount(Integer.MIN_VALUE);;
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

            //gennericDao.create(report001);

            System.out.println("Done!!");
        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }
}
