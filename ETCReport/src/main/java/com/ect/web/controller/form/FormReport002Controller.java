/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.Report001;
import com.ect.db.entity.Report001Detail;
import com.ect.db.report.entity.ViewReport001;
import com.ect.web.controller.model.ReportVO;
import com.ect.web.service.Report001Service;
import com.ect.web.utils.ECTUtils;
import com.ect.web.utils.JsfUtil;
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
import org.slf4j.MDC;

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
     * Dummy Data Object
     */
    private List<ReportVO> rptWaitStatusList;
    private List<ReportVO> rptCompleteList;
    private List<ReportVO> rptList;

    @PostConstruct
    public void init() {

        MDC.put("reqId", ECTUtils.generateToken());

        /**
         * *
         * Set Dummy Login
         */
        logger.debug("UserAuthen : {}", super.getUserAuthen());

        loadReportAllState();

    }

    @Override
    public void resetForm() {
    }

    @Override
    public void save() {

        report001.setReport001DetailList(report001Details);
        report001.setCreatedDate(new Date());
        report001.setCreatedUser(super.getUserAuthen().getUserId());
        report001.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report001.setReportDesc(ectConfManager.getReportName("REPORT_001"));


        logger.debug("Save... {} ", report001);

        try {

            getReportGennericService().create(report001);

            logger.debug("Save Success !! ");

            JsfUtil.alertJavaScript("บันทึกข้อมูล!!");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage("ไม่สามารถบันทึกข้อมูล!!");

            logger.error("Cannot Save Data : ", ex);

        }
    }

    public String statusConvertor(Integer status) {

        if (status == null) {
            return "รอดำเนินการ";
        }
        if (status == 1) {
            return "รอดำเนินการ";
        }
        if (status == 2) {
            return "อนุมัติ";
        }
        if (status == 3) {
            return "ยกเลิก";
        }

        return "ไม่ระบุ";
    }
    private List<ViewReport001> listReportStatusWait = new ArrayList<ViewReport001>();
    private List<Report001> listReportStatusReject = new ArrayList<Report001>();
    private List<Report001> listReportStatusApprove = new ArrayList<Report001>();

    public void loadReportAllState() {

        listReportStatusWait = (List<ViewReport001>) report001Service.findByStatus(1);

        if (listReportStatusWait == null || listReportStatusWait.isEmpty()) {

            logger.debug("listReportStatusWait is null!!");
            return;

        }

        logger.debug("listReportStatusWait : {}", new Gson().toJson(listReportStatusWait));

    }

    /**
     * *
     * init before loadPopup
     */
    @Override
    public void initReportDetail() {

        logger.debug("initReportDetail...");

        clearAllMessage();
        
        inputReport001Detail = new Report001Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.debug("addReportDetail... {}", inputReport001Detail);

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
    @Override
    public void onCancel(RowEditEvent event) {

        JsfUtil.addSuccessMessage("ยกเลิก!!");

    }

    public List<ReportVO> getRptWaitStatusList() {
        return rptWaitStatusList;
    }

    public void setRptWaitStatusList(List<ReportVO> rptWaitStatusList) {
        this.rptWaitStatusList = rptWaitStatusList;
    }

    public List<ReportVO> getRptCompleteList() {
        return rptCompleteList;
    }

    public void setRptCompleteList(List<ReportVO> rptCompleteList) {
        this.rptCompleteList = rptCompleteList;
    }

    public List<ReportVO> getRptList() {
        return rptList;
    }

    public void setRptList(List<ReportVO> rptList) {
        this.rptList = rptList;
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

        logger.trace("validateReportDetail : {}",inputReport001Detail);
        
        String msg = "";

        if (StringUtils.isBlank(inputReport001Detail.getDepName())) {
            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
        }
//        if (StringUtils.isBlank(inputReport001Detail.getActivityName())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
        if (inputReport001Detail.getActivityType()== null) {
            msg += "กรุณาระบุผลการดำเนินงาน<br/>";
        }
        if (inputReport001Detail.getActivityAmount() == null || inputReport001Detail.getActivityAmount().intValue()==0) {
            msg += "กรุณาระบุเป้าหมาย<br/>";
        }
        if (inputReport001Detail.getBudgetSet() == null || inputReport001Detail.getBudgetSet().intValue()==0) {
            msg += "กรุณาระบุงบประมาณตั้งไว้<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }
}
