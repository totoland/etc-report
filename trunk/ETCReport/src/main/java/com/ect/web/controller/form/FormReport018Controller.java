/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report018Detail;
import com.ect.db.report.entity.Report018;
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
public class FormReport018Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport018Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report018> reportGennericService;
    /**
     * *
     * For Insert Report018
     */
    private Report018 report018 = new Report018();
    /**
     * *
     * For ListDetail
     */
    private List<Report018Detail> report018Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report018Detail inputReport018Detail = new Report018Detail();
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
        this.report018 = new Report018();
        report018Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_018 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report018.setReport018DetailList(report018Details);
        report018.setCreatedDate(new Date());
        report018.setCreatedUser(super.getUserAuthen().getUserId());
        report018.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report018.setReportDesc(ectConfManager.getReportName(REPORT_018));
        report018.setReportCode(REPORT_018);
        report018.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report018);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_018");

            resetForm();
        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report018);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_018 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        //report018.setReport018DetailList(report018Details);
        report018.setUpdatedDate(new Date());
        report018.setUpdatedUser(super.getUserAuthen().getUserId());
        report018.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report018.setReportDesc(ectConfManager.getReportName(REPORT_018));
        report018.setReportCode(REPORT_018);
        report018.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report018);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report018);

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

        inputReport018Detail = new Report018Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport018Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report018Details == null || report018Details.isEmpty()) {

            report018Details = new ArrayList<>();
            inputReport018Detail.setKey(1);

        } else {

            inputReport018Detail.setKey(report018Details.get(report018Details.size() - 1).getKey() + 1);

        }

        inputReport018Detail.setReportId(report018);

        report018Details.add(inputReport018Detail);

        JsfUtil.hidePopup("REPORT_018dlgAddReportDetail");
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

        Report018Detail editRow = ((Report018Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report018Details.size(); i++) {

            if (report018Details.get(i).getKey() == editRow.getKey()) {

                report018Details.remove(i);
                report018Details.add(i, editRow);

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
     * @return the report018Details
     */
    public List<Report018Detail> getReport018Details() {
        return report018Details;
    }

    /**
     * @param report018Details the report018Details to set
     */
    public void setReport018Details(List<Report018Detail> report018Details) {
        this.report018Details = report018Details;
    }

    /**
     * @return the inputReport018Detail
     */
    public Report018Detail getInputReport018Detail() {
        return inputReport018Detail;
    }

    /**
     * @param inputReport018Detail the inputReport018Detail to set
     */
    public void setInputReport018Detail(Report018Detail inputReport018Detail) {
        this.inputReport018Detail = inputReport018Detail;
    }

    /**
     * @return the report018
     */
    public Report018 getReport018() {
        return report018;
    }

    /**
     * @param report018 the report018 to set
     */
    public void setReport018(Report018 report018) {
        this.report018 = report018;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport018Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport018Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport018Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport018Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport018Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport018Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport018Detail.getBuggetSource())) {
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

//        if (report018.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report018.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report018.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report018.getReport018DetailList() == null || report018.getReport018DetailList().isEmpty()) {
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

        if (REPORT_018.equalsIgnoreCase(paramReportCode)) {

            report018 = reportService.findByReport018ById(paramReportId);

            logger.trace("report018 : {}", report018);

            /**
             * * Set ReportDetail **
             */
            report018Details = new ArrayList<>();
            report018Details.addAll(report018.getReport018DetailList());

            for (int i = 0; i < report018Details.size(); i++) {

                report018Details.get(i).setKey(i);

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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_018 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report018> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report018> reportGennericService) {
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

        report018Details = new ArrayList<>();

        Report018Detail report018Detail1 = new Report018Detail();
        report018Detail1.setLaas("กรุงเทพมหานคร");
        report018Detail1.setReportId(report018);
        
        Report018Detail report018Detail2 = new Report018Detail();
        report018Detail2.setLaas("องค์การบริหารส่วนจังหวัด");
        report018Detail2.setReportId(report018);

        Report018Detail report018Detail3 = new Report018Detail();
        report018Detail3.setLaas("เมืองพัทยา");
        report018Detail3.setReportId(report018);
        
        Report018Detail report018Detail4 = new Report018Detail();
        report018Detail4.setLaas("เทศบาลนคร");
        report018Detail4.setReportId(report018);
        
        Report018Detail report018Detail5 = new Report018Detail();
        report018Detail5.setLaas("เทศบาลเมือง");
        report018Detail5.setReportId(report018);
        
        Report018Detail report018Detail6 = new Report018Detail();
        report018Detail6.setLaas("เทศบาลตพบล");
        report018Detail6.setReportId(report018);
        
        Report018Detail report018Detail7 = new Report018Detail();
        report018Detail7.setLaas("องค์การบริหารส่วนตำบล");
        report018Detail7.setReportId(report018);

        report018Details.add(report018Detail1);
        report018Details.add(report018Detail2);
        report018Details.add(report018Detail3);
        report018Details.add(report018Detail4);
        report018Details.add(report018Detail5);
        report018Details.add(report018Detail6);
        report018Details.add(report018Detail7);
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calSum() {

//        for (int i = 0; i < report018Details.size(); i++) {
//            report018Details.get(i).setSumAmount(report018Details.get(i).getStAmount()+report018Details.get(i).getPtAmount());
//        }
    }
}
