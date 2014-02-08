/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.report.entity.Report009;

/**
 *
 * @author totoland
 */
public interface Report009Dao {

    Report009 findByReportId(Integer paramReportId);
    
}
