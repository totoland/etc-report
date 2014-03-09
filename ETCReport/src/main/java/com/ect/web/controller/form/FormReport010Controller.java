/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report010Detail;
import com.ect.db.report.entity.Report010;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.io.InputStream;
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

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class FormReport010Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport010Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report010> reportGennericService;
    /**
     * *
     * For Insert Report010
     */
    private Report010 report010 = new Report010();
    /**
     * *
     * For ListDetail
     */
    private List<Report010Detail> report010Details;
    /**
     * *
     * For Add Record
     */
    private Report010Detail inputReport010Detail = new Report010Detail();
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

    @PostConstruct
    public void init() {

        logger.trace("init Form010!!");

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
        this.report010 = new Report010();
        report010Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_010 + MessageUtils.PRINT_LINE_STAR());

        report010.setReport010DetailList(report010Details);
        report010.setCreatedDate(new Date());
        report010.setCreatedUser(super.getUserAuthen().getUserId());
        report010.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report010.setReportDesc(ectConfManager.getReportName(REPORT_010));
        report010.setReportCode(REPORT_010);
        report010.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report010);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_010");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report010);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_010 + MessageUtils.PRINT_LINE_STAR());

        report010.setReport010DetailList(report010Details);
        report010.setUpdatedDate(new Date());
        report010.setUpdatedUser(super.getUserAuthen().getUserId());
        //report010.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report010.setReportDesc(ectConfManager.getReportName(REPORT_010));
        report010.setReportCode(REPORT_010);
        report010.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report010);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report010);

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

        inputReport010Detail = new Report010Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport010Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report010Details == null || report010Details.isEmpty()) {

            report010Details = new ArrayList<>();
            inputReport010Detail.setKey(1);

        } else {

            inputReport010Detail.setKey(report010Details.get(report010Details.size() - 1).getKey() + 1);

        }

        inputReport010Detail.setReportId(report010);

        report010Details.add(inputReport010Detail);

        JsfUtil.hidePopup("REPORT_010dlgAddReportDetail");
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

        Report010Detail editRow = ((Report010Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report010Details.size(); i++) {

            if (report010Details.get(i).getKey() == editRow.getKey()) {

                report010Details.remove(i);
                report010Details.add(i, editRow);

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
     * @return the curYear
     */
    public String getCurYear() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date()).toString();

    }

    /**
     * @return the report010Details
     */
    public List<Report010Detail> getReport010Details() {
        return report010Details;
    }

    /**
     * @param report010Details the report010Details to set
     */
    public void setReport010Details(List<Report010Detail> report010Details) {
        this.report010Details = report010Details;
    }

    /**
     * @return the inputReport010Detail
     */
    public Report010Detail getInputReport010Detail() {
        return inputReport010Detail;
    }

    /**
     * @param inputReport010Detail the inputReport010Detail to set
     */
    public void setInputReport010Detail(Report010Detail inputReport010Detail) {
        this.inputReport010Detail = inputReport010Detail;
    }

    /**
     * @return the report010
     */
    public Report010 getReport010() {
        return report010;
    }

    /**
     * @param report010 the report010 to set
     */
    public void setReport010(Report010 report010) {
        this.report010 = report010;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport010Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport010Detail.getPoliticalParty())) {
            msg += "กรุณาระบุพรรค<br/>";
        }
        if (StringUtils.isBlank(inputReport010Detail.getPosition())) {
            msg += "กรุณาระบุตำแหน่ง<br/>";
        }
        if (inputReport010Detail.getAcceptedDate() == null) {
            msg += "กรุณาระบุวันเดือนปีที่ นายทะเบียนตอบรับ<br/>";
        }
        if (!NumberUtils.isNumber(inputReport010Detail.getCaseIn()) || inputReport010Detail.getCaseIn().intValue() == 0) {
            msg += "กรุณาระบุกรณีย้ายเข้า (ราย)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport010Detail.getCaseOut()) || inputReport010Detail.getCaseOut().intValue() == 0) {
            msg += "กรุณาระบุกรณีพ้นฯ (ราย)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport010Detail.getCaseOut()) || inputReport010Detail.getCaseOut().intValue() == 0) {
            msg += "กรุณาระบุกรณีพ้นฯ (ราย)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport010Detail.getCaseDissolveParliament()) || inputReport010Detail.getCaseDissolveParliament() == 0) {
            msg += "กรุณาระบุกรณียุบสภา<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report010.getReport010DetailList() == null || report010.getReport010DetailList().isEmpty()) {
            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        return true;
    }

    /**
     * @return the paramReportCode
     */
    public String getParamReportCode() {
        return paramReportCode;
    }

    /**
     * @param paramReportCode the paramReportCode to set
     */
    public void setParamReportCode(String paramReportCode) {
        this.paramReportCode = paramReportCode;
    }

    /**
     * @return the paramReportId
     */
    public Integer getParamReportId() {
        return paramReportId;
    }

    /**
     * @param paramReportId the paramReportId to set
     */
    public void setParamReportId(Integer paramReportId) {
        this.paramReportId = paramReportId;
    }

    /**
     * @return the paramMode
     */
    public String getParamMode() {
        return paramMode;
    }

    /**
     * @param paramMode the paramMode to set
     */
    public void setParamMode(String paramMode) {
        this.paramMode = paramMode;
    }

    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_010.equalsIgnoreCase(paramReportCode)) {

            report010 = reportService.findByReport010ById(paramReportId);

            logger.trace("report010 : {}", report010);

            /**
             * * Set ReportDetail **
             */
            report010Details = new ArrayList<>();
            report010Details.addAll(report010.getReport010DetailList());

            for (int i = 0; i < report010Details.size(); i++) {

                report010Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    public void goToEdit() {
        String url = "?mode=" + REPORT_MODE_EDIT + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);
    }

    public void goToClose() {
        JsfUtil.hidePopupIframe("dialogEdit");
    }

    public void goToCancel() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_010 + MessageUtils.PRINT_LINE_STAR());
        String url = "?mode=" + REPORT_MODE_VIEW + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);

    }

    private void initParam() {
        paramMode = getParameter("mode");
        paramReportCode = getParameter("reportCode");
        paramReportId = NumberUtils.toInteger(getParameter("reportId"));

        logger.trace("paramMode : {}", StringUtils.isBlank(paramMode) ? REPORT_MODE_CREATE : paramMode);
        logger.trace("paramReportCode : {}", paramReportCode);
        logger.trace("paramReportId : {}", paramReportId);
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report010> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report010> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    /**
     * @return the reportTitle
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * @param reportTitle the reportTitle to set
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    private void initForm() {

        Date curDate = new Date();

        reportTitle = MessageUtils.getResourceBundleString("report_header_title_dep", DateTimeUtils.getInstance().thDate(curDate, "MMMM"), DateTimeUtils.getInstance().thDate(curDate, "yyyy"), getUserAuthen().getProvinceName(), "");

    }

    @Override
    public void onDelete(Object object) {

        Report010Detail rowDelete = (Report010Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report010Details.remove(rowDelete);

    }
}
