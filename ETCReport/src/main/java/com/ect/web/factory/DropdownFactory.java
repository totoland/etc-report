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
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'strategic'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กลยุทธ์
     * @return All SubStrategic
     */
    public List<DropDownList> ddlSubStrategic() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'sub.strategic'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * แผนงาน
     * @return All Plan
     */
    public List<DropDownList> ddlPlan() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'plan'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * โครงการ
     * @return All Project 
     */
    public List<DropDownList> ddlProject() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'project'");

        return commonService.getDropdownList(criteria);
    }
    
    /***
     * กิจกรรม
     * @return All Activity 
     */
    public List<DropDownList> ddlActivity() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_CONSTANT");
        criteria.setOrderByField("CONS_SORT");
        criteria.setName("CONS_NAME");
        criteria.setValue("CONS_VALUE");
        criteria.setCondition("CONS_KEY = 'activity'");

        return commonService.getDropdownList(criteria);
    }
}
