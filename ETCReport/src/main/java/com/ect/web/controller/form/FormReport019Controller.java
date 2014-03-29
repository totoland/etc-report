/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report019Detail;
import com.ect.db.report.entity.Report019;
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
public class FormReport019Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport019Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report019> reportGennericService;
    /**
     * *
     * For Insert Report019
     */
    private Report019 report019 = new Report019();
    /**
     * *
     * For ListDetail
     */
    private List<Report019Detail> report019Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report019Detail inputReport019Detail = new Report019Detail();
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
        this.report019 = new Report019();
        report019Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_019 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report019.setReport019DetailList(report019Details);
        report019.setCreatedDate(new Date());
        report019.setCreatedUser(super.getUserAuthen().getUserId());
        report019.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report019.setReportDesc(ectConfManager.getReportName(REPORT_019));
        report019.setReportCode(REPORT_019);
        report019.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report019);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_019");

            resetForm();
        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report019);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_019 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        //report019.setReport019DetailList(report019Details);
        report019.setUpdatedDate(new Date());
        report019.setUpdatedUser(super.getUserAuthen().getUserId());
        report019.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report019.setReportDesc(ectConfManager.getReportName(REPORT_019));
        report019.setReportCode(REPORT_019);
        report019.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report019);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report019);

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

        inputReport019Detail = new Report019Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport019Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report019Details == null || report019Details.isEmpty()) {

            report019Details = new ArrayList<>();
            inputReport019Detail.setKey(1);

        } else {

            inputReport019Detail.setKey(report019Details.get(report019Details.size() - 1).getKey() + 1);

        }

        inputReport019Detail.setReportId(report019);

        report019Details.add(inputReport019Detail);

        JsfUtil.hidePopup("REPORT_019dlgAddReportDetail");
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

        Report019Detail editRow = ((Report019Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report019Details.size(); i++) {

            if (report019Details.get(i).getKey() == editRow.getKey()) {

                report019Details.remove(i);
                report019Details.add(i, editRow);

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
     * @return the report019Details
     */
    public List<Report019Detail> getReport019Details() {
        return report019Details;
    }

    /**
     * @param report019Details the report019Details to set
     */
    public void setReport019Details(List<Report019Detail> report019Details) {
        this.report019Details = report019Details;
    }

    /**
     * @return the inputReport019Detail
     */
    public Report019Detail getInputReport019Detail() {
        return inputReport019Detail;
    }

    /**
     * @param inputReport019Detail the inputReport019Detail to set
     */
    public void setInputReport019Detail(Report019Detail inputReport019Detail) {
        this.inputReport019Detail = inputReport019Detail;
    }

    /**
     * @return the report019
     */
    public Report019 getReport019() {
        return report019;
    }

    /**
     * @param report019 the report019 to set
     */
    public void setReport019(Report019 report019) {
        this.report019 = report019;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport019Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport019Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport019Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport019Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport019Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport019Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport019Detail.getBuggetSource())) {
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

//        if (report019.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report019.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report019.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report019.getReport019DetailList() == null || report019.getReport019DetailList().isEmpty()) {
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

        if (REPORT_019.equalsIgnoreCase(paramReportCode)) {

            report019 = reportService.findByReport019ById(paramReportId);

            logger.trace("report019 : {}", report019);

            /**
             * * Set ReportDetail **
             */
            report019Details = new ArrayList<>();
            report019Details.addAll(report019.getReport019DetailList());

            for (int i = 0; i < report019Details.size(); i++) {

                report019Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_019 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report019> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report019> reportGennericService) {
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

        report019Details = new ArrayList<>();

        Report019Detail report019Detail1 = new Report019Detail();
        report019Detail1.setVerifyElection("1. กรุงเทพมหานคร");
        report019Detail1.setReportId(report019);
        
        Report019Detail report019Detail2 = new Report019Detail();
        report019Detail2.setVerifyElection("2. องค์การบริหารส่วนจังหวัด");
        report019Detail2.setReportId(report019);

        Report019Detail report019Detail3 = new Report019Detail();
        report019Detail3.setVerifyElection("3. เมืองพัทยา");
        report019Detail3.setReportId(report019);
        
        Report019Detail report019Detail4 = new Report019Detail();
        report019Detail4.setVerifyElection("4. เทศบาล");
        report019Detail4.setReportId(report019);
        
        Report019Detail report019Detail5 = new Report019Detail();
        report019Detail5.setVerifyElection("4.1 เทศบาลนคร");
        report019Detail5.setReportId(report019);
        
        Report019Detail report019Detail6 = new Report019Detail();
        report019Detail6.setVerifyElection("4.2 เทศบาลเมือง");
        report019Detail6.setReportId(report019);
        
        Report019Detail report019Detail7 = new Report019Detail();
        report019Detail7.setVerifyElection("4.3 เทศบาลตำบล");
        report019Detail7.setReportId(report019);
        
        Report019Detail report019Detail8 = new Report019Detail();
        report019Detail8.setVerifyElection("5. องค์การบริหารส่วนตำบล");
        report019Detail8.setReportId(report019);

        report019Details.add(report019Detail1);
        report019Details.add(report019Detail2);
        report019Details.add(report019Detail3);
        report019Details.add(report019Detail4);
        report019Details.add(report019Detail5);
        report019Details.add(report019Detail6);
        report019Details.add(report019Detail7);
        report019Details.add(report019Detail8);
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calSum() {

//        for (int i = 0; i < report019Details.size(); i++) {
//            report019Details.get(i).setSumAmount(report019Details.get(i).getStAmount()+report019Details.get(i).getPtAmount());
//        }
    }
}
