/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.user;

import com.ect.db.entity.Report001Detail;
import com.ect.web.controller.BaseController;
import com.ect.web.controller.model.ReportVO;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.utils.JsfUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
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
    private static final long serialVersionUID = 7863151922951862688L;
    
    private List<ReportVO> rptPreSendList;
    private List<ReportVO> rptWaitStatusList;
    private List<ReportVO> rptCompleteList;
    private List<ReportVO> rptList;
    
    @ManagedProperty(value="#{dropdownFactory}")
    private DropdownFactory dropdownFactory;

    private String curYear;
    
    private List<Report001Detail> report001Details;
    private Report001Detail inputReport001Detail = new Report001Detail();
    
    @PostConstruct
    public void init() {
        
    }

    @Override
    public void resetForm() {
        
    }
    /***
     * init before loadPopup
     */
    public void initReportDetail(){
    
        logger.debug("initReportDetail...");
        
        inputReport001Detail =  new Report001Detail();
    }
    
    /***
     * AddReportDetail to Grid
     */
    public void addReportDetail(ActionEvent  actionEvent) throws Exception{
    
        logger.debug("addReportDetail... {}",inputReport001Detail);
        
        if(report001Details==null || report001Details.isEmpty()){
            
            report001Details = new ArrayList<>();
            inputReport001Detail.setReportDetailId(1);
            
        }else{
            
            inputReport001Detail.setReportDetailId(report001Details.get(report001Details.size()-1).getReportDetailId()+1);
            
        }
        
        report001Details.add(inputReport001Detail);
        
        JsfUtil.hidePopup("dlgAddReportDetail");
    }
    
    public void onEdit(RowEditEvent event) {  
        
        JsfUtil.addSuccessMessage("แก้ใขข้อมูลสำเร็จ!!");
        
    }  
      
    public void onCancel(RowEditEvent event) {  
        
        JsfUtil.addSuccessMessage("ยกเลิก!!");
        
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

    public List<ReportVO> getRptList() {
        return rptList;
    }

    public void setRptList(List<ReportVO> rptList) {
        this.rptList = rptList;
    }
    
    /**
     * @return the dropdownFactory
     */
    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    /**
     * @param dropdownFactory the dropdownFactory to set
     */
    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
    }

    /**
     * @return the curYear
     */
    public String getCurYear() {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date()).toString();
        
    }

    /**
     * @return the report001Details
     */
    public List<Report001Detail> getReport001Details() {
        return report001Details;
    }

    /**
     * @param report001Details the report001Details to set
     */
    public void setReport001Details(List<Report001Detail> report001Details) {
        this.report001Details = report001Details;
    }

    /**
     * @return the inputReport001Detail
     */
    public Report001Detail getInputReport001Detail() {
        return inputReport001Detail;
    }

    /**
     * @param inputReport001Detail the inputReport001Detail to set
     */
    public void setInputReport001Detail(Report001Detail inputReport001Detail) {
        this.inputReport001Detail = inputReport001Detail;
    }
}
