/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.EctGroupLvl.GroupLevel;
import com.ect.db.entity.ViewUser;
import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.ReportService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
public abstract class BaseFormReportController extends BaseController {

    private static final long serialVersionUID = -7864668711794526812L;
    private static final Logger logger = LoggerFactory.getLogger(BaseFormReportController.class);
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{dropdownFactory}")
    protected DropdownFactory dropdownFactory;
    @ManagedProperty(value = "#{ectConfManager}")
    protected EctConfManager ectConfManager;
    @ManagedProperty(value = "#{reportService}")
    protected ReportService reportService;
    protected static final String REPORT_001 = "REPORT_001";
    protected static final String REPORT_002 = "REPORT_002";
    protected static final String REPORT_003 = "REPORT_003";
    protected static final String REPORT_004 = "REPORT_004";
    protected static final String REPORT_005 = "REPORT_005";
    protected static final String REPORT_006 = "REPORT_006";
    protected static final String REPORT_007 = "REPORT_007";
    protected static final String REPORT_008 = "REPORT_008";
    protected static final String REPORT_009 = "REPORT_009";
    protected static final String REPORT_010 = "REPORT_010";
    protected static final String REPORT_011 = "REPORT_011";
    protected static final String REPORT_012 = "REPORT_012";
    protected static final String REPORT_013 = "REPORT_013";
    protected static final String REPORT_014 = "REPORT_014";
    protected static final String REPORT_015 = "REPORT_015";
    protected static final String REPORT_016 = "REPORT_016";
    protected static final String REPORT_017 = "REPORT_017";
    protected static final String REPORT_018 = "REPORT_018";
    protected static final String REPORT_019 = "REPORT_019";
    protected static final String REPORT_020 = "REPORT_020";
    protected static final String REPORT_021 = "REPORT_021";
    protected static final String REPORT_022 = "REPORT_022";
    protected static final String REPORT_023 = "REPORT_023";
    protected static final String REPORT_MODE_EDIT = "edit";
    protected static final String REPORT_MODE_VIEW = "view";
    protected static final String REPORT_MODE_CREATE = "create";

    
    protected String reportMonth;
    protected String reportYear;
    protected String reportTitle;
    protected String paramReportCode;
    protected Integer paramReportId;
    protected String paramMode;
    
    @Override
    public void resetForm() {
    }

    public abstract void save();

    public abstract void edit();
    
    protected void initTitle(){
        reportTitle = MessageUtils.getResourceBundleString("report_header_title", dropdownFactory.getMonthName(getReportMonth()), reportYear, getUserAuthen().getUserGroupName());
    }

    protected void initParam() {
        setParamMode(getParameter("mode"));
        setParamReportCode(getParameter("reportCode"));
        setParamReportId(NumberUtils.toInteger(getParameter("reportId")));
        setReportMonth(getParameter("reportMonth"));
        reportYear = getParameter("reportYear");
        
        logger.trace("paramMode : {}", StringUtils.isBlank(getParamMode()) ? REPORT_MODE_CREATE : getParamMode());
        logger.trace("paramReportCode : {}", getParamReportCode());
        logger.trace("paramReportId : {}", getParamReportId());
        logger.trace("reportMonth : {}", getReportMonth());
        logger.trace("reportYear : {}", reportYear);
    }
    
    public void goToEdit() {
        String url = "?mode=" + REPORT_MODE_EDIT + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode + "&reportMonth=" +reportMonth +"&reportYear="+reportYear;
        redirectPage(url);
    }

    public void goToClose() {
        JsfUtil.hidePopupIframe("dialogEdit");
    }

    public void goToCancel() {

        String url = "?mode=" + REPORT_MODE_VIEW + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode + "&reportMonth=" +reportMonth +"&reportYear="+reportYear;
        redirectPage(url);

    }
    
    /**
     * Close dialogEdit
     */
    protected void closeIframePopup(){
        JsfUtil.hidePopupIframe("dialogEdit");
    }
    
    /**
     * *
     * AddReportDetail to Grid
     * @param actionEvent
     */
    public abstract void addReportDetail(ActionEvent actionEvent);

    /**
     * *
     * Row Edit
     *
     * @param event
     */
    public abstract void onEdit(RowEditEvent event);

    /**
     * *
     * Row Cancel
     *
     * @param event
     */
    public abstract void onCancel(RowEditEvent event);

    /**
     * *
     * Row Delete
     *
     * @param row object
     */
    public abstract void onDelete(Object object);

    /**
     * *
     * init before loadPopup
     */
    public abstract void initReportDetail();

    /**
     * *
     * File Export Excel
     */
    public abstract void fileXLSDownload();

    /**
     * *
     * Only SYS_ADMIN OR OPERATOR can See Tab Create Report
     *
     * @return boolean
     */
    public boolean canCreateReport() {
        if (getUserAuthen() == null) {
            return false;
        }
        return getUserAuthen().getUserGroupLvl() == GroupLevel.OPERATOR.getLevel() || getUserAuthen().getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel();
    }

