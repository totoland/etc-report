/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.ViewUser;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.Report001Detail;
import com.ect.db.report.entity.Report002;
import com.ect.db.report.entity.Report002Detail;
import com.ect.db.report.entity.Report003;
import com.ect.db.report.entity.Report003Detail;
import com.ect.db.report.entity.Report004;
import com.ect.db.report.entity.Report004Detail;
import com.ect.db.report.entity.Report005;
import com.ect.db.report.entity.Report005Detail;
import com.ect.db.report.entity.Report006;
import com.ect.db.report.entity.Report006Detail;
import com.ect.db.report.entity.Report007;
import com.ect.db.report.entity.Report007Detail;
import com.ect.db.report.entity.Report008;
import com.ect.db.report.entity.Report008Detail;
import com.ect.db.report.entity.Report009;
import com.ect.db.report.entity.Report009Detail;
import com.ect.db.report.entity.Report010;
import com.ect.db.report.entity.Report010Detail;
import com.ect.db.report.entity.Report011;
import com.ect.db.report.entity.Report012;
import com.ect.db.report.entity.Report013;
import com.ect.db.report.entity.Report014;
import com.ect.db.report.entity.Report015;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.form.BaseFormReportController;
import com.ect.web.controller.model.LazyViewReportImpl;
import com.ect.web.service.UserService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class AllReportController extends BaseFormReportController {

    private static final long serialVersionUID = 8451238753520170431L;
    private static Logger logger = LoggerFactory.getLogger(AllReportController.class);
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    private List<ViewReportStatus> viewReportResult;
    private ReportCriteria reportCriteria;
    private LazyDataModel<ViewReportStatus> lazyModel;

    @PostConstruct
    public void init() {

        initCriteria();

    }

    public void search() {

        logger.trace("Search!!");
        logger.trace("Criteria : {}", reportCriteria);

        final Integer count = reportService.countByCriteria(reportCriteria);

        if (count != null || count > 0) {

            final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                    .findComponent(":form1:rptPreSendList2");
            d.setFirst(0);

            LazyViewReportImpl reportModel = new LazyViewReportImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            lazyModel = reportModel;

        }
    }

    @Override
    public void resetForm() {
        initCriteria();
        lazyModel = null;
    }

    public void fileXLSDownload(ViewReportStatus viewReportStatus) {

        logger.trace("Select report : {}", viewReportStatus);

        String month = DateTimeUtils.getInstance().thDate(viewReportStatus.getCreatedDate(), "MMMM");
        String year = DateTimeUtils.getInstance().thDate(viewReportStatus.getCreatedDate(), "yyyy");
        String depName = "";
        String createdDate = DateTimeUtils.getInstance().thDate(viewReportStatus.getCreatedDate(), DateTimeUtils.DISPLAY_DATETIME_FORMAT);
        String reportName = "";

        ViewUser viewUser = userService.findByUserId(viewReportStatus.getCreatedUser());

        if (viewUser != null) {

            depName = viewUser.getProvinceName();

        }

        Map<String, Object> beans = new HashMap<>();
        beans.put("month", month);
        beans.put("year", year);
        beans.put("depName", depName);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", viewReportStatus.getCreatedUserFullName());

        if (viewReportStatus.getReportCode().equals(REPORT_001)) {

            reportName = REPORT_001;

            Report001 report001 = reportService.findByReport001ById(viewReportStatus.getReportId());

            if (report001 == null || report001.getReport001DetailList() == null || report001.getReport001DetailList().isEmpty()) {

                logger.warn("Cannot find Report001 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report001Detail>());

            } else {

                beans.put("details", report001.getReport001DetailList());

            }
        } else if (viewReportStatus.getReportCode().equals(REPORT_002)) {

            reportName = REPORT_002;

            Report002 report002 = reportService.findByReport002ById(viewReportStatus.getReportId());

            if (report002 == null || report002.getReport002DetailList() == null || report002.getReport002DetailList().isEmpty()) {

                logger.warn("Cannot find Report002 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report002Detail>());

            } else {

                beans.put("details", report002.getReport002DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_003)) {

            reportName = REPORT_003;

            Report003 report003 = reportService.findByReport003ById(viewReportStatus.getReportId());

            if (report003 == null || report003.getReport003DetailList() == null || report003.getReport003DetailList().isEmpty()) {

                logger.warn("Cannot find Report003 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report003Detail>());

            } else {

                beans.put("details", report003.getReport003DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_004)) {

            reportName = REPORT_004;

            Report004 report004 = reportService.findByReport004ById(viewReportStatus.getReportId());

            if (report004 == null || report004.getReport004DetailList() == null || report004.getReport004DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report004Detail>());

            } else {

                beans.put("details", report004.getReport004DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_005)) {

            reportName = REPORT_005;

            Report005 report005 = reportService.findByReport005ById(viewReportStatus.getReportId());

            beans.put("report", report005);

            if (report005 == null || report005.getReport005DetailList() == null || report005.getReport005DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report004Detail>());
                beans.put("details2", new ArrayList<Report004Detail>());

            } else {

                List<Report005Detail> report005Details = new ArrayList<>();
                List<Report005Detail> report005Details2 = new ArrayList<>();

                for (Report005Detail report005Detail : report005.getReport005DetailList()) {

                    if (report005Detail.getElectedType() != null && report005Detail.getElectedType().intValue() == 1) {
                        report005Details.add(report005Detail);
                    } else {
                        report005Details2.add(report005Detail);
                    }

                }

                beans.put("details", report005Details);
                beans.put("details2", report005Details2);

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_006)) {

            reportName = REPORT_006;

            Report006 report006 = reportService.findByReport006ById(viewReportStatus.getReportId());

            if (report006 == null || report006.getReport006DetailList() == null || report006.getReport006DetailList().isEmpty()) {

                logger.warn("Cannot find Report004 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report006Detail>());

            } else {

                beans.put("details", report006.getReport006DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_007)) {

            reportName = REPORT_007;

            Report007 report007 = reportService.findByReport007ById(viewReportStatus.getReportId());

            if (report007 == null || report007.getReport007DetailList() == null || report007.getReport007DetailList().isEmpty()) {

                logger.warn("Cannot find Report007 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report007Detail>());

            } else {

                beans.put("details", report007.getReport007DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_008)) {

            reportName = REPORT_008;

            Report008 report008 = reportService.findByReport008ById(viewReportStatus.getReportId());

            if (report008 == null || report008.getReport008DetailList() == null || report008.getReport008DetailList().isEmpty()) {

                logger.warn("Cannot find Report008 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report008Detail>());

            } else {

                beans.put("details", report008.getReport008DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_009)) {

            reportName = REPORT_009;

            Report009 report009 = reportService.findByReport009ById(viewReportStatus.getReportId());

            if (report009 == null || report009.getReport009DetailList() == null || report009.getReport009DetailList().isEmpty()) {

                logger.warn("Cannot find Report010 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report009Detail>());

            } else {

                beans.put("details", report009.getReport009DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_010)) {

            reportName = REPORT_010;

            Report010 report010 = reportService.findByReport010ById(viewReportStatus.getReportId());

            if (report010 == null || report010.getReport010DetailList() == null || report010.getReport010DetailList().isEmpty()) {

                logger.warn("Cannot find Report010 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report010.getReport010DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_011)) {

            reportName = REPORT_011;

            Report011 report011 = reportService.findByReport011ById(viewReportStatus.getReportId());

            if (report011 == null || report011.getReport011DetailList() == null || report011.getReport011DetailList().isEmpty()) {

                logger.warn("Cannot find Report011 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report011.getReport011DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_012)) {

            reportName = REPORT_012;

            Report012 report012 = reportService.findByReport012ById(viewReportStatus.getReportId());

            if (report012 == null || report012.getReport012DetailList() == null || report012.getReport012DetailList().isEmpty()) {

                logger.warn("Cannot find Report012 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report012.getReport012DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_013)) {

            reportName = REPORT_013;

            Report013 report013 = reportService.findByReport013ById(viewReportStatus.getReportId());

            if (report013 == null || report013.getReport013DetailList() == null || report013.getReport013DetailList().isEmpty()) {

                logger.warn("Cannot find Report013 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report013.getReport013DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_014)) {

            reportName = REPORT_014;

            Report014 report014 = reportService.findByReport014ById(viewReportStatus.getReportId());

            if (report014 == null || report014.getReport014DetailList() == null || report014.getReport014DetailList().isEmpty()) {

                logger.warn("Cannot find Report014 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report014.getReport014DetailList());

            }

        } else if (viewReportStatus.getReportCode().equals(REPORT_015)) {

            reportName = REPORT_015;

            Report015 report015 = reportService.findByReport015ById(viewReportStatus.getReportId());

            if (report015 == null || report015.getReport015DetailList() == null || report015.getReport015DetailList().isEmpty()) {

                logger.warn("Cannot find Report015 by Id : {}", viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report010Detail>());

            } else {

                beans.put("details", report015.getReport015DetailList());

            }

        }

        HSSFWorkbook wb = null;
        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/" + reportName + ".xls");
        XLSTransformer transformer = new XLSTransformer();

        try {

            wb = transformer.transformXLS(is, beans);

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();

            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + "" + DateTimeUtils.getInstance().thDateNow(DateTimeUtils.DATE_TIME_FORMAT) + ".xls\"");

            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
            
            ctx.responseComplete();
        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.getString("error", ex.getMessage()));
            logger.error("cannot export report", ex);

        }finally{
        
        }
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        InputStream is = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(is, "application/vnd.ms-excel ", "report.xlsx");
        return file;
    }

    /**
     * @return the viewReportResult
     */
    public List<ViewReportStatus> getViewReportResult() {
        return viewReportResult;
    }

    /**
     * @param viewReportResult the viewReportResult to set
     */
    public void setViewReportResult(List<ViewReportStatus> viewReportResult) {
        this.viewReportResult = viewReportResult;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save() {
    }

    @Override
    public void edit() {
    }

    @Override
    public void addReportDetail(ActionEvent actionEvent) {
    }

    @Override
    public void onEdit(RowEditEvent event) {
    }

    @Override
    public void onCancel(RowEditEvent event) {
    }

    @Override
    public void initReportDetail() {
    }

    @Override
    public void fileXLSDownload() {
    }

    /**
     * @return the reportCriteria
     */
    public ReportCriteria getReportCriteria() {
        return reportCriteria;
    }

    /**
     * @param reportCriteria the reportCriteria to set
     */
    public void setReportCriteria(ReportCriteria reportCriteria) {
        this.reportCriteria = reportCriteria;
    }

    /**
     * @return the lazyModel
     */
    public LazyDataModel<ViewReportStatus> getLazyModel() {
        return lazyModel;
    }

    /**
     * @param lazyModel the lazyModel to set
     */
    public void setLazyModel(LazyDataModel<ViewReportStatus> lazyModel) {
        this.lazyModel = lazyModel;
    }

    private void initCriteria() {
        reportCriteria = new ReportCriteria();
    }

    @Override
    public void onDelete(Object object) {
    }
}
