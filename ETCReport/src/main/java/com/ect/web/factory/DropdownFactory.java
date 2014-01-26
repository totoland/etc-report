/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.factory;

import com.ect.db.common.entity.DropDownList;
import com.ect.web.service.CommonService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author totoland
 */
@Component
public class DropdownFactory implements Serializable {

    private static final long serialVersionUID = 7739283351270265693L;

    @Autowired
    CommonService commonService;

    /**
     * *
     * getFiscalYear
     *
     * @return ปีงบประมาณ ย้อนหลัง 10 ปี
     */
    public List<DropDownList> ddlFiscalYear() {

        List<DropDownList> dropDownLists = new ArrayList<DropDownList>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

        int curYear = Integer.parseInt(dateFormat.format(new Date()).toString());

        for (int i = 0; i < 10; i++) {
            dropDownLists.add(new DropDownList(String.valueOf(curYear - i), String.valueOf(curYear - i)));
        }

        return dropDownLists;
    }

    /**
     * *
     *
     * @return get All Months
     */
    public List<DropDownList> ddlMonth() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'month'");

        return commonService.getDropdownList(criteria);
    }

    /**
     * *
     * รายงานผลการปฏิบัติการ
     *
     * @return ALl Operating
     */
    public List<DropDownList> ddlReportOperating() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'ddl.report.operating'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * แบบรายงาน
     * @return ReportForm
     */
    public List<DropDownList> ddlReportForm() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'ddl.report.form'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * ยุทธศาสตร์
     * @return All Strategic
     */
    public List<DropDownList> ddlStrategic() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_STRATEGIC");
        criteria.setOrderByField("STRATEGIC_ID");
        criteria.setName("STRATEGIC_NAME");
        criteria.setValue("STRATEGIC_ID");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กลยุทธ์
     * @return All SubStrategic
     */
    public List<DropDownList> ddlSubStrategic() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_SUB_STRATEGIC");
        criteria.setOrderByField("SUB_STRATEGIC_NAME");
        criteria.setName("SUB_STRATEGIC_NAME");
        criteria.setValue("SUB_STRATEGIC_ID");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กลยุทธ์
     * @return All SubStrategic
     */
    public List<DropDownList> ddlSubStrategic(Integer strateGicId) {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_SUB_STRATEGIC");
        criteria.setOrderByField("SUB_STRATEGIC_NAME");
        criteria.setName("SUB_STRATEGIC_NAME");
        criteria.setValue("SUB_STRATEGIC_ID");
        criteria.setCondition("STRATEGIC_ID = "+strateGicId);

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * แผนงาน
     * @return All Plan
     */
    public List<DropDownList> ddlPlan(Integer subStrateGicId) {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PLAN");
        criteria.setOrderByField("PLAN_NAME");
        criteria.setName("PLAN_NAME");
        criteria.setValue("PLAN_ID");
        criteria.setCondition("SUB_STATEGIC_ID = "+subStrateGicId);
        
        return commonService.getDropdownList(criteria);
    }
    
    /***
     * แผนงาน
     * @return All Plan
     */
    public List<DropDownList> ddlPlan() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PLAN");
        criteria.setOrderByField("PLAN_NAME");
        criteria.setName("PLAN_NAME");
        criteria.setValue("PLAN_ID");
        
        return commonService.getDropdownList(criteria);
    }
    
    /***
     * โครงการ
     * @return All Project 
     */
    public List<DropDownList> ddlProject(Integer planId) {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PROJECT");
        criteria.setOrderByField("PROJECT_NAME");
        criteria.setName("PROJECT_NAME");
        criteria.setValue("PROJECT_ID");
        criteria.setCondition("PLAN_ID = "+planId);
        return commonService.getDropdownList(criteria);
    }
    
    /***
     * โครงการ
     * @return All Project 
     */
    public List<DropDownList> ddlProject() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PROJECT");
        criteria.setOrderByField("PROJECT_NAME");
        criteria.setName("PROJECT_NAME");
        criteria.setValue("PROJECT_ID");
        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กิจกรรม
     * @return All Activity 
     */
    public List<DropDownList> ddlActivity(Integer projectId) {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_ACTIVITY");
        criteria.setOrderByField("ACTIVITY_NAME");
        criteria.setName("ACTIVITY_NAME");
        criteria.setValue("ACTIVITY_ID");
        criteria.setCondition("PROJECT_ID = "+projectId);
        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กิจกรรม
     * @return All Activity 
     */
    public List<DropDownList> ddlActivity() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_ACTIVITY");
        criteria.setOrderByField("ACTIVITY_NAME");
        criteria.setName("ACTIVITY_NAME");
        criteria.setValue("ACTIVITY_ID");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * ระบบรายงานผลการปฏิบัติงาน สนง.กกต.
     * @return All Activity 
     */
    public List<DropDownList> ddlDepEct() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("REPORT_NAME");
        criteria.setOrderByField("REPORT_CODE");
        criteria.setName("REPORT_DESC + ' ' + REPORT_NAME");
        criteria.setValue("REPORT_CODE");
        criteria.setCondition("REPORT_TYPE = 1");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * ระบบรายงานผลการปฏิบัติงาน สนง.กกต. ส่วนกลาง
     * @return All Activity 
     */
    public List<DropDownList> ddlDepEctCenter() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("REPORT_NAME");
        criteria.setOrderByField("REPORT_CODE");
        criteria.setName("REPORT_NAME");
        criteria.setValue("REPORT_CODE");
        criteria.setCondition("REPORT_TYPE = 2");

        return commonService.getDropdownList(criteria);
    }
}
