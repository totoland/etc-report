/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.report.entity.Report012;

/**
 *
 * @author totoland
 */
public interface Report012Dao {

    Report012 findByReportId(Integer paramReportId);
    
}
