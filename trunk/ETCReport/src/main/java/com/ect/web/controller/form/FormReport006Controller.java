/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report006Detail;
import com.ect.db.report.entity.Report006;
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
public class FormReport006Controller extends BaseFormReportController {

    private static final Logger logger = LoggerFactory.getLogger(FormReport006Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report006> reportGennericService;
    /**
     * *
     * For Insert Report006
     */
    private Report006 report006 = new Report006();
    /**
     * *
     * For ListDetail
     */
    private List<Report006Detail> report006Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report006Detail inputReport006Detail = new Report006Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form006!!");

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
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_006 + MessageUtils.PRINT_LINE_STAR());

        report006.setReport006DetailList(report006Details);
        report006.setCreatedDate(new Date());
        report006.setCreatedUser(super.getUserAuthen().getUserId());
        report006.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report006.setReportDesc(ectConfManager.getReportName(REPORT_006));
        report006.setReportCode(REPORT_006);
        report006.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report006);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report006);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_006 + MessageUtils.PRINT_LINE_STAR());

        report006.setReport006DetailList(report006Details);
        report006.setUpdatedDate(new Date());
        report006.setUpdatedUser(super.getUserAuthen().getUserId());
        report006.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report006.setReportDesc(ectConfManager.getReportName(REPORT_006));
        report006.setReportCode(REPORT_006);
        report006.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report006);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report006);

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

        inputReport006Detail = new Report006Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport006Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report006Details == null || report006Details.isEmpty()) {

            report006Details = new ArrayList<>();
            inputReport006Detail.setKey(1);

        } else {

            inputReport006Detail.setKey(report006Details.get(report006Details.size() - 1).getKey() + 1);

        }

        inputReport006Detail.setReportId(report006);

        report006Details.add(inputReport006Detail);

        sumReport006Details();

        JsfUtil.hidePopup("REPORT_006dlgAddReportDetail");
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

        Report006Detail editRow = ((Report006Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report006Details.size(); i++) {

            if (report006Details.get(i).getKey() == editRow.getKey()) {

                report006Details.set(i, editRow);

                logger.trace("After Edit Row : {}", editRow);

                sumReport006Details();
                break;
            }

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
     * @return the report006Details
     */
    public List<Report006Detail> getReport006Details() {
        return report006Details;
    }

    /**
     * @param report006Details the report006Details to set
     */
    public void setReport006Details(List<Report006Detail> report006Details) {
        this.report006Details = report006Details;
    }

    /**
     * @return the inputReport006Detail
     */
    public Report006Detail getInputReport006Detail() {
        return inputReport006Detail;
    }

    /**
     * @param inputReport006Detail the inputReport006Detail to set
     */
    public void setInputReport006Detail(Report006Detail inputReport006Detail) {
        this.inputReport006Detail = inputReport006Detail;
    }

    /**
     * @return the report006
     */
    public Report006 getReport006() {
        return report006;
    }

    /**
     * @param report006 the report006 to set
     */
    public void setReport006(Report006 report006) {
        this.report006 = report006;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport006Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport006Detail.getTitle())) {
            msg += "กรุณาระบุเรื่อง<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getAmount())) {
            msg += "กรุณาระบุจำนวน (เรื่อง)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getSubmitManager())) {
            msg += "กรุณาระบุเสนอผู้บริหาร สนง. (เรื่อง)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getSubmitPresidentEct())) {
            msg += "กรุณาระบุเสนอผู้ ปธ.กกต. ลงนามบรรจุวาระ (เรื่อง)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getSubmited())) {
            msg += "กรุณาระบุบรรจุวาระแล้ว (เรื่อง)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getConclusion())) {
            msg += "กรุณาระบุเป็นมติ (เรื่อง)<br/>";
        }
        if (!NumberUtils.isNumber(inputReport006Detail.getComment())) {
            msg += "กรุณาระบุเป็นความเห็น (เรื่อง)<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

//        if (report006.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report006.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report006.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report006.getReport006DetailList() == null || report006.getReport006DetailList().isEmpty()) {
//            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
//        }
//
//        if (!StringUtils.isBlank(msg.toString())) {
//            JsfUtil.alertJavaScript(msg.toString().trim());
//            return false;
//        }
        return true;
    }

    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_006.equalsIgnoreCase(paramReportCode)) {

            report006 = reportService.findByReport006ById(paramReportId);

            logger.trace("report006 : {}", report006);

            /**
             * * Set ReportDetail **
             */
            report006Details = new ArrayList<>();
            report006Details.addAll(report006.getReport006DetailList());

            for (int i = 0; i < report006Details.size(); i++) {

                report006Details.get(i).setKey(i);

            }

            sumReport006Details();
        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report006> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report006> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        report006Details = new ArrayList<>();

        Report006Detail report006Detail1 = new Report006Detail();
        report006Detail1.setAmount(0);
        report006Detail1.setComment(0);
        report006Detail1.setConclusion(0);
        report006Detail1.setSubmitManager(0);
        report006Detail1.setSubmitPresidentEct(0);
        report006Detail1.setSubmited(0);
        report006Detail1.setTitle("ขยายระยะเวลาการเลือกตั้ง");
        report006Detail1.setReportId(report006);

        Report006Detail report006Detail2 = new Report006Detail();
        report006Detail2.setAmount(0);
        report006Detail2.setComment(0);
        report006Detail2.setConclusion(0);
        report006Detail2.setSubmitManager(0);
        report006Detail2.setSubmitPresidentEct(0);
        report006Detail2.setSubmited(0);
        report006Detail2.setTitle("การแบ่งเขตเลือกตั้ง");
        report006Detail2.setReportId(report006);

        Report006Detail report006Detail3 = new Report006Detail();
        report006Detail3.setAmount(0);
        report006Detail3.setComment(0);
        report006Detail3.setConclusion(0);
        report006Detail3.setSubmitManager(0);
        report006Detail3.setSubmitPresidentEct(0);
        report006Detail3.setSubmited(0);
        report006Detail3.setTitle("การวินิจฉัยคุณสมบัติผู้สมัครรับเลือกตั้ง");
        report006Detail3.setReportId(report006);

        Report006Detail report006Detail4 = new Report006Detail();
        report006Detail4.setAmount(0);
        report006Detail4.setComment(0);
        report006Detail4.setConclusion(0);
        report006Detail4.setSubmitManager(0);
        report006Detail4.setSubmitPresidentEct(0);
        report006Detail4.setSubmited(0);
        report006Detail4.setTitle("ตอบข้อหารือ");
        report006Detail4.setReportId(report006);

        report006Details.add(report006Detail1);
        report006Details.add(report006Detail2);
        report006Details.add(report006Detail3);
        report006Details.add(report006Detail4);
        
        //Set key
        for(int i=0;i<report006Details.size();i++){
            report006Details.get(i).setKey(i);
        }

        report006.setReport006DetailList(report006Details);
        report006.setReportMonth(reportMonth);
        report006.setReportYear(reportYear);
        
        sumReport006Details();
    }

    @Override
    public void onDelete(Object object) {

        Report006Detail rowDelete = (Report006Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report006Details.remove(rowDelete);

        sumReport006Details();

    }

    private Report006Detail sumDetail = new Report006Detail();

    public void sumReport006Details() {

        sumDetail.setAmount(0);
        sumDetail.setComment(0);
        sumDetail.setConclusion(0);
        sumDetail.setSubmitManager(0);
        sumDetail.setSubmitPresidentEct(0);
        sumDetail.setSubmited(0);

        for (Report006Detail rd : report006Details) {

            getSumDetail().setAmount(getSumDetail().getAmount() + rd.getAmount());
            getSumDetail().setComment(getSumDetail().getComment() + rd.getComment());
            getSumDetail().setConclusion(getSumDetail().getConclusion() + rd.getConclusion());
            getSumDetail().setSubmitManager(getSumDetail().getSubmitManager() + rd.getSubmitManager());
            getSumDetail().setSubmitPresidentEct(getSumDetail().getSubmitPresidentEct() + rd.getSubmitPresidentEct());
            getSumDetail().setSubmited(getSumDetail().getSubmited() + rd.getSubmited());

        }

        logger.trace("sumDetail1 : {}", sumDetail);

        JsfUtil.executeJavaScript("updateFooter();");
    }

    /**
     * @return the sumDetail
     */
    public Report006Detail getSumDetail() {
        return sumDetail;
    }

    /**
     * @param sumDetail the sumDetail to set
     */
    public void setSumDetail(Report006Detail sumDetail) {
        this.sumDetail = sumDetail;
    }

}
