/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report017Detail;
import com.ect.db.report.entity.Report017;
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
import org.slf4j.MDC;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class FormReport017Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport017Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report017> reportGennericService;
    /**
     * *
     * For Insert Report017
     */
    private Report017 report017 = new Report017();
    /**
     * *
     * For ListDetail
     */
    private List<Report017Detail> report017Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report017Detail inputReport017Detail = new Report017Detail();

    @PostConstruct
    public void init() {

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
        this.report017 = new Report017();
        report017Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_017 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        report017.setReport017DetailList(report017Details);
        report017.setCreatedDate(new Date());
        report017.setCreatedUser(super.getUserAuthen().getUserId());
        report017.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report017.setReportDesc(ectConfManager.getReportName(REPORT_017));
        report017.setReportCode(REPORT_017);
        report017.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report017);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("REPORT_MainDialog_REPORT_017");

            resetForm();
        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report017);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_017 + MessageUtils.PRINT_LINE_STAR());

        calSum();
        
        //report017.setReport017DetailList(report017Details);
        report017.setUpdatedDate(new Date());
        report017.setUpdatedUser(super.getUserAuthen().getUserId());
        report017.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report017.setReportDesc(ectConfManager.getReportName(REPORT_017));
        report017.setReportCode(REPORT_017);
        report017.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report017);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report017);

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

        inputReport017Detail = new Report017Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport017Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report017Details == null || report017Details.isEmpty()) {

            report017Details = new ArrayList<>();
            inputReport017Detail.setKey(1);

        } else {

            inputReport017Detail.setKey(report017Details.get(report017Details.size() - 1).getKey() + 1);

        }

        inputReport017Detail.setReportId(report017);

        report017Details.add(inputReport017Detail);

        JsfUtil.hidePopup("REPORT_017dlgAddReportDetail");
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

        Report017Detail editRow = ((Report017Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report017Details.size(); i++) {

            if (report017Details.get(i).getKey() == editRow.getKey()) {

                report017Details.remove(i);
                report017Details.add(i, editRow);

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
     * @return the report017Details
     */
    public List<Report017Detail> getReport017Details() {
        return report017Details;
    }

    /**
     * @param report017Details the report017Details to set
     */
    public void setReport017Details(List<Report017Detail> report017Details) {
        this.report017Details = report017Details;
    }

    /**
     * @return the inputReport017Detail
     */
    public Report017Detail getInputReport017Detail() {
        return inputReport017Detail;
    }

    /**
     * @param inputReport017Detail the inputReport017Detail to set
     */
    public void setInputReport017Detail(Report017Detail inputReport017Detail) {
        this.inputReport017Detail = inputReport017Detail;
    }

    /**
     * @return the report017
     */
    public Report017 getReport017() {
        return report017;
    }

    /**
     * @param report017 the report017 to set
     */
    public void setReport017(Report017 report017) {
        this.report017 = report017;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport017Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport017Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport017Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport017Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport017Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport017Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport017Detail.getBuggetSource())) {
//            msg += "กรุณาระบุที่มาของงบประมาณbr/>";
//        }

        if (!StringUtils.isBlank(msg)) {
            addError(msg);
            return false;
        }

        return true;
    }

    private boolean validateBeforeSave() {

        String msg = "";

//        if (report017.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report017.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report017.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report017.getReport017DetailList() == null || report017.getReport017DetailList().isEmpty()) {
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

        if (REPORT_017.equalsIgnoreCase(paramReportCode)) {

            report017 = reportService.findByReport017ById(paramReportId);

            logger.trace("report017 : {}", report017);

            /**
             * * Set ReportDetail **
             */
            report017Details = new ArrayList<>();
            report017Details.addAll(report017.getReport017DetailList());

            for (int i = 0; i < report017Details.size(); i++) {

                report017Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    public void goToEdit() {
        String url = "?mode=" + REPORT_MODE_EDIT + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);
    }

    public void goToClose() {
        JsfUtil.hidePopupIframe("dialogEdit");
    }

    public void goToCancel() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "resetForm Report : {}", REPORT_017 + MessageUtils.PRINT_LINE_STAR());
        String url = "?mode=" + REPORT_MODE_VIEW + "&reportId=" + paramReportId + "&reportCode=" + paramReportCode;
        redirectPage(url);

    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report017> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report017> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        report017Details = new ArrayList<>();

        Report017Detail report017Detail1 = new Report017Detail();
        report017Detail1.setInstitution("สำนักสืบสวนและวินิจฉัย 1");
        report017Detail1.setReportId(report017);
        
        Report017Detail report017Detail2 = new Report017Detail();
        report017Detail2.setInstitution("สำนักสืบสวนและวินิจฉัย 2");
        report017Detail2.setReportId(report017);

        Report017Detail report017Detail3 = new Report017Detail();
        report017Detail3.setInstitution("สำนักสืบสวนและวินิจฉัย 3");
        report017Detail3.setReportId(report017);
        
        Report017Detail report017Detail4 = new Report017Detail();
        report017Detail4.setInstitution("สำนักสืบสวนและวินิจฉัย 4");
        report017Detail4.setReportId(report017);
        
        Report017Detail report017Detail5 = new Report017Detail();
        report017Detail5.setInstitution("สำนักสืบสวนและวินิจฉัย 5");
        report017Detail5.setReportId(report017);

        report017Details.add(report017Detail1);
        report017Details.add(report017Detail2);
        report017Details.add(report017Detail3);
        report017Details.add(report017Detail4);
        report017Details.add(report017Detail5);
        
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calSum() {

//        for (int i = 0; i < report017Details.size(); i++) {
//            report017Details.get(i).setSumAmount(report017Details.get(i).getStAmount()+report017Details.get(i).getPtAmount());
//        }
    }
}
