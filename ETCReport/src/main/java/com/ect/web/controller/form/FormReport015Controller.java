/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report015Detail;
import com.ect.db.report.entity.Report015;
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
public class FormReport015Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport015Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report015> reportGennericService;
    /**
     * *
     * For Insert Report015
     */
    private Report015 report015 = new Report015();
    /**
     * *
     * For ListDetail
     */
    private List<Report015Detail> report015Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report015Detail inputReport015Detail = new Report015Detail();
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

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
        this.report015 = new Report015();
        report015Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_015 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report015.setReport015DetailList(report015Details);
        report015.setCreatedDate(new Date());
        report015.setCreatedUser(super.getUserAuthen().getUserId());
        report015.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report015.setReportDesc(ectConfManager.getReportName(REPORT_015));
        report015.setReportCode(REPORT_015);
        report015.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report015);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_015");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report015);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_015 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        //report015.setReport015DetailList(report015Details);
        report015.setUpdatedDate(new Date());
        report015.setUpdatedUser(super.getUserAuthen().getUserId());
        report015.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report015.setReportDesc(ectConfManager.getReportName(REPORT_015));
        report015.setReportCode(REPORT_015);
        report015.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report015);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report015);

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

        inputReport015Detail = new Report015Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport015Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report015Details == null || report015Details.isEmpty()) {

            report015Details = new ArrayList<>();
            inputReport015Detail.setKey(1);

        } else {

            inputReport015Detail.setKey(report015Details.get(report015Details.size() - 1).getKey() + 1);

        }

        inputReport015Detail.setReportId(report015);

        report015Details.add(inputReport015Detail);

        JsfUtil.hidePopup("REPORT_015dlgAddReportDetail");
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

        Report015Detail editRow = ((Report015Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report015Details.size(); i++) {

            if (report015Details.get(i).getKey() == editRow.getKey()) {

                report015Details.remove(i);
                report015Details.add(i, editRow);

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
     * @return the report015Details
     */
    public List<Report015Detail> getReport015Details() {
        return report015Details;
    }

    /**
     * @param report015Details the report015Details to set
     */
    public void setReport015Details(List<Report015Detail> report015Details) {
        this.report015Details = report015Details;
    }

    /**
     * @return the inputReport015Detail
     */
    public Report015Detail getInputReport015Detail() {
        return inputReport015Detail;
    }

    /**
     * @param inputReport015Detail the inputReport015Detail to set
     */
    public void setInputReport015Detail(Report015Detail inputReport015Detail) {
        this.inputReport015Detail = inputReport015Detail;
    }

    /**
     * @return the report015
     */
    public Report015 getReport015() {
        return report015;
    }

    /**
     * @param report015 the report015 to set
     */
    public void setReport015(Report015 report015) {
        this.report015 = report015;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport015Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport015Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport015Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport015Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport015Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport015Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport015Detail.getBuggetSource())) {
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

//        if (report015.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report015.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report015.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report015.getReport015DetailList() == null || report015.getReport015DetailList().isEmpty()) {
//            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
//        }
//
//        if (!StringUtils.isBlank(msg.toString())) {
//            JsfUtil.alertJavaScript(msg.toString().trim());
//            return false;
//        }

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

        if (REPORT_015.equalsIgnoreCase(paramReportCode)) {

            report015 = reportService.findByReport015ById(paramReportId);

            logger.trace("report015 : {}", report015);

            /**
             * * Set ReportDetail **
             */
            report015Details = new ArrayList<>();
            report015Details.addAll(report015.getReport015DetailList());

            for (int i = 0; i < report015Details.size(); i++) {

                report015Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_015 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report015> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report015> reportGennericService) {
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

        report015Details = new ArrayList<>();

        Report015Detail report015Detail1 = new Report015Detail();
        report015Detail1.setInstitution("สำนักสืบสวนและวินิจฉัย 1");
        report015Detail1.setConclusionMeeting("1.สั่งเพิกถอนสิทธิเลือกตั้ง ก่อนวันเลือกตั้ง");
        report015Detail1.setReportId(report015);
        
        Report015Detail report015Detail2 = new Report015Detail();
        report015Detail2.setInstitution("สำนักสืบสวนและวินิจฉัย 2");
        report015Detail2.setConclusionMeeting("2.สั่งเพิกถอนสิทธิเลือกตั้ง ก่อนประกาศผลการเลือกตั้ง");
        report015Detail2.setReportId(report015);

        Report015Detail report015Detail3 = new Report015Detail();
        report015Detail3.setInstitution("สำนักสืบสวนและวินิจฉัย 3");
        report015Detail3.setConclusionMeeting("2.1 คะแนนอยู่ในเกณฑ์ไดเรับเลือกตั้ง");
        report015Detail3.setReportId(report015);
        
        Report015Detail report015Detail4 = new Report015Detail();
        report015Detail4.setInstitution("สำนักสืบสวนและวินิจฉัย 4");
        report015Detail4.setConclusionMeeting("2.2 คะแนนอยู่ไม่ในเกณฑ์ไดเรับเลือกตั้ง");
        report015Detail4.setReportId(report015);
        
        Report015Detail report015Detail5 = new Report015Detail();
        report015Detail5.setInstitution("สำนักสืบสวนและวินิจฉัย 5");
        report015Detail5.setConclusionMeeting("3. สั่งเพิกถอนสิทธิเลือกตั้งหลังประกาศผลการเลือกตั้ง");
        report015Detail5.setReportId(report015);
        
        Report015Detail report015Detail6 = new Report015Detail();
        report015Detail6.setInstitution("");
        report015Detail6.setReportId(report015);
        
        Report015Detail report015Detail7 = new Report015Detail();
        report015Detail7.setInstitution("");
        report015Detail7.setReportId(report015);

        report015Details.add(report015Detail1);
        report015Details.add(report015Detail2);
        report015Details.add(report015Detail3);
        report015Details.add(report015Detail4);
        report015Details.add(report015Detail5);
        report015Details.add(report015Detail6);
        report015Details.add(report015Detail7);
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calSum() {

        for (int i = 0; i < report015Details.size(); i++) {
            report015Details.get(i).setSumAmount(report015Details.get(i).getStAmount()+report015Details.get(i).getPtAmount());
        }
    }
}
