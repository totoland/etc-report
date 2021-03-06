/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report011Detail;
import com.ect.db.report.entity.Report011;
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
public class FormReport011Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport011Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report011> reportGennericService;
    /**
     * *
     * For Insert Report011
     */
    private Report011 report011 = new Report011();
    /**
     * *
     * For ListDetail
     */
    private List<Report011Detail> report011Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report011Detail inputReport011Detail = new Report011Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form011!!");

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
        this.report011 = new Report011();
        report011Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_011 + MessageUtils.PRINT_LINE_STAR());

        calSum();

        report011.setReport011DetailList(report011Details);
        report011.setCreatedDate(new Date());
        report011.setCreatedUser(super.getUserAuthen().getUserId());
        report011.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report011.setReportDesc(ectConfManager.getReportName(REPORT_011));
        report011.setReportCode(REPORT_011);
        report011.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report011);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report011);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_011 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report011.setReport011DetailList(report011Details);
        report011.setUpdatedDate(new Date());
        report011.setUpdatedUser(super.getUserAuthen().getUserId());
        //report011.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report011.setReportDesc(ectConfManager.getReportName(REPORT_011));
        report011.setReportCode(REPORT_011);
        report011.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report011);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report011);

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

        inputReport011Detail = new Report011Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport011Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report011Details == null || report011Details.isEmpty()) {

            report011Details = new ArrayList<>();
            inputReport011Detail.setKey(1);

        } else {

            inputReport011Detail.setKey(report011Details.get(report011Details.size() - 1).getKey() + 1);

        }

        inputReport011Detail.setReportId(report011);

        report011Details.add(inputReport011Detail);

        JsfUtil.hidePopup("REPORT_011dlgAddReportDetail");
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

        Report011Detail editRow = ((Report011Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report011Details.size(); i++) {

            if (report011Details.get(i).getKey() == editRow.getKey()) {

                report011Details.remove(i);
                report011Details.add(i, editRow);

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
    
    private Report011Detail sumDetail = new Report011Detail();

    public void calSum() {
        
        sumDetail.setAccessCommittee(0);
        sumDetail.setAllamount(0);
        sumDetail.setAnalystRemain(0);
        sumDetail.setAtCenter(0);
        sumDetail.setAtEctProvince(0);
        sumDetail.setEctResolve(0);
        sumDetail.setOfferEct(0);
        sumDetail.setOnAgenda(0);
        
        for (int i = 0; i < report011Details.size(); i++) {
            report011Details.get(i).setAtCenter(NumberUtils.convertNUllToZero(report011Details.get(i).getOnAgenda()) + NumberUtils.convertNUllToZero(report011Details.get(i).getAccessCommittee()) + NumberUtils.convertNUllToZero(report011Details.get(i).getOfferEct()) + NumberUtils.convertNUllToZero(report011Details.get(i).getAnalystRemain()));
            report011Details.get(i).setAllamount(NumberUtils.convertNUllToZero(report011Details.get(i).getAtCenter()) + NumberUtils.convertNUllToZero(report011Details.get(i).getAtEctProvince()) + NumberUtils.convertNUllToZero(report011Details.get(i).getEctResolve()));
            
            sumDetail.setAccessCommittee(sumDetail.getAccessCommittee()+NumberUtils.convertNUllToZero(report011Details.get(i).getAccessCommittee()));
            sumDetail.setAllamount(sumDetail.getAllamount()+NumberUtils.convertNUllToZero(report011Details.get(i).getAllamount()));
            sumDetail.setAnalystRemain(sumDetail.getAnalystRemain()+NumberUtils.convertNUllToZero(report011Details.get(i).getAnalystRemain()));
            sumDetail.setAtCenter(sumDetail.getAtCenter()+NumberUtils.convertNUllToZero(report011Details.get(i).getAtCenter()));
            sumDetail.setAtEctProvince(sumDetail.getAtEctProvince()+NumberUtils.convertNUllToZero(report011Details.get(i).getAtEctProvince()));
            sumDetail.setEctResolve(sumDetail.getEctResolve()+NumberUtils.convertNUllToZero(report011Details.get(i).getEctResolve()));
            sumDetail.setOfferEct(sumDetail.getOfferEct()+NumberUtils.convertNUllToZero(report011Details.get(i).getOfferEct()));
            sumDetail.setOnAgenda(sumDetail.getOnAgenda()+NumberUtils.convertNUllToZero(report011Details.get(i).getOnAgenda()));
        }
    }

    public Report011Detail getSumDetail() {
        return sumDetail;
    }

    public void setSumDetail(Report011Detail sumDetail) {
        this.sumDetail = sumDetail;
    }
    
    /**
     * @return the report011Details
     */
    public List<Report011Detail> getReport011Details() {
        return report011Details;
    }

    /**
     * @param report011Details the report011Details to set
     */
    public void setReport011Details(List<Report011Detail> report011Details) {
        this.report011Details = report011Details;
    }

    /**
     * @return the inputReport011Detail
     */
    public Report011Detail getInputReport011Detail() {
        return inputReport011Detail;
    }

    /**
     * @param inputReport011Detail the inputReport011Detail to set
     */
    public void setInputReport011Detail(Report011Detail inputReport011Detail) {
        this.inputReport011Detail = inputReport011Detail;
    }

    /**
     * @return the report011
     */
    public Report011 getReport011() {
        return report011;
    }

    /**
     * @param report011 the report011 to set
     */
    public void setReport011(Report011 report011) {
        this.report011 = report011;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport011Detail);

        String msg = "";

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

        if (report011.getReport011DetailList() == null || report011.getReport011DetailList().isEmpty()) {
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

        if (REPORT_011.equalsIgnoreCase(paramReportCode)) {

            report011 = reportService.findByReport011ById(paramReportId);

            logger.trace("report011 : {}", report011);

            /**
             * * Set ReportDetail **
             */
            report011Details = new ArrayList<>();
            report011Details.addAll(report011.getReport011DetailList());

            for (int i = 0; i < report011Details.size(); i++) {

                report011Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report011> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report011> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        
        report011Details = new ArrayList<>();

        Report011Detail report011Detail1 = new Report011Detail();
//        report011Detail1.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 1");
//        report011Detail1.setAtCenter(0);
//        report011Detail1.setAtEctProvince(0);
//        report011Detail1.setEctResolve(0);
//        report011Detail1.setAnalystRemain(0);
//        report011Detail1.setOfferEct(0);
//        report011Detail1.setAccessCommittee(0);
//        report011Detail1.setOnAgenda(0);
        report011Detail1.setReportId(report011);

        Report011Detail report011Detail2 = new Report011Detail();
//        report011Detail2.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 2");
//        report011Detail2.setAtCenter(0);
//        report011Detail2.setAtEctProvince(0);
//        report011Detail2.setEctResolve(0);
//        report011Detail2.setAnalystRemain(0);
//        report011Detail2.setOfferEct(0);
//        report011Detail2.setAccessCommittee(0);
//        report011Detail2.setOnAgenda(0);
        report011Detail2.setReportId(report011);

        Report011Detail report011Detail3 = new Report011Detail();
//        report011Detail3.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 3");
//        report011Detail3.setAtCenter(0);
//        report011Detail3.setAtEctProvince(0);
//        report011Detail3.setEctResolve(0);
//        report011Detail3.setAnalystRemain(0);
//        report011Detail3.setOfferEct(0);
//        report011Detail3.setAccessCommittee(0);
//        report011Detail3.setOnAgenda(0);
        report011Detail3.setReportId(report011);

        Report011Detail report011Detail4 = new Report011Detail();
//        report011Detail4.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 4");
//        report011Detail4.setAtCenter(0);
//        report011Detail4.setAtEctProvince(0);
//        report011Detail4.setEctResolve(0);
//        report011Detail4.setAnalystRemain(0);
//        report011Detail4.setOfferEct(0);
//        report011Detail4.setAccessCommittee(0);
//        report011Detail4.setOnAgenda(0);
        report011Detail4.setReportId(report011);

        Report011Detail report011Detail5 = new Report011Detail();
//        report011Detail5.setInstitution("สำนักสืบสวนสอบสวนและวินิจฉัย 5");
//        report011Detail5.setAtCenter(0);
//        report011Detail5.setAtEctProvince(0);
//        report011Detail5.setEctResolve(0);
//        report011Detail5.setAnalystRemain(0);
//        report011Detail5.setOfferEct(0);
//        report011Detail5.setAccessCommittee(0);
//        report011Detail5.setOnAgenda(0);
        report011Detail5.setReportId(report011);

        report011Details.add(report011Detail1);
        report011Details.add(report011Detail2);
        report011Details.add(report011Detail3);
        report011Details.add(report011Detail4);
        report011Details.add(report011Detail5);
        
        report011.setReport011DetailList(report011Details);
        report011.setReportMonth(reportMonth);
        report011.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {

        Report011Detail rowDelete = (Report011Detail) object;

        logger.trace("delete item : {}", rowDelete);

        report011Details.remove(rowDelete);

    }
}
