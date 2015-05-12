/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.report.dao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportExpression;
import com.ect.db.report.entity.ViewReportExpression014;
import java.util.List;

/**
 *
 * @author Totoland
 */
public interface ViewReportExpressionDao {
    List<ViewReportExpression>findByCriteria(ReportCriteria criteria);
    
    List<ViewReportExpression> findReport011ByCriteria(ReportCriteria criteria);

    List<ViewReportExpression> findReport012ByCriteria(ReportCriteria reportCriteria);

    List<ViewReportExpression014> findReport014ByCriteria(ReportCriteria reportCriteria);
}
