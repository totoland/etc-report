/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report021Detail;
import com.ect.db.report.entity.Report021;
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
public class FormReport021Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport021Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report021> reportGennericService;
    /**
     * *
     * For Insert Report021
     */
    private Report021 report021 = new Report021();
    /**
     * *
     * For ListDetail
     */
    private List<Report021Detail> report021Details;
    /**
     * *
     * For Add Record
     */
    private Report021Detail inputReport021Detail = new Report021Detail();
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

    @PostConstruct
    public void init() {

        logger.trace("init Form021!!");

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
        this.report021 = new Report021();
        report021Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_021 + MessageUtils.PRINT_LINE_STAR());

        report021.setReport021DetailList(report021Details);
        report021.setCreatedDate(new Date());
        report021.setCreatedUser(super.getUserAuthen().getUserId());
        report021.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report021.setReportDesc(ectConfManager.getReportName(REPORT_021));
        report021.setReportCode(REPORT_021);
        report021.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report021);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_021");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report021);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_021 + MessageUtils.PRINT_LINE_STAR());

        report021.setReport021DetailList(report021Details);
        report021.setUpdatedDate(new Date());
        report021.setUpdatedUser(super.getUserAuthen().getUserId());
        //report021.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report021.setReportDesc(ectConfManager.getReportName(REPORT_021));
        report021.setReportCode(REPORT_021);
        report021.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report021);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report021);

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

        inputReport021Detail = new Report021Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport021Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report021Details == null || report021Details.isEmpty()) {

            report021Details = new ArrayList<>();
            inputReport021Detail.setKey(1);

        } else {

            inputReport021Detail.setKey(report021Details.get(report021Details.size() - 1).getKey() + 1);

        }

        inputReport021Detail.setReportId(report021);

        report021Details.add(inputReport021Detail);

        JsfUtil.hidePopup("REPORT_021dlgAddReportDetail");
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

        Report021Detail editRow = ((Report021Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report021Details.size(); i++) {

            if (report021Details.get(i).getKey() == editRow.getKey()) {

                report021Details.remove(i);
                report021Details.add(i, editRow);

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
     * @return the report021Details
     */
    public List<Report021Detail> getReport021Details() {
        return report021Details;
    }

    /**
     * @param report021Details the report021Details to set
     */
    public void setReport021Details(List<Report021Detail> report021Details) {
        this.report021Details = report021Details;
    }

    /**
     * @return the inputReport021Detail
     */
    public Report021Detail getInputReport021Detail() {
        return inputReport021Detail;
    }

    /**
     * @param inputReport021Detail the inputReport021Detail to set
     */
    public void setInputReport021Detail(Report021Detail inputReport021Detail) {
        this.inputReport021Detail = inputReport021Detail;
    }

    /**
     * @return the report021
     */
    public Report021 getReport021() {
        return report021;
    }

    /**
     * @param report021 the report021 to set
     */
    public void setReport021(Report021 report021) {
        this.report021 = report021;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport021Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport021Detail.getInspector())) {
            msg += "กรุณาระบุทีมตรวจการตรวจสอบฯ/นิเทศ<br/>";
        }
        if (null == inputReport021Detail.getOperationDate()) {
            msg += "กรุณาระบุว.ด.ป.ที่ปฎิบัติ<br/>";
        }
        if (!NumberUtils.isNumber(inputReport021Detail.getObtained()) || inputReport021Detail.getObtained().intValue() == 0) {
            msg += "กรุณาระบุหน่วยรับการตรวจฯ/จำนวน(แห่ง)<br/>";
        }
        if (StringUtils.isBlank(inputReport021Detail.getResult())) {
            msg += "กรุณาระบุข้อสังเกต/ผลการตรวจพบที่สำคัญ<br/>";
        }

//        if (!NumberUtils.isNumber(inputReport021Detail.getBalance()) || inputReport021Detail.getBalance().intValue() == 0) {
//            msg += "กรุณาระบุคงเหลือ<br/>";
//        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report021.getReport021DetailList() == null || report021.getReport021DetailList().isEmpty()) {
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

        if (REPORT_021.equalsIgnoreCase(paramReportCode)) {

            report021 = reportService.findByReport021ById(paramReportId);

            logger.trace("report021 : {}", report021);

            /**
             * * Set ReportDetail **
             */
            report021Details = new ArrayList<>();
            report021Details.addAll(report021.getReport021DetailList());

            for (int i = 0; i < report021Details.size(); i++) {

                report021Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_021 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report021> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report021> reportGennericService) {
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

        Report021Detail rowDelete = (Report021Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report021Details.remove(rowDelete);

    }
}
