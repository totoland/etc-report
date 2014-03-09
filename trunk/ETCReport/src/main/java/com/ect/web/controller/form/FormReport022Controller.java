/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report022Detail;
import com.ect.db.report.entity.Report022;
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
public class FormReport022Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport022Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report022> reportGennericService;
    /**
     * *
     * For Insert Report022
     */
    private Report022 report022 = new Report022();
    /**
     * *
     * For ListDetail
     */
    private List<Report022Detail> report022Details;
    /**
     * *
     * For Add Record
     */
    private Report022Detail inputReport022Detail = new Report022Detail();
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

    @PostConstruct
    public void init() {

        logger.trace("init Form022!!");

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
        this.report022 = new Report022();
        report022Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_022 + MessageUtils.PRINT_LINE_STAR());

        report022.setReport022DetailList(report022Details);
        report022.setCreatedDate(new Date());
        report022.setCreatedUser(super.getUserAuthen().getUserId());
        report022.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report022.setReportDesc(ectConfManager.getReportName(REPORT_022));
        report022.setReportCode(REPORT_022);
        report022.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report022);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_022");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report022);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_022 + MessageUtils.PRINT_LINE_STAR());

        report022.setReport022DetailList(report022Details);
        report022.setUpdatedDate(new Date());
        report022.setUpdatedUser(super.getUserAuthen().getUserId());
        //report022.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report022.setReportDesc(ectConfManager.getReportName(REPORT_022));
        report022.setReportCode(REPORT_022);
        report022.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report022);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report022);

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

        inputReport022Detail = new Report022Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport022Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report022Details == null || report022Details.isEmpty()) {

            report022Details = new ArrayList<>();
            inputReport022Detail.setKey(1);

        } else {

            inputReport022Detail.setKey(report022Details.get(report022Details.size() - 1).getKey() + 1);

        }

        inputReport022Detail.setReportId(report022);

        report022Details.add(inputReport022Detail);

        JsfUtil.hidePopup("REPORT_022dlgAddReportDetail");
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

        Report022Detail editRow = ((Report022Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report022Details.size(); i++) {

            if (report022Details.get(i).getKey() == editRow.getKey()) {

                report022Details.remove(i);
                report022Details.add(i, editRow);

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
     * @return the report022Details
     */
    public List<Report022Detail> getReport022Details() {
        return report022Details;
    }

    /**
     * @param report022Details the report022Details to set
     */
    public void setReport022Details(List<Report022Detail> report022Details) {
        this.report022Details = report022Details;
    }

    /**
     * @return the inputReport022Detail
     */
    public Report022Detail getInputReport022Detail() {
        return inputReport022Detail;
    }

    /**
     * @param inputReport022Detail the inputReport022Detail to set
     */
    public void setInputReport022Detail(Report022Detail inputReport022Detail) {
        this.inputReport022Detail = inputReport022Detail;
    }

    /**
     * @return the report022
     */
    public Report022 getReport022() {
        return report022;
    }

    /**
     * @param report022 the report022 to set
     */
    public void setReport022(Report022 report022) {
        this.report022 = report022;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport022Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport022Detail.getNotice())) {
            msg += "กรุณาระบุกฎหมาย ระเบียบ ประกาศ ข้อกำหนด<br/>";
        }
//        if (!NumberUtils.isNumber(inputReport022Detail.getBudget()) || inputReport022Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณที่ได้รับสนับสนุน<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport022Detail.getDisbursedPrevious()) || inputReport022Detail.getDisbursedPrevious().intValue() == 0) {
//            msg += "กรุณาระบุก่อนหน้านี้<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport022Detail.getDisbursedMonth()) || inputReport022Detail.getDisbursedPrevious().intValue() == 0) {
//            msg += "กรุณาระบุเดือนนี้<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport022Detail.getBalance()) || inputReport022Detail.getBalance().intValue() == 0) {
//            msg += "กรุณาระบุคงเหลือ<br/>";
//        }
//
        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report022.getReport022DetailList() == null || report022.getReport022DetailList().isEmpty()) {
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

        if (REPORT_022.equalsIgnoreCase(paramReportCode)) {

            report022 = reportService.findByReport022ById(paramReportId);

            logger.trace("report022 : {}", report022);

            /**
             * * Set ReportDetail **
             */
            report022Details = new ArrayList<>();
            report022Details.addAll(report022.getReport022DetailList());

            for (int i = 0; i < report022Details.size(); i++) {

                report022Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_022 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report022> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report022> reportGennericService) {
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

        Report022Detail rowDelete = (Report022Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report022Details.remove(rowDelete);

    }
}
