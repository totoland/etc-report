/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller;

import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.ViewUser;
import com.ect.web.controller.exception.SessionExpireException;
import com.ect.web.controller.login.LoginController;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.MessageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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

    /**
     * *
     * Close Popup
     *
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

    public ViewUser getUserAuthen() {
        ViewUser viewUser = (ViewUser) getSessionBean("userAuthen");
        return viewUser;
    }

    public void sendError(FacesContext faces, int code, String message) {
        try {
            faces.getExternalContext().setResponseStatus(code);
            faces.getExternalContext().getRequestMap().put("javax.servlet.error.message", message);
            ViewHandler views = faces.getApplication().getViewHandler();
            String template = "/pages/error/" + code + ".xhtml";
            UIViewRoot view = views.createView(faces, template);
            faces.setViewRoot(view);
            views.getViewDeclarationLanguage(faces, template).
                    buildView(faces, view);
            views.renderView(faces, view);
            faces.responseComplete();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void redirectPage(String url) {
        executeJavaScript("window.location='" + url + "'");
    }

    public void clearAllMessage() {

        if (FacesContext.getCurrentInstance().getMessages().hasNext()) {
            FacesContext.getCurrentInstance().getMessages().remove();
        }

    }

    public void updateCliend(String updateId) {
        context.update(updateId);
    }

    public void disablePopup(String id) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Dialog panel = (Dialog) ctx.getViewRoot().findComponent(id);

        if (panel == null) {
            return;
        }

        disableAll(panel.getChildren());
    }

    public void disableAll(List<UIComponent> components) {

        for (UIComponent component : components) {

            if (component instanceof HtmlInputText) {
                ((HtmlInputText) component).setDisabled(true);
            }
            if (component instanceof InputText) {
                ((InputText) component).setDisabled(true);
            }
            if (component instanceof SelectOneMenu) {
                ((SelectOneMenu) component).setDisabled(true);
            }

            disableAll(component.getChildren());
        }
    }

    public void openIframe(String url) {

        if (url.indexOf("?") == -1) {
            url += "?q=q";
        }

        executeJavaScript("dialogEdit.show();");
        executeJavaScript("$(\"#divFrmEdit\").html(\"<iframe src='" + url + "&random=\" + Math.random() + \"'  scrolling='no' style='border: none;width: 100%;height:500px'></iframe>\");");
    }

    public String getParameter(String param) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(param);
    }

    public String dateTH(Date date,String format) {
        if(format == null || format.isEmpty()){
            return DateTimeUtils.getInstance().thDate(date, DateTimeUtils.DISPLAY_DATETIME_FORMAT);
        }
        return DateTimeUtils.getInstance().thDate(date, format);

    }
}
