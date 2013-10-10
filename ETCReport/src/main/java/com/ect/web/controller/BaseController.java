/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.web.controller;

import com.ect.web.utils.MessageUtils;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Totoland
 */
public abstract class BaseController implements Serializable{
    private static final long serialVersionUID = -3424239608725799082L;
    
    public void addMessages(FacesMessage.Severity severity,String message,String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity,message, detail));  
    }
    
    public void addInfo(String message) {  
         addMessages(FacesMessage.SEVERITY_INFO, message, "");
    }
    
    public void addError(String message) {  
         addMessages(FacesMessage.SEVERITY_ERROR, message, "");
    }
    
    public void executeJavaScript(String function){
        RequestContext.getCurrentInstance().execute(function);
    }
    
    public void addCallbackParam(String key,Object value){
        RequestContext.getCurrentInstance().addCallbackParam(key,value);
    }
    
    public void consoleLog(String log){
        executeJavaScript("logger('"+log+"');");
    }
    
    public abstract void resetForm();
}
