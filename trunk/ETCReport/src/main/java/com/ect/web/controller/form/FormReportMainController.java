/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.EctFlowStatus.ReportStatus;
import com.ect.db.entity.EctGroupLvl;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report001Detail;
import com.ect.db.report.entity.ReportName.ReportCode;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.model.LazyViewReportImpl;
import com.ect.web.service.Report001Service;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
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
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
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
    private LazyDataModel<ViewReportStatus> listReportStatusStep1;
    private LazyDataModel<ViewReportStatus> listReportStatusStep2;
    private LazyDataModel<ViewReportStatus> listReportStatusStep3;
    private LazyDataModel<ViewReportStatus> listReportStatusReject;
    private LazyDataModel<ViewReportStatus> listReportStatusApprove;
    private LazyDataModel<ViewReportStatus> listReportStatusTracking;
    private String remark;
    private boolean validRemark = true;
    private ViewReportStatus selectReject;
    private Integer flowStatusUpdate;

    @PostConstruct
    public void init() {

        notifyFistLogin();

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
     * Load Report Tracking report your self
     */
    public void loadReportTracking() {

        logger.trace("loadReportSTEP1State!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null && count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusTracking = reportModel;

        }

        if (listReportStatusTracking == null) {

            logger.debug("listReportStatusWait is null!!");
            return;

        }

    }

    /**
     * *
     * Load Report Step1
     */
    public void loadReportSTEP1State() {

        logger.trace("loadReportSTEP1State!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        reportCriteria.setFlowStatus(FlowStatus.STEP_1.getStatus() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null && count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusStep1 = reportModel;

        }

        if (listReportStatusStep1 == null) {

            logger.debug("listReportStatusWait is null!!");
            return;

        }

    }

    /**
     * *
     * Load Report Step2
     */
    public void loadReportSTEP2State() {

        logger.trace("loadReportSTEP2State!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setFlowStatus(FlowStatus.STEP_2.getStatus() + "");
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusStep2 = reportModel;

        }

    }

    /**
     * *
     * Load Report Step3
     */
    public void loadReportSTEP3State() {

        logger.trace("loadReportSTEP3State!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setFlowStatus(FlowStatus.STEP_3.getStatus() + "");
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusStep3 = reportModel;

        }

    }

    /**
     * *
     * Load Report Approve
     */
    public void loadReportApproveState() {

        logger.trace("loadReportApproveState!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setFlowStatus(FlowStatus.APPROVED.getStatus() + "");
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusApprove = reportModel;

        }

    }

    /**
     * *
     * Load Report Reject
     */
    public void loadReportDrafState() {

        logger.trace("loadReportDrafState!!");

        ReportCriteria reportCriteria = new ReportCriteria();
        reportCriteria.setFlowStatus(FlowStatus.DRAFF.getStatus() + "");
        reportCriteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            listReportStatusReject = reportModel;

        }

    }

    /**
     * *
     * AddReportDetail to Grid
     * @throws java.lang.Exception
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

        if (tabName.equalsIgnoreCase("TRACKING")) {

            loadReportTracking();

        }

        if (tabName.equalsIgnoreCase(FlowStatus.DRAFF.getName())) {

            loadReportDrafState();

        }

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
    @Override
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
    @Override
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

                addInfo("ส่งพิจารณาแล้ว");

                loadReportDrafState();
                loadReportSTEP1State();
                loadReportSTEP2State();

            }

        } catch (Exception ex) {

            addError("เกิดข้อผิดพลาด เลขที่ : " + MDC.get("reqId"));
            logger.error("Cannot updateReportFlowStatus ", ex);

        }
    }
    
    public void delete(String reportName,Integer reportId) {

        logger.trace("Delete reportName {}", reportName);
        logger.trace("Delete reportId {} ", reportId + "");

        try {

            reportService.deleteReport(reportName, reportId);

            addInfo("ลบรายงาน");

        } catch (Exception ex) {

            addError("เกิดข้อผิดพลาด เลขที่ : " + MDC.get("reqId"));
            logger.error("Cannot deleteReport ", ex);

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

            /**
             * *
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

    public String reportStatusName(Integer reportStatus) {

        if (reportStatus == null) {
            return "";
        }

        if (reportStatus == ReportStatus.APPROVE.getStatus()) {

            return "อนุมัติ";

        } else if (reportStatus == ReportStatus.REJECTE.getStatus()) {

            return "ไม่ผ่านการอนุมัติ";

        } else if (reportStatus == ReportStatus.WAIT.getStatus()) {

            return "รอส่งพิจารณา";

        } else {

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
    public LazyDataModel<ViewReportStatus> getListReportStatusReject() {
        return listReportStatusReject;
    }

    /**
     * @param listReportStatusReject the listReportStatusReject to set
     */
    public void setListReportStatusReject(LazyDataModel<ViewReportStatus> listReportStatusReject) {
        this.listReportStatusReject = listReportStatusReject;
    }

    /**
     * @return the listReportStatusApprove
     */
    public LazyDataModel<ViewReportStatus> getListReportStatusApprove() {
        return listReportStatusApprove;
    }

    /**
     * @param listReportStatusApprove the listReportStatusApprove to set
     */
    public void setListReportStatusApprove(LazyDataModel<ViewReportStatus> listReportStatusApprove) {
        this.listReportStatusApprove = listReportStatusApprove;
    }

    /**
     * @return the listReportStatusStep1
     */
    public LazyDataModel<ViewReportStatus> getListReportStatusStep1() {
        return listReportStatusStep1;
    }

    /**
     * @param listReportStatusStep1 the listReportStatusStep1 to set
     */
    public void setListReportStatusStep1(LazyDataModel<ViewReportStatus> listReportStatusStep1) {
        this.listReportStatusStep1 = listReportStatusStep1;
    }

    /**
     * @return the listReportStatusStep2
     */
    public LazyDataModel<ViewReportStatus> getListReportStatusStep2() {
        return listReportStatusStep2;
    }

    /**
     * @param listReportStatusStep2 the listReportStatusStep2 to set
     */
    public void setListReportStatusStep2(LazyDataModel<ViewReportStatus> listReportStatusStep2) {
        this.listReportStatusStep2 = listReportStatusStep2;
    }

    /**
     * @return the listReportStatusStep3
     */
    public LazyDataModel<ViewReportStatus> getListReportStatusStep3() {
        return listReportStatusStep3;
    }

    /**
     * @param listReportStatusStep3 the listReportStatusStep3 to set
     */
    public void setListReportStatusStep3(LazyDataModel<ViewReportStatus> listReportStatusStep3) {
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

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String notifyTaskMsg;

    private void notifyFistLogin() {

        if(super.getParameter("firstLogin")!=null){
        
            Integer task = getFollowUpNewTask();
            
            notifyTaskMsg = "คุณมีรายงานรอพิจารณา "+task+" รายการ";
            
        }

    }

    public Integer getFollowUpNewTask() {
        ReportCriteria criteria = new ReportCriteria();
        criteria.setUserGroupId(super.getUserAuthen().getUserGroupId() + "");

        if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.OPERATOR.getLevel()) {
            criteria.setFlowStatus(EctFlowStatus.FlowStatus.DRAFF.getStatus() + "");
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.HEAD.getLevel()) {
            criteria.setFlowStatus(EctFlowStatus.FlowStatus.STEP_1.getStatus() + "");
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.LEAD.getLevel()) {
            criteria.setFlowStatus(EctFlowStatus.FlowStatus.STEP_2.getStatus() + "");
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.CENTER.getLevel()) {
            criteria.setFlowStatus(EctFlowStatus.FlowStatus.STEP_3.getStatus() + "");
        }
        return reportService.countByCriteria(criteria);
    }

    /**
     * Select Tab for Render
     */
    public void goToUserTab() {

        if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.OPERATOR.getLevel()) {
            super.selectTab("tab_" + EctFlowStatus.FlowStatus.DRAFF.getName());
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.HEAD.getLevel()) {
            super.selectTab("tab_" + EctFlowStatus.FlowStatus.STEP_1.getName());
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.LEAD.getLevel()) {
            super.selectTab("tab_" + EctFlowStatus.FlowStatus.STEP_2.getName());
        } else if (super.getUserAuthen().getUserGroupLvl().intValue() == EctGroupLvl.GroupLevel.CENTER.getLevel()) {
            super.selectTab("tab_" + EctFlowStatus.FlowStatus.STEP_3.getName());
        }

    }

    /**
     * @return the notifyTaskMsg
     */
    public String getNotifyTaskMsg() {
        return notifyTaskMsg;
    }

    /**
     * @param notifyTaskMsg the notifyTaskMsg to set
     */
    public void setNotifyTaskMsg(String notifyTaskMsg) {
        this.notifyTaskMsg = notifyTaskMsg;
    }

    /**
     * @return the listReportStatusTracking
     */
    public LazyDataModel<ViewReportStatus> getListReportStatusTracking() {
        return listReportStatusTracking;
    }

    /**
     * @param listReportStatusTracking the listReportStatusTracking to set
     */
    public void setListReportStatusTracking(LazyDataModel<ViewReportStatus> listReportStatusTracking) {
        this.listReportStatusTracking = listReportStatusTracking;
    }
}
