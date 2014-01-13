/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.welcome;

import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class WelcomeController extends BaseController{
    private static final long serialVersionUID = -7718511630197731207L;

    @ManagedProperty(value = "#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    
    private String link;
    
    @Override
    public void resetForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void redirect(){
    
        if("REPORT_002".equalsIgnoreCase(link)){
            executeJavaScript("setTimeout(function(){window.location='/ETCReport/pages/user/formReport002.xhtml';},100);");
            return;
        }
        executeJavaScript("setTimeout(function(){window.location='/ETCReport/pages/user/formRoleUser.xhtml';},100);");
        
    }
    /**
     * @return the dropdownFactory
     */
    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    /**
     * @param dropdownFactory the dropdownFactory to set
     */
    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
}
