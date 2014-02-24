/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.factory;

import com.ect.db.common.entity.DropDownList;
import com.ect.db.entity.EctProvince;
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

    /**
     * *
     * แบบรายงาน
     *
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
    private static List<DropDownList> strategic;

    /**
     * *
     * ยุทธศาสตร์
     *
     * @return All Strategic
     */
    public List<DropDownList> ddlStrategic() {

        if (strategic == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("ECT_STRATEGIC");
            criteria.setOrderByField("STRATEGIC_ID");
            criteria.setName("STRATEGIC_NAME");
            criteria.setValue("STRATEGIC_ID");

            strategic = commonService.getDropdownList(criteria);

        }
        return strategic;
    }

    /**
     * *
     * กลยุทธ์
     *
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

    /**
     * *
     * กลยุทธ์
     *
     * @return All SubStrategic
     */
    public List<DropDownList> ddlSubStrategic(Integer strateGicId) {

        if (strateGicId == null || strateGicId == -1) {
            return null;
        }

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_SUB_STRATEGIC");
        criteria.setOrderByField("SUB_STRATEGIC_NAME");
        criteria.setName("SUB_STRATEGIC_NAME");
        criteria.setValue("SUB_STRATEGIC_ID");
        criteria.setCondition("STRATEGIC_ID = " + strateGicId);

        return commonService.getDropdownList(criteria);
    }

    /**
     * *
     * แผนงาน
     *
     * @return All Plan
     */
    public List<DropDownList> ddlPlan(Integer subStrateGicId) {

        if(subStrateGicId == null || subStrateGicId == -1){
            return null;
        }
        
        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PLAN");
        criteria.setOrderByField("PLAN_NAME");
        criteria.setName("PLAN_NAME");
        criteria.setValue("PLAN_ID");
        criteria.setCondition("SUB_STATEGIC_ID = " + subStrateGicId);

        return commonService.getDropdownList(criteria);
    }

    /**
     * *
     * แผนงาน
     *
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

    /**
     * *
     * โครงการ
     *
     * @return All Project
     */
    public List<DropDownList> ddlProject(Integer planId) {

        if(planId == null || planId == -1){
            return null;
        }
        
        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_PROJECT");
        criteria.setOrderByField("PROJECT_NAME");
        criteria.setName("PROJECT_NAME");
        criteria.setValue("PROJECT_ID");
        criteria.setCondition("PLAN_ID = " + planId);
        return commonService.getDropdownList(criteria);
    }

    /**
     * *
     * โครงการ
     *
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

    /**
     * *
     * กิจกรรม
     *
     * @return All Activity
     */
    public List<DropDownList> ddlActivity(Integer projectId) {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("ECT_ACTIVITY");
        criteria.setOrderByField("ACTIVITY_NAME");
        criteria.setName("ACTIVITY_NAME");
        criteria.setValue("ACTIVITY_ID");
        criteria.setCondition("PROJECT_ID = " + projectId);
        return commonService.getDropdownList(criteria);
    }

    /**
     * *
     * กิจกรรม
     *
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
    private static List<DropDownList> depEct;

    /**
     * *
     * ระบบรายงานผลการปฏิบัติงาน สนง.กกต.
     *
     * @return All Activity
     */
    public List<DropDownList> ddlDepEct() {

        if (depEct == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("REPORT_NAME");
            criteria.setOrderByField("REPORT_CODE");
            criteria.setName("REPORT_DESC + ' ' + REPORT_NAME");
            criteria.setValue("REPORT_CODE");
            criteria.setCondition("REPORT_TYPE = 1");

            depEct = commonService.getDropdownList(criteria);

        }

        return depEct;
    }
    private static List<DropDownList> depEctCenter;

    /**
     * *
     * ระบบรายงานผลการปฏิบัติงาน สนง.กกต. ส่วนกลาง
     *
     * @return All Activity
     */
    public List<DropDownList> ddlDepEctCenter() {

        if (depEctCenter == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("REPORT_NAME");
            criteria.setOrderByField("REPORT_CODE");
            criteria.setName("REPORT_NAME");
            criteria.setValue("REPORT_CODE");
            criteria.setCondition("REPORT_TYPE = 2");

            depEctCenter = commonService.getDropdownList(criteria);
        }

        return depEctCenter;
    }
    private static List<DropDownList> ectProvinces;

    /**
     * *
     * จังหวัด
     *
     * @return All Activity
     */
    public List<DropDownList> ddlProvince() {

        if (ectProvinces == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("ECT_PROVINCE");
            criteria.setOrderByField("PROVINCE_NAME");
            criteria.setName("PROVINCE_NAME");
            criteria.setValue("PROVINCE_ID");
            criteria.setSortName("ASC");

            ectProvinces = commonService.getDropdownList(criteria);
        }

        return ectProvinces;
    }

    public EctProvince findProvinceById(Integer id) {

        for (DropDownList ddl : ectProvinces) {

            if (id.intValue() == Integer.valueOf(ddl.getValue())) {
                EctProvince ectProvince = new EctProvince();
                ectProvince.setProvinceId(id);
                ectProvince.setProvinceName(ddl.getName());

                return ectProvince;
            }
        }

        return null;

    }
    
    private static List<DropDownList> ectGroupLvl;
    /***
     * ระดับผู้ใช้
     * @return 
     */
    public List<DropDownList>ddlUserLvl(){
        if (ectGroupLvl == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("ECT_GROUP_LVL");
            criteria.setOrderByField("GROUP_LVL_ID");
            criteria.setName("GROUP_LVL_NAME");
            criteria.setValue("GROUP_LVL_ID");
            criteria.setSortName("ASC");
            //criteria.setCondition("GROUP_LVL_ID <> 5");

            ectGroupLvl = commonService.getDropdownList(criteria);
        }

        return ectGroupLvl;
    }
    
    private List<DropDownList> ectUserGroup;
    /***
     * กลุ่มผู้ใช้
     * @return 
     */
    public List<DropDownList>ddlUserGroup(){
        if (ectUserGroup == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("ECT_USER_GROUP");
            criteria.setOrderByField("USER_GROUP_NAME");
            criteria.setName("USER_GROUP_NAME");
            criteria.setValue("USER_GROUP_ID");
            criteria.setSortName("ASC");

            ectUserGroup = commonService.getDropdownList(criteria);
        }

        return ectUserGroup;
    }
}
