/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

import com.ect.db.report.entity.ViewReportStatus;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author totoland
 */
public abstract class LazyDataModel extends org.primefaces.model.LazyDataModel<ViewReportStatus> implements Serializable {

    private static final long serialVersionUID = -7332140435235556716L;
    private List<ViewReportStatus> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    @Override
    public abstract List<ViewReportStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

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
}
