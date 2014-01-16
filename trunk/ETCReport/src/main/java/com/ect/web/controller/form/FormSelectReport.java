/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.ReportName;
import com.ect.web.service.ReportService;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.StringUtils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class FormSelectReport extends BaseFormReportController {

    private static final long serialVersionUID = -3172811617429712390L;
    private static final Logger logger = LoggerFactory.getLogger(FormSelectReport.class);
    protected String reportCode;
    protected String reportPath = "/pages/form/";
    protected String reportUrl;
    protected boolean selectedReport = true;
    protected String reportMode;
    
    @Autowired
    ReportService reportService;
    
    @PostConstruct
    public void init() {
    }

    /**
     * *
     * Event Select Dropdown Report
     */
    public void selectReport() {

        logger.trace("Select report by create mode: " + reportCode);

        this.reportMode = REPORT_MODE_CREATE;
        
        ReportName reportName = ectConfManager.getReportObj(reportCode);

        logger.trace("Report : {}", reportName);

        if (reportName != null) {
            reportUrl = reportName.getReportUrl();
        }

    }

    /***
     * Init for ViewEdit Report
     * @param reportCode 
     */
    public void initViewEditReport(String reportCode,Integer reportId){
        
        logger.trace("Select report by view mode : {} reportId : {}", reportCode);
        
        this.reportCode = reportCode;
        
        this.reportMode = REPORT_MODE_VIEW;
        
        String url = ectConfManager.getReportObj(reportCode).getReportUrl();
        
        logger.trace("Open iframe URL : {}",url);
        
        openIframe("edit/"+url);
        
        if(REPORT_001.equalsIgnoreCase(reportCode)){
            
            reportService.findByReportId(reportId);
            
        }
    }
    
    public void validateSelectReport() {

        reportMode = REPORT_MODE_CREATE;
        
        if (StringUtils.isBlank(reportCode)) {

            addError(MessageUtils.getResourceBundleString("require_select_message", "รายงาน"));
            return;

        }

        logger.trace("clearAllMessage!!");
        
        clearAllMessage();
        openDialog("REPORT_MainDialog");
    }

    @Override
    public void save() {
    }

    @Override
    public void addReportDetail(ActionEvent actionEvent) {
    }

    @Override
    public void onEdit(RowEditEvent event) {
    }

    @Override
    public void onCancel(RowEditEvent event) {
    }

    @Override
    public void initReportDetail() {
    }

    @Override
    public void fileXLSDownload() {
    }

    /**
     * @return the reportCode
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * @param reportCode the reportCode to set
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * @return the reportUrl
     */
    public String getReportUrl() {
        return reportUrl;
    }

    /**
     * @param reportUrl the reportUrl to set
     */
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    /**
     * @return the selectdReport
     */
    public boolean isSelectedReport() {
        return selectedReport;
    }

    /**
     * @param selectdReport the selectdReport to set
     */
    public void setSelectedReport(boolean selectedReport) {
        this.selectedReport = selectedReport;
    }

    /**
     * @return the reportMode
     */
    public String getReportMode() {
        return reportMode;
    }

    /**
     * @param reportMode the reportMode to set
     */
    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }
}
