/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report014Detail;
import com.ect.db.report.entity.Report014;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import com.ect.web.utils.StringUtils;
import java.io.InputStream;
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
public class FormReport014Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport014Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report014> reportGennericService;
    /**
     * *
     * For Insert Report014
     */
    private Report014 report014 = new Report014();
    /**
     * *
     * For ListDetail
     */
    private List<Report014Detail> report014Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report014Detail inputReport014Detail = new Report014Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form014!!");

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
        this.report014 = new Report014();
        report014Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_014 + MessageUtils.PRINT_LINE_STAR());

        calSum();

        report014.setReport014DetailList(report014Details);
        report014.setCreatedDate(new Date());
        report014.setCreatedUser(super.getUserAuthen().getUserId());
        report014.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report014.setReportDesc(ectConfManager.getReportName(REPORT_014));
        report014.setReportCode(REPORT_014);
        report014.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report014);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            goToClose();

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report014);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_014 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report014.setReport014DetailList(report014Details);
        report014.setUpdatedDate(new Date());
        report014.setUpdatedUser(super.getUserAuthen().getUserId());
        //report014.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report014.setReportDesc(ectConfManager.getReportName(REPORT_014));
        report014.setReportCode(REPORT_014);
        report014.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report014);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report014);

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

        inputReport014Detail = new Report014Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport014Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report014Details == null || report014Details.isEmpty()) {

            report014Details = new ArrayList<>();
            inputReport014Detail.setKey(1);

        } else {

            inputReport014Detail.setKey(report014Details.get(report014Details.size() - 1).getKey() + 1);

        }

        inputReport014Detail.setReportId(report014);

        report014Details.add(inputReport014Detail);

        JsfUtil.hidePopup("REPORT_014dlgAddReportDetail");
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

        Report014Detail editRow = ((Report014Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report014Details.size(); i++) {

            if (report014Details.get(i).getKey() == editRow.getKey()) {

                report014Details.remove(i);
                report014Details.add(i, editRow);

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

    public void calSum() {

        for (int i = 0; i < report014Details.size(); i++) {
            report014Details.get(i).setAtCenter(NumberUtils.convertNUllToZero(report014Details.get(i).getOnAgenda()) + NumberUtils.convertNUllToZero(report014Details.get(i).getAccessCommittee()) + NumberUtils.convertNUllToZero(report014Details.get(i).getOfferEct() + report014Details.get(i).getAnalystRemain() + report014Details.get(i).getSendRequest()));
            report014Details.get(i).setAllAmount(NumberUtils.convertNUllToZero(report014Details.get(i).getAtCenter()) + NumberUtils.convertNUllToZero(report014Details.get(i).getAtEctProvince()) + NumberUtils.convertNUllToZero(report014Details.get(i).getEctResolve()));
        }
    }

    /**
     * @return the report014Details
     */
    public List<Report014Detail> getReport014Details() {
        return report014Details;
    }

    /**
     * @param report014Details the report014Details to set
     */
    public void setReport014Details(List<Report014Detail> report014Details) {
        this.report014Details = report014Details;
    }

    /**
     * @return the inputReport014Detail
     */
    public Report014Detail getInputReport014Detail() {
        return inputReport014Detail;
    }

    /**
     * @param inputReport014Detail the inputReport014Detail to set
     */
    public void setInputReport014Detail(Report014Detail inputReport014Detail) {
        this.inputReport014Detail = inputReport014Detail;
    }

    /**
     * @return the report014
     */
    public Report014 getReport014() {
        return report014;
    }

    /**
     * @param report014 the report014 to set
     */
    public void setReport014(Report014 report014) {
        this.report014 = report014;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport014Detail);

        String msg = "";

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report014.getReport014DetailList() == null || report014.getReport014DetailList().isEmpty()) {
            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        return true;
    }

    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_014.equalsIgnoreCase(paramReportCode)) {

            report014 = reportService.findByReport014ById(paramReportId);

            logger.trace("report014 : {}", report014);

            /**
             * * Set ReportDetail **
             */
            report014Details = new ArrayList<>();
            report014Details.addAll(report014.getReport014DetailList());

            for (int i = 0; i < report014Details.size(); i++) {

                report014Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report014> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report014> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        
        report014Details = new ArrayList<>();

        Report014Detail report014Detail1 = new Report014Detail();
        report014Detail1.setInstitution("สำนักกิจการพรรคการเมืองและการออกเสียงประชามติ");
//        report014Detail1.setAtCenter(0);
//        report014Detail1.setAtEctProvince(0);
//        report014Detail1.setEctResolve(0);
//        report014Detail1.setAnalystRemain(0);
//        report014Detail1.setOfferEct(0);
//        report014Detail1.setAccessCommittee(0);
//        report014Detail1.setOnAgenda(0);
//        report014Detail1.setSendRequest(0);
        report014Detail1.setReportId(report014);

//        Report014Detail report014Detail2 = new Report014Detail();
//        report014Detail2.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 2");
//        report014Detail2.setAtCenter(0);
//        report014Detail2.setAtEctProvince(0);
//        report014Detail2.setEctResolve(0);
//        report014Detail2.setAnalystRemain(0);
//        report014Detail2.setOfferEct(0);
//        report014Detail2.setAccessCommittee(0);
//        report014Detail2.setOnAgenda(0);
//        report014Detail2.setSendRequest(0);
//        report014Detail2.setReportId(report014);
//
//        Report014Detail report014Detail3 = new Report014Detail();
//        report014Detail3.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 3");
//        report014Detail3.setAtCenter(0);
//        report014Detail3.setAtEctProvince(0);
//        report014Detail3.setEctResolve(0);
//        report014Detail3.setAnalystRemain(0);
//        report014Detail3.setOfferEct(0);
//        report014Detail3.setAccessCommittee(0);
//        report014Detail3.setOnAgenda(0);
//        report014Detail3.setSendRequest(0);
//        report014Detail3.setReportId(report014);
//
//        Report014Detail report014Detail4 = new Report014Detail();
//        report014Detail4.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 4");
//        report014Detail4.setAtCenter(0);
//        report014Detail4.setAtEctProvince(0);
//        report014Detail4.setEctResolve(0);
//        report014Detail4.setAnalystRemain(0);
//        report014Detail4.setOfferEct(0);
//        report014Detail4.setAccessCommittee(0);
//        report014Detail4.setOnAgenda(0);
//        report014Detail4.setSendRequest(0);
//        report014Detail4.setReportId(report014);
//
//        Report014Detail report014Detail5 = new Report014Detail();
//        report014Detail5.setInstitution("สำนักสืบสวนสอบสวนและวิจัย 5");
//        report014Detail5.setAtCenter(0);
//        report014Detail5.setAtEctProvince(0);
//        report014Detail5.setEctResolve(0);
//        report014Detail5.setAnalystRemain(0);
//        report014Detail5.setOfferEct(0);
//        report014Detail5.setAccessCommittee(0);
//        report014Detail5.setOnAgenda(0);
//        report014Detail5.setSendRequest(0);
//        report014Detail5.setReportId(report014);

        report014Details.add(report014Detail1);
        
        report014.setReport014DetailList(report014Details);
        report014.setReportMonth(reportMonth);
        report014.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {

        Report014Detail rowDelete = (Report014Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report014Details.remove(rowDelete);

    }
}
