/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report020Detail;
import com.ect.db.report.entity.Report020;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class FormReport020Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport020Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report020> reportGennericService;
    /**
     * *
     * For Insert Report020
     */
    private Report020 report020 = new Report020();
    /**
     * *
     * For ListDetail
     */
    private List<Report020Detail> report020Details;
    /**
     * *
     * For Add Record
     */
    private Report020Detail inputReport020Detail = new Report020Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form020!!");

        initParam();
        initForm();
        /**
         * *
         * Check Mode
         */
        if (StringUtils.isBlank(paramMode)) {
            //loadReportAllState();
        } else if (paramMode.equals(REPORT_MODE_VIEW)) {

            initViewMode();

        } else if (paramMode.equals(REPORT_MODE_EDIT)) {

            initEditMode();

        }


    }

    @Override
    public void resetForm() {
        this.report020 = new Report020();
        report020Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_020 + MessageUtils.PRINT_LINE_STAR());

        report020.setReport020DetailList(report020Details);
        report020.setCreatedDate(new Date());
        report020.setCreatedUser(super.getUserAuthen().getUserId());
        report020.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report020.setReportDesc(ectConfManager.getReportName(REPORT_020));
        report020.setReportCode(REPORT_020);
        report020.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report020);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            goToClose();

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report020);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_020 + MessageUtils.PRINT_LINE_STAR());

        report020.setReport020DetailList(report020Details);
        report020.setUpdatedDate(new Date());
        report020.setUpdatedUser(super.getUserAuthen().getUserId());
        //report020.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report020.setReportDesc(ectConfManager.getReportName(REPORT_020));
        report020.setReportCode(REPORT_020);
        report020.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report020);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report020);

        }

    }

    /**
     * *
     * init before loadPopup
     */
    @Override
    public void initReportDetail() {

        logger.trace("initReportDetail...");

        clearAllMessage();

        inputReport020Detail = new Report020Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport020Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report020Details == null || report020Details.isEmpty()) {

            report020Details = new ArrayList<>();
            inputReport020Detail.setKey(1);

        } else {

            inputReport020Detail.setKey(report020Details.get(report020Details.size() - 1).getKey() + 1);

        }

        inputReport020Detail.setReportId(report020);

        report020Details.add(inputReport020Detail);

        JsfUtil.hidePopup("REPORT_020dlgAddReportDetail");
    }
    private StreamedContent file;

    @Override
    public void fileXLSDownload() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(stream, "application/vnd.ms-excel ", "report.xlsx");
    }

    public StreamedContent getFile() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(stream, "application/vnd.ms-excel ", "report.xlsx");
        return file;
    }

    /**
     * *
     * Row Edit
     *
     * @param event
     */
    @Override
    public void onEdit(RowEditEvent event) {

        Report020Detail editRow = ((Report020Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report020Details.size(); i++) {

            if (report020Details.get(i).getKey() == editRow.getKey()) {

                report020Details.remove(i);
                report020Details.add(i, editRow);

                logger.trace("After Edit Row : {}", editRow);
            }

            break;

        }

        JsfUtil.addSuccessMessage("แก้ใขข้อมูลสำเร็จ!!");

    }

    /**
     * *
     * Row Cancel
     *
     * @param event
     */
    @Override
    public void onCancel(RowEditEvent event) {

        JsfUtil.addSuccessMessage("ยกเลิก!!");

    }

    /**
     * @return the report020Details
     */
    public List<Report020Detail> getReport020Details() {
        return report020Details;
    }

    /**
     * @param report020Details the report020Details to set
     */
    public void setReport020Details(List<Report020Detail> report020Details) {
        this.report020Details = report020Details;
    }

    /**
     * @return the inputReport020Detail
     */
    public Report020Detail getInputReport020Detail() {
        return inputReport020Detail;
    }

    /**
     * @param inputReport020Detail the inputReport020Detail to set
     */
    public void setInputReport020Detail(Report020Detail inputReport020Detail) {
        this.inputReport020Detail = inputReport020Detail;
    }

    /**
     * @return the report020
     */
    public Report020 getReport020() {
        return report020;
    }

    /**
     * @param report020 the report020 to set
     */
    public void setReport020(Report020 report020) {
        this.report020 = report020;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport020Detail);

        String msg = "";
//
//        if (StringUtils.isBlank(inputReport020Detail.getPoliticalParty())) {
//            msg += "กรุณาระบุพรรค<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport020Detail.getBudget()) || inputReport020Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณที่ได้รับสนับสนุน<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport020Detail.getDisbursedPrevious()) || inputReport020Detail.getDisbursedPrevious().intValue() == 0) {
//            msg += "กรุณาระบุก่อนหน้านี้<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport020Detail.getDisbursedMonth()) || inputReport020Detail.getDisbursedPrevious().intValue() == 0) {
//            msg += "กรุณาระบุเดือนนี้<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport020Detail.getBalance()) || inputReport020Detail.getBalance().intValue() == 0) {
//            msg += "กรุณาระบุคงเหลือ<br/>";
//        }
//
//        if (!StringUtils.isBlank(msg)) {
//            addError(msg);
//            return false;
//        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

//        if (report020.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report020.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report020.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
        if (report020.getReport020DetailList() == null || report020.getReport020DetailList().isEmpty()) {
            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        return true;
    }

    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_020.equalsIgnoreCase(paramReportCode)) {

            report020 = reportService.findByReport020ById(paramReportId);

            logger.trace("report020 : {}", report020);

            /**
             * * Set ReportDetail **
             */
            report020Details = new ArrayList<>();
            report020Details.addAll(report020.getReport020DetailList());

            for (int i = 0; i < report020Details.size(); i++) {

                report020Details.get(i).setKey(i);

            }

            sumReport020Details();
        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report020> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report020> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        report020Details = new ArrayList<>();
        Report020Detail rpt023dtl1 = new Report020Detail();
        rpt023dtl1.setList("1.แจ้งอนุมัติโครงการ");
        Report020Detail rpt023dtl2 = new Report020Detail();
        rpt023dtl2.setList("2.แจ้งอนุมัติปรับปรุงสำนักงาน");
        Report020Detail rpt023dtl3 = new Report020Detail();
        rpt023dtl3.setList("3.แจ้งอนุมัติจักซื้อครุภัณฑ์");
        Report020Detail rpt023dtl4 = new Report020Detail();
        rpt023dtl4.setList("4.แจ้งอนุมัติงบประมาณ");
        Report020Detail rpt023dtl5 = new Report020Detail();
        rpt023dtl5.setList("");
        Report020Detail rpt023dtl6 = new Report020Detail();
        rpt023dtl6.setList("");
        Report020Detail rpt023dtl7 = new Report020Detail();
        rpt023dtl7.setList("");
        Report020Detail rpt023dtl8 = new Report020Detail();
        rpt023dtl8.setList("");
        report020Details.add(rpt023dtl1);
        report020Details.add(rpt023dtl2);
        report020Details.add(rpt023dtl3);
        report020Details.add(rpt023dtl4);
        report020Details.add(rpt023dtl5);
        report020Details.add(rpt023dtl6);
        report020Details.add(rpt023dtl7);
        report020Details.add(rpt023dtl8);

        for (int i = 0; i < report020Details.size(); i++) {
            report020Details.get(i).setReportId(report020);
        }
        
        report020.setReport020DetailList(report020Details);
        report020.setReportMonth(reportMonth);
        report020.setReportYear(reportYear);
        
        sumReport020Details();
    }

    private Report020Detail sumDetail = new Report020Detail();

    public void sumReport020Details() {

        getSumDetail().setSupport(0);
        getSumDetail().setApprove(0);

        for (Report020Detail rd : report020Details) {

            getSumDetail().setSupport(getSumDetail().getSupport()+NumberUtils.convertNUllToZero(rd.getSupport()));
            getSumDetail().setApprove(getSumDetail().getApprove()+NumberUtils.convertNUllToZero(rd.getApprove()));

        }

        logger.trace("sumDetail : {}", getSumDetail());

    }
    
    @Override
    public void onDelete(Object object) {

        Report020Detail rowDelete = (Report020Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report020Details.remove(rowDelete);

    }

    /**
     * @return the sumDetail
     */
    public Report020Detail getSumDetail() {
        return sumDetail;
    }

    /**
     * @param sumDetail the sumDetail to set
     */
    public void setSumDetail(Report020Detail sumDetail) {
        this.sumDetail = sumDetail;
    }
}
