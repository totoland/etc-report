/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.ViewReport001SummaryDetail;
import com.ect.web.service.ReportService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Totoland
 */
public class LazyViewReport001SummaryDetailImpl extends LazyDataModel<ViewReport001SummaryDetail>{
    
    private static final long serialVersionUID = 3393928916012392996L;
    private static final Logger logger = LoggerFactory.getLogger(LazyViewReport001SummaryImpl.class);
    
    protected List<ViewReport001SummaryDetail> datasource;
    protected int pageSize = 0;
    protected int rowIndex = 0;
    protected int rowCount = 0;
    protected ReportCriteria reportCriteria;
    protected ReportService reportService;

    @Override
    public List<ViewReport001SummaryDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        getReportCriteria().setStartRow(first);
        getReportCriteria().setMaxRow(pageSize);
        
        datasource = reportService.findReport001DetailByCriteria(reportCriteria);

        return datasource;

    }

    @Override
    public boolean isRowAvailable() {
        if (datasource == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < datasource.size();
    }

    @Override
    public Object getRowKey(ViewReport001SummaryDetail user) {
        return user.getId();
    }

    @Override
    public ViewReport001SummaryDetail getRowData() {
        if (datasource == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index > datasource.size()) {
            return null;
        }
        return datasource.get(index);
    }

    @Override
    public ViewReport001SummaryDetail getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }
        for (ViewReport001SummaryDetail user : datasource) {
            if ((user.getId() + "").equals(rowKey)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<ViewReport001SummaryDetail>) list;
    }

    @Override
    public Object getWrappedData() {
        return datasource;
    }

    /**
     * @return the reportCriteria
     */
    public ReportCriteria getReportCriteria() {
        return reportCriteria;
    }

    /**
     * @param reportCriteria the reportCriteria to set
     */
    public void setReportCriteria(ReportCriteria reportCriteria) {
        this.reportCriteria = reportCriteria;
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
