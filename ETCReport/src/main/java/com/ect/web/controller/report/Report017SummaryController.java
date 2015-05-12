/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportExpression017;
import com.ect.web.controller.form.BaseFormReportController;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.NumberUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class Report017SummaryController extends BaseFormReportController {
    
    private static final long serialVersionUID = -1582274513103355961L;
    
    private static final Logger logger = LoggerFactory.getLogger(Report017SummaryController.class);

    private List<ViewReportExpression017> viewReportResult;
    private ReportCriteria reportCriteria;

    private List<String> selectedGroup = new ArrayList<>();

    @PostConstruct
    public void init() {

        initCriteria();

    }

    public void search() {

        logger.trace("Search by : {}",reportCriteria);
        
        viewReportResult = reportService.findReportExpression017ByCriteria(reportCriteria);
        
        logger.trace("viewReportResult : {}",viewReportResult);
    }

    @Override
    public void resetForm() {
        initCriteria();
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        InputStream is = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
                .getResourceAsStream("/pages/xls/report.xlsx");
        file = new DefaultStreamedContent(is, "application/vnd.ms-excel ", "report.xlsx");
        return file;
    }

    public boolean canSearchUserGroup() {
        return isAdmin() || isCenter();
    }

    /**
     * @return the viewReportResult
     */
    public List<ViewReportExpression017> getViewReportResult() {
        return viewReportResult;
    }

    /**
     * @param viewReportResult the viewReportResult to set
     */
    public void setViewReportResult(List<ViewReportExpression017> viewReportResult) {
        this.viewReportResult = viewReportResult;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
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

    @Override
    public void fileXLSDownload() {
        String month = dropdownFactory.getMonthName(reportCriteria.getMonth());
        String year = reportCriteria.getYear();
        String createdDate = DateTimeUtils.getInstance().thDate(new Date(), DateTimeUtils.DISPLAY_DATETIME_FORMAT);
        String reportName = REPORT_EXPRESSION_017;
        
        Map<String, Object> beans = new HashMap<>();
        beans.put("month", month);
        beans.put("year", year);
        beans.put("createdDate", createdDate);
        beans.put("createdUser", getUserAuthen().getFname() +"  "+ getUserAuthen().getLname());
        
        beans.put("details", viewReportResult);
        
        ViewReportExpression017 sumDetail = getSum();
        
        beans.put("sum", sumDetail);
        
        logger.trace("beans : {}",beans);
        
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

    private ViewReportExpression017 getSum() {
        ViewReportExpression017 sumDetail = new ViewReportExpression017();

        sumDetail.setAllStory(0);
        sumDetail.setRedCard(0);
        sumDetail.setYellowCard(0);
        sumDetail.setYellowCardCriminalCase(0);
        sumDetail.setResetCounter(0);
        sumDetail.setCriminalCase(0);
        sumDetail.setAdding(0);
        sumDetail.setRequestReceived(0);
        sumDetail.setRequestNoReceived(0);
        sumDetail.setWithdrawnRequest(0);
        sumDetail.setEct(0);

        for (int i = 0; i < viewReportResult.size(); i++) {
            viewReportResult.get(i).setKey(i + 1);

            sumDetail.setAllStory(sumDetail.getAllStory() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getAllStory()));
            sumDetail.setRedCard(sumDetail.getRedCard() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getRedCard()));
            sumDetail.setYellowCard(sumDetail.getYellowCard() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getYellowCard()));
            sumDetail.setYellowCardCriminalCase(sumDetail.getYellowCardCriminalCase() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getYellowCardCriminalCase()));
            sumDetail.setResetCounter(sumDetail.getResetCounter() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getResetCounter()));
            sumDetail.setCriminalCase(sumDetail.getCriminalCase() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getCriminalCase()));
            sumDetail.setAdding(sumDetail.getAdding() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getAdding()));
            sumDetail.setRequestReceived(sumDetail.getRequestReceived() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getRequestReceived()));
            sumDetail.setRequestNoReceived(sumDetail.getRequestNoReceived() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getRequestNoReceived()));
            sumDetail.setWithdrawnRequest(sumDetail.getWithdrawnRequest() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getWithdrawnRequest()));
            sumDetail.setEct(sumDetail.getEct() + NumberUtils.convertNUllToZero(viewReportResult.get(i).getEct()));
        }
        
        return sumDetail;
    }
}
