/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report016Detail;
import com.ect.db.report.entity.Report016;
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
public class FormReport016Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport016Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report016> reportGennericService;
    /**
     * *
     * For Insert Report016
     */
    private Report016 report016 = new Report016();
    /**
     * *
     * For ListDetail
     */
    private List<Report016Detail> report016Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report016Detail inputReport016Detail = new Report016Detail();

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
        this.report016 = new Report016();
        report016Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_016 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report016.setReport016DetailList(report016Details);
        report016.setCreatedDate(new Date());
        report016.setCreatedUser(super.getUserAuthen().getUserId());
        report016.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report016.setReportDesc(ectConfManager.getReportName(REPORT_016));
        report016.setReportCode(REPORT_016);
        report016.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report016);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_016");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report016);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_016 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        //report016.setReport016DetailList(report016Details);
        report016.setUpdatedDate(new Date());
        report016.setUpdatedUser(super.getUserAuthen().getUserId());
        report016.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report016.setReportDesc(ectConfManager.getReportName(REPORT_016));
        report016.setReportCode(REPORT_016);
        report016.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report016);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report016);

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

        inputReport016Detail = new Report016Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport016Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report016Details == null || report016Details.isEmpty()) {

            report016Details = new ArrayList<>();
            inputReport016Detail.setKey(1);

        } else {

            inputReport016Detail.setKey(report016Details.get(report016Details.size() - 1).getKey() + 1);

        }

        inputReport016Detail.setReportId(report016);

        report016Details.add(inputReport016Detail);

        JsfUtil.hidePopup("REPORT_016dlgAddReportDetail");
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

        Report016Detail editRow = ((Report016Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report016Details.size(); i++) {

            if (report016Details.get(i).getKey() == editRow.getKey()) {

                report016Details.remove(i);
                report016Details.add(i, editRow);

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
     * @return the report016Details
     */
    public List<Report016Detail> getReport016Details() {
        return report016Details;
    }

    /**
     * @param report016Details the report016Details to set
     */
    public void setReport016Details(List<Report016Detail> report016Details) {
        this.report016Details = report016Details;
    }

    /**
     * @return the inputReport016Detail
     */
    public Report016Detail getInputReport016Detail() {
        return inputReport016Detail;
    }

    /**
     * @param inputReport016Detail the inputReport016Detail to set
     */
    public void setInputReport016Detail(Report016Detail inputReport016Detail) {
        this.inputReport016Detail = inputReport016Detail;
    }

    /**
     * @return the report016
     */
    public Report016 getReport016() {
        return report016;
    }

    /**
     * @param report016 the report016 to set
     */
    public void setReport016(Report016 report016) {
        this.report016 = report016;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport016Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport016Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport016Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport016Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport016Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport016Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport016Detail.getBuggetSource())) {
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

//        if (report016.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report016.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report016.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report016.getReport016DetailList() == null || report016.getReport016DetailList().isEmpty()) {
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

        if (REPORT_016.equalsIgnoreCase(paramReportCode)) {

            report016 = reportService.findByReport016ById(paramReportId);

            logger.trace("report016 : {}", report016);

            /**
             * * Set ReportDetail **
             */
            report016Details = new ArrayList<>();
            report016Details.addAll(report016.getReport016DetailList());

            for (int i = 0; i < report016Details.size(); i++) {

                report016Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_016 + MessageUtils.PRINT_LINE_STAR());
        String url = "?mode=" + REPORT_MODE_VIEW + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);

    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report016> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report016> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        
        report016Details = new ArrayList<>();

        Report016Detail report016Detail1 = new Report016Detail();
        report016Detail1.setInstitution("สำนักสืบสวนและวินิจฉัย 1");
        report016Detail1.setConclusionMeeting("1.สั่งให้มีการเลือกตั้งใหม่ ก่อนการประกาศผลการเลือกตั้ง");
        report016Detail1.setReportId(report016);
        
        Report016Detail report016Detail2 = new Report016Detail();
        report016Detail2.setInstitution("สำนักสืบสวนและวินิจฉัย 2");
        report016Detail2.setConclusionMeeting("2.สั่งให้มีการเลือกตั้งใหม่ หลังการประกาศผลการเลือกตั้ง");
        report016Detail2.setReportId(report016);

        Report016Detail report016Detail3 = new Report016Detail();
        report016Detail3.setInstitution("สำนักสืบสวนและวินิจฉัย 3");
        report016Detail3.setConclusionMeeting("");
        report016Detail3.setReportId(report016);
        
        Report016Detail report016Detail4 = new Report016Detail();
        report016Detail4.setInstitution("สำนักสืบสวนและวินิจฉัย 4");
        report016Detail4.setConclusionMeeting("");
        report016Detail4.setReportId(report016);
        
        Report016Detail report016Detail5 = new Report016Detail();
        report016Detail5.setInstitution("สำนักสืบสวนและวินิจฉัย 5");
        report016Detail5.setConclusionMeeting("");
        report016Detail5.setReportId(report016);
        
        Report016Detail report016Detail6 = new Report016Detail();
        report016Detail6.setInstitution("");
        report016Detail6.setReportId(report016);
        
        Report016Detail report016Detail7 = new Report016Detail();
        report016Detail7.setInstitution("");
        report016Detail7.setReportId(report016);

        report016Details.add(report016Detail1);
        report016Details.add(report016Detail2);
        report016Details.add(report016Detail3);
        report016Details.add(report016Detail4);
        report016Details.add(report016Detail5);
        report016Details.add(report016Detail6);
        report016Details.add(report016Detail7);
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calSum() {

        for (int i = 0; i < report016Details.size(); i++) {
            report016Details.get(i).setSumAmount(report016Details.get(i).getStAmount()+report016Details.get(i).getPtAmount());
        }
    }
}
