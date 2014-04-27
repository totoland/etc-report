/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report009Detail;
import com.ect.db.report.entity.Report009;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
public class FormReport009Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport009Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report009> reportGennericService;
    /**
     * *
     * For Insert Report009
     */
    private Report009 report009 = new Report009();
    /**
     * *
     * For ListDetail
     */
    private List<Report009Detail> report009Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report009Detail inputReport009Detail = new Report009Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form009!!");

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
        this.report009 = new Report009();
        report009Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_009 + MessageUtils.PRINT_LINE_STAR());

        report009.setReport009DetailList(report009Details);
        report009.setCreatedDate(new Date());
        report009.setCreatedUser(super.getUserAuthen().getUserId());
        report009.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report009.setReportDesc(ectConfManager.getReportName(REPORT_009));
        report009.setReportCode(REPORT_009);
        report009.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report009);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report009);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_009 + MessageUtils.PRINT_LINE_STAR());

        report009.setReport009DetailList(report009Details);
        report009.setUpdatedDate(new Date());
        report009.setUpdatedUser(super.getUserAuthen().getUserId());
        //report009.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report009.setReportDesc(ectConfManager.getReportName(REPORT_009));
        report009.setReportCode(REPORT_009);
        report009.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report009);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report009);

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

        inputReport009Detail = new Report009Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport009Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report009Details == null || report009Details.isEmpty()) {

            report009Details = new ArrayList<>();
            inputReport009Detail.setKey(1);

        } else {

            inputReport009Detail.setKey(report009Details.get(report009Details.size() - 1).getKey() + 1);

        }

        inputReport009Detail.setReportId(report009);

        report009Details.add(inputReport009Detail);
        
        sumReport009Details();

        JsfUtil.hidePopup("REPORT_009dlgAddReportDetail");
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

        Report009Detail editRow = ((Report009Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report009Details.size(); i++) {

            if (report009Details.get(i).getKey() == editRow.getKey()) {

                report009Details.remove(i);
                report009Details.add(i, editRow);

                logger.trace("After Edit Row : {}", editRow);
            }

            sumReport009Details();
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
     * @return the report009Details
     */
    public List<Report009Detail> getReport009Details() {
        return report009Details;
    }

    /**
     * @param report009Details the report009Details to set
     */
    public void setReport009Details(List<Report009Detail> report009Details) {
        this.report009Details = report009Details;
    }

    /**
     * @return the inputReport009Detail
     */
    public Report009Detail getInputReport009Detail() {
        return inputReport009Detail;
    }

    /**
     * @param inputReport009Detail the inputReport009Detail to set
     */
    public void setInputReport009Detail(Report009Detail inputReport009Detail) {
        this.inputReport009Detail = inputReport009Detail;
    }

    /**
     * @return the report009
     */
    public Report009 getReport009() {
        return report009;
    }

    /**
     * @param report009 the report009 to set
     */
    public void setReport009(Report009 report009) {
        this.report009 = report009;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport009Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport009Detail.getPoliticalParty())) {
            msg += "กรุณาระบุพรรค<br/>";
        }
        if (!NumberUtils.isNumber(inputReport009Detail.getDonate()) || inputReport009Detail.getDonate().intValue() == 0) {
            msg += "กรุณาระบุงบจำนวนเงินที่ได้รับบริจาค<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report009.getReport009DetailList() == null || report009.getReport009DetailList().isEmpty()) {
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

        if (REPORT_009.equalsIgnoreCase(paramReportCode)) {

            report009 = reportService.findByReport009ById(paramReportId);

            logger.trace("report009 : {}", report009);

            /**
             * * Set ReportDetail **
             */
            report009Details = new ArrayList<>();
            report009Details.addAll(report009.getReport009DetailList());

            for (int i = 0; i < report009Details.size(); i++) {

                report009Details.get(i).setKey(i);

            }

            sumReport009Details();
        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report009> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report009> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        logger.trace("reportTitle : {}", reportTitle);

        report009.setReport009DetailList(report009Details);
        report009.setReportMonth(reportMonth);
        report009.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {

        Report009Detail rowDelete = (Report009Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report009Details.remove(rowDelete);
        
        sumReport009Details();

    }
    
    private Report009Detail sumDetail = new Report009Detail();

    public void sumReport009Details() {

        sumDetail.setDonate(BigDecimal.ZERO);

        for (Report009Detail rd : report009Details) {

            getSumDetail().setDonate(getSumDetail().getDonate().add(rd.getDonate()));

        }

        logger.trace("sumDetail : {}", sumDetail);

    }
    
    /**
     * @return the sumDetail
     */
    public Report009Detail getSumDetail() {
        return sumDetail;
    }

    /**
     * @param sumDetail the sumDetail to set
     */
    public void setSumDetail(Report009Detail sumDetail) {
        this.sumDetail = sumDetail;
    }
}
