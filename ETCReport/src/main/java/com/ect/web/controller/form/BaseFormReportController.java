/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.Report001;
import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.ReportGennericService;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author totoland
 */
public abstract class BaseFormReportController extends BaseController{
    private static final long serialVersionUID = -7864668711794526812L;

    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{dropdownFactory}")
    protected DropdownFactory dropdownFactory;
    @ManagedProperty(value = "#{reportGennericService}")
    protected ReportGennericService<Report001> reportGennericService;
    
    @ManagedProperty(value = "#{ectConfManager}")
    protected EctConfManager ectConfManager;
    
    @Override
    public void resetForm() {
        
    }
    
    public abstract void save();
    
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
     * init before loadPopup
     */
    public abstract void initReportDetail();
    
    /***
     * File Export Excel
     */
    public abstract void fileXLSDownload();

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
     * @return the reportGennericService
     */
    public ReportGennericService<Report001> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report001> reportGennericService) {
        this.reportGennericService = reportGennericService;
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
}
