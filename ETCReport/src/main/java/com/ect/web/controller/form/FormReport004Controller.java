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
    private String paramReportCode;
    private Integer paramReportId;
    private String paramMode;
    private String reportTitle;

    @PostConstruct
    public void init() {

        initParam();
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

        initForm();

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

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_004");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

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

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

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
     * @return the curYear
     */
    public String getCurYear() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date()).toString();

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

    public void goToEdit() {
        String url = "?mode=" + REPORT_MODE_EDIT + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);
    }

    public void goToClose() {
        JsfUtil.hidePopupIframe("dialogEdit");
    }

    public void goToCancel() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_004 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report004> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report004> reportGennericService) {
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

        report004Details = new ArrayList<>();

        Report004Detail report004Detail1 = new Report004Detail();
        report004Detail1.setDlaType("กรุงเทพมหานคร");
        report004Detail1.setReportId(report004);
        
        Report004Detail report004Detail2 = new Report004Detail();
        report004Detail2.setDlaType("องค์การบริหารส่วนตำบล");
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
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
