/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.form;

import com.ect.db.entity.EctFlowStatus;
import com.ect.db.report.entity.Report007Detail;
import com.ect.db.report.entity.Report007;
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
public class FormReport007Controller extends BaseFormReportController {

    private static Logger logger = LoggerFactory.getLogger(FormReport007Controller.class);
    private static final long serialVersionUID = 1764749403349238850L;
    /**
     * *
     * Service
     */
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<Report007> reportGennericService;
    /**
     * *
     * For Insert Report007
     */
    private Report007 report007 = new Report007();
    /**
     * *
     * For ListDetail
     */
    private List<Report007Detail> report007Details = new ArrayList<>();
    /**
     * *
     * For Add Record
     */
    private Report007Detail inputReport007Detail = new Report007Detail();

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
        this.report007 = new Report007();
        report007Details.clear();
        initForm();
    }

    @Override
    public void save() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Save Report : {}", REPORT_007 + MessageUtils.PRINT_LINE_STAR());

        report007.setReport007DetailList(report007Details);
        report007.setCreatedDate(new Date());
        report007.setCreatedUser(super.getUserAuthen().getUserId());
        report007.setFlowStatusId(EctFlowStatus.FlowStatus.DRAFF.getStatus());
        report007.setReportDesc(ectConfManager.getReportName(REPORT_007));
        report007.setReportCode(REPORT_007);
        report007.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(report007);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", report007);
            resetForm();

        }
    }

    @Override
    public void edit() {

        logger.trace(MessageUtils.PRINT_LINE_STAR() + "Edit Report : {}", REPORT_007 + MessageUtils.PRINT_LINE_STAR());

        //report007.setReport007DetailList(report007Details);
        report007.setUpdatedDate(new Date());
        report007.setUpdatedUser(super.getUserAuthen().getUserId());
        report007.setFlowStatusId(EctFlowStatus.FlowStatus.STEP_1.getStatus());
        report007.setReportDesc(ectConfManager.getReportName(REPORT_007));
        report007.setReportCode(REPORT_007);
        report007.setCreatedUserGroup(getUserAuthen().getUserGroupId());

        if (!validateBeforeSave()) {
            return;
        }

        try {

            getReportGennericService().edit(report007);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopupIframe("dialogEdit");

        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Edit Data : ", ex);

        } finally {

            logger.trace("Edit... {} ", report007);

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

        inputReport007Detail = new Report007Detail();
    }

    /**
     * *
     * AddReportDetail to Grid
     */
    @Override
    public void addReportDetail(ActionEvent actionEvent) {

        logger.trace("addReportDetail... {}", inputReport007Detail);

        if (!validateReportDetail()) {
            return;
        }

        if (report007Details == null || report007Details.isEmpty()) {

            report007Details = new ArrayList<>();
            inputReport007Detail.setKey(1);

        } else {

            inputReport007Detail.setKey(report007Details.get(report007Details.size() - 1).getKey() + 1);

        }

        inputReport007Detail.setReportId(report007);

        report007Details.add(inputReport007Detail);

        JsfUtil.hidePopup("REPORT_007dlgAddReportDetail");
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

        Report007Detail editRow = ((Report007Detail) event.getObject());

        logger.trace("Edit Row : {}", editRow);

        for (int i = 0; i < report007Details.size(); i++) {

            if (report007Details.get(i).getKey() == editRow.getKey()) {

                report007Details.remove(i);
                report007Details.add(i, editRow);

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
     * @return the report007Details
     */
    public List<Report007Detail> getReport007Details() {
        return report007Details;
    }

    /**
     * @param report007Details the report007Details to set
     */
    public void setReport007Details(List<Report007Detail> report007Details) {
        this.report007Details = report007Details;
    }

    /**
     * @return the inputReport007Detail
     */
    public Report007Detail getInputReport007Detail() {
        return inputReport007Detail;
    }

    /**
     * @param inputReport007Detail the inputReport007Detail to set
     */
    public void setInputReport007Detail(Report007Detail inputReport007Detail) {
        this.inputReport007Detail = inputReport007Detail;
    }

    /**
     * @return the report007
     */
    public Report007 getReport007() {
        return report007;
    }

    /**
     * @param report007 the report007 to set
     */
    public void setReport007(Report007 report007) {
        this.report007 = report007;
    }

    private boolean validateReportDetail() {

        logger.trace("validateReportDetail : {}", inputReport007Detail);

        String msg = "";

//        if (StringUtils.isBlank(inputReport007Detail.getWorkDetail())) {
//            msg += "กรุณาระบุรายละเอียดการดำเนินงาน<br/>";
//        }
//        if (StringUtils.isBlank(inputReport007Detail.getObjective())) {
//            msg += "กรุณาระบุวัตถุประสงค์<br/>";
//        }
//        if (inputReport007Detail.getGoalAmount().intValue() == 0) {
//            msg += "กรุณาระบุจำนวนเป้าหมาย<br/>";
//        }
//        if (StringUtils.isBlank(inputReport007Detail.getResult())) {
//            msg += "กรุณาระบุผลการปฏิบัติงาน<br/>";
//        }
//        if (inputReport007Detail.getBudget().intValue() == 0) {
//            msg += "กรุณาระบุงบประมาณbr/>";
//        }
//        if (StringUtils.isBlank(inputReport007Detail.getBuggetSource())) {
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

//        if (report007.getStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_STRATEGICID()) + ("\\n");
//        }
//        if (report007.getSubStrategicId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID()) + ("\\n");
//        }
//        if (report007.getPlanId().intValue() == -1) {
//            msg += (MessageUtils.REQUIRE_SELECT_PLAN()) + ("\\n");
//        }
//        if (report007.getReport007DetailList() == null || report007.getReport007DetailList().isEmpty()) {
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

        if (REPORT_007.equalsIgnoreCase(paramReportCode)) {

            report007 = reportService.findByReport007ById(paramReportId);

            logger.trace("report007 : {}", report007);

            /**
             * * Set ReportDetail **
             */
            report007Details = new ArrayList<>();
            report007Details.addAll(report007.getReport007DetailList());

            for (int i = 0; i < report007Details.size(); i++) {

                report007Details.get(i).setKey(i);

            }

        }

    }

    private void initEditMode() {
        initViewMode();
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<Report007> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<Report007> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private void initForm() {
        
        initTitle();

        report007Details = new ArrayList<>();

        Report007Detail report007Detail1 = new Report007Detail();
        report007Detail1.setTitle("1.รับรองการจัดตั้งพรรคการเมือง");
//        report007Detail1.setPoliticalPartyAmount(0);
//        report007Detail1.setStoryAmount(0);
//        report007Detail1.setBranchAmount(0);
        report007Detail1.setReportId(report007);
        
        Report007Detail report007Detail2 = new Report007Detail();
        report007Detail2.setTitle("2.รับรองการจัดตั้งสาขาพรรคการเมือง");
//        report007Detail2.setPoliticalPartyAmount(0);
//        report007Detail2.setStoryAmount(0);
//        report007Detail2.setBranchAmount(0);
        report007Detail2.setReportId(report007);

        Report007Detail report007Detail3 = new Report007Detail();
        report007Detail3.setTitle("3.ไม่รับรองการจัดตั้งพรรคการเมือง");
//        report007Detail3.setPoliticalPartyAmount(0);
//        report007Detail3.setStoryAmount(0);
//        report007Detail3.setBranchAmount(0);
        report007Detail3.setReportId(report007);
        
        Report007Detail report007Detail4 = new Report007Detail();
        report007Detail4.setTitle("4.ยกเลิกรับรองการจัดตั้งสาขาพรรคการเมือง");
//        report007Detail4.setPoliticalPartyAmount(0);
//        report007Detail4.setStoryAmount(0);
//        report007Detail4.setBranchAmount(0);
        report007Detail4.setReportId(report007);
        
        Report007Detail report007Detail5 = new Report007Detail();
        report007Detail5.setTitle("5.ตอบรับการเปลี่ยนแปลงตามมาตรา 41 ของ พระราชบัญญัติประกอบรัฐธรรมนูญว่าด้วย พรรคการเมือง พ.ศ. 2550");
//        report007Detail5.setPoliticalPartyAmount(0);
//        report007Detail5.setStoryAmount(0);
//        report007Detail5.setBranchAmount(0);
        report007Detail5.setReportId(report007);
        
        Report007Detail report007Detail6 = new Report007Detail();
        report007Detail6.setTitle("6.ดำเนินกิจการของพรรคการเมืองตามมาตรา 69 ของ พระราชบัญญัติประกอบรัฐธรรมนูญว่าด้วย พรรคการเมือง พ.ศ. 2550");
//        report007Detail6.setPoliticalPartyAmount(0);
//        report007Detail6.setStoryAmount(0);
//        report007Detail6.setBranchAmount(0);
        report007Detail6.setReportId(report007);
        
        Report007Detail report007Detail7 = new Report007Detail();
        report007Detail7.setTitle("");
//        report007Detail7.setPoliticalPartyAmount(0);
//        report007Detail7.setStoryAmount(0);
//        report007Detail7.setBranchAmount(0);
        report007Detail7.setReportId(report007);
        
        Report007Detail report007Detail8 = new Report007Detail();
        report007Detail8.setTitle("");
//        report007Detail8.setPoliticalPartyAmount(0);
//        report007Detail8.setStoryAmount(0);
//        report007Detail8.setBranchAmount(0);
        report007Detail8.setReportId(report007);
        
        Report007Detail report007Detail9 = new Report007Detail();
        report007Detail9.setTitle("");
//        report007Detail9.setPoliticalPartyAmount(0);
//        report007Detail9.setStoryAmount(0);
//        report007Detail9.setBranchAmount(0);
        report007Detail9.setReportId(report007);

        report007Details.add(report007Detail1);
        report007Details.add(report007Detail2);
        report007Details.add(report007Detail3);
        report007Details.add(report007Detail4);
        report007Details.add(report007Detail5);
        report007Details.add(report007Detail6);
        report007Details.add(report007Detail7);
        report007Details.add(report007Detail8);
        report007Details.add(report007Detail9);
        
        report007.setReport007DetailList(report007Details);
        report007.setReportMonth(reportMonth);
        report007.setReportYear(reportYear);
    }

    @Override
    public void onDelete(Object object) {
        Report007Detail rowDelete = (Report007Detail)object;
        
        logger.trace("delete item : {}",rowDelete);
        
        report007Details.remove(rowDelete);
    }
}
