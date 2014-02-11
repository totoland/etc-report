/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.service.ReportService;
import com.ect.web.utils.StringUtils;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
public class LazyViewReportImpl extends LazyDataModel<ViewReportStatus> {

    private static final long serialVersionUID = -7332140435235556716L;
    private static Logger logger = LoggerFactory.getLogger(LazyViewReportImpl.class);
    private List<ViewReportStatus> datasource;
    private int pageSize = 0;
    private int rowIndex = 0;
    private int rowCount = 0;
    private ReportCriteria reportCriteria;
    private ReportService reportService;

    @Override
    public List<ViewReportStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        logger.trace("sortField : {} , sortOrder : {}", sortField, sortOrder);

        getReportCriteria().setStartRow(first);
        getReportCriteria().setMaxRow(pageSize);

        if (!StringUtils.isBlank(sortField)) {

            getReportCriteria().setSortField(sortField);

            if (sortOrder.name().equals("ASCENDING")) {
                getReportCriteria().setSortOrder("ASC");
            } else {
                getReportCriteria().setSortOrder("DESC");
            }

        }



        datasource = reportService.findByCriteria(getReportCriteria());

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
    public Object getRowKey(ViewReportStatus user) {
        return user.getReportId();
    }

    @Override
    public ViewReportStatus getRowData() {
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
    public ViewReportStatus getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }
        for (ViewReportStatus user : datasource) {
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
        this.datasource = (List<ViewReportStatus>) list;
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
