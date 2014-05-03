/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report023Detail;
import com.ect.db.report.entity.Report023;
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
public class FormReport023Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport023Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report023> reportGennericService;
    /**
     * *
     * For Insert Report023
     */
    private Report023 report023 = new Report023();
    /**
     * *
     * For ListDetail
     */
    private List<Report023Detail> report023Details;
    /**
     * *
     * For Add Record
     */
    private Report023Detail inputReport023Detail = new Report023Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form023!!");

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_023 + MessageUtils.PRINT_LINE_STAR());

        report023.setReport023DetailList(report023Details);
        report023.setCreatedDate(new Date());
        report023.setCreatedUser(super.getUserAuthen().getUserId());
        report023.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report023.setReportDesc(ectConfManager.getReportName(REPORT_023));
        report023.setReportCode(REPORT_023);
        report023.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report023);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            goToClose();

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report023);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_023 + MessageUtils.PRINT_LINE_STAR());

        report023.setReport023DetailList(report023Details);
        report023.setUpdatedDate(new Date());
        report023.setUpdatedUser(super.getUserAuthen().getUserId());
        report023.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report023.setReportDesc(ectConfManager.getReportName(REPORT_023));
        report023.setReportCode(REPORT_023);
        report023.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report023);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report023);

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

        inputReport023Detail = new Report023Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport023Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report023Details == null || report023Details.isEmpty()) {

            report023Details = new ArrayList<>();
            inputReport023Detail.setKey(1);

        } else {

            inputReport023Detail.setKey(report023Details.get(report023Details.size() - 1).getKey() + 1);

        }

        inputReport023Detail.setReportId(report023);

        report023Details.add(inputReport023Detail);

        JsfUtil.hidePopup("REPORT_023dlgAddReportDetail");
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

        Report023Detail editRow = ((Report023Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report023Details.size(); i++) {

            if (report023Details.get(i).getKey() == editRow.getKey()) {

                report023Details.remove(i);
                report023Details.add(i, editRow);

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
     * @return the report023Details
     */
    public List<Report023Detail> getReport023Details() {
        return report023Details;
    }

    /**
     * @param report023Details the report023Details to set
     */
    public void setReport023Details(List<Report023Detail> report023Details) {
        this.report023Details = report023Details;
    }

    /**
     * @return the inputReport023Detail
     */
    public Report023Detail getInputReport023Detail() {
        return inputReport023Detail;
    }

    /**
     * @param inputReport023Detail the inputReport023Detail to set
     */
    public void setInputReport023Detail(Report023Detail inputReport023Detail) {
        this.inputReport023Detail = inputReport023Detail;
    }

    /**
     * @return the report023
     */
    public Report023 getReport023() {
        return report023;
    }

    /**
     * @param report023 the report023 to set
     */
    public void setReport023(Report023 report023) {
        this.report023 = report023;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport023Detail);

        String msg = "";
//
//        if (StringUtils.isBlank(inputReport023Detail.getTitle())) {
//            msg += "กรุณาระบุเรื่อง<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getAmount())) {
//            msg += "กรุณาระบุจำนวน (เรื่อง)<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getSubmitManager())) {
//            msg += "กรุณาระบุเสนอผู้บริหาร สนง. (เรื่อง)<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getSubmitPresidentEct())) {
//            msg += "กรุณาระบุเสนอผู้ ปธ.กกต. ลงนามบรรจุวาระ (เรื่อง)<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getSubmited())) {
//            msg += "กรุณาระบุบรรจุวาระแล้ว (เรื่อง)<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getConclusion())) {
//            msg += "กรุณาระบุเป็นมติ (เรื่อง)<br/>";
//        }
//        if (!NumberUtils.isNumber(inputReport023Detail.getComment())) {
//            msg += "กรุณาระบุเป็นความเห็น (เรื่อง)<br/>";
//        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report023.getReport023DetailList() == null || report023.getReport023DetailList().isEmpty()) {
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

        if (REPORT_023.equalsIgnoreCase(paramReportCode)) {

            report023 = reportService.findByReport023ById(paramReportId);

            logger.trace("report023 : {}", report023);

            /**
             * * Set ReportDetail **
             */
            report023Details = new ArrayList<>();
            report023Details.addAll(report023.getReport023DetailList());

            for (int i = 0; i < report023Details.size(); i++) {

                report023Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report023> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report023> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        
        report023Details = new ArrayList<>();
//
        Report023Detail report023Detail1 = new Report023Detail();
        report023Detail1.setCases("การเลือกตั้ง สว");
//        report023Detail1.setOldCasesQuoted(1);
//        report023Detail1.setNewCasesQuoted(2);
//        report023Detail1.setSumCases(3);
//        report023Detail1.setAdminCourt(4);
//        report023Detail1.setCriminalCases(5);
//        report023Detail1.setCivilCases(6);
//        report023Detail1.setRulingCases(7);
//        report023Detail1.setRhetoricWaiting(8);
//        report023Detail1.setProgress(9);
//        report023Detail1.setInvestigator(10);
//        report023Detail1.setNacc(11);
//        report023Detail1.setAttorney(12);
//        report023Detail1.setProsecutors(13);
//        report023Detail1.setCivilCases(14);
//        report023Detail1.setAppeals(15);
//        report023Detail1.setThessaloniki(16);
//        report023Detail1.setTerminates(17);
//        report023Detail1.setPending(18);
//        report023Detail1.setAttorney2(19);
//        report023Detail1.setProsecutors2(20);
//        report023Detail1.setAppeals2(21);
//        report023Detail1.setThessaloniki2(22);
//        report023Detail1.setExecution(23);
//        report023Detail1.setTerminates2(24);
//        report023Detail1.setProgress2(25);
//        report023Detail1.setProvince(26);
//        report023Detail1.setSupreme(27);
//        report023Detail1.setTerminates3(28);
//        report023Detail1.setConsidering(29);
//        report023Detail1.setTerminates4(30);

        Report023Detail report023Detail2 = new Report023Detail();
        report023Detail2.setCases("การเลือกตั้ง สส");
//        report023Detail2.setOldCasesQuoted(1);
//        report023Detail2.setNewCasesQuoted(2);
//        report023Detail2.setSumCases(3);
//        report023Detail2.setAdminCourt(4);
//        report023Detail2.setCriminalCases(5);
//        report023Detail2.setCivilCases(6);
//        report023Detail2.setRulingCases(7);
//        report023Detail2.setRhetoricWaiting(8);
//        report023Detail2.setProgress(9);
//        report023Detail2.setInvestigator(10);
//        report023Detail2.setNacc(11);
//        report023Detail2.setAttorney(12);
//        report023Detail2.setProsecutors(13);
//        report023Detail2.setCivilCases(14);
//        report023Detail2.setAppeals(15);
//        report023Detail2.setThessaloniki(16);
//        report023Detail2.setTerminates(17);
//        report023Detail2.setPending(18);
//        report023Detail2.setAttorney2(19);
//        report023Detail2.setProsecutors2(20);
//        report023Detail2.setAppeals2(21);
//        report023Detail2.setThessaloniki2(22);
//        report023Detail2.setExecution(23);
//        report023Detail2.setTerminates2(24);
//        report023Detail2.setProgress2(25);
//        report023Detail2.setProvince(26);
//        report023Detail2.setSupreme(27);
//        report023Detail2.setTerminates3(28);
//        report023Detail2.setConsidering(29);
//        report023Detail2.setTerminates4(30);

        Report023Detail report023Detail3 = new Report023Detail();
        report023Detail3.setCases("การเลือกตั้งท้องถิ่น");
//        report023Detail3.setOldCasesQuoted(1);
//        report023Detail3.setNewCasesQuoted(2);
//        report023Detail3.setSumCases(3);
//        report023Detail3.setAdminCourt(4);
//        report023Detail3.setCriminalCases(5);
//        report023Detail3.setCivilCases(6);
//        report023Detail3.setRulingCases(7);
//        report023Detail3.setRhetoricWaiting(8);
//        report023Detail3.setProgress(9);
//        report023Detail3.setInvestigator(10);
//        report023Detail3.setNacc(11);
//        report023Detail3.setAttorney(12);
//        report023Detail3.setProsecutors(13);
//        report023Detail3.setCivilCases(14);
//        report023Detail3.setAppeals(15);
//        report023Detail3.setThessaloniki(16);
//        report023Detail3.setTerminates(17);
//        report023Detail3.setPending(18);
//        report023Detail3.setAttorney2(19);
//        report023Detail3.setProsecutors2(20);
//        report023Detail3.setAppeals2(21);
//        report023Detail3.setThessaloniki2(22);
//        report023Detail3.setExecution(23);
//        report023Detail3.setTerminates2(24);
//        report023Detail3.setProgress2(25);
//        report023Detail3.setProvince(26);
//        report023Detail3.setSupreme(27);
//        report023Detail3.setTerminates3(28);
//        report023Detail3.setConsidering(29);
//        report023Detail3.setTerminates4(30);

        Report023Detail report023Detail4 = new Report023Detail();
        report023Detail4.setCases("พรรคการเมือง");
//        report023Detail4.setOldCasesQuoted(1);
//        report023Detail4.setNewCasesQuoted(2);
//        report023Detail4.setSumCases(3);
//        report023Detail4.setAdminCourt(4);
//        report023Detail4.setCriminalCases(5);
//        report023Detail4.setCivilCases(6);
//        report023Detail4.setRulingCases(7);
//        report023Detail4.setRhetoricWaiting(8);
//        report023Detail4.setProgress(9);
//        report023Detail4.setInvestigator(10);
//        report023Detail4.setNacc(11);
//        report023Detail4.setAttorney(12);
//        report023Detail4.setProsecutors(13);
//        report023Detail4.setCivilCases(14);
//        report023Detail4.setAppeals(15);
//        report023Detail4.setThessaloniki(16);
//        report023Detail4.setTerminates(17);
//        report023Detail4.setPending(18);
//        report023Detail4.setAttorney2(19);
//        report023Detail4.setProsecutors2(20);
//        report023Detail4.setAppeals2(21);
//        report023Detail4.setThessaloniki2(22);
//        report023Detail4.setExecution(23);
//        report023Detail4.setTerminates2(24);
//        report023Detail4.setProgress2(25);
//        report023Detail4.setProvince(26);
//        report023Detail4.setSupreme(27);
//        report023Detail4.setTerminates3(28);
//        report023Detail4.setConsidering(29);
//        report023Detail4.setTerminates4(30);

        Report023Detail report023Detail5 = new Report023Detail();
        report023Detail5.setCases("องค์การเอกชน");
//        report023Detail5.setOldCasesQuoted(1);
//        report023Detail5.setNewCasesQuoted(2);
//        report023Detail5.setSumCases(3);
//        report023Detail5.setAdminCourt(4);
//        report023Detail5.setCriminalCases(5);
//        report023Detail5.setCivilCases(6);
//        report023Detail5.setRulingCases(7);
//        report023Detail5.setRhetoricWaiting(8);
//        report023Detail5.setProgress(9);
//        report023Detail5.setInvestigator(10);
//        report023Detail5.setNacc(11);
//        report023Detail5.setAttorney(12);
//        report023Detail5.setProsecutors(13);
//        report023Detail5.setCivilCases(14);
//        report023Detail5.setAppeals(15);
//        report023Detail5.setThessaloniki(16);
//        report023Detail5.setTerminates(17);
//        report023Detail5.setPending(18);
//        report023Detail5.setAttorney2(19);
//        report023Detail5.setProsecutors2(20);
//        report023Detail5.setAppeals2(21);
//        report023Detail5.setThessaloniki2(22);
//        report023Detail5.setExecution(23);
//        report023Detail5.setTerminates2(24);
//        report023Detail5.setProgress2(25);
//        report023Detail5.setProvince(26);
//        report023Detail5.setSupreme(27);
//        report023Detail5.setTerminates3(28);
//        report023Detail5.setConsidering(29);
//        report023Detail5.setTerminates4(30);

        Report023Detail report023Detail6 = new Report023Detail();
        report023Detail6.setCases("กกต.ถูกฟ้อง");
//        report023Detail6.setOldCasesQuoted(1);
//        report023Detail6.setNewCasesQuoted(2);
//        report023Detail6.setSumCases(3);
//        report023Detail6.setAdminCourt(4);
//        report023Detail6.setCriminalCases(5);
//        report023Detail6.setCivilCases(6);
//        report023Detail6.setRulingCases(7);
//        report023Detail6.setRhetoricWaiting(8);
//        report023Detail6.setProgress(9);
//        report023Detail6.setInvestigator(10);
//        report023Detail6.setNacc(11);
//        report023Detail6.setAttorney(12);
//        report023Detail6.setProsecutors(13);
//        report023Detail6.setCivilCases(14);
//        report023Detail6.setAppeals(15);
//        report023Detail6.setThessaloniki(16);
//        report023Detail6.setTerminates(17);
//        report023Detail6.setPending(18);
//        report023Detail6.setAttorney2(19);
//        report023Detail6.setProsecutors2(20);
//        report023Detail6.setAppeals2(21);
//        report023Detail6.setThessaloniki2(22);
//        report023Detail6.setExecution(23);
//        report023Detail6.setTerminates2(24);
//        report023Detail6.setProgress2(25);
//        report023Detail6.setProvince(26);
//        report023Detail6.setSupreme(27);
//        report023Detail6.setTerminates3(28);
//        report023Detail6.setConsidering(29);
//        report023Detail6.setTerminates4(30);

        report023Details.add(report023Detail1);
        report023Details.add(report023Detail2);
        report023Details.add(report023Detail3);
        report023Details.add(report023Detail4);
        report023Details.add(report023Detail5);
        report023Details.add(report023Detail6);

        for (int i = 0; i < report023Details.size(); i++) {
            report023Details.get(i).setReportId(report023);
        }
        
        report023.setReport023DetailList(report023Details);
        report023.setReportMonth(reportMonth);
        report023.setReportYear(reportYear);
//        
    }

    @Override
    public void onDelete(Object object) {

        Report023Detail rowDelete = (Report023Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report023Details.remove(rowDelete);

    }
    
    private Report023Detail sumDetail = new Report023Detail();

    public void sumReport023Details() {

        sumDetail.setAdminCourt(0);
        sumDetail.setAppeals(0);
        sumDetail.setAppeals2(0);
        sumDetail.setAttorney(0);
        sumDetail.setAttorney2(0);
        sumDetail.setCivilCases(0);
        sumDetail.setCivil_cases2(0);
        sumDetail.setConsidering(0);
        sumDetail.setCriminalCases(0);
        sumDetail.setExecution(0);
        sumDetail.setInvestigator(0);
        sumDetail.setNacc(0);
        sumDetail.setNewCasesQuoted(0);
        sumDetail.setOldCasesQuoted(0);
        sumDetail.setPending(0);
        sumDetail.setProgress(0);
        sumDetail.setProgress2(0);
        sumDetail.setProsecutors(0);
        sumDetail.setProsecutors2(0);
        sumDetail.setRhetoricWaiting(0);
        sumDetail.setRulingCases(0);
        sumDetail.setSumCases(0);
        sumDetail.setTerminates(0);
        sumDetail.setTerminates2(0);
        sumDetail.setTerminates3(0);
        sumDetail.setTerminates4(0);
        sumDetail.setThessaloniki(0);
        sumDetail.setThessaloniki2(0);

        for (Report023Detail rd : report023Details) {

            //1+2 = 3
            rd.setSumCases(NumberUtils.convertNUllToZero(rd.getOldCasesQuoted()) + NumberUtils.convertNUllToZero(rd.getNewCasesQuoted()));

        }

        logger.trace("sumDetail : {}", sumDetail);

    }
}
