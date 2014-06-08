/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report004Detail;
import com.ect.db.report.entity.Report004;
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
public class FormReport004Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport004Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report004> reportGennericService;
    /**
     * *
     * For Insert Report004
     */
    private Report004 report004 = new Report004();
    /**
     * *
     * For ListDetail
     */
    private List<Report004Detail> report004Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report004Detail inputReport004Detail = new Report004Detail();

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

        cal();
    }

    @Override
    public void resetForm() {
        this.report004 = new Report004();
        report004Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_004 + MessageUtils.PRINT_LINE_STAR());

        report004.setReport004DetailList(report004Details);
        report004.setCreatedDate(new Date());
        report004.setCreatedUser(super.getUserAuthen().getUserId());
        report004.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report004.setReportDesc(ectConfManager.getReportName(REPORT_004));
        report004.setReportCode(REPORT_004);
        report004.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report004);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report004);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_004 + MessageUtils.PRINT_LINE_STAR());

        //report004.setReport004DetailList(report004Details);
        report004.setUpdatedDate(new Date());
        report004.setUpdatedUser(super.getUserAuthen().getUserId());
        report004.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report004.setReportDesc(ectConfManager.getReportName(REPORT_004));
        report004.setReportCode(REPORT_004);
        report004.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report004);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report004);

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

        inputReport004Detail = new Report004Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport004Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report004Details == null || report004Details.isEmpty()) {

            report004Details = new ArrayList<>();
            inputReport004Detail.setKey(1);

        } else {

            inputReport004Detail.setKey(report004Details.get(report004Details.size() - 1).getKey() + 1);

        }

        inputReport004Detail.setReportId(report004);

        report004Details.add(inputReport004Detail);

        JsfUtil.hidePopup("REPORT_004dlgAddReportDetail");
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

        Report004Detail editRow = ((Report004Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report004Details.size(); i++) {

            if (report004Details.get(i).getKey() == editRow.getKey()) {

                report004Details.remove(i);
                report004Details.add(i, editRow);

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
     * @return the report004Details
     */
    public List<Report004Detail> getReport004Details() {
        return report004Details;
    }

    /**
     * @param report004Details the report004Details to set
     */
    public void setReport004Details(List<Report004Detail> report004Details) {
        this.report004Details = report004Details;
    }

    /**
     * @return the inputReport004Detail
     */
    public Report004Detail getInputReport004Detail() {
        return inputReport004Detail;
    }

    /**
     * @param inputReport004Detail the inputReport004Detail to set
     */
    public void setInputReport004Detail(Report004Detail inputReport004Detail) {
        this.inputReport004Detail = inputReport004Detail;
    }

    /**
     * @return the report004
     */
    public Report004 getReport004() {
        return report004;
    }

    /**
     * @param report004 the report004 to set
     */
    public void setReport004(Report004 report004) {
        this.report004 = report004;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport004Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport004Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport004Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport004Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport004Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport004Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport004Detail.getBuggetSource())) {
//            msg += "กรุณาระบุที่มาของงบประมาณbr/>";
//        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

//        if (report004.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report004.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report004.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report004.getReport004DetailList() == null || report004.getReport004DetailList().isEmpty()) {
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

        if (REPORT_004.equalsIgnoreCase(paramReportCode)) {

            report004 = reportService.findByReport004ById(paramReportId);

            logger.trace("report004 : {}", report004);

            /**
             * * Set ReportDetail **
             */
            report004Details = new ArrayList<>();
            report004Details.addAll(report004.getReport004DetailList());

            for (int i = 0; i < report004Details.size(); i++) {

                report004Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report004> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report004> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        report004Details = new ArrayList<>();

        Report004Detail report004Detail1 = new Report004Detail();
        report004Detail1.setDlaType("กรุงเทพมหานคร");
        report004Detail1.setReportId(report004);

        Report004Detail report004Detail2 = new Report004Detail();
        report004Detail2.setDlaType("องค์การบริหารส่วนจังหวัด");
        report004Detail2.setReportId(report004);

        Report004Detail report004Detail3 = new Report004Detail();
        report004Detail3.setDlaType("เมืองพัทยา");
        report004Detail3.setReportId(report004);

        Report004Detail report004Detail4 = new Report004Detail();
        report004Detail4.setDlaType("1.เทศบาลนคร");
        report004Detail4.setReportId(report004);

        Report004Detail report004Detail5 = new Report004Detail();
        report004Detail5.setDlaType("2.เทศบาลเมือง");
        report004Detail5.setReportId(report004);

        Report004Detail report004Detail6 = new Report004Detail();
        report004Detail6.setDlaType("3.เทศบาลตำบล");
        report004Detail6.setReportId(report004);

        Report004Detail report004Detail7 = new Report004Detail();
        report004Detail7.setDlaType("องค์การบริหารส่วนตำบล");
        report004Detail7.setReportId(report004);

        report004Details.add(report004Detail1);
        report004Details.add(report004Detail2);
        report004Details.add(report004Detail3);
        report004Details.add(report004Detail4);
        report004Details.add(report004Detail5);
        report004Details.add(report004Detail6);
        report004Details.add(report004Detail7);

        /***
         * Init Footer
         */
        setFooter(new Report004Detail());
        getFooter().setAmountPhTh(0);
        getFooter().setAmountSTh(0);
        getFooter().setElectionBeforeAnnouncement(0);
        getFooter().setElectionEarlierAmountPhTh(0);
        getFooter().setElectionEarlierAmountSTh(0);
        getFooter().setElectionEarlierCurMonthPhTh(0);
        getFooter().setElectionEarlierCurMonthSTh(0);
        getFooter().setElectionEarlierLastMonthPhTh(0);
        getFooter().setElectionEarlierLastMonthSTh(0);
        getFooter().setElectionFillVacancy(0);
        getFooter().setFullTerm(0);
        
        report004.setReport004DetailList(report004Details);
        report004.setReportMonth(reportMonth);
        report004.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Report004Detail footer;

    public void cal() {

        footer = new Report004Detail();
        
        setFooter(new Report004Detail());
        getFooter().setAmountPhTh(0);
        getFooter().setAmountSTh(0);
        getFooter().setElectionBeforeAnnouncement(0);
        getFooter().setElectionEarlierAmountPhTh(0);
        getFooter().setElectionEarlierAmountSTh(0);
        getFooter().setElectionEarlierCurMonthPhTh(0);
        getFooter().setElectionEarlierCurMonthSTh(0);
        getFooter().setElectionEarlierLastMonthPhTh(0);
        getFooter().setElectionEarlierLastMonthSTh(0);
        getFooter().setElectionFillVacancy(0);
        getFooter().setFullTerm(0);
        
        for (Report004Detail rwDetail : report004Details) {

            
            //electionEarlierLastMonthSTh+electionEarlierCurMonthSTh = electionEarlierAmountSTh
            //electionEarlierLastMonthPhTh+electionEarlierCurMonthPhTh = electionEarlierAmountPhTh
            rwDetail.setElectionEarlierAmountSTh(NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthSTh())+NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthSTh()));
            rwDetail.setElectionEarlierAmountPhTh(NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthPhTh())+NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthPhTh()));
            
            getFooter().setAmountPhTh(getFooter().getAmountPhTh() + NumberUtils.convertNUllToZero(rwDetail.getAmountPhTh()));
            getFooter().setAmountSTh(getFooter().getAmountSTh() + NumberUtils.convertNUllToZero(rwDetail.getAmountSTh()));
            getFooter().setElectionBeforeAnnouncement(getFooter().getElectionBeforeAnnouncement() + NumberUtils.convertNUllToZero(rwDetail.getElectionBeforeAnnouncement()));
            getFooter().setElectionEarlierAmountPhTh(getFooter().getElectionEarlierAmountPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierAmountPhTh()));
            getFooter().setElectionEarlierAmountSTh(getFooter().getElectionEarlierAmountSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierAmountSTh()));
            getFooter().setElectionEarlierCurMonthPhTh(getFooter().getElectionEarlierCurMonthPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierAmountPhTh()));
            getFooter().setElectionEarlierCurMonthSTh(getFooter().getElectionEarlierCurMonthSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierCurMonthSTh()));
            getFooter().setElectionEarlierLastMonthPhTh(getFooter().getElectionEarlierLastMonthPhTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthPhTh()));
            getFooter().setElectionEarlierLastMonthSTh(getFooter().getElectionEarlierLastMonthSTh() + NumberUtils.convertNUllToZero(rwDetail.getElectionEarlierLastMonthSTh()));
            getFooter().setElectionFillVacancy(getFooter().getElectionFillVacancy() + NumberUtils.convertNUllToZero(rwDetail.getElectionFillVacancy()));
            getFooter().setFullTerm(getFooter().getFullTerm() + NumberUtils.convertNUllToZero(rwDetail.getFullTerm()));

        }
    }

    /**
     * @return the footer
     */
    public Report004Detail getFooter() {
        return footer;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(Report004Detail footer) {
        this.footer = footer;
    }
}
