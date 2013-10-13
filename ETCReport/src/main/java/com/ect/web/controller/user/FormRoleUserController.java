/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.user;

import com.ect.web.controller.BaseController;
import com.ect.web.controller.model.ReportVO;
import com.ect.web.controller.report.FormSavePrintReportController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jirawat.l
 */
@ViewScoped
@ManagedBean(name = "roleUser")
public class FormRoleUserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FormRoleUserController.class);
    private List<ReportVO> rptPreSendList;
    private List<ReportVO> rptWaitStatusList;
    private List<ReportVO> rptCompleteList;
    private boolean isCreate = false;

    @PostConstruct
    public void init() {       
        mockData();
    }

    public void mockData() {
        ReportVO rpt = null;
        rptPreSendList = new ArrayList<ReportVO>();
        rpt = new ReportVO();
        rpt.setNo("1004");
        rpt.setRpt_name("ผลงานประจำงวดเดือนที่ 4");
        rpt.setRpt_type("ยุทธศาสตร์");
        rpt.setCreate_date("10/07/2556");
        rpt.setCreate_user("สมศักดิ์ รักไทย");
        rpt.setStatus("รอส่งพิจารณา");
        rptPreSendList.add(rpt);
        
        rptWaitStatusList = new ArrayList<ReportVO>();
        rpt = new ReportVO();
        rpt.setNo("1003");
        rpt.setRpt_name("ผลงานประจำงวดเดือนที่ 3");
        rpt.setRpt_type("ยุทธศาสตร์");
        rpt.setCreate_date("21/06/2556");
        rpt.setCreate_user("สมศักดิ์ รักไทย");
        rpt.setStatus("รออนุมัติ");
        rptWaitStatusList.add(rpt);
        
        rpt = new ReportVO();
        rpt.setNo("1002");
        rpt.setRpt_name("ผลงานประจำงวดเดือนที่ 2");
        rpt.setRpt_type("ยุทธศาสตร์");
        rpt.setCreate_date("15/05/2556");
        rpt.setCreate_user("สมศักดิ์ รักไทย");
        rpt.setStatus("ไม่อนุมัติ");
        rpt.setReject_reason("บันทึกรายงานไม่ครบ");
        rpt.setMod_user("สมชาย แซ่ตั้ง");
        rpt.setMod_date("17/05/2556");
        rptWaitStatusList.add(rpt);

        // complete
        rptCompleteList = new ArrayList<ReportVO>();
        rpt = new ReportVO();
        rpt.setNo("1001");
        rpt.setRpt_name("ผลงานประจำงวดเดือนที่ 1");
        rpt.setRpt_type("ยุทธศาสตร์");
        rpt.setCreate_date("13/04/2556");
        rpt.setCreate_user("สมศักดิ์ รักไทย");
        rpt.setStatus("อนุมัติเรียบร้อย");
        rpt.setMod_user("สมชาย แซ่ตั้ง");
        rpt.setMod_date("15/04/2556");
        rptCompleteList.add(rpt);
    }

    public void createRpt() {
        isCreate = true;
    }

    @Override
    public void resetForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ReportVO> getRptPreSendList() {
        return rptPreSendList;
    }

    public void setRptPreSendList(List<ReportVO> rptPreSendList) {
        this.rptPreSendList = rptPreSendList;
    }

    public List<ReportVO> getRptWaitStatusList() {
        return rptWaitStatusList;
    }

    public void setRptWaitStatusList(List<ReportVO> rptWaitStatusList) {
        this.rptWaitStatusList = rptWaitStatusList;
    }

    public List<ReportVO> getRptCompleteList() {
        return rptCompleteList;
    }

    public void setRptCompleteList(List<ReportVO> rptCompleteList) {
        this.rptCompleteList = rptCompleteList;
    }

    public boolean isIsCreate() {
        return isCreate;
    }

    public void setIsCreate(boolean isCreate) {
        this.isCreate = isCreate;
    }
}
