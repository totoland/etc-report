/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.ViewUser;
import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.ReportService;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
public abstract class BaseFormReportController extends BaseController{
    private static final long serialVersionUID = -7864668711794526812L;

    private static Logger logger = LoggerFactory.getLogger(BaseFormReportController.class);

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
    protected static final String REPORT_022 = "REPORT_022";
    protected static final String REPORT_023 = "REPORT_023";
    protected static final String REPORT_MODE_EDIT = "edit";
    protected static final String REPORT_MODE_VIEW = "view";
    protected static final String REPORT_MODE_CREATE = "create";
    
    @Override
    public void resetForm() {
        
    }
    
    public abstract void save();
    
    public abstract void edit();
    
    /**
     * *
     * AddReportDetail to Grid
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
    
    /***
     * File Export Excel
     */
    public abstract void fileXLSDownload();

    public boolean canApprove(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

//        logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        } else if (EctFlowStatus.FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 4;

        } else if (EctFlowStatus.FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 3;

        } else if (EctFlowStatus.FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 2;

        } else if (EctFlowStatus.FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 0;

        } else if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        }

        return false;

    }

    public boolean canReject(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

//        logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if (EctFlowStatus.FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 4;

        } else if (EctFlowStatus.FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 3;

        } else if (EctFlowStatus.FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 2;

        } else if (EctFlowStatus.FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 0;

        } else if (EctFlowStatus.FlowStatus.REJECT.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() != 4;

        } else if (EctFlowStatus.FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        }

        return false;

    }

    public boolean canEdit(Integer createdUserGroup) {

        ViewUser user = getUserAuthen();

//        logger.trace("Login UserGroup {} Created UserGroup {}", user.getUserGroupId(), createdUserGroup);

        if (user.getUserGroupId() != null && createdUserGroup.intValue() == user.getUserGroupId().intValue()) {
            return true;
        }

        return false;
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
}
