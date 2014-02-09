/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.entity.Report003;
import com.ect.db.report.entity.Report004;
import com.ect.db.report.entity.Report005;
import com.ect.db.report.entity.Report006;
import com.ect.db.report.entity.Report007;
import com.ect.db.report.entity.Report008;
import com.ect.db.report.entity.Report009;
import com.ect.db.report.entity.Report010;
import com.ect.db.report.entity.Report023;
import com.ect.db.report.entity.ViewReport001;
import com.ect.db.report.entity.ViewReportStatus;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface ReportService {

    List<ViewReportStatus> findReportByStatus(Integer flowStatusId);

    Integer updateReportStatusApprove(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser);

    Integer updateReportStatusReject(String reportName, Integer reportId, Integer flowStatusId, Integer approvedUser, String remark);

    List<ViewReport001> findByStatus(Integer status);

    Report001 findByReport001ById(Integer reportId);

    List<ViewReportStatus> findByCriteria(ReportCriteria reportCriteria);

    Report002 findByReport002ById(Integer paramReportId);

    List<ViewReportStatus> findReportByStatus(Integer flowStatusId, Integer reportStatus);

    Report003 findByReport003ById(Integer paramReportId);

    Report004 findByReport004ById(Integer paramReportId);

    Integer countByCriteria(ReportCriteria reportCriteria);

    Report005 findByReport005ById(Integer paramReportId);

    Report006 findByReport006ById(Integer paramReportId);

    Report007 findByReport007ById(Integer paramReportId);

    Report023 findByReport023ById(Integer paramReportId);

    Report008 findByReport008ById(Integer paramReportId);

    Report009 findByReport009ById(Integer paramReportId);

    Report010 findByReport010ById(Integer paramReportId);
}
