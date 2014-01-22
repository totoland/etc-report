/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.report;

import com.ect.db.bean.ReportCriteria;
import com.ect.db.report.entity.ViewReportStatus;
import com.ect.web.controller.BaseController;
import com.ect.web.service.ReportService;
import com.ect.web.utils.DateTimeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class AllReportController extends BaseController {

    private static final long serialVersionUID = 8451238753520170431L;
    private static Logger logger = LoggerFactory.getLogger(AllReportController.class);

    @ManagedProperty(value = "#{reportService}")
    private ReportService reportService;
    
    private List<ViewReportStatus>viewReportResult;
    
    public void search(){
    
        logger.trace("Search!!");
        
        viewReportResult = reportService.findByCriteria(new ReportCriteria());
        
    }
    
    @Override
    public void resetForm() {
        
    }

    public void fileXLSDownload() {

        HSSFWorkbook wb = null;
        //InputStream is = MemImportXLSController.class.getResourceAsStream("errorXLS.xls");
        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/report.xls");
        XLSTransformer transformer = new XLSTransformer();
        Map<String, Object> beans = new HashMap<>();

        wb = transformer.transformXLS(is, beans);

        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        
        HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"errorXLS" + DateTimeUtils.getInstance().thDateNow(DateTimeUtils.DATE_TIME_FORMAT) + ".xls\"");

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
     * @return the reportService
     */
    public ReportService getReportService() {
        return reportService;
    }

    /**
     * @param reportService the reportService to set
     */
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
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
}
