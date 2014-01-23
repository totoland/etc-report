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
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.form.BaseFormReportController;
import com.ect.web.service.UserService;
import com.ect.web.utils.DateTimeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AllReportController extends BaseFormReportController {

    private static final long serialVersionUID = 8451238753520170431L;
    private static Logger logger = LoggerFactory.getLogger(AllReportController.class);
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    private List<ViewReportStatus> viewReportResult;

    public void search() {

        logger.trace("Search!!");

        viewReportResult = reportService.findByCriteria(new ReportCriteria());

    }

    @Override
    public void resetForm() {
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
        beans.put("createdUser",viewReportStatus.getCreatedUserFullName());
        
        if (viewReportStatus.getReportCode().equals(REPORT_001)) {

            reportName = REPORT_001;
            
            Report001 report001 = reportService.findByReport001ById(viewReportStatus.getReportId());

            if (report001 == null || report001.getReport001DetailList() == null || report001.getReport001DetailList().isEmpty()) {
            
                logger.warn("Cannot find Report001 by Id : {}",viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report001Detail>());
                
            } else {
                
                beans.put("details", report001.getReport001DetailList());
                
            }
        } else if(viewReportStatus.getReportCode().equals(REPORT_002)){
        
            reportName = REPORT_002;
            
            Report002 report002 = reportService.findByReport002ById(viewReportStatus.getReportId());

            if (report002 == null || report002.getReport002DetailList() == null || report002.getReport002DetailList().isEmpty()) {
            
                logger.warn("Cannot find Report002 by Id : {}",viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report002Detail>());
                
            } else {
                
                beans.put("details", report002.getReport002DetailList());
                
            }
            
        } else if(viewReportStatus.getReportCode().equals(REPORT_003)){
        
            reportName = REPORT_003;
            
            Report003 report003 = reportService.findByReport003ById(viewReportStatus.getReportId());

            if (report003 == null || report003.getReport003DetailList() == null || report003.getReport003DetailList().isEmpty()) {
            
                logger.warn("Cannot find Report003 by Id : {}",viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report003Detail>());
                
            } else {
                
                beans.put("details", report003.getReport003DetailList());
                
            }
            
        } else if(viewReportStatus.getReportCode().equals(REPORT_004)){
        
            reportName = REPORT_004;
            
            Report004 report004 = reportService.findByReport004ById(viewReportStatus.getReportId());

            if (report004 == null || report004.getReport004DetailList() == null || report004.getReport004DetailList().isEmpty()) {
            
                logger.warn("Cannot find Report004 by Id : {}",viewReportStatus.getReportId());
                beans.put("details", new ArrayList<Report004Detail>());
                
            } else {
                
                beans.put("details", report004.getReport004DetailList());
                
            }
            
        }

        HSSFWorkbook wb = null;
        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/"+reportName+".xls");
        XLSTransformer transformer = new XLSTransformer();


        wb = transformer.transformXLS(is, beans);

        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();

        HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""+reportName+"" + DateTimeUtils.getInstance().thDateNow(DateTimeUtils.DATE_TIME_FORMAT) + ".xls\"");

        try {
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException ioe) {
            //whatever
        }
        ctx.responseComplete();
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
}
