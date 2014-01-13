/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller;

import com.ect.db.entity.EctUser;
import com.ect.web.utils.MessageUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Totoland
 */
public abstract class BaseController implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    private static final long serialVersionUID = -3424239608725799082L;
    protected RequestContext context = RequestContext.getCurrentInstance();

    public String getMessage(String key) {
        return MessageUtils.getResourceBundleString(key);
    }

    public void addMessages(FacesMessage.Severity severity, String message, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, detail));
    }

    public void addMessages(String clientId, FacesMessage.Severity severity, String message, String detail) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severity, message, detail));
    }

    public void addInfo(String message) {
        addMessages(FacesMessage.SEVERITY_INFO, message, "");
    }

    public void addError(String clientId, String message) {
        addMessages(clientId, FacesMessage.SEVERITY_ERROR, message, "");
    }

    public void addError(String message) {
        addMessages(FacesMessage.SEVERITY_ERROR, message, "");
    }

    public void executeJavaScript(String function) {
        RequestContext.getCurrentInstance().execute(function);
    }

    public void addCallbackParam(String key, Object value) {
        RequestContext.getCurrentInstance().addCallbackParam(key, value);
    }

    /***
     * Close Popup
     * @param dialogId 
     */
    public void closeDialog(String dialogId) {
        executeJavaScript(dialogId + ".hide()");
    }

    public void openDialog(String dialogId) {
        executeJavaScript(dialogId + ".show()");
    }

    public void consoleLog(String log) {
        executeJavaScript("logger('" + log + "');");
    }

    public abstract void resetForm();

    public Object getSessionBean(String sessionBeanName) {
        return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, sessionBeanName);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public EctUser getUserAuthen() {
        return (EctUser) getSessionBean("userAuthen");
    }

    public void redirectPage(String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + url);
        } catch (IOException ex) {
            logger.error("Cannot Redirect page : {} ", url, ex);
        }
    }

    public void clearAllMessage() {

        if (FacesContext.getCurrentInstance().getMessages().hasNext()) {
            FacesContext.getCurrentInstance().getMessages().remove();
        }

    }

    public void updateCliend(String updateId) {
        context.update(updateId);
    }
}
