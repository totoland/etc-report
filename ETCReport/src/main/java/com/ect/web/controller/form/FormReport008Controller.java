/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report008Detail;
import com.ect.db.report.entity.Report008;
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
public class FormReport008Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport008Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report008> reportGennericService;
    
    /**
     * *
     * For Insert Report008
     */
    private Report008 report008 = new Report008();
    /**
     * *
     * For ListDetail
     */
    private List<Report008Detail> report008Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report008Detail inputReport008Detail = new Report008Detail();

    @PostConstruct
    public void init() {

        logger.trace("init Form008!!");
        
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
        this.report008 = new Report008();
        report008Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_008 + MessageUtils.PRINT_LINE_STAR());

        report008.setReport008DetailList(report008Details);
        report008.setCreatedDate(new Date());
        report008.setCreatedUser(super.getUserAuthen().getUserId());
        report008.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report008.setReportDesc(ectConfManager.getReportName(REPORT_008));
        report008.setReportCode(REPORT_008);
        report008.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report008);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report008);
            resetForm();

        }
    }
    
    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_008 + MessageUtils.PRINT_LINE_STAR());

        report008.setReport008DetailList(report008Details);
        report008.setUpdatedDate(new Date());
        report008.setUpdatedUser(super.getUserAuthen().getUserId());
        //report008.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report008.setReportDesc(ectConfManager.getReportName(REPORT_008));
        report008.setReportCode(REPORT_008);
        report008.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report008);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report008);

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

        inputReport008Detail = new Report008Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport008Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report008Details == null || report008Details.isEmpty()) {

            report008Details = new ArrayList<>();
            inputReport008Detail.setKey(1);

        } else {

            inputReport008Detail.setKey(report008Details.get(report008Details.size() - 1).getKey() + 1);

        }

        inputReport008Detail.setReportId(report008);

        report008Details.add(inputReport008Detail);

        JsfUtil.hidePopup("REPORT_008dlgAddReportDetail");
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

        Report008Detail editRow = ((Report008Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report008Details.size(); i++) {

            if (report008Details.get(i).getKey() == editRow.getKey()) {

                report008Details.remove(i);
                report008Details.add(i, editRow);

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
     * @return the report008Details
     */
    public List<Report008Detail> getReport008Details() {
        return report008Details;
    }

    /**
     * @param report008Details the report008Details to set
     */
    public void setReport008Details(List<Report008Detail> report008Details) {
        this.report008Details = report008Details;
    }

    /**
     * @return the inputReport008Detail
     */
    public Report008Detail getInputReport008Detail() {
        return inputReport008Detail;
    }

    /**
     * @param inputReport008Detail the inputReport008Detail to set
     */
    public void setInputReport008Detail(Report008Detail inputReport008Detail) {
        this.inputReport008Detail = inputReport008Detail;
    }

    /**
     * @return the report008
     */
    public Report008 getReport008() {
        return report008;
    }

    /**
     * @param report008 the report008 to set
     */
    public void setReport008(Report008 report008) {
        this.report008 = report008;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport008Detail);

        String msg = "";

        if (StringUtils.isBlank(inputReport008Detail.getPoliticalParty())) {
            msg += "กรุณาระบุพรรค<br/>";
        }
        if (!NumberUtils.isNumber(inputReport008Detail.getBudget()) || inputReport008Detail.getBudget().intValue() == 0) {
            msg += "กรุณาระบุงบประมาณที่ได้รับสนับสนุน<br/>";
        }
        if (!NumberUtils.isNumber(inputReport008Detail.getDisbursedPrevious()) || inputReport008Detail.getDisbursedPrevious().intValue() == 0) {
            msg += "กรุณาระบุก่อนหน้านี้<br/>";
        }
        if (!NumberUtils.isNumber(inputReport008Detail.getDisbursedMonth()) || inputReport008Detail.getDisbursedPrevious().intValue() == 0) {
            msg += "กรุณาระบุเดือนนี้<br/>";
        }
        if (!NumberUtils.isNumber(inputReport008Detail.getBalance()) || inputReport008Detail.getBalance().intValue() == 0) {
            msg += "กรุณาระบุคงเหลือ<br/>";
        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

//        if (report008.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report008.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report008.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report008.getReport008DetailList() == null || report008.getReport008DetailList().isEmpty()) {
//            msg += (MessageUtils.REQUIRE_ADD_REPORT_DETAIL());
//        }
//
//        if (!StringUtils.isBlank(msg.toString())) {
//            JsfUtil.alertJavaScript(msg.toString().trim());
//            return false;
//        }

        return true;
    }
    
    private void initViewMode() {

        logger.trace("initViewMode...");

        if (REPORT_008.equalsIgnoreCase(paramReportCode)) {

            report008 = reportService.findByReport008ById(paramReportId);

            logger.trace("report008 : {}", report008);

            /**
             * * Set ReportDetail **
             */
            report008Details = new ArrayList<>();
            report008Details.addAll(report008.getReport008DetailList());

            for (int i = 0; i < report008Details.size(); i++) {

                report008Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }
    
    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report008> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report008> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();
        
        logger.trace("reportTitle : {}",reportTitle);
        
        report008.setReport008DetailList(report008Details);
        report008.setReportMonth(reportMonth);
        report008.setReportYear(reportYear);
     
    }

    @Override
    public void onDelete(Object object) {
        
        Report008Detail rowDelete = (Report008Detail)object;
        
        logger.trace("delete item : {}",rowDelete);
        
        report008Details.remove(rowDelete);
        
    }
    
}
