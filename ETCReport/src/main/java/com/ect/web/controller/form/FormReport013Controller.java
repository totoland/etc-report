/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report013Detail;
import com.ect.db.report.entity.Report013;
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
public class FormReport013Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport013Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report013> reportGennericService;
    /**
     * *
     * For Insert Report013
     */
    private Report013 report013 = new Report013();
    /**
     * *
     * For ListDetail
     */
    private List<Report013Detail> report013Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report013Detail inputReport013Detail = new Report013Detail();
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

    @PostConstruct
    public void init() {

        logger.trace("init Form013!!");

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
        this.report013 = new Report013();
        report013Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_013 + MessageUtils.PRINT_LINE_STAR());

        calSum();

        report013.setReport013DetailList(report013Details);
        report013.setCreatedDate(new Date());
        report013.setCreatedUser(super.getUserAuthen().getUserId());
        report013.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report013.setReportDesc(ectConfManager.getReportName(REPORT_013));
        report013.setReportCode(REPORT_013);
        report013.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report013);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_013");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report013);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_013 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report013.setReport013DetailList(report013Details);
        report013.setUpdatedDate(new Date());
        report013.setUpdatedUser(super.getUserAuthen().getUserId());
        //report013.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report013.setReportDesc(ectConfManager.getReportName(REPORT_013));
        report013.setReportCode(REPORT_013);
        report013.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report013);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report013);

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

        inputReport013Detail = new Report013Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport013Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report013Details == null || report013Details.isEmpty()) {

            report013Details = new ArrayList<>();
            inputReport013Detail.setKey(1);

        } else {

            inputReport013Detail.setKey(report013Details.get(report013Details.size() - 1).getKey() + 1);

        }

        inputReport013Detail.setReportId(report013);

        report013Details.add(inputReport013Detail);

        JsfUtil.hidePopup("REPORT_013dlgAddReportDetail");
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

        Report013Detail editRow = ((Report013Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report013Details.size(); i++) {

            if (report013Details.get(i).getKey() == editRow.getKey()) {

                report013Details.remove(i);
                report013Details.add(i, editRow);

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

    public void calSum() {

        for (int i = 0; i < report013Details.size(); i++) {
            report013Details.get(i).setEctSignedComplee(report013Details.get(i).getSended()+report013Details.get(i).getNoSend());
            report013Details.get(i).setComplete(report013Details.get(i).getEctSignedComplee() + report013Details.get(i).getEctSignedOnprocess());
            report013Details.get(i).setDecisionToPrepare(report013Details.get(i).getOnProcess()+report013Details.get(i).getComplete());
        }
    }

    /**
     * @return the curYear
     */
    public String getCurYear() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date()).toString();

    }

    /**
     * @return the report013Details
     */
    public List<Report013Detail> getReport013Details() {
        return report013Details;
    }

    /**
     * @param report013Details the report013Details to set
     */
    public void setReport013Details(List<Report013Detail> report013Details) {
        this.report013Details = report013Details;
    }

    /**
     * @return the inputReport013Detail
     */
    public Report013Detail getInputReport013Detail() {
        return inputReport013Detail;
    }

    /**
     * @param inputReport013Detail the inputReport013Detail to set
     */
    public void setInputReport013Detail(Report013Detail inputReport013Detail) {
        this.inputReport013Detail = inputReport013Detail;
    }

    /**
     * @return the report013
     */
    public Report013 getReport013() {
        return report013;
    }

    /**
     * @param report013 the report013 to set
     */
    public void setReport013(Report013 report013) {
        this.report013 = report013;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport013Detail);

        String msg = "";

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report013.getReport013DetailList() == null || report013.getReport013DetailList().isEmpty()) {
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

        if (REPORT_013.equalsIgnoreCase(paramReportCode)) {

            report013 = reportService.findByReport013ById(paramReportId);

            logger.trace("report013 : {}", report013);

            /**
             * * Set ReportDetail **
             */
            report013Details = new ArrayList<>();
            report013Details.addAll(report013.getReport013DetailList());

            for (int i = 0; i < report013Details.size(); i++) {

                report013Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_013 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report013> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report013> reportGennericService) {
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

        report013Details = new ArrayList<>();

        Report013Detail report013Detail1 = new Report013Detail();
        report013Detail1.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 1");
        report013Detail1.setDecisionToPrepare(0);
        report013Detail1.setOnProcess(0);
        report013Detail1.setComplete(0);
        report013Detail1.setEctSignedOnprocess(0);
        report013Detail1.setEctSignedComplee(0);
        report013Detail1.setSended(0);
        report013Detail1.setNoSend(0);
        report013Detail1.setReportId(report013);

        Report013Detail report013Detail2 = new Report013Detail();
        report013Detail2.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 2");
        report013Detail2.setDecisionToPrepare(0);
        report013Detail2.setOnProcess(0);
        report013Detail2.setComplete(0);
        report013Detail2.setEctSignedOnprocess(0);
        report013Detail2.setEctSignedComplee(0);
        report013Detail2.setSended(0);
        report013Detail2.setNoSend(0);
        report013Detail2.setReportId(report013);

        Report013Detail report013Detail3 = new Report013Detail();
        report013Detail3.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 3");
        report013Detail3.setDecisionToPrepare(0);
        report013Detail3.setOnProcess(0);
        report013Detail3.setComplete(0);
        report013Detail3.setEctSignedOnprocess(0);
        report013Detail3.setEctSignedComplee(0);
        report013Detail3.setSended(0);
        report013Detail3.setNoSend(0);
        report013Detail3.setReportId(report013);

        Report013Detail report013Detail4 = new Report013Detail();
        report013Detail4.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 4");
        report013Detail4.setDecisionToPrepare(0);
        report013Detail4.setOnProcess(0);
        report013Detail4.setComplete(0);
        report013Detail4.setEctSignedOnprocess(0);
        report013Detail4.setEctSignedComplee(0);
        report013Detail4.setSended(0);
        report013Detail4.setNoSend(0);
        report013Detail4.setReportId(report013);

        Report013Detail report013Detail5 = new Report013Detail();
        report013Detail5.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 5");
        report013Detail5.setDecisionToPrepare(0);
        report013Detail5.setOnProcess(0);
        report013Detail5.setComplete(0);
        report013Detail5.setEctSignedOnprocess(0);
        report013Detail5.setEctSignedComplee(0);
        report013Detail5.setSended(0);
        report013Detail5.setNoSend(0);
        report013Detail5.setReportId(report013);

        report013Details.add(report013Detail1);
        report013Details.add(report013Detail2);
        report013Details.add(report013Detail3);
        report013Details.add(report013Detail4);
        report013Details.add(report013Detail5);
    }

    @Override
    public void onDelete(Object object) {

        Report013Detail rowDelete = (Report013Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report013Details.remove(rowDelete);

    }
}
