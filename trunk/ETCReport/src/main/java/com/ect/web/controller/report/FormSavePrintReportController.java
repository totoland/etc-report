/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.web.controller.report;

import com.ect.db.common.entity.DropDownList;
import com.ect.web.controller.BaseController;
import com.ect.web.service.CommonService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Totoland
 */
@ManagedBean
@ViewScoped
public class FormSavePrintReportController extends BaseController{
    private static final long serialVersionUID = -5579656927495884037L;
    
    private static Logger logger = LoggerFactory.getLogger(FormSavePrintReportController.class);
    
    @ManagedProperty(value="#{commonService}")
    private CommonService commonService;
    
    private List<DropDownList>listDropDown;
    
    private String listDropDownValue;
    
    @PostConstruct
    public void init(){
        logger.info("initPage");
        
        listDropDown = commonService.getDropdownList(new DropDownList());
        
        for(DropDownList dll : getListDropDown()){
            logger.debug(dll.toString());
        }
    }

    /**
     * @return the commonService
     */
    public CommonService getCommonService() {
        return commonService;
    }

    /**
     * @param commonService the commonService to set
     */
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * @return the listDropDown
     */
    public List<DropDownList> getListDropDown() {
        return listDropDown;
    }

    /**
     * @param listDropDown the listDropDown to set
     */
    public void setListDropDown(List<DropDownList> listDropDown) {
        this.listDropDown = listDropDown;
    }

    /**
     * @return the listDropDownValue
     */
    public String getListDropDownValue() {
        return listDropDownValue;
    }

    /**
     * @param listDropDownValue the listDropDownValue to set
     */
    public void setListDropDownValue(String listDropDownValue) {
        this.listDropDownValue = listDropDownValue;
    }

}
