/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report012Detail;
import com.ect.db.report.entity.Report012;
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
public class FormReport012Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport012Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report012> reportGennericService;
    /**
     * *
     * For Insert Report012
     */
    private Report012 report012 = new Report012();
    /**
     * *
     * For ListDetail
     */
    private List<Report012Detail> report012Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report012Detail inputReport012Detail = new Report012Detail();
    
    @PostConstruct
    public void init() {

        logger.trace("init Form012!!");

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

        calSum();

    }

    @Override
    public void resetForm() {
        this.report012 = new Report012();
        report012Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_012 + MessageUtils.PRINT_LINE_STAR());

        calSum();

        report012.setReport012DetailList(report012Details);
        report012.setCreatedDate(new Date());
        report012.setCreatedUser(super.getUserAuthen().getUserId());
        report012.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report012.setReportDesc(ectConfManager.getReportName(REPORT_012));
        report012.setReportCode(REPORT_012);
        report012.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report012);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            goToClose();

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report012);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_012 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report012.setReport012DetailList(report012Details);
        report012.setUpdatedDate(new Date());
        report012.setUpdatedUser(super.getUserAuthen().getUserId());
        //report012.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report012.setReportDesc(ectConfManager.getReportName(REPORT_012));
        report012.setReportCode(REPORT_012);
        report012.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report012);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report012);

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

        inputReport012Detail = new Report012Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport012Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report012Details == null || report012Details.isEmpty()) {

            report012Details = new ArrayList<>();
            inputReport012Detail.setKey(1);

        } else {

            inputReport012Detail.setKey(report012Details.get(report012Details.size() - 1).getKey() + 1);

        }

        inputReport012Detail.setReportId(report012);

        report012Details.add(inputReport012Detail);

        JsfUtil.hidePopup("REPORT_012dlgAddReportDetail");
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

        Report012Detail editRow = ((Report012Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report012Details.size(); i++) {

            if (report012Details.get(i).getKey() == editRow.getKey()) {

                report012Details.remove(i);
                report012Details.add(i, editRow);

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

    private Report012Detail sumDetail = new Report012Detail();
    
    public void calSum() {

        sumDetail.setAccessCommittee(0);
        sumDetail.setAllamount(0);
        sumDetail.setAnalystRemain(0);
        sumDetail.setAtCenter(0);
        sumDetail.setAtEctProvince(0);
        sumDetail.setEctResolve(0);
        sumDetail.setOfferEct(0);
        sumDetail.setOnAgenda(0);
        
        for (int i = 0; i < report012Details.size(); i++) {
            report012Details.get(i).setAtCenter(NumberUtils.convertNUllToZero(report012Details.get(i).getOnAgenda()) + NumberUtils.convertNUllToZero(report012Details.get(i).getAccessCommittee()) + NumberUtils.convertNUllToZero(report012Details.get(i).getOfferEct()) + NumberUtils.convertNUllToZero(report012Details.get(i).getAnalystRemain()));
            report012Details.get(i).setAllamount(NumberUtils.convertNUllToZero(report012Details.get(i).getAtCenter()) + NumberUtils.convertNUllToZero(report012Details.get(i).getAtEctProvince()) + NumberUtils.convertNUllToZero(report012Details.get(i).getEctResolve()));
            
            sumDetail.setAccessCommittee(sumDetail.getAccessCommittee()+NumberUtils.convertNUllToZero(report012Details.get(i).getAccessCommittee()));
            sumDetail.setAllamount(sumDetail.getAllamount()+NumberUtils.convertNUllToZero(report012Details.get(i).getAllamount()));
            sumDetail.setAnalystRemain(sumDetail.getAnalystRemain()+NumberUtils.convertNUllToZero(report012Details.get(i).getAnalystRemain()));
            sumDetail.setAtCenter(sumDetail.getAtCenter()+NumberUtils.convertNUllToZero(report012Details.get(i).getAtCenter()));
            sumDetail.setAtEctProvince(sumDetail.getAtEctProvince()+NumberUtils.convertNUllToZero(report012Details.get(i).getAtEctProvince()));
            sumDetail.setEctResolve(sumDetail.getEctResolve()+NumberUtils.convertNUllToZero(report012Details.get(i).getEctResolve()));
            sumDetail.setOfferEct(sumDetail.getOfferEct()+NumberUtils.convertNUllToZero(report012Details.get(i).getOfferEct()));
            sumDetail.setOnAgenda(sumDetail.getOnAgenda()+NumberUtils.convertNUllToZero(report012Details.get(i).getOnAgenda()));
        }
    }

    public Report012Detail getSumDetail() {
        return sumDetail;
    }

    public void setSumDetail(Report012Detail sumDetail) {
        this.sumDetail = sumDetail;
    }
    
    /**
     * @return the report012Details
     */
    public List<Report012Detail> getReport012Details() {
        return report012Details;
    }

    /**
     * @param report012Details the report012Details to set
     */
    public void setReport012Details(List<Report012Detail> report012Details) {
        this.report012Details = report012Details;
    }

    /**
     * @return the inputReport012Detail
     */
    public Report012Detail getInputReport012Detail() {
        return inputReport012Detail;
    }

    /**
     * @param inputReport012Detail the inputReport012Detail to set
     */
    public void setInputReport012Detail(Report012Detail inputReport012Detail) {
        this.inputReport012Detail = inputReport012Detail;
    }

    /**
     * @return the report012
     */
    public Report012 getReport012() {
        return report012;
    }

    /**
     * @param report012 the report012 to set
     */
    public void setReport012(Report012 report012) {
        this.report012 = report012;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport012Detail);

        String msg = "";

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report012.getReport012DetailList() == null || report012.getReport012DetailList().isEmpty()) {
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

        if (REPORT_012.equalsIgnoreCase(paramReportCode)) {

            report012 = reportService.findByReport012ById(paramReportId);

            logger.trace("report012 : {}", report012);

            /**
             * * Set ReportDetail **
             */
            report012Details = new ArrayList<>();
            report012Details.addAll(report012.getReport012DetailList());

            for (int i = 0; i < report012Details.size(); i++) {

                report012Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }
    
    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report012> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report012> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }
    
    private void initForm() {

        initTitle();
        
        report012Details = new ArrayList<>();

        Report012Detail report012Detail1 = new Report012Detail();
//        report012Detail1.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 1");
//        report012Detail1.setAtCenter(0);
//        report012Detail1.setAtEctProvince(0);
//        report012Detail1.setEctResolve(0);
//        report012Detail1.setAnalystRemain(0);
//        report012Detail1.setOfferEct(0);
//        report012Detail1.setAccessCommittee(0);
//        report012Detail1.setOnAgenda(0);
        report012Detail1.setReportId(report012);

        Report012Detail report012Detail2 = new Report012Detail();
//        report012Detail2.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 2");
//        report012Detail2.setAtCenter(0);
//        report012Detail2.setAtEctProvince(0);
//        report012Detail2.setEctResolve(0);
//        report012Detail2.setAnalystRemain(0);
//        report012Detail2.setOfferEct(0);
//        report012Detail2.setAccessCommittee(0);
//        report012Detail2.setOnAgenda(0);
        report012Detail2.setReportId(report012);

        Report012Detail report012Detail3 = new Report012Detail();
//        report012Detail3.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 3");
//        report012Detail3.setAtCenter(0);
//        report012Detail3.setAtEctProvince(0);
//        report012Detail3.setEctResolve(0);
//        report012Detail3.setAnalystRemain(0);
//        report012Detail3.setOfferEct(0);
//        report012Detail3.setAccessCommittee(0);
//        report012Detail3.setOnAgenda(0);
        report012Detail3.setReportId(report012);

        Report012Detail report012Detail4 = new Report012Detail();
//        report012Detail4.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 4");
//        report012Detail4.setAtCenter(0);
//        report012Detail4.setAtEctProvince(0);
//        report012Detail4.setEctResolve(0);
//        report012Detail4.setAnalystRemain(0);
//        report012Detail4.setOfferEct(0);
//        report012Detail4.setAccessCommittee(0);
//        report012Detail4.setOnAgenda(0);
        report012Detail4.setReportId(report012);

        Report012Detail report012Detail5 = new Report012Detail();
//        report012Detail5.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 5");
//        report012Detail5.setAtCenter(0);
//        report012Detail5.setAtEctProvince(0);
//        report012Detail5.setEctResolve(0);
//        report012Detail5.setAnalystRemain(0);
//        report012Detail5.setOfferEct(0);
//        report012Detail5.setAccessCommittee(0);
//        report012Detail5.setOnAgenda(0);
        report012Detail5.setReportId(report012);

        report012Details.add(report012Detail1);
        report012Details.add(report012Detail2);
        report012Details.add(report012Detail3);
        report012Details.add(report012Detail4);
        report012Details.add(report012Detail5);
        
        report012.setReport012DetailList(report012Details);
        report012.setReportMonth(reportMonth);
        report012.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {

        Report012Detail rowDelete = (Report012Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report012Details.remove(rowDelete);

    }
}
