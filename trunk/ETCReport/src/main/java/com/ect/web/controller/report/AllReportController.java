/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.common.entity.DropDownList;
import com.ect.db.entity.EctGroupLvl;
import com.ect.db.entity.ViewUser;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report001Detail;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.entity.Report002Detail;
import com.ect.db.report.entity.Report003;
import com.ect.db.report.entity.Report003Detail;
import com.ect.db.report.entity.Report004;
import com.ect.db.report.entity.Report004Detail;
import com.ect.db.report.entity.Report005;
import com.ect.db.report.entity.Report005Detail;
import com.ect.db.report.entity.Report006;
import com.ect.db.report.entity.Report006Detail;
import com.ect.db.report.entity.Report007;
import com.ect.db.report.entity.Report007Detail;
import com.ect.db.report.entity.Report008;
import com.ect.db.report.entity.Report008Detail;
import com.ect.db.report.entity.Report009;
import com.ect.db.report.entity.Report009Detail;
import com.ect.db.report.entity.Report010;
import com.ect.db.report.entity.Report010Detail;
import com.ect.db.report.entity.Report011;
import com.ect.db.report.entity.Report011Detail;
import com.ect.db.report.entity.Report012;
import com.ect.db.report.entity.Report012Detail;
import com.ect.db.report.entity.Report013;
import com.ect.db.report.entity.Report013Detail;
import com.ect.db.report.entity.Report014;
import com.ect.db.report.entity.Report015;
import com.ect.db.report.entity.Report015Detail;
import com.ect.db.report.entity.Report016;
import com.ect.db.report.entity.Report017;
import com.ect.db.report.entity.Report017Detail;
import com.ect.db.report.entity.Report018;
import com.ect.db.report.entity.Report018Detail;
import com.ect.db.report.entity.Report019;
import com.ect.db.report.entity.Report019Detail;
import com.ect.db.report.entity.Report020;
import com.ect.db.report.entity.Report020Detail;
import com.ect.db.report.entity.Report021;
import com.ect.db.report.entity.Report022;
import com.ect.db.report.entity.Report023;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.form.BaseFormReportController;
import com.ect.web.controller.model.LazyViewReportImpl;
import com.ect.web.service.UserService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class AllReportController extends BaseFormReportController {

    private static final long serialVersionUID = 8451238753520170431L;
    private static final Logger logger = LoggerFactory.getLogger(AllReportController.class);

    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    private List<ViewReportStatus> viewReportResult;
    private ReportCriteria reportCriteria;
    private LazyDataModel<ViewReportStatus> lazyModel;

    @PostConstruct
    public void init() {

        initCriteria();

    }

    public void search() {

        logger.trace("Search!!");

        if (!StringUtils.isBlank(reportCriteria.getMonth())) {
            reportCriteria.setMonth(reportCriteria.getMonth());
        }
        if (!StringUtils.isBlank(reportCriteria.getYear())) {
            reportCriteria.setYear(reportCriteria.getYear());
        }

        reportCriteria.setUserGroupId(getUserAuthen().getUserGroupId() + "");
        reportCriteria.setUserGroupLvl(getUserAuthen().getUserGroupLvl() + "");

        logger.trace("Criteria : {}", reportCriteria);

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                    .findComponent(":form1:rptPreSendList2");
            d.setFirst(0);

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            lazyModel = reportModel;

        }
    }

    @Override
    public void resetForm() {
        initCriteria();
        lazyModel = null;
    }

    public void fileXLSDownload(ViewReportStatus viewReportStatus) {

        logger.trace("Select report : {}", viewReportStatus);

        String month = dropdownFactory.getMonthName(viewReportStatus.getReportMonth());
        String year = viewReportStatus.getReportYear();
        String depName = "";
        String createdDate = DateTimeUtils.getInstance().thDate(viewReportStatus.getCreatedDate(), DateTimeUtils.DISPLAY_DATETIME_FORMAT);
        String reportName = "";

        ViewUser viewUser = userService.findByUserId(viewReportStatus.getCreatedUser());

        if (viewUser != null) {

            depName = viewUser.getUserGroupName();

        }

        Map<String, Object> beans = new HashMap<>();
        beans.put("month", month);
        beans.put("year", year);
        beans.put("depName", depName);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", viewReportStatus.getCreatedUserFullName());

        if (viewReportStatus.getReportCode().equals(REPORT_001)) {

            reportName = REPORT_001;

            Report001 report001 = reportService.findByReport001ById(viewReportStatus.getReportId());

            if (report001 == null || report001.getReport001DetailList() == null || report001.getReport001DetailList().isEmpty()) {

                logger.warn("Cannot find Report001 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report001Detail>());

            } else {

                for (DropDownList ddl : dropdownFactory.ddlStrategic()) {

                    if (ddl.getValue().equals(report001.getStrategicId() + "")) {

                        beans.put("strategic", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlSubStrategic()) {

                    if (ddl.getValue().equals(report001.getSubStrategicId() + "")) {

                        beans.put("subStrategic", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlPlan()) {

                    if (ddl.getValue().equals(report001.getPlanId() + "")) {

                        beans.put("plan", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlProject()) {

                    if (ddl.getValue().equals(report001.getProjectId() + "")) {

                        beans.put("project", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlActivity()) {

                    if (ddl.getValue().equals(report001.getActivityId() + "")) {

                        beans.put("activity", ddl.getName());

                    }

                }

                for (int i = 0; i < report001.getReport001DetailList().size(); i++) {
                    report001.getReport001DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report001.getReport001DetailList());

            }
        } else if (viewReportStatus.getReportCode().equals(REPORT_002)) {

            reportName = REPORT_002;

            Report002 report002 = reportService.findByReport002ById(viewReportStatus.getReportId());

            if (report002 == null || report002.getReport002DetailList() == null || report002.getReport002DetailList().isEmpty()) {

                logger.warn("Cannot find Report002 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report002Detail>());

            } else {

                for (DropDownList ddl : dropdownFactory.ddlStrategic()) {

                    if (ddl.getValue().equals(report002.getStrategicId() + "")) {

                        beans.put("strategic", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlSubStrategic()) {

                    if (ddl.getValue().equals(report002.getSubStrategicId() + "")) {

                        beans.put("subStrategic", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlPlan()) {

                    if (ddl.getValue().equals(report002.getPlanId() + "")) {

                        beans.put("plan", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlProject()) {

                    if (ddl.getValue().equals(report002.getProjectId() + "")) {

                        beans.put("project", ddl.getName());

                    }

                }

                for (DropDownList ddl : dropdownFactory.ddlActivity()) {

                    if (ddl.getValue().equals(report002.getActivityId() + "")) {

                        beans.put("activity", ddl.getName());

                    }

                }

                for (int i = 0; i < report002.getReport002DetailList().size(); i++) {
                    report002.getReport002DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report002.getReport002DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_003)) {

            reportName = REPORT_003;

            Report003 report003 = reportService.findByReport003ById(viewReportStatus.getReportId());

            if (report003 == null || report003.getReport003DetailList() == null || report003.getReport003DetailList().isEmpty()) {

                logger.warn("Cannot find Report003 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report003Detail>());

            } else {

                for (int i = 0; i < report003.getReport003DetailList().size(); i++) {
                    report003.getReport003DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report003.getReport003DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_004)) {

            reportName = REPORT_004;

            Report004 report004 = reportService.findByReport004ById(viewReportStatus.getReportId());

            if (report004 == null || report004.getReport004DetailList() == null || report004.getReport004DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report004Detail>());

            } else {
                int i = 1;

                Report004Detail sum = new Report004Detail();
                sum.setAmountPhTh(0);
                sum.setAmountSTh(0);
                sum.setElectionBeforeAnnouncement(0);
                sum.setElectionEarlierAmountPhTh(0);
                sum.setElectionEarlierAmountSTh(0);
                sum.setElectionEarlierCurMonthPhTh(0);
                sum.setElectionEarlierCurMonthSTh(0);
                sum.setElectionEarlierLastMonthPhTh(0);
                sum.setElectionEarlierLastMonthSTh(0);
                sum.setElectionFillVacancy(0);
                sum.setFullTerm(0);

                for (Report004Detail rwDetail : report004.getReport004DetailList()) {
                    rwDetail.setKey(i++);

                    rwDetail.setElectionEarlierAmountSTh(NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthSTh()) + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthSTh()));
                    rwDetail.setElectionEarlierAmountPhTh(NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthPhTh()) + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthPhTh()));

                    sum.setAmountPhTh(sum.getAmountPhTh() + NumberUtils.convertNUllToZero(rwDetail.getAmountPhTh()));
                    sum.setAmountSTh(sum.getAmountSTh() + NumberUtils.convertNUllToZero(rwDetail.getAmountSTh()));
                    sum.setElectionBeforeAnnouncement(sum.getElectionBeforeAnnouncement() + NumberUtils.convertNUllToZero(rwDetail.getElectionBeforeAnnouncement()));
                    sum.setElectionEarlierAmountPhTh(sum.getElectionEarlierAmountPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierAmountPhTh()));
                    sum.setElectionEarlierAmountSTh(sum.getElectionEarlierAmountSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierAmountSTh()));
                    sum.setElectionEarlierCurMonthPhTh(sum.getElectionEarlierCurMonthPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthPhTh()));
                    sum.setElectionEarlierCurMonthSTh(sum.getElectionEarlierCurMonthSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthSTh()));
                    sum.setElectionEarlierLastMonthPhTh(sum.getElectionEarlierLastMonthPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthPhTh()));
                    sum.setElectionEarlierLastMonthSTh(sum.getElectionEarlierLastMonthSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthSTh()));
                    sum.setElectionFillVacancy(sum.getElectionFillVacancy() + NumberUtils.convertNUllToZero(rwDetail.getElectionFillVacancy()));
                    sum.setFullTerm(sum.getFullTerm() + NumberUtils.convertNUllToZero(rwDetail.getFullTerm()));
                }

                beans.put("details", report004.getReport004DetailList());
                beans.put("sum", sum);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_005)) {

            reportName = REPORT_005;

            Report005 report005 = reportService.findByReport005ById(viewReportStatus.getReportId());

            beans.put("report", report005);

            if (report005 == null || report005.getReport005DetailList() == null || report005.getReport005DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());

                beans.put("details", new ArrayList<Report004Detail>());
                beans.put("details2", new ArrayList<Report004Detail>());

            } else {

                List<Report005Detail> report005Details = new ArrayList<>();
                List<Report005Detail> report005Details2 = new ArrayList<>();

                int key1 = 1;
                int key2 = 1;

                for (Report005Detail report005Detail : report005.getReport005DetailList()) {

                    if (report005Detail.getElectedType() != null && report005Detail.getElectedType() == 1) {
                        report005Detail.setKey(key1++);
                        report005Detail.setSsElectedName(dropdownFactory.getElectedName(report005Detail.getSsElected()));
                        report005Detail.setSsElectedTypeName(dropdownFactory.getTypeElectedName(report005Detail.getSsElectedType()));
                        report005Details.add(report005Detail);
                    } else {
                        report005Detail.setKey(key2++);
                        report005Details2.add(report005Detail);
                    }

                }

                String prefix = "x ";
                String blank = "  ";
                beans.put("isElected", BooleanUtils.isTrue(report005.getIsElected()) ? prefix : blank);
                beans.put("isElectedNormal", BooleanUtils.isTrue(report005.getIsElectedNormal()) ? prefix : blank);
                beans.put("isElectedVacancy", BooleanUtils.isTrue(report005.getIsElectedVacancy()) ? prefix : blank);
                beans.put("isSenElected", BooleanUtils.isTrue(report005.getIsSenElected()) ? prefix : blank);
                beans.put("isSenElectedNormal", BooleanUtils.isTrue(report005.getIsSenElectedNormal()) ? prefix : blank);
                beans.put("isSenElectedVacancy", BooleanUtils.isTrue(report005.getIsSenElectedVacancy()) ? prefix : blank);

                beans.put("details", report005Details);
                beans.put("details2", report005Details2);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_006)) {

            reportName = REPORT_006;

            Report006 report006 = reportService.findByReport006ById(viewReportStatus.getReportId());

            if (report006 == null || report006.getReport006DetailList() == null || report006.getReport006DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report006Detail>());

            } else {

                Report006Detail sumDetail = new Report006Detail();

                sumDetail.setAmount(0);
                sumDetail.setComment(0);
                sumDetail.setConclusion(0);
                sumDetail.setSubmitManager(0);
                sumDetail.setSubmitPresidentEct(0);
                sumDetail.setSubmited(0);

                for (int i = 0; i < report006.getReport006DetailList().size(); i++) {
                    report006.getReport006DetailList().get(i).setKey(i + 1);

                    sumDetail.setAmount(sumDetail.getAmount() + report006.getReport006DetailList().get(i).getAmount());
                    sumDetail.setComment(sumDetail.getComment() + report006.getReport006DetailList().get(i).getComment());
                    sumDetail.setConclusion(sumDetail.getConclusion() + report006.getReport006DetailList().get(i).getConclusion());
                    sumDetail.setSubmitManager(sumDetail.getSubmitManager() + report006.getReport006DetailList().get(i).getSubmitManager());
                    sumDetail.setSubmitPresidentEct(sumDetail.getSubmitPresidentEct() + report006.getReport006DetailList().get(i).getSubmitPresidentEct());
                    sumDetail.setSubmited(sumDetail.getSubmited() + report006.getReport006DetailList().get(i).getSubmited());
                }

                beans.put("details", report006.getReport006DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_007)) {

            reportName = REPORT_007;

            Report007 report007 = reportService.findByReport007ById(viewReportStatus.getReportId());

            if (report007 == null || report007.getReport007DetailList() == null || report007.getReport007DetailList().isEmpty()) {

                logger.warn("Cannot find Report007 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report007Detail>());

            } else {

                for (int i = 0; i < report007.getReport007DetailList().size(); i++) {
                    report007.getReport007DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report007.getReport007DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_008)) {

            reportName = REPORT_008;

            Report008 report008 = reportService.findByReport008ById(viewReportStatus.getReportId());

            if (report008 == null || report008.getReport008DetailList() == null || report008.getReport008DetailList().isEmpty()) {

                logger.warn("Cannot find Report008 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report008Detail>());

            } else {

                for (int i = 0; i < report008.getReport008DetailList().size(); i++) {
                    report008.getReport008DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report008.getReport008DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_009)) {

            reportName = REPORT_009;

            Report009 report009 = reportService.findByReport009ById(viewReportStatus.getReportId());

            if (report009 == null || report009.getReport009DetailList() == null || report009.getReport009DetailList().isEmpty()) {

                logger.warn("Cannot find Report010 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report009Detail>());

            } else {

                Report009Detail sumDetail = new Report009Detail();
                sumDetail.setDonate(BigDecimal.ZERO);

                for (int i = 0; i < report009.getReport009DetailList().size(); i++) {
                    report009.getReport009DetailList().get(i).setKey(i + 1);

                    sumDetail.setDonate(sumDetail.getDonate().add(report009.getReport009DetailList().get(i).getDonate()));
                }

                beans.put("details", report009.getReport009DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_010)) {

            reportName = REPORT_010;

            Report010 report010 = reportService.findByReport010ById(viewReportStatus.getReportId());

            if (report010 == null || report010.getReport010DetailList() == null || report010.getReport010DetailList().isEmpty()) {

                logger.warn("Cannot find Report010 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report010.getReport010DetailList().size(); i++) {
                    report010.getReport010DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report010.getReport010DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_011)) {

            reportName = REPORT_011;

            Report011 report011 = reportService.findByReport011ById(viewReportStatus.getReportId());

            if (report011 == null || report011.getReport011DetailList() == null || report011.getReport011DetailList().isEmpty()) {

                logger.warn("Cannot find Report011 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report011Detail sumDetail = new Report011Detail();

                sumDetail.setAccessCommittee(0);
                sumDetail.setAllamount(0);
                sumDetail.setAnalystRemain(0);
                sumDetail.setAtCenter(0);
                sumDetail.setAtEctProvince(0);
                sumDetail.setEctResolve(0);
                sumDetail.setOfferEct(0);
                sumDetail.setOnAgenda(0);

                for (int i = 0; i < report011.getReport011DetailList().size(); i++) {
                    report011.getReport011DetailList().get(i).setKey(i + 1);

                    report011.getReport011DetailList().get(i).setAtCenter(NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getOnAgenda()) + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAccessCommittee()) + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getOfferEct()) + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAnalystRemain()));
                    report011.getReport011DetailList().get(i).setAllamount(NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAtCenter()) + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAtEctProvince()) + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getEctResolve()));

                    sumDetail.setAccessCommittee(sumDetail.getAccessCommittee() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAccessCommittee()));
                    sumDetail.setAllamount(sumDetail.getAllamount() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAllamount()));
                    sumDetail.setAnalystRemain(sumDetail.getAnalystRemain() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAnalystRemain()));
                    sumDetail.setAtCenter(sumDetail.getAtCenter() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAtCenter()));
                    sumDetail.setAtEctProvince(sumDetail.getAtEctProvince() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getAtEctProvince()));
                    sumDetail.setEctResolve(sumDetail.getEctResolve() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getEctResolve()));
                    sumDetail.setOfferEct(sumDetail.getOfferEct() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getOfferEct()));
                    sumDetail.setOnAgenda(sumDetail.getOnAgenda() + NumberUtils.convertNUllToZero(report011.getReport011DetailList().get(i).getOnAgenda()));
                }

                beans.put("details", report011.getReport011DetailList());
                beans.put("sum", sumDetail);
            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_012)) {

            reportName = REPORT_012;

            Report012 report012 = reportService.findByReport012ById(viewReportStatus.getReportId());

            if (report012 == null || report012.getReport012DetailList() == null || report012.getReport012DetailList().isEmpty()) {

                logger.warn("Cannot find Report012 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {
                Report012Detail sumDetail = new Report012Detail();

                sumDetail.setAccessCommittee(0);
                sumDetail.setAllamount(0);
                sumDetail.setAnalystRemain(0);
                sumDetail.setAtCenter(0);
                sumDetail.setAtEctProvince(0);
                sumDetail.setEctResolve(0);
                sumDetail.setOfferEct(0);
                sumDetail.setOnAgenda(0);

                for (int i = 0; i < report012.getReport012DetailList().size(); i++) {
                    report012.getReport012DetailList().get(i).setKey(i + 1);
                    report012.getReport012DetailList().get(i).setAtCenter(NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getOnAgenda()) + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAccessCommittee()) + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getOfferEct()) + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAnalystRemain()));
                    report012.getReport012DetailList().get(i).setAllamount(NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAtCenter()) + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAtEctProvince()) + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getEctResolve()));

                    sumDetail.setAccessCommittee(sumDetail.getAccessCommittee() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAccessCommittee()));
                    sumDetail.setAllamount(sumDetail.getAllamount() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAllamount()));
                    sumDetail.setAnalystRemain(sumDetail.getAnalystRemain() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAnalystRemain()));
                    sumDetail.setAtCenter(sumDetail.getAtCenter() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAtCenter()));
                    sumDetail.setAtEctProvince(sumDetail.getAtEctProvince() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getAtEctProvince()));
                    sumDetail.setEctResolve(sumDetail.getEctResolve() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getEctResolve()));
                    sumDetail.setOfferEct(sumDetail.getOfferEct() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getOfferEct()));
                    sumDetail.setOnAgenda(sumDetail.getOnAgenda() + NumberUtils.convertNUllToZero(report012.getReport012DetailList().get(i).getOnAgenda()));
                }

                beans.put("details", report012.getReport012DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_013)) {

            reportName = REPORT_013;

            Report013 report013 = reportService.findByReport013ById(viewReportStatus.getReportId());

            if (report013 == null || report013.getReport013DetailList() == null || report013.getReport013DetailList().isEmpty()) {

                logger.warn("Cannot find Report013 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report013Detail sumDetail = new Report013Detail();
                sumDetail.setComplete(0);
                sumDetail.setDecisionToPrepare(0);
                sumDetail.setEctSignedComplee(0);
                sumDetail.setEctSignedOnprocess(0);
                sumDetail.setNoSend(0);
                sumDetail.setOnProcess(0);
                sumDetail.setSended(0);

                for (int i = 0; i < report013.getReport013DetailList().size(); i++) {
                    report013.getReport013DetailList().get(i).setKey(i + 1);
                    report013.getReport013DetailList().get(i).setEctSignedComplee(NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getSended()) + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getNoSend()));
                    report013.getReport013DetailList().get(i).setComplete(NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getEctSignedComplee()) + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getEctSignedOnprocess()));
                    report013.getReport013DetailList().get(i).setDecisionToPrepare(NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getOnProcess()) + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getComplete()));

                    sumDetail.setComplete(sumDetail.getComplete() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getComplete()));
                    sumDetail.setDecisionToPrepare(sumDetail.getDecisionToPrepare() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getDecisionToPrepare()));
                    sumDetail.setEctSignedComplee(sumDetail.getEctSignedComplee() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getEctSignedComplee()));
                    sumDetail.setEctSignedOnprocess(sumDetail.getEctSignedOnprocess() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getEctSignedOnprocess()));
                    sumDetail.setNoSend(sumDetail.getNoSend() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getNoSend()));
                    sumDetail.setOnProcess(sumDetail.getOnProcess() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getOnProcess()));
                    sumDetail.setSended(sumDetail.getSended() + NumberUtils.convertNUllToZero(report013.getReport013DetailList().get(i).getSended()));
                }

                beans.put("details", report013.getReport013DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_014)) {

            reportName = REPORT_014;

            Report014 report014 = reportService.findByReport014ById(viewReportStatus.getReportId());

            if (report014 == null || report014.getReport014DetailList() == null || report014.getReport014DetailList().isEmpty()) {

                logger.warn("Cannot find Report014 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report014.getReport014DetailList().size(); i++) {
                    report014.getReport014DetailList().get(i).setKey(i + 1);
                    beans.put("remark", report014.getReport014DetailList().get(i).getRemark());
                }

                beans.put("details", report014.getReport014DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_015)) {

            reportName = REPORT_015;

            Report015 report015 = reportService.findByReport015ById(viewReportStatus.getReportId());

            if (report015 == null || report015.getReport015DetailList() == null || report015.getReport015DetailList().isEmpty()) {

                logger.warn("Cannot find Report015 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report015Detail sumDetail = new Report015Detail();
                sumDetail.setMeetingTime(0);
                sumDetail.setPtAmount(0);
                sumDetail.setSumAmount(0);
                sumDetail.setStAmount(0);

                for (int i = 0; i < report015.getReport015DetailList().size(); i++) {
                    report015.getReport015DetailList().get(i).setKey(i + 1);
                    report015.getReport015DetailList().get(i).setSumAmount(NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getStAmount()) + NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getPtAmount()));

                    sumDetail.setMeetingTime(sumDetail.getMeetingTime() + NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getMeetingTime()));
                    sumDetail.setPtAmount(sumDetail.getPtAmount() + NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getPtAmount()));
                    sumDetail.setSumAmount(sumDetail.getSumAmount() + NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getSumAmount()));
                    sumDetail.setStAmount(sumDetail.getStAmount() + NumberUtils.convertNUllToZero(report015.getReport015DetailList().get(i).getStAmount()));
                }

                beans.put("details", report015.getReport015DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_016)) {

            reportName = REPORT_016;

            Report016 report016 = reportService.findByReport016ById(viewReportStatus.getReportId());

            if (report016 == null || report016.getReport016DetailList() == null || report016.getReport016DetailList().isEmpty()) {

                logger.warn("Cannot find Report016 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report016.getReport016DetailList().size(); i++) {
                    report016.getReport016DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report016.getReport016DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_017)) {

            reportName = REPORT_017;

            Report017 report017 = reportService.findByReport017ById(viewReportStatus.getReportId());

            if (report017 == null || report017.getReport017DetailList() == null || report017.getReport017DetailList().isEmpty()) {

                logger.warn("Cannot find Report017 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report017Detail sumDetail = new Report017Detail();
                sumDetail.setAdding(0);
                sumDetail.setAllStory(0);
                sumDetail.setCriminalCase(0);
                sumDetail.setEct(0);
                sumDetail.setRedCard(0);
                sumDetail.setRequestNoReceived(0);
                sumDetail.setRequestReceived(0);
                sumDetail.setResetCounter(0);
                sumDetail.setWithdrawnRequest(0);
                sumDetail.setYellowCard(0);
                sumDetail.setYellowCardCriminalCase(0);

                for (int i = 0; i < report017.getReport017DetailList().size(); i++) {
                    report017.getReport017DetailList().get(i).setKey(i + 1);

                    sumDetail.setAllStory(sumDetail.getAllStory() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getAllStory()));
                    sumDetail.setCriminalCase(sumDetail.getCriminalCase() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getCriminalCase()));

                    sumDetail.setEct(sumDetail.getEct() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getEct()));
                    sumDetail.setRedCard(sumDetail.getRedCard() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getRedCard()));
                    sumDetail.setRequestNoReceived(sumDetail.getRequestNoReceived() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getRequestNoReceived()));
                    sumDetail.setRequestReceived(sumDetail.getRequestReceived() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getRequestReceived()));
                    sumDetail.setAdding(sumDetail.getAdding() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getAdding()));
                    sumDetail.setResetCounter(sumDetail.getResetCounter() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getResetCounter()));
                    sumDetail.setWithdrawnRequest(sumDetail.getWithdrawnRequest() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getWithdrawnRequest()));
                    sumDetail.setYellowCard(sumDetail.getYellowCard() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getYellowCard()));
                    sumDetail.setYellowCardCriminalCase(sumDetail.getYellowCardCriminalCase() + NumberUtils.convertNUllToZero(report017.getReport017DetailList().get(i).getYellowCardCriminalCase()));
                }

                beans.put("details", report017.getReport017DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_018)) {

            reportName = REPORT_018;

            Report018 report018 = reportService.findByReport018ById(viewReportStatus.getReportId());

            if (report018 == null || report018.getReport018DetailList() == null || report018.getReport018DetailList().isEmpty()) {

                logger.warn("Cannot find Report018 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report018Detail sumDetail = new Report018Detail();

                sumDetail.setEctDepProvince(0);
                sumDetail.setOocAmount(0);
                sumDetail.setProject(0);
                sumDetail.setLaasAmount(0);
                sumDetail.setFullTerm(0);
                sumDetail.setBudgetFullTerm(0);
                sumDetail.setNewElection(0);
                sumDetail.setBudgetElection(0);
                sumDetail.setReplaceEmplyPosition(0);
                sumDetail.setReplaceBudget(0);

                for (int i = 0; i < report018.getReport018DetailList().size(); i++) {
                    report018.getReport018DetailList().get(i).setKey(i + 1);
                    report018.getReport018DetailList().get(i).setLaasAmount(NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getFullTerm()) + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getNewElection()) + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getReplaceEmplyPosition()));

                    sumDetail.setEctDepProvince(sumDetail.getEctDepProvince() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getEctDepProvince()));
                    sumDetail.setOocAmount(sumDetail.getOocAmount() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getOocAmount()));
                    sumDetail.setProject(sumDetail.getProject() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getProject()));
                    sumDetail.setLaasAmount(sumDetail.getLaasAmount() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getLaasAmount()));
                    sumDetail.setFullTerm(sumDetail.getFullTerm() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getFullTerm()));
                    sumDetail.setBudgetFullTerm(sumDetail.getBudgetFullTerm() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getBudgetFullTerm()));
                    sumDetail.setNewElection(sumDetail.getNewElection() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getNewElection()));
                    sumDetail.setBudgetElection(sumDetail.getBudgetElection() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getBudgetElection()));
                    sumDetail.setReplaceEmplyPosition(sumDetail.getReplaceEmplyPosition() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getReplaceEmplyPosition()));
                    sumDetail.setReplaceBudget(sumDetail.getReplaceBudget() + NumberUtils.convertNUllToZero(report018.getReport018DetailList().get(i).getReplaceBudget()));
                }

                beans.put("details", report018.getReport018DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_019)) {

            reportName = REPORT_019;

            Report019 report019 = reportService.findByReport019ById(viewReportStatus.getReportId());

            if (report019 == null || report019.getReport019DetailList() == null || report019.getReport019DetailList().isEmpty()) {

                logger.warn("Cannot find Report019 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report019Detail sumDetail = new Report019Detail();
                sumDetail.setVerifyElectionAmount(0);
                sumDetail.setFullTerm(0);
                sumDetail.setNewElection(0);
                sumDetail.setReplaceEmplyPosition(0);
                sumDetail.setReportLaas(0);
                sumDetail.setNoReportLaas(0);
                sumDetail.setExtendResult(0);
                sumDetail.setNoExtendResult(0);

                for (Report019Detail rd : report019.getReport019DetailList()) {

                    rd.setVerifyElectionAmount(NumberUtils.convertNUllToZero(rd.getFullTerm()) + NumberUtils.convertNUllToZero(rd.getNewElection()) + NumberUtils.convertNUllToZero(rd.getReplaceEmplyPosition()));
                    rd.setReportLaas(NumberUtils.convertNUllToZero(rd.getExtendResult()) + NumberUtils.convertNUllToZero(rd.getNoExtendResult()));

                    sumDetail.setVerifyElectionAmount(sumDetail.getVerifyElectionAmount() + NumberUtils.convertNUllToZero(rd.getVerifyElectionAmount()));
                    sumDetail.setFullTerm(sumDetail.getFullTerm() + NumberUtils.convertNUllToZero(rd.getFullTerm()));
                    sumDetail.setNewElection(sumDetail.getNewElection() + NumberUtils.convertNUllToZero(rd.getNewElection()));
                    sumDetail.setReplaceEmplyPosition(sumDetail.getReplaceEmplyPosition() + NumberUtils.convertNUllToZero(rd.getReplaceEmplyPosition()));
                    sumDetail.setReportLaas(sumDetail.getReportLaas() + NumberUtils.convertNUllToZero(rd.getReportLaas()));
                    sumDetail.setNoReportLaas(sumDetail.getNoReportLaas() + NumberUtils.convertNUllToZero(rd.getNoReportLaas()));
                    sumDetail.setExtendResult(sumDetail.getExtendResult() + NumberUtils.convertNUllToZero(rd.getExtendResult()));
                    sumDetail.setNoExtendResult(sumDetail.getNoExtendResult() + NumberUtils.convertNUllToZero(rd.getNoExtendResult()));

                }

                beans.put("details", report019.getReport019DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_020)) {

            reportName = REPORT_020;

            Report020 report020 = reportService.findByReport020ById(viewReportStatus.getReportId());

            if (report020 == null || report020.getReport020DetailList() == null || report020.getReport020DetailList().isEmpty()) {

                logger.warn("Cannot find Report020 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                Report020Detail sumDetail = new Report020Detail();
                sumDetail.setSupport(0);
                sumDetail.setApprove(0);

                for (int i = 0; i < report020.getReport020DetailList().size(); i++) {
                    report020.getReport020DetailList().get(i).setKey(i + 1);

                    sumDetail.setSupport(sumDetail.getSupport() + NumberUtils.convertNUllToZero(report020.getReport020DetailList().get(i).getSupport()));
                    sumDetail.setApprove(sumDetail.getApprove() + NumberUtils.convertNUllToZero(report020.getReport020DetailList().get(i).getApprove()));

                }

                beans.put("details", report020.getReport020DetailList());
                beans.put("sum", sumDetail);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_021)) {

            reportName = REPORT_021;

            Report021 report021 = reportService.findByReport021ById(viewReportStatus.getReportId());

            if (report021 == null || report021.getReport021DetailList() == null || report021.getReport021DetailList().isEmpty()) {

                logger.warn("Cannot find Report021 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report021.getReport021DetailList().size(); i++) {
                    report021.getReport021DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report021.getReport021DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_022)) {

            reportName = REPORT_022;

            Report022 report022 = reportService.findByReport022ById(viewReportStatus.getReportId());

            if (report022 == null || report022.getReport022DetailList() == null || report022.getReport022DetailList().isEmpty()) {

                logger.warn("Cannot find Report022 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report022.getReport022DetailList().size(); i++) {
                    report022.getReport022DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report022.getReport022DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_023)) {

            reportName = REPORT_023;

            Report023 report023 = reportService.findByReport023ById(viewReportStatus.getReportId());

            if (report023 == null || report023.getReport023DetailList() == null || report023.getReport023DetailList().isEmpty()) {

                logger.warn("Cannot find Report023 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                for (int i = 0; i < report023.getReport023DetailList().size(); i++) {
                    report023.getReport023DetailList().get(i).setKey(i + 1);
                }

                beans.put("details", report023.getReport023DetailList());

            }

        }

        Workbook wb = null;
        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/" + reportName + ".xls");
        XLSTransformer transformer = new XLSTransformer();

        try {

            wb = transformer.transformXLS(is, beans);

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();

            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + "" + DateTimeUtils.getInstance().thDateNow(DateTimeUtils.DATE_TIME_FORMAT) + ".xls\"");

            try (ServletOutputStream out = response.getOutputStream()) {
                wb.write(out);
                out.flush();
            }

            ctx.responseComplete();
        } catch (ParsePropertyException | IOException | InvalidFormatException ex) {

            JsfUtil.alertJavaScript(MessageUtils.getString("error", ex.getMessage()));
            logger.error("cannot export report", ex);

        } finally {
        }
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        InputStream is = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(is, "application/vnd.ms-excel ", "report.xlsx");
        return file;
    }

    public boolean canSearchUserGroup(){
        return isAdmin() || isCenter();
    }
    
    /**
     * @return the viewReportResult
     */
    public List<ViewReportStatus> getViewReportResult() {
        return viewReportResult;
    }

    /**
     * @param viewReportResult the viewReportResult to set
     */
    public void setViewReportResult(List<ViewReportStatus> viewReportResult) {
        this.viewReportResult = viewReportResult;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save() {
    }

    @Override
    public void edit() {
    }

    @Override
    public void addReportDetail(ActionEvent actionEvent) {
    }

    @Override
    public void onEdit(RowEditEvent event) {
    }

    @Override
    public void onCancel(RowEditEvent event) {
    }

    @Override
    public void initReportDetail() {
    }

    @Override
    public void fileXLSDownload() {
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
     * @return the lazyModel
     */
    public LazyDataModel<ViewReportStatus> getLazyModel() {
        return lazyModel;
    }

    /**
     * @param lazyModel the lazyModel to set
     */
    public void setLazyModel(LazyDataModel<ViewReportStatus> lazyModel) {
        this.lazyModel = lazyModel;
    }

    private void initCriteria() {
        reportCriteria = new ReportCriteria();
    }

    @Override
    public void onDelete(Object object) {
    }
}
