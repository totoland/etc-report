/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report003Detail;
import com.ect.db.report.entity.Report003;
import static com.ect.web.controller.form.BaseFormReportController.REPORT_MODE_VIEW;
import com.ect.web.service.ReportGennericService;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
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
public class FormReport003Controller extends BaseFormReportController {

    private static final Logger logger = LoggerFactory.getLogger(FormReport003Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report003> reportGennericService;
    /**
     * *
     * For Insert Report003
     */
    private Report003 report003 = new Report003();
    /**
     * *
     * For ListDetail
     */
    private List<Report003Detail> report003Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report003Detail inputReport003Detail = new Report003Detail();

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
        this.report003 = new Report003();
        this.report003Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_003 + MessageUtils.PRINT_LINE_STAR());

        report003.setReport003DetailList(report003Details);
        report003.setCreatedDate(new Date());
        report003.setCreatedUser(super.getUserAuthen().getUserId());
        report003.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report003.setReportDesc(ectConfManager.getReportName(REPORT_003));
        report003.setReportCode(REPORT_003);
        report003.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report003);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report003);

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_003 + MessageUtils.PRINT_LINE_STAR());

        report003.setReport003DetailList(report003Details);
        report003.setUpdatedDate(new Date());
        report003.setUpdatedUser(super.getUserAuthen().getUserId());
//        report003.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report003.setReportDesc(ectConfManager.getReportName(REPORT_003));
        report003.setReportCode(REPORT_003);
        report003.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report003);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            goToClose();

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS() + " ข้อผิดพลาด :" + MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report003);

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

        inputReport003Detail = new Report003Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport003Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report003Details == null || report003Details.isEmpty()) {

            report003Details = new ArrayList<>();
            inputReport003Detail.setKey(1);

        } else {

            inputReport003Detail.setKey(report003Details.get(report003Details.size() - 1).getKey() + 1);

        }

        inputReport003Detail.setReportId(report003);

        report003Details.add(inputReport003Detail);

        JsfUtil.hidePopup("REPORT_003dlgAddReportDetail");
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

        Report003Detail editRow = ((Report003Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report003Details.size(); i++) {

            if (report003Details.get(i).getKey() == editRow.getKey()) {

                report003Details.remove(i);
                report003Details.add(i, editRow);

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
     * @return the report003Details
     */
    public List<Report003Detail> getReport003Details() {
        return report003Details;
    }

    /**
     * @param report003Details the report003Details to set
     */
    public void setReport003Details(List<Report003Detail> report003Details) {
        this.report003Details = report003Details;
    }

    /**
     * @return the inputReport003Detail
     */
    public Report003Detail getInputReport003Detail() {
        return inputReport003Detail;
    }

    /**
     * @param inputReport003Detail the inputReport003Detail to set
     */
    public void setInputReport003Detail(Report003Detail inputReport003Detail) {
        this.inputReport003Detail = inputReport003Detail;
    }

    /**
     * @return the report003
     */
    public Report003 getReport003() {
        return report003;
    }

    /**
     * @param report003 the report003 to set
     */
    public void setReport003(Report003 report003) {
        this.report003 = report003;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport003Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport003Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport003Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport003Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport003Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport003Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport003Detail.getBuggetSource())) {
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

//        if (report003.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report003.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report003.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report003.getReport003DetailList() == null || report003.getReport003DetailList().isEmpty()) {
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

        if (REPORT_003.equalsIgnoreCase(paramReportCode)) {

            report003 = reportService.findByReport003ById(paramReportId);

            logger.trace("report003 : {}", report003);

            if (report003.getReport003DetailList().isEmpty()) {
                logger.warn("report003Details is empty set default add new object");
                for (int i = 0; i < 7; i++) {
                    Report003Detail details = new Report003Detail();
                    details.setKey(i);
                    details.setReportId(report003);
                    report003.getReport003DetailList().add(details);
                }
            }
            
            /**
             * * Set ReportDetail **
             */
            report003Details = new ArrayList<>();
            report003Details.addAll(report003.getReport003DetailList());

            for (int i = 0; i < report003Details.size(); i++) {

                report003Details.get(i).setKey(i);

            }

            logger.trace("details : {}", report003Details);

        }

    }

    private void initEditMode() {

        logger.trace("initEditMode...");

        initViewMode();

        if (paramMode.equalsIgnoreCase(REPORT_MODE_EDIT)) {

            reportMonth = report003.getReportMonth();
            reportYear = report003.getReportYear();

        }
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report003> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report003> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {

        initTitle();

        report003Details = new ArrayList<>();

        Report003Detail report003Detail1 = new Report003Detail();
        report003Detail1.setDocReceive("หนังสือรับภายใน");
        report003Detail1.setDocSend("หนังสือส่งภายใน");
        //report003Detail1.setDocSendAmount(0);
        //report003Detail1.setDocReceiveAmount(0);
        report003Detail1.setReportId(report003);

        Report003Detail report003Detail2 = new Report003Detail();
        report003Detail2.setDocReceive("หนังสือรับภายใน (ชั้นความลับ)");
        report003Detail2.setDocSend("หนังสือส่งภายใน (ชั้นความลับ)");
        //report003Detail2.setDocSendAmount(0);
        //report003Detail2.setDocReceiveAmount(0);
        report003Detail2.setReportId(report003);

        Report003Detail report003Detail3 = new Report003Detail();
        report003Detail3.setDocReceive("หนังสือรับภายนอก");
        report003Detail3.setDocSend("หนังสือส่งภายนอก");
        //report003Detail3.setDocSendAmount(0);
        //report003Detail3.setDocReceiveAmount(0);
        report003Detail3.setReportId(report003);

        Report003Detail report003Detail4 = new Report003Detail();
        report003Detail4.setDocReceive("หนังสือรับภายนอก (ชั้นความลับ)");
        report003Detail4.setDocSend("หนังสือส่งภายนอก (ชั้นความลับ)");
        //report003Detail4.setDocSendAmount(0);
        //report003Detail4.setDocReceiveAmount(0);
        report003Detail4.setReportId(report003);

        Report003Detail report003Detail5 = new Report003Detail();
        report003Detail5.setDocReceive("เรื่องร้องเรียน ร้องขอความเป็นธรรม");
        //report003Detail5.setDocSendAmount(0);
        //report003Detail5.setDocReceiveAmount(0);
        report003Detail5.setReportId(report003);

        Report003Detail report003Detail6 = new Report003Detail();
        report003Detail6.setDocReceive("สำนวนร้องคัดค้าน");
        //report003Detail6.setDocSendAmount(0);
        //report003Detail6.setDocReceiveAmount(0);
        report003Detail6.setReportId(report003);

        Report003Detail report003Detail7 = new Report003Detail();
        report003Detail7.setDocReceive("อื่นๆ");
        //report003Detail7.setDocSendAmount(0);
        //report003Detail7.setDocReceiveAmount(0);
        report003Detail7.setReportId(report003);

        report003Details.add(report003Detail1);
        report003Details.add(report003Detail2);
        report003Details.add(report003Detail3);
        report003Details.add(report003Detail4);
        report003Details.add(report003Detail5);
        report003Details.add(report003Detail6);
        report003Details.add(report003Detail7);

        report003.setReport003DetailList(report003Details);
        report003.setReportMonth(reportMonth);
        report003.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