    public boolean canApprove(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

        //logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if(user.getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel()){
        
            return true;
            
        }
        
        if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.OPERATOR.getLevel();

        } else if (EctFlowStatus.FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.HEAD.getLevel();

        } else if (EctFlowStatus.FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.LEAD.getLevel();

        } else if (EctFlowStatus.FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.CENTER.getLevel();

        } else if (EctFlowStatus.FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.CENTER.getLevel();

        } else if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.OPERATOR.getLevel();

        }

        return false;

    }

    public boolean canReject(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

//        logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if (EctFlowStatus.FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.HEAD.getLevel();

        } else if (EctFlowStatus.FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.LEAD.getLevel();

        } else if (EctFlowStatus.FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.CENTER.getLevel();

        } else if (EctFlowStatus.FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == GroupLevel.CENTER.getLevel();

        } else if (EctFlowStatus.FlowStatus.REJECT.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() != 4;

        } else if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        }

        return false;

    }

    public boolean canEdit(Integer createdUserGroup) {

        ViewUser user = getUserAuthen();
        
        if(user.getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel()){
        
            return true;
            
        }

//        logger.trace("Login UserGroup {} Created UserGroup {}", user.getUserGroupId(), createdUserGroup);

        if (user.getUserGroupId() != null && createdUserGroup == user.getUserGroupId().intValue()) {
            return true;
        }

        return false;
    }
    
    /***
     * Check Can Edit 
     * @param createdUserGroup
     * @param status
     * @return 
     */
    public boolean canEdit(Integer createdUserGroup,Integer status) {

        ViewUser user = getUserAuthen();
        
        if(user.getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel()){
        
            return true;
            
        }

        return status == EctFlowStatus.ReportStatus.REJECTE.getStatus()
                && user.getUserGroupId() != null && createdUserGroup == user.getUserGroupId().intValue();
    }
    
    public boolean canEdit(Integer createdUserGroup,Integer status,Integer flowStatusId) {

        ViewUser user = getUserAuthen();
        
        //If admin or status is draff allow to edit
        if(user.getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel()
                || flowStatusId == EctFlowStatus.FlowStatus.DRAFF.getStatus()){
        
            return true;
            
        }

        //Allow to edit in case report status is reject and user is in gruop
        return status == EctFlowStatus.ReportStatus.REJECTE.getStatus()
                && user.getUserGroupId() != null && createdUserGroup == user.getUserGroupId().intValue();
    }
    
    public boolean canRemove(Integer createdUserGroup,Integer status) {

        ViewUser user = getUserAuthen();
        
        if(user.getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel()){
        
            return true;
            
        }

        if(status==401){
            
            return false;
            
        }
        
        if (user.getUserGroupId() != null && Objects.equals(createdUserGroup, user.getUserGroupId())) {
            return true;
        }

        return false;
    }
    
    public boolean isAdmin(){
        return getUserAuthen().getUserGroupLvl() == GroupLevel.SYSTEM_ADMIN.getLevel();
    }
    
    public boolean isCenter(){
        return getUserAuthen().getUserGroupLvl() == GroupLevel.CENTER.getLevel();
    }
    
    /**
     * @return the ectConfManager
     */
    public EctConfManager getEctConfManager() {
        return ectConfManager;
    }

    /**
     * @param ectConfManager the ectConfManager to set
     */
    public void setEctConfManager(EctConfManager ectConfManager) {
        this.ectConfManager = ectConfManager;
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
     * @return the reportService
     */
    public ReportService getReportService() {
        return reportService;
    }

    /**
     * @param reportService the reportService to set
     */
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void selectTab(String tabId) {
        executeJavaScript("$('#form1\\\\:tabView ul li a[href=\"#form1:tabView:"+tabId+"\"]').click();");
    }
    
    /**
     * @return the curYear
     */
    public String getCurYear() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date());

    }
    

    /**
     * @return the reportMonth
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth the reportMonth to set
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return the reportYear
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear the reportYear to set
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return the reportTitle
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * @param reportTitle the reportTitle to set
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * @return the paramReportCode
     */
    public String getParamReportCode() {
        return paramReportCode;
    }

    /**
     * @param paramReportCode the paramReportCode to set
     */
    public void setParamReportCode(String paramReportCode) {
        this.paramReportCode = paramReportCode;
    }

    /**
     * @return the paramReportId
     */
    public Integer getParamReportId() {
        return paramReportId;
    }

    /**
     * @param paramReportId the paramReportId to set
     */
    public void setParamReportId(Integer paramReportId) {
        this.paramReportId = paramReportId;
    }

    /**
     * @return the paramMode
     */
    public String getParamMode() {
        return paramMode;
    }

    /**
     * @param paramMode the paramMode to set
     */
    public void setParamMode(String paramMode) {
        this.paramMode = paramMode;
    }
    
    public Date getSysDate(){
        return new Date();
    }
}
