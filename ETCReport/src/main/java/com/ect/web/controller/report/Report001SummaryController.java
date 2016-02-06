/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.entity.EctFlowStatus;
import com.ect.db.entity.EctGroupLvl;
import com.ect.db.entity.ViewReport001SummaryDetail;
import com.ect.db.entity.ViewUser;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
    private List<ViewReport001SummaryDetail> lazyModelDetail;

    private List<String> selectedGroup = new ArrayList<>();

    private boolean viewDetail;

    @PostConstruct
    public void init() {

        initCriteria();

    }

    public void search() {

        logger.trace("Search!!");
        viewDetail = false;
        if (getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.CENTER.getLevel()
                && getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.SYSTEM_ADMIN.getLevel()) {
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

    public void searchDetail() {
        logger.trace("SearchDetail!!");
        viewDetail = true;
        if (getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.CENTER.getLevel()
                && getUserAuthen().getUserGroupLvl() != EctGroupLvl.GroupLevel.SYSTEM_ADMIN.getLevel()) {
            reportCriteria.setUserGroupId(String.valueOf(getUserAuthen().getUserGroupId()));
        }

        reportCriteria.setUserGroupLvl(String.valueOf(getUserAuthen().getUserGroupLvl()));
        reportCriteria.setGroupIds(selectedGroup);
        reportCriteria.setStatus(EctFlowStatus.FlowStatus.APPROVED.getStatus() + "");

        logger.trace("Criteria : {}", reportCriteria);

        final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                .findComponent(":form1:rptPreSendListDetail");
        dataTable.setFirst(0);

        lazyModelDetail = reportService.findReport001DetailByCriteria(reportCriteria);

        groupReportModel(lazyModelDetail);
    }

    @Override
    public void resetForm() {
        initCriteria();
        setLazyModel(null);
    }

    @Override
    public void fileXLSDownload() {
        
        if(isViewDetail()){
            fileXLSDetailDownload();
            return;
        }
        
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
        beans.put("month", StringUtils.isBlank(month) ? "ทั้งหมด" : month);
        beans.put("year", year);
        beans.put("depName", depName);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", getUserAuthen().getFname() + " " + getUserAuthen().getLname());

        reportName = REPORT_001_SUMMARY;

        reportCriteria.setUserGroupLvl(String.valueOf(getUserAuthen().getUserGroupLvl()));
        reportCriteria.setGroupIds(selectedGroup);
        reportCriteria.setStatus(EctFlowStatus.FlowStatus.APPROVED.getStatus() + "");
        reportCriteria.setMaxRow(60000);

        logger.trace("Criteria : {}", reportCriteria);

        List<ViewReport001Summary> report001Summarys = reportService.findReport001ByCriteria(reportCriteria);

        beans.put("details", report001Summarys);
        beans.put("sum", getSummary(report001Summarys));

        
        InputStream is = FacesContext.getCurrentInstance().getExternalContext()
                .getResourceAsStream("/template/" + reportName + ".xls");
        
        XLSTransformer transformer = new XLSTransformer();
        
        Workbook wb = null;
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
    
    public void fileXLSDetailDownload() {
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
        beans.put("month", StringUtils.isBlank(month) ? "ทั้งหมด" : month);
        beans.put("year", year);
        beans.put("depName", depName);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", getUserAuthen().getFname() + " " + getUserAuthen().getLname());

        reportName = REPORT_001_SUMMARY_DETAIL;

        reportCriteria.setUserGroupLvl(String.valueOf(getUserAuthen().getUserGroupLvl()));
        reportCriteria.setGroupIds(selectedGroup);
        reportCriteria.setStatus(EctFlowStatus.FlowStatus.APPROVED.getStatus() + "");
        reportCriteria.setMaxRow(60000);

        logger.trace("Criteria : {}", reportCriteria);

        beans.put("details", this.lazyModelDetail);
        beans.put("sum", getSummaryDetail(this.lazyModelDetail));

        
        InputStream is = FacesContext.getCurrentInstance().getExternalContext()
                .getResourceAsStream("/template/" + reportName + ".xls");
        
        XLSTransformer transformer = new XLSTransformer();
        
        Workbook wb = null;
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
        viewDetail = false;
        selectedGroup.clear();
        reportCriteria = new ReportCriteria();
        reportCriteria.setFiscalYear(true);
        reportCriteria.setStrategic("-1");
        reportCriteria.setSubStrategic("-1");
        reportCriteria.setPlan("-1");
        reportCriteria.setProject("-1");
        reportCriteria.setActivity("-1");
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

    private ViewReport001Summary getSummary(List<ViewReport001Summary> report001Summarys) {
        ViewReport001Summary summary = new ViewReport001Summary();
        summary.setGoalAmount(0L);
        summary.setGoalResult(0L);
        summary.setBudgetSet(new BigDecimal(BigInteger.ZERO, 2));
        summary.setBudgetReal(new BigDecimal(BigInteger.ZERO, 2));
        long pass = 0;
        long notPass = 0;

        for (ViewReport001Summary viewReport001Summary : report001Summarys) {
            summary.setGoalAmount(summary.getGoalAmount() + NumberUtils.convertNUllToZero(viewReport001Summary.getGoalAmount()));
            summary.setGoalResult(summary.getGoalResult() + NumberUtils.convertNUllToZero(viewReport001Summary.getGoalResult()));
            summary.setBudgetSet(summary.getBudgetSet().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetSet())));
            summary.setBudgetReal(summary.getBudgetReal().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetReal())));
            summary.setIsPass(viewReport001Summary.isIsPass());

            if (summary != null && summary.isIsPass()) {
                pass = pass + 1;
            } else {
                notPass = notPass + 1;
            }
        }

        summary.setPercenBudget(summary.getBudgetReal().multiply(new BigDecimal(100)).divide(summary.getBudgetSet(), 2, RoundingMode.HALF_UP));
        summary.setSumResult("ผ่าน : " + pass + " ไม่ผ่าน : " + notPass);

        logger.trace("summary : {}", summary);

        return summary;
    }
    
    private ViewReport001SummaryDetail getSummaryDetail(List<ViewReport001SummaryDetail> report001Summarys) {
        ViewReport001SummaryDetail summary = new ViewReport001SummaryDetail();
        summary.setBudgetSet(new BigDecimal(BigInteger.ZERO, 2));
        summary.setBudgetReal(new BigDecimal(BigInteger.ZERO, 2));
        long pass = 0;
        long notPass = 0;

        for (ViewReport001SummaryDetail viewReport001Summary : report001Summarys) {
            summary.setBudgetSet(summary.getBudgetSet().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetSet())));
            summary.setBudgetReal(summary.getBudgetReal().add(NumberUtils.convertNUllToZero(viewReport001Summary.getBudgetReal())));
        }

        return summary;
    }

    public BigDecimal setPercen(BigDecimal a, BigDecimal b) {
        
        if(!NumberUtils.isNumber(a) || !NumberUtils.isNumber(b)){
            return BigDecimal.ZERO;
        }
        
        if (a.intValue() == 0 || b.intValue() == 0) {
            return BigDecimal.ZERO;
        }
        
        try{
            return a.multiply(new BigDecimal(100)).divide(b, 2, RoundingMode.HALF_UP);
        }catch(Exception ex){
            return BigDecimal.ZERO;
        }
    }

    /**
     * @return the viewDetail
     */
    public boolean isViewDetail() {
        return viewDetail;
    }

    /**
     * @param viewDetail the viewDetail to set
     */
    public void setViewDetail(boolean viewDetail) {
        this.viewDetail = viewDetail;
    }

    /**
     * @return the lazyModelDetail
     */
    public List<ViewReport001SummaryDetail> getLazyModelDetail() {
        return lazyModelDetail;
    }

    /**
     * @param lazyModelDetail the lazyModelDetail to set
     */
    public void setLazyModelDetail(List<ViewReport001SummaryDetail> lazyModelDetail) {
        this.lazyModelDetail = lazyModelDetail;
    }

    private void groupReportModel(List<ViewReport001SummaryDetail> lazyModelDetail) {

        Set<Integer> mapStrategicId = new HashSet<>();
        Set<String> mapStrategicIdProjectId = new HashSet<>();

        for (int i = 0; i < lazyModelDetail.size(); i++) {
            if (!mapStrategicId.contains(lazyModelDetail.get(i).getStrategicId())) {
                mapStrategicId.add(lazyModelDetail.get(i).getStrategicId());
                lazyModelDetail.get(i).setStrategicName(lazyModelDetail.get(i).getStrategicName());
            } else {
                lazyModelDetail.get(i).setStrategicName("");
            }

            String strategicIdProjectId = lazyModelDetail.get(i).getStrategicId() + "_" + lazyModelDetail.get(i).getProjectId();

            if (!mapStrategicIdProjectId.contains(strategicIdProjectId)) {
                mapStrategicIdProjectId.add(strategicIdProjectId);
                lazyModelDetail.get(i).setProjectName(lazyModelDetail.get(i).getProjectName());
            } else {
                lazyModelDetail.get(i).setProjectName("");
            }
        }

        logger.debug("mapStrategicId : {}", mapStrategicId);
        
        List<ViewReport001SummaryDetail> newReport = new ArrayList();
        
        Iterator<Integer> iter = mapStrategicId.iterator();
        while (iter.hasNext()) {
            Integer stractegicId = iter.next();
            BigDecimal sumBudgetSet = new BigDecimal(0);
            BigDecimal sumBudgetReal = new BigDecimal(0);
            
            for(ViewReport001SummaryDetail detail : this.lazyModelDetail){
                if(Objects.equals(detail.getStrategicId(), stractegicId)){
                    sumBudgetSet = sumBudgetSet.add(detail.getBudgetSet());
                    sumBudgetReal = sumBudgetReal.add(detail.getBudgetReal());
                    newReport.add(detail);
                }
            }
            
            ViewReport001SummaryDetail sumOfRec = new ViewReport001SummaryDetail();
            sumOfRec.setBudgetSet(sumBudgetSet);
            sumOfRec.setBudgetReal(sumBudgetReal);
            sumOfRec.setActivityName("รวม");
            
            newReport.add(sumOfRec);
        }

        this.lazyModelDetail = new ArrayList(newReport);
    }
}
