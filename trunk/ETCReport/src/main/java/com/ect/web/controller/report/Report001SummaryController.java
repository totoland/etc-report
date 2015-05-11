/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.common.dao.GennericDao;
import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.EctGroupLvl;
import com.ect.db.entity.ViewUser;
import com.ect.db.report.entity.Report001;
import com.ect.db.report.entity.ViewReport001Summary;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.form.BaseFormReportController;
import com.ect.web.controller.model.LazyViewReport001SummaryImpl;
import com.ect.web.service.UserService;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
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
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class Report001SummaryController extends BaseFormReportController {

    private static final long serialVersionUID = 8451238753520170431L;
    private static final Logger logger = LoggerFactory.getLogger(Report001SummaryController.class);

    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    private List<ViewReportStatus> viewReportResult;
    private ReportCriteria reportCriteria;
    private LazyDataModel<ViewReport001Summary> lazyModel;

    @Autowired
    private GennericDao<Report001> gennericDao;

    private List<String> selectedGroup = new ArrayList<>();

    @PostConstruct
    public void init() {

        initCriteria();

    }

    public void search() {

        logger.trace("Search!!");

        if (getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.CENTER.getLevel() && 
                getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.SYSTEM_ADMIN.getLevel()) {
            reportCriteria.setUserGroupId(String.valueOf(getUserAuthen().getUserGroupId()));
        }

        reportCriteria.setUserGroupLvl(String.valueOf(getUserAuthen().getUserGroupLvl()));
        reportCriteria.setGroupIds(selectedGroup);
        reportCriteria.setStatus(EctFlowStatus.FlowStatus.APPROVED.getStatus() + "");

        logger.trace("Criteria : {}", reportCriteria);

        final Integer count = reportService.countReport001ByCriteria(reportCriteria);

        logger.trace("count : {}", count);

        if (count != null || count > 0) {

            final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                    .findComponent(":form1:rptPreSendList2");
            dataTable.setFirst(0);

            LazyViewReport001SummaryImpl reportModel = new LazyViewReport001SummaryImpl();
            reportModel.setRowCount(count);
            reportModel.setReportService(reportService);
            reportModel.setReportCriteria(reportCriteria);

            lazyModel = reportModel;

        }

    }

    @Override
    public void resetForm() {
        initCriteria();
        setLazyModel(null);
    }

    @Override
    public void fileXLSDownload() {

        logger.trace("Select report : {}", reportCriteria);

        String month = dropdownFactory.getMonthName(reportCriteria.getMonth());
        String year = reportCriteria.getYear();
        String depName = "";
        String createdDate = DateTimeUtils.getInstance().thDate(new Date(), DateTimeUtils.DISPLAY_DATETIME_FORMAT);
        String reportName = "";

        ViewUser viewUser = userService.findByUserId(getUserAuthen().getUserId());

        if (viewUser != null) {

            depName = viewUser.getUserGroupName();

        }

        Map<String, Object> beans = new HashMap<>();
        beans.put("month", StringUtils.isBlank(month)?"ทั้งหมด":month);
        beans.put("year", year);
        beans.put("depName", depName);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", getUserAuthen().getFname() + " " + getUserAuthen().getLname());

        reportName = "REPORT_001_SUMMARY";

        reportCriteria.setUserGroupLvl(String.valueOf(getUserAuthen().getUserGroupLvl()));
        reportCriteria.setGroupIds(selectedGroup);
        reportCriteria.setStatus(EctFlowStatus.FlowStatus.APPROVED.getStatus() + "");
        reportCriteria.setMaxRow(60000);

        logger.trace("Criteria : {}", reportCriteria);

        List<ViewReport001Summary> report001Summarys = reportService.findReport001ByCriteria(reportCriteria);

        beans.put("details", report001Summarys);
        beans.put("sum", getSummary(report001Summarys));

        Workbook wb = null;
        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/" + reportName + ".xls");
        XLSTransformer transformer = new XLSTransformer();

        try {

            wb = transformer.transformXLS(is, beans);

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();

            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + "" + DateTimeUtils.getInstance().thDateNow(DateTimeUtils.DATE_TIME_FORMAT) + ".xls\"");

            try (ServletOutputStream out = response.getOutputStream()) {
                wb.write(out);
                out.flush();
            }

            ctx.responseComplete();
        } catch (ParsePropertyException | IOException | InvalidFormatException ex) {

            JsfUtil.alertJavaScript(MessageUtils.getString("error", ex.getMessage()));
            logger.error("cannot export report", ex);

        } finally {
        }
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        InputStream is = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(is, "application/vnd.ms-excel ", "report.xlsx");
        return file;
    }

    public boolean canSearchUserGroup() {
        return isAdmin() || isCenter();
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
    public LazyDataModel<ViewReport001Summary> getLazyModel() {
        return lazyModel;
    }

    /**
     * @param lazyModel the lazyModel to set
     */
    public void setLazyModel(LazyDataModel<ViewReport001Summary> lazyModel) {
        this.lazyModel = lazyModel;
    }

    private void initCriteria() {
        selectedGroup.clear();
        reportCriteria = new ReportCriteria();
    }

    @Override
    public void onDelete(Object object) {
    }

    public List<String> getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(List<String> selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    /**
     * @return the gennericDao
     */
    public GennericDao<Report001> getGennericDao() {
        return gennericDao;
    }

    /**
     * @param gennericDao the gennericDao to set
     */
    public void setGennericDao(GennericDao<Report001> gennericDao) {
        this.gennericDao = gennericDao;
    }

    private ViewReport001Summary getSummary(List<ViewReport001Summary> report001Summarys) {
        ViewReport001Summary summary = new ViewReport001Summary();
        summary.setGoalAmount(0L);
        summary.setGoalResult(0L);
        summary.setBudgetSet(new BigDecimal(BigInteger.ZERO,2));
        summary.setBudgetReal(new BigDecimal(BigInteger.ZERO,2));
        long pass = 0;
        long notPass = 0;

        for (ViewReport001Summary viewReport001Summary : report001Summarys) {
            summary.setGoalAmount(summary.getGoalAmount()+NumberUtils.convertNUllToZero(viewReport001Summary.getGoalAmount()));
            summary.setGoalResult(summary.getGoalResult()+NumberUtils.convertNUllToZero(viewReport001Summary.getGoalResult()));
            summary.setBudgetSet(summary.getBudgetSet().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetSet())));
            summary.setBudgetReal(summary.getBudgetReal().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetReal())));
            summary.setIsPass(viewReport001Summary.isIsPass());
            
            if(summary!=null && summary.isIsPass()){
                pass = pass+1;
            }else{
                notPass = notPass+1;
            }
        }
        
        summary.setPercenBudget(summary.getBudgetReal().multiply(new BigDecimal(100)).divide(summary.getBudgetSet(),2,RoundingMode.HALF_UP));
        summary.setSumResult("ผ่าน : "+pass + " ไม่ผ่าน : "+notPass);

        logger.trace("summary : {}",summary);
        
        return summary;
    }
}
