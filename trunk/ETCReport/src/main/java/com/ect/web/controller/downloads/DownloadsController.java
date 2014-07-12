/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.downloads;

import com.ect.web.controller.BaseController;
import com.ect.web.utils.JsfUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class DownloadsController extends BaseController {

    private static final long serialVersionUID = 9017308346831054856L;

    private StreamedContent fileFirefox;
    private StreamedContent fileChrome;

    private final static String PATH_DOWNLOAD = "path.file.download";
    
    private final static Logger LOGGER = LoggerFactory.getLogger(DownloadsController.class);
    
    @PostConstruct
    public void init() {
        JsfUtil.addSuccessMessage("เพื่อให้การใช้งานและการแสดงผลของเว็บไซต์สมบูรณ์ กรุณา ดาวน์โหลด บราวเซอร์ที่ต้องการได้จากลิงก์ด้านล่าง ดังนี้");
    }

    @Override
    public void resetForm() {

    }

    public StreamedContent fileDownload(String browser) throws IOException {
        
        LOGGER.debug("fileDownload : {}",getPathDownload()+browser);
        
        InputStream stream = new FileInputStream(new File(getPathDownload()+browser));
        return new DefaultStreamedContent(stream, "application/exe", browser);
        
    }


    public String getPathDownload() {
        Locale locale = new Locale("en", "US");

        ResourceBundle labels = ResourceBundle.getBundle("conf", locale);
        
        return labels.getString(PATH_DOWNLOAD);
    }

    /**
     * @return the fileFirefox
     */
    public StreamedContent getFileFirefox() {
        try {
            fileFirefox = fileDownload("FirefoxSetup.exe");
        } catch (IOException ex) {
            
        }
        return fileFirefox;
    }

    /**
     * @return the fileChrome
     */
    public StreamedContent getFileChrome() {
        try {
            fileChrome = fileDownload("ChromeStandaloneSetup.exe");
        } catch (IOException ex) {
            
        }
        return fileChrome;
    }

}
