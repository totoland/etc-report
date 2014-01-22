/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.report.dao;

import com.ect.db.entity.Report002;
import com.ect.db.report.entity.ViewReport002;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface Report002Dao {

    List<ViewReport002> findByStatus(Integer status);

    Report002 findByReportId(Integer reportId);
}
