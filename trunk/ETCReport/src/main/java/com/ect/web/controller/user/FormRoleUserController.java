/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.user;

import com.ect.db.entity.Report001;
import com.ect.db.entity.Report001Detail;
import com.ect.web.controller.BaseController;
import com.ect.web.controller.model.ReportVO;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.ECTUtils;
import com.ect.web.utils.JsfUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author Jirawat.l
 */
@ViewScoped
@ManagedBean(name = "roleUser")
public class FormRoleUserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FormRoleUserController.class);
    private static final long serialVersionUID = 7863151922951862688L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
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
     * Dummy Data Object
     */
    private List<ReportVO> rptPreSendList;
    private List<ReportVO> rptWaitStatusList;
    private List<ReportVO> rptCompleteList;
    private List<ReportVO> rptList;

    @PostConstruct
    public void init() {

        MDC.put("reqId", ECTUtils.generateToken());

    }

    @Override
    public void resetForm() {
    }

    public void save() {

        report001.setReport001DetailList(report001Details);

        logger.debug("Save... {} ", report001);

        try {

            reportGennericService.create(report001);

            logger.debug("Save Success !! ");

            JsfUtil.addSuccessMessage("บันทึกข้อมูล!!");

        } catch (Exception ex) {

            JsfUtil.addErrorMessage("ไม่สามารถบันทึกข้อมูล!!");

            logger.error("Cannot Save Data : ", ex);

        }
    }

    /**
     * *
     * init before loadPopup
     */
    public void initReportDetail() {

        logger.debug("initReportDetail...");

        inputReport001Detail = new Report001Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    public void addReportDetail(ActionEvent actionEvent) throws Exception {

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

    public List<ReportVO> getRptPreSendList() {
        return rptPreSendList;
    }

    public void setRptPreSendList(List<ReportVO> rptPreSendList) {
        this.rptPreSendList = rptPreSendList;
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
     * @return the dropdownFactory
     */
    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    /**
     * @param dropdownFactory the dropdownFactory to set
     */
    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
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
}
