/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.common.dao.hibernate.EctConfManager;
import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.EctFlowStatus.ReportStatus;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report001Detail;
import com.ect.db.report.entity.ReportName.ReportCode;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.Report001Service;
import com.ect.web.service.ReportGennericService;
import com.ect.web.service.ReportService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
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
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author Jirawat.l
 */
@ViewScoped
@ManagedBean(name = "FormReportMainController")
public class FormReportMainController extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReportMainController.class);
    private static final long serialVersionUID = 7863151922951862688L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report001> reportGennericService;
    @ManagedProperty(value = "#{report001Service}")
    private Report001Service report001Service;
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
     * For Query each flow status
     */
    private List<ViewReportStatus> listReportStatusStep1;
    private List<ViewReportStatus> listReportStatusStep2;
    private List<ViewReportStatus> listReportStatusStep3;
    private List<ViewReportStatus> listReportStatusReject;
    private List<ViewReportStatus> listReportStatusApprove;
    private String remark;
    private boolean validRemark = true;
    private ViewReportStatus selectReject;
    private Integer flowStatusUpdate;

    @PostConstruct
    public void init() {

    }

    @Override
    public void resetForm() {
    }

    /**
     * *
     * Save and close popup
     */
    @Override
    public void save() {

        report001.setReport001DetailList(report001Details);
        report001.setCreatedDate(new Date());
        report001.setCreatedUser(getUserAuthen().getUserId());
        report001.setFlowStatusId(FlowStatus.STEP_1.getStatus());
        report001.setReportDesc(ectConfManager.getReportName(ReportCode.REPORT_001.getCode()));
        report001.setReportCode(ReportCode.REPORT_001.getCode());

        logger.debug("Save... {} ", report001);

        try {

            reportGennericService.create(report001);

            logger.debug("Save Success !! ");

            JsfUtil.alertJavaScript("บันทึกข้อมูล!!");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage("ไม่สามารถบันทึกข้อมูล!!");

            logger.error("Cannot Save Data : ", ex);

        }
    }

    /**
     * *
     * init before loadPopup
     */
    @Override
    public void initReportDetail() {

        logger.debug("initReportDetail...");

        inputReport001Detail = new Report001Detail();
    }

    /**
     * *
     * Load Report Step1
     */
    public void loadReportSTEP1State() {

        logger.trace("loadReportSTEP1State!!");

        listReportStatusStep1 = (List<ViewReportStatus>) reportService.findReportByStatus(FlowStatus.STEP_1.getStatus());

        if (listReportStatusStep1 == null || listReportStatusStep1.isEmpty()) {

            logger.debug("listReportStatusWait is null!!");
            return;

        }

        logger.debug("listReportStatusWait : {}", new Gson().toJson(listReportStatusStep1));

    }

    /**
     * *
     * Load Report Step2
     */
    public void loadReportSTEP2State() {

        logger.trace("loadReportSTEP2State!!");

        listReportStatusStep2 = (List<ViewReportStatus>) reportService.findReportByStatus(FlowStatus.STEP_2.getStatus());

        if (listReportStatusStep2 == null || listReportStatusStep2.isEmpty()) {

            logger.debug("listReportStatusStep2 is null!!");
            return;

        }

        logger.debug("listReportStatusStep2 : {}", new Gson().toJson(listReportStatusStep2));

    }

    /**
     * *
     * Load Report Step3
     */
    public void loadReportSTEP3State() {

        logger.trace("loadReportSTEP3State!!");

        listReportStatusStep3 = (List<ViewReportStatus>) reportService.findReportByStatus(FlowStatus.STEP_3.getStatus());

        if (listReportStatusStep3 == null || listReportStatusStep3.isEmpty()) {

            logger.debug("listReportStatusStep3 is null!!");
            return;

        }

        logger.debug("listReportStatusStep3 : {}", new Gson().toJson(listReportStatusStep3));

    }

    /**
     * *
     * Load Report Approve
     */
    public void loadReportApproveState() {

        logger.trace("loadReportApproveState!!");

        listReportStatusApprove = (List<ViewReportStatus>) reportService.findReportByStatus(FlowStatus.APPROVED.getStatus());

        if (listReportStatusApprove == null || listReportStatusApprove.isEmpty()) {

            logger.debug("loadReportApproveState is null!!");
            return;

        }

        logger.debug("loadReportApproveState : {}", new Gson().toJson(listReportStatusApprove));

    }

    /**
     * *
     * Load Report Reject
     */
    public void loadReportDrafState() {

        logger.trace("loadReportDrafState!!");

        listReportStatusReject = (List<ViewReportStatus>) reportService.findReportByStatus(FlowStatus.DRAFF.getStatus());

        if (listReportStatusReject == null || listReportStatusReject.isEmpty()) {

            logger.debug("loadReportDrafState is null!!");
            return;

        }

        logger.debug("loadReportRejectState : {}", new Gson().toJson(listReportStatusReject));

    }

    /**
     * *
     * AddReportDetail to Grid
     */
    public void addReportDetail() throws Exception {

        logger.debug("addReportDetail... {}", inputReport001Detail);

        if (report001Details == null || report001Details.isEmpty()) {

            report001Details = new ArrayList<>();
            inputReport001Detail.setKey(1);

        } else {

            inputReport001Detail.setKey(report001Details.get(report001Details.size() - 1).getKey() + 1);

        }

        inputReport001Detail.setReportId(report001);

        report001Details.add(inputReport001Detail);

        JsfUtil.hidePopup("dlgAddReportDetail");
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
     * Tab change for Query ReportStatus
     *
     * @param event
     */
    public void onTabChange(TabChangeEvent event) {

        logger.trace("Active Tab: " + event.getTab().getId());

        String tabName = event.getTab().getId().replaceFirst("tab_", "");

        if (tabName.equalsIgnoreCase(FlowStatus.STEP_1.getName())) {

            loadReportSTEP1State();

        } else if (tabName.equalsIgnoreCase(FlowStatus.STEP_2.getName())) {

            loadReportSTEP2State();

        } else if (tabName.equalsIgnoreCase(FlowStatus.STEP_3.getName())) {

            loadReportSTEP3State();

        } else if (tabName.equalsIgnoreCase(FlowStatus.APPROVED.getName())) {

            loadReportApproveState();

        } else if (tabName.equalsIgnoreCase(FlowStatus.REJECT.getName())) {

            loadReportDrafState();

        }

    }

    /**
     * *
     * Row Edit
     *
     * @param event
     */
    public void onEdit(RowEditEvent event) {

        Report001Detail editRow = ((Report001Detail) event.getObject());

        logger.debug("Edit Row : {}", editRow);

        for (int i = 0; i < report001Details.size(); i++) {

            if (report001Details.get(i).getKey() == editRow.getKey()) {

                report001Details.remove(i);
                report001Details.add(i, editRow);

                logger.debug("After Edit Row : {}", editRow);
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
    public void onCancel(RowEditEvent event) {

        JsfUtil.addSuccessMessage("ยกเลิก!!");

    }

    public void approve(String reportName, Integer reportId, Integer flowStatusId) {

        logger.trace("Approve reportName {}", reportName);
        logger.trace("Approve reportId {}  by status {}", reportId + "", flowStatusId + "");

        try {

            reportService.updateReportStatusApprove(reportName, reportId, flowStatusId, getUserAuthen().getUserId());

            if (FlowStatus.APPROVED.getStatus() == flowStatusId) {

                addInfo("อนุมัติรายงาน");
                
                loadReportSTEP3State();
                
            } else {

                addInfo("ส่งพิจรณาแล้ว");
                
                loadReportDrafState();
                loadReportSTEP1State();
                loadReportSTEP2State();

            }

        } catch (Exception ex) {

            addError("เกิดข้อผิดพลาด เลขที่ : " + MDC.get("reqId"));
            logger.error("Cannot updateReportFlowStatus ", ex);

        }
    }

    public void initReject(ViewReportStatus selectReject, Integer flowStatusUpdate) {

        logger.trace("InitReject select {} flowStatusUpdate {}", selectReject, flowStatusUpdate);

        this.selectReject = selectReject;
        this.flowStatusUpdate = flowStatusUpdate;
        
        this.remark = "";

    }

    public void reject(ActionEvent actionEvent) {

        logger.trace("Reject reportName {}", selectReject.getReportCode());
        logger.trace("Reject reportId {}  by status {}", selectReject.getReportId() + "", flowStatusUpdate + "");
        logger.trace("Remark : {}", remark);

        if (StringUtils.isBlank(remark)) {

            addError(MessageUtils.getResourceBundleString("require_message", "เหตุผล"));
            
            return;

        }

        try {

            reportService.updateReportStatusReject(selectReject.getReportCode(), selectReject.getReportId(), flowStatusUpdate, getUserAuthen().getUserId(), remark);

            addInfo("ส่งกลับสำเร็จ");

            /***
             * Load Tab after update flow status
             */
            loadReportSTEP1State();
            loadReportSTEP2State();
            loadReportSTEP3State();
            
            closeDialog("dialogRemark");

        } catch (Exception ex) {

            logger.error("Cannot updateReportFlowStatus ", ex);

        } finally {

            logger.trace("Clear Remark");
            remark = "";
            validRemark = true;

        }
    }
    
    public String reportStatusName(Integer reportStatus){
        
        if(reportStatus==null){
            return "";
        }
        
        if(reportStatus==ReportStatus.APPROVE.getStatus()){
        
            return "อนุมัต";
            
        }else if(reportStatus == ReportStatus.REJECTE.getStatus()){
        
            return "ไม่ผ่านการอนุมัติ";
        
        }else if(reportStatus == ReportStatus.WAIT.getStatus()){
        
            return "รอส่งพิจรณา";
            
        }else{
        
            return "N/A";
        
        }
        
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
     * @return the report001Service
     */
    public Report001Service getReport001Service() {
        return report001Service;
    }

    /**
     * @param report001Service the report001Service to set
     */
    public void setReport001Service(Report001Service report001Service) {
        this.report001Service = report001Service;
    }

    /**
     * @return the listReportStatusReject
     */
    public List<ViewReportStatus> getListReportStatusReject() {
        return listReportStatusReject;
    }

    /**
     * @param listReportStatusReject the listReportStatusReject to set
     */
    public void setListReportStatusReject(List<ViewReportStatus> listReportStatusReject) {
        this.listReportStatusReject = listReportStatusReject;
    }

    /**
     * @return the listReportStatusApprove
     */
    public List<ViewReportStatus> getListReportStatusApprove() {
        return listReportStatusApprove;
    }

    /**
     * @param listReportStatusApprove the listReportStatusApprove to set
     */
    public void setListReportStatusApprove(List<ViewReportStatus> listReportStatusApprove) {
        this.listReportStatusApprove = listReportStatusApprove;
    }

    /**
     * @return the listReportStatusStep1
     */
    public List<ViewReportStatus> getListReportStatusStep1() {
        return listReportStatusStep1;
    }

    /**
     * @param listReportStatusStep1 the listReportStatusStep1 to set
     */
    public void setListReportStatusStep1(List<ViewReportStatus> listReportStatusStep1) {
        this.listReportStatusStep1 = listReportStatusStep1;
    }
    
    /**
     * @return the listReportStatusStep2
     */
    public List<ViewReportStatus> getListReportStatusStep2() {
        return listReportStatusStep2;
    }

    /**
     * @param listReportStatusStep2 the listReportStatusStep2 to set
     */
    public void setListReportStatusStep2(List<ViewReportStatus> listReportStatusStep2) {
        this.listReportStatusStep2 = listReportStatusStep2;
    }

    /**
     * @return the listReportStatusStep3
     */
    public List<ViewReportStatus> getListReportStatusStep3() {
        return listReportStatusStep3;
    }

    /**
     * @param listReportStatusStep3 the listReportStatusStep3 to set
     */
    public void setListReportStatusStep3(List<ViewReportStatus> listReportStatusStep3) {
        this.listReportStatusStep3 = listReportStatusStep3;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the validRemark
     */
    public boolean isValidRemark() {
        return validRemark;
    }

    /**
     * @param validRemark the validRemark to set
     */
    public void setValidRemark(boolean validRemark) {
        this.validRemark = validRemark;
    }

    /**
     * @return the flowStatusUpdate
     */
    public Integer getFlowStatusUpdate() {
        return flowStatusUpdate;
    }

    /**
     * @param flowStatusUpdate the flowStatusUpdate to set
     */
    public void setFlowStatusUpdate(Integer flowStatusUpdate) {
        this.flowStatusUpdate = flowStatusUpdate;
    }

    /**
     * @return the selectReject
     */
    public ViewReportStatus getSelectReject() {
        return selectReject;
    }

    /**
     * @param selectReject the selectReject to set
     */
    public void setSelectReject(ViewReportStatus selectReject) {
        this.selectReject = selectReject;
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addReportDetail(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
