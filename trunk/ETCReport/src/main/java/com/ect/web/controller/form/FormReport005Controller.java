/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report005Detail;
import com.ect.db.report.entity.Report005;
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
import org.slf4j.MDC;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class FormReport005Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport005Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report005> reportGennericService;
    /**
     * *
     * For Insert Report005
     */
    private Report005 report005 = new Report005();
    /**
     * *
     * For ListDetail
     */
    private List<Report005Detail> report005Details = new ArrayList<>();
    private List<Report005Detail> report005Details2 = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report005Detail inputReport005Detail = new Report005Detail();
    private Report005Detail inputReport005Detail2 = new Report005Detail();

    @PostConstruct
    public void init() {

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
        this.report005 = new Report005();
        this.report005Details.clear();
        this.report005Details2.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_005 + MessageUtils.PRINT_LINE_STAR());

        if (report005Details2 != null && !report005Details2.isEmpty()) {

            report005Details.addAll(report005Details2);

        }
        report005.setReport005DetailList(report005Details);
        report005.setCreatedDate(new Date());
        report005.setCreatedUser(super.getUserAuthen().getUserId());
        report005.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report005.setReportDesc(ectConfManager.getReportName(REPORT_005));
        report005.setReportCode(REPORT_005);
        report005.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }
        
        try {

            reportGennericService.create(report005);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report005);

            JsfUtil.windowReload();
        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_005 + MessageUtils.PRINT_LINE_STAR());

        report005.setReport005DetailList(report005Details);
        report005.setUpdatedDate(new Date());
        report005.setUpdatedUser(super.getUserAuthen().getUserId());
        report005.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report005.setReportDesc(ectConfManager.getReportName(REPORT_005));
        report005.setReportCode(REPORT_005);
        report005.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report005);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report005);

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

        inputReport005Detail = new Report005Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport005Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report005Details == null || report005Details.isEmpty()) {

            report005Details = new ArrayList<>();
            inputReport005Detail.setKey(1);

        } else {

            inputReport005Detail.setKey(report005Details.get(report005Details.size() - 1).getKey() + 1);

        }

        /**
         * *
         * 1=เลือกตั้ง,2=สรรหา for REPORT_005 only
         */
        inputReport005Detail.setElectedType(1);

        inputReport005Detail.setReportId(report005);

        report005Details.add(inputReport005Detail);

        JsfUtil.hidePopup("REPORT_005dlgAddReportDetail");
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    public void addReportDetail2(ActionEvent actionEvent) {

        logger.trace("addReportDetail2... {}", inputReport005Detail2);

        if (!validateReportDetail2()) {
            return;
        }

        if (report005Details2 == null || report005Details2.isEmpty()) {

            report005Details2 = new ArrayList<>();
            inputReport005Detail2.setKey(1);

        } else {

            inputReport005Detail2.setKey(report005Details2.get(report005Details2.size() - 1).getKey() + 1);

        }

        /**
         * *
         * 1=เลือกตั้ง,2=สรรหา for REPORT_005 only
         */
        inputReport005Detail.setElectedType(2);
        inputReport005Detail2.setReportId(report005);

        report005Details2.add(inputReport005Detail2);

        JsfUtil.hidePopup("REPORT_005pn_addReportDetail2");
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

        Report005Detail editRow = ((Report005Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report005Details.size(); i++) {

            if (report005Details.get(i).getKey() == editRow.getKey()) {

                report005Details.remove(i);
                report005Details.add(i, editRow);

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
     * *
     * Row Edit
     *
     * @param event
     */
    public void onEdit2(RowEditEvent event) {

        Report005Detail editRow = ((Report005Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report005Details2.size(); i++) {

            if (report005Details2.get(i).getKey() == editRow.getKey()) {

                report005Details2.remove(i);
                report005Details2.add(i, editRow);

                logger.trace("After Edit Row : {}", editRow);
            }

            break;

        }

        JsfUtil.addSuccessMessage("แก้ใขข้อมูลสำเร็จ!!");

    }

    /**
     * @return the report005Details
     */
    public List<Report005Detail> getReport005Details() {
        return report005Details;
    }

    /**
     * @param report005Details the report005Details to set
     */
    public void setReport005Details(List<Report005Detail> report005Details) {
        this.report005Details = report005Details;
    }

    /**
     * @return the inputReport005Detail
     */
    public Report005Detail getInputReport005Detail() {
        return inputReport005Detail;
    }

    /**
     * @param inputReport005Detail the inputReport005Detail to set
     */
    public void setInputReport005Detail(Report005Detail inputReport005Detail) {
        this.inputReport005Detail = inputReport005Detail;
    }

    /**
     * @return the report005
     */
    public Report005 getReport005() {
        return report005;
    }

    /**
     * @param report005 the report005 to set
     */
    public void setReport005(Report005 report005) {
        this.report005 = report005;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport005Detail);

        String msg = "";

        if (inputReport005Detail.getElectedDay() == null) {
            msg += "กรุณาระบุวันที่เลือกตั้ง<br/>";
        }
        if (inputReport005Detail.getProvinceId() == null) {
            msg += "กรุณาระบุจังหวัด<br/>";
        }
        if (StringUtils.isBlank(inputReport005Detail.getElectedZone())) {
            msg += "กรุณาระบุเขตเลือกตั้ง<br/>";
        }
        if (inputReport005Detail.getVoterAmount() == null) {
            msg += "กรุณาระบุผู้มีสิทธิ์เลือกตั้ง<br/>";
        }
        if (inputReport005Detail.getVoidedBallotPaper() == null) {
            msg += "กรุณาระบุบัตรเสีย<br/>";
        }
        if (inputReport005Detail.getVoteNo() == null) {
            msg += "กรุณาระบุบัตรไม่ประสงค์จะลงคะแนนbr/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateReportDetail2() {

        logger.trace("validateReportDetail2 : {}", inputReport005Detail2);

        String msg = "";

        if (inputReport005Detail2.getNominationPeriod() == null) {
            msg += "กรุณาระบุระยะเวลาสรรหา ส.ว.<br/>";
        }
        if (inputReport005Detail2.getElection() == null) {
            msg += "กรุณาระบุการเลือกตั้ง<br/>";
        }
        if (inputReport005Detail2.getCorporateAmount() == null) {
            msg += "กรุณาระบุจำนวนองค์/รายชื่อที่เสนอ<br/>";
        }
        if (inputReport005Detail2.getSenatorNominationAmount() == null) {
            msg += "กรุณาระบุจำนวน ส.ว. สรรหา<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report005.getReport005DetailList() == null || report005.getReport005DetailList().isEmpty()) {
            msg += (MessageUtils.getResourceBundleString("require_vote_or_nomination")) + ("\\n");
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        return true;
    }

    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_005.equalsIgnoreCase(paramReportCode)) {

            report005 = reportService.findByReport005ById(paramReportId);

            logger.trace("report005 : {}", report005);

            /**
             * * Set ReportDetail **
             */
            report005Details = new ArrayList<>();
            report005Details2 = new ArrayList<>();

            /**
             * *
             * For Report005 only
             */
            for (Report005Detail report005Detail : report005.getReport005DetailList()) {

                if (report005Detail.getElectedType() != null && report005Detail.getElectedType().intValue() == 1) {

                    report005Details.add(report005Detail);

                } else {

                    report005Details2.add(report005Detail);

                }
            }

            for (int i = 0; i < report005Details.size(); i++) {

                report005Details.get(i).setKey(i);

            }

            for (int i = 0; i < report005Details2.size(); i++) {

                report005Details2.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report005> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report005> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        report005.setReportMonth(reportMonth);
        report005.setReportYear(reportYear);
    }

    /**
     * @return the inputReport005Detail2
     */
    public Report005Detail getInputReport005Detail2() {
        return inputReport005Detail2;
    }

    /**
     * @param inputReport005Detail2 the inputReport005Detail2 to set
     */
    public void setInputReport005Detail2(Report005Detail inputReport005Detail2) {
        this.inputReport005Detail2 = inputReport005Detail2;
    }

    /**
     * @return the report005Details2
     */
    public List<Report005Detail> getReport005Details2() {
        return report005Details2;
    }

    /**
     * @param report005Details2 the report005Details2 to set
     */
    public void setReport005Details2(List<Report005Detail> report005Details2) {
        this.report005Details2 = report005Details2;
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
