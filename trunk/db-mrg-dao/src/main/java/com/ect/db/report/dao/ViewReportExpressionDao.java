/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.report.dao;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportExpression;
import java.util.List;

/**
 *
 * @author Totoland
 */
public interface ViewReportExpressionDao {
    public List<ViewReportExpression>findByCriteria(ReportCriteria criteria);
}
