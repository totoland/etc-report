/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.Report001;
import com.ect.db.entity.Report001Detail;
import com.ect.db.report.entity.ViewReport001;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_001;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_CREATE;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_EDIT;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import com.google.gson.Gson;
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
 * @author Totoland
 */
@ViewScoped
@ManagedBean
public class FormReport001Controller extends BaseFormReportController {

    private static final Logger logger = LoggerFactory.getLogger(FormReport001Controller.class);
    private static final long serialVersionUID = 7863151922951862688L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report001> reportGennericService;
    /**
     * *
     * For Insert Report001
     */
    private Report001 report001 = new Report001();
    /**
     * *
     * For ListDetail
     */
    private List<Report001Detail> report001Details;
    /**
     * *
     * For Add Record
     */
    private Report001Detail inputReport001Detail = new Report001Detail();
    /**
     * *
     * For display in grid by status
     */
    private List<ViewReport001> listReportStatusWait = new ArrayList<>();
    private List<Report001> listReportStatusReject = new ArrayList<>();
    private List<Report001> listReportStatusApprove = new ArrayList<>();
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

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_001 + MessageUtils.PRINT_LINE_STAR());

        report001.setReport001DetailList(report001Details);
        report001.setCreatedDate(new Date());
        report001.setCreatedUser(super.getUserAuthen().getUserId());
        report001.setFlowStatusId(FlowStatus.STEP_1.getStatus());
        report001.setReportDesc(ectConfManager.getReportName(REPORT_001));
        report001.setReportCode(REPORT_001);
        report001.setCreatedUserGroup(getUserAuthen().getUserGroupId());
        
        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().create(report001);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report001);

        }
    }
    
    @Override
    public void edit(){
    
        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_001 + MessageUtils.PRINT_LINE_STAR());

        report001.setReport001DetailList(report001Details);
        report001.setUpdatedDate(new Date());
        report001.setUpdatedUser(super.getUserAuthen().getUserId());
        report001.setFlowStatusId(FlowStatus.STEP_1.getStatus());
        report001.setReportDesc(ectConfManager.getReportName(REPORT_001));
        report001.setReportCode(REPORT_001);
        report001.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report001);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report001);

        }
        
    }

    public void loadReportAllState() {

        listReportStatusWait = (List<ViewReport001>) getReportService().findByStatus(1);

        if (listReportStatusWait == null || listReportStatusWait.isEmpty()) {

            logger.trace("listReportStatusWait is null!!");
            return;

        }

        logger.trace("listReportStatusWait : {}", new Gson().toJson(listReportStatusWait));

    }

    /**
     * *
     * init before loadPopup
     */
    @Override
    public void initReportDetail() {

        logger.trace("initReportDetail...");

        clearAllMessage();

        inputReport001Detail = new Report001Detail();
        inputReport001Detail.setIsPass(Boolean.FALSE);
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport001Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report001Details == null || report001Details.isEmpty()) {

            report001Details = new ArrayList<>();
            inputReport001Detail.setKey(1);

        } else {

            inputReport001Detail.setKey(report001Details.get(report001Details.size() - 1).getKey() + 1);

        }

        inputReport001Detail.setReportId(report001);

        report001Details.add(inputReport001Detail);

        JsfUtil.hidePopup("REPORT_001dlgAddReportDetail");
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

        Report001Detail editRow = ((Report001Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report001Details.size(); i++) {

            if (report001Details.get(i).getKey() == editRow.getKey()) {

                report001Details.remove(i);
                report001Details.add(i, editRow);

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
     * @return the report001Details
     */
    public List<Report001Detail> getReport001Details() {
        return report001Details;
    }

    /**
     * @param report001Details the report001Details to set
     */
    public void setReport001Details(List<Report001Detail> report001Details) {
        this.report001Details = report001Details;
    }

    /**
     * @return the inputReport001Detail
     */
    public Report001Detail getInputReport001Detail() {
        return inputReport001Detail;
    }

    /**
     * @param inputReport001Detail the inputReport001Detail to set
     */
    public void setInputReport001Detail(Report001Detail inputReport001Detail) {
        this.inputReport001Detail = inputReport001Detail;
    }

    /**
     * @return the report001
     */
    public Report001 getReport001() {
        return report001;
    }

    /**
     * @param report001 the report001 to set
     */
    public void setReport001(Report001 report001) {
        this.report001 = report001;
    }

    /**
     * @return the listReportStatusWait
     */
    public List<ViewReport001> getListReportStatusWait() {
        return listReportStatusWait;
    }

    /**
     * @param listReportStatusWait the listReportStatusWait to set
     */
    public void setListReportStatusWait(List<ViewReport001> listReportStatusWait) {
        this.listReportStatusWait = listReportStatusWait;
    }

    /**
     * @return the listReportStatusReject
     */
    public List<Report001> getListReportStatusReject() {
        return listReportStatusReject;
    }

    /**
     * @param listReportStatusReject the listReportStatusReject to set
     */
    public void setListReportStatusReject(List<Report001> listReportStatusReject) {
        this.listReportStatusReject = listReportStatusReject;
    }

    /**
     * @return the listReportStatusApprove
     */
    public List<Report001> getListReportStatusApprove() {
        return listReportStatusApprove;
    }

    /**
     * @param listReportStatusApprove the listReportStatusApprove to set
     */
    public void setListReportStatusApprove(List<Report001> listReportStatusApprove) {
        this.listReportStatusApprove = listReportStatusApprove;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport001Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport001Detail.getWorkDetail())) {
            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
        }
        if (StringUtils.isBlank(inputReport001Detail.getGoalType())) {
            msg += "กรุณาระบุเป้าหมายประเภท<br/>";
        }
        if (inputReport001Detail.getGoalAmount().intValue() == 0) {
            msg += "กรุณาระบุจำนวน<br/>";
        }
        if (StringUtils.isBlank(inputReport001Detail.getResultType())) {
            msg += "กรุณาระบุผลการปฏิบัติงานประเภท<br/>";
        }
        if (inputReport001Detail.getBudgetSet().intValue() == 0) {
            msg += "กรุณาระบุงบประมาณตั้งไว้<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report001.getStrategicId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
        }
        if (report001.getSubStrategicId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
        }
        if (report001.getPlanId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
        }
        if (report001.getProjectId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_PROJECT()) + ("\\n");
        }
        if (report001.getActivityId().intValue() == -1) {
            msg += (MessageUtils.REQUIRE_SELECT_ACTIVITY()) + ("\\n");
        }
        if (report001.getReport001DetailList() == null || report001.getReport001DetailList().isEmpty()) {
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

        if (REPORT_001.equalsIgnoreCase(paramReportCode)) {

            report001 = reportService.findByReport001ById(paramReportId);
            
            logger.trace("report001 : {}", report001);
            
            /*** Set ReportDetail ***/
            report001Details = new ArrayList<>();
            report001Details.addAll(report001.getReport001DetailList());
            
            for(int i=0;i<report001Details.size();i++){
                
                report001Details.get(i).setKey(i);
                
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
        
        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_001 + MessageUtils.PRINT_LINE_STAR());
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
    public ReportGennericService<Report001> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report001> reportGennericService) {
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
