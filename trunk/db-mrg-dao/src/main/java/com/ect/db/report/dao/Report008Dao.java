/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.report.entity.Report008;

/**
 *
 * @author totoland
 */
public interface Report008Dao {
    
    Report008 findByReportId(Integer paramReportId);
    
}
