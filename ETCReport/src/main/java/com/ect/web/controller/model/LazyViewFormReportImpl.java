/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.utils.StringUtils;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
public class LazyViewFormReportImpl extends LazyViewReportImpl {

    private static final Logger logger = LoggerFactory.getLogger(LazyViewReportImpl.class);
    private static final long serialVersionUID = -3672240582985581704L;

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

        }else{
        
            /***
             * Default Order by ACTION_DATE DESC
             */
            logger.trace("Default Order by ACTION_DATE DESC");
            
            getReportCriteria().setSortField("ACTION_DATE");
            getReportCriteria().setSortOrder("DESC");
            
        }

        datasource = reportService.findReportByCriteria(getReportCriteria());

        return datasource;

    }
}
