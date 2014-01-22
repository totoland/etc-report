/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.Report002Detail;
import com.ect.db.entity.Report002;
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
public class FormReport002Controller extends BaseFormReportController{
    private static Logger logger = LoggerFactory.getLogger(FormReport002Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;

    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report002> reportGennericService;
    /**
     * *
     * For Insert Report002
     */
    private Report002 report002 = new Report002();
    /**
     * *
     * For ListDetail
     */
    private List<Report002Detail> report002Details;
    /**
     * *
     * For Add Record
     */
    private Report002Detail inputReport002Detail = new Report002Detail();
    
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
        
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_002 + MessageUtils.PRINT_LINE_STAR());

        report002.setReport002DetailList(report002Details);
        report002.setCreatedDate(new Date());
        report002.setCreatedUser(super.getUserAuthen().getUserId());
        report002.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report002.setReportDesc(ectConfManager.getReportName(REPORT_002));
        report002.setReportCode(REPORT_002);
        report002.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report002);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_002");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report002);

        }
    }
    
    @Override
    public void edit(){
    
        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_002 + MessageUtils.PRINT_LINE_STAR());

        report002.setReport002DetailList(report002Details);
        report002.setUpdatedDate(new Date());
        report002.setUpdatedUser(super.getUserAuthen().getUserId());
        report002.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report002.setReportDesc(ectConfManager.getReportName(REPORT_002));
        report002.setReportCode(REPORT_002);
        report002.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report002);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report002);

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

        inputReport002Detail = new Report002Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport002Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report002Details == null || report002Details.isEmpty()) {

            report002Details = new ArrayList<>();
            inputReport002Detail.setKey(1);

        } else {

            inputReport002Detail.setKey(report002Details.get(report002Details.size() - 1).getKey() + 1);

        }

        inputReport002Detail.setReportId(report002);

        report002Details.add(inputReport002Detail);

        JsfUtil.hidePopup("REPORT_002dlgAddReportDetail");
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

        Report002Detail editRow = ((Report002Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report002Details.size(); i++) {

            if (report002Details.get(i).getKey() == editRow.getKey()) {

                report002Details.remove(i);
                report002Details.add(i, editRow);

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
     * @return the report002Details
     */
    public List<Report002Detail> getReport002Details() {
        return report002Details;
    }

    /**
     * @param report002Details the report002Details to set
     */
    public void setReport002Details(List<Report002Detail> report002Details) {
        this.report002Details = report002Details;
    }

    /**
     * @return the inputReport002Detail
     */
    public Report002Detail getInputReport002Detail() {
        return inputReport002Detail;
    }

    /**
     * @param inputReport002Detail the inputReport002Detail to set
     */
    public void setInputReport002Detail(Report002Detail inputReport002Detail) {
        this.inputReport002Detail = inputReport002Detail;
    }

    /**
     * @return the report002
     */
    public Report002 getReport002() {
        return report002;
    }

    /**
     * @param report002 the report002 to set
     */
    public void setReport002(Report002 report002) {
        this.report002 = report002;
    }
    
    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport002Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport002Detail.getWorkDetail())) {
            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
        }
        if (StringUtils.isBlank(inputReport002Detail.getObjective())) {
            msg += "กรุณาระบุวัตถุประสงค์<br/>";
        }
        if (inputReport002Detail.getGoalAmount().intValue() == 0) {
            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
        }
        if (StringUtils.isBlank(inputReport002Detail.getResult())) {
            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
        }
        if (inputReport002Detail.getBudget().intValue() == 0) {
            msg += "กรุณาระบุงบประมาณbr/>";
        }
        if (StringUtils.isBlank(inputReport002Detail.getBuggetSource())) {
            msg += "กรุณาระบุที่มาของงบประมาณbr/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report002.getStrategicId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
        }
        if (report002.getSubStrategicId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
        }
        if (report002.getPlanId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
        }
        if (report002.getReport002DetailList() == null || report002.getReport002DetailList().isEmpty()) {
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

        if (REPORT_002.equalsIgnoreCase(paramReportCode)) {

            report002 = reportService.findByReport002ById(paramReportId);
            
            logger.trace("report002 : {}", report002);
            
            /*** Set ReportDetail ***/
            report002Details = new ArrayList<>();
            report002Details.addAll(report002.getReport002DetailList());
            
            for(int i=0;i<report002Details.size();i++){
                
                report002Details.get(i).setKey(i);
                
            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }
    
    public void goToEdit(){
        String url =  "?mode="+REPORT_MODE_EDIT+"&reportId="+paramReportId+"&reportCode="+paramReportCode;
        redirectPage(url);
    }
    
    public void goToClose(){
        JsfUtil.hidePopupIframe("dialogEdit");
    }
    
    public void goToCancel(){
        
        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_002 + MessageUtils.PRINT_LINE_STAR());
        String url =  "?mode="+REPORT_MODE_VIEW+"&reportId="+paramReportId+"&reportCode="+paramReportCode;
        redirectPage(url);
        
    }

    private void initParam() {
        paramMode = getParameter("mode");
        paramReportCode = getParameter("reportCode");
        paramReportId = NumberUtils.toInteger(getParameter("reportId"));
        
        logger.trace("paramMode : {}", StringUtils.isBlank(paramMode) ? REPORT_MODE_CREATE : paramMode);
        logger.trace("paramReportCode : {}",paramReportCode);
        logger.trace("paramReportId : {}",paramReportId);
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report002> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report002> reportGennericService) {
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
        
        reportTitle = MessageUtils.getResourceBundleString("report_header_title", DateTimeUtils.getInstance().thDate(curDate,"MMMM"),DateTimeUtils.getInstance().thDate(curDate,"yyyy"),getUserAuthen().getProvinceName());
        
    }
}
