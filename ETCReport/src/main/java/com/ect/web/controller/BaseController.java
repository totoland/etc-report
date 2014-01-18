/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller;

import com.ect.db.entity.EctFlowStatus.FlowStatus;
import com.ect.db.entity.ViewUser;
import com.ect.web.utils.MessageUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
        return (ViewUser) getSessionBean("userAuthen");
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

    public boolean canApprove(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

        logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if (FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 4;

        } else if (FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 3;

        } else if (FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 2;

        } else if (FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 0;

        } else if (FlowStatus.REJECT.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() != 4;

        } else if (FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        }

        return false;

    }

    public boolean canReject(Integer flowStatusId) {

        ViewUser user = getUserAuthen();

        logger.trace("User LVL {} FlowStatus {}", user.getUserGroupLvl(), flowStatusId);

        if (FlowStatus.STEP_1.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 4;

        } else if (FlowStatus.STEP_2.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 3;

        } else if (FlowStatus.STEP_3.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() < 2;

        } else if (FlowStatus.APPROVED.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 0;

        } else if (FlowStatus.REJECT.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() != 4;

        } else if (FlowStatus.DRAFF.getStatus() == flowStatusId) {

            return user.getUserGroupLvl() == 4;

        }

        return false;

    }

    public boolean canEdit(Integer createdUserGroup) {

        ViewUser user = getUserAuthen();

        logger.trace("Login UserGroup {} Created UserGroup {}",user.getUserGroupId(),createdUserGroup);
        
        if (user.getUserGroupId() != null && createdUserGroup.intValue() == user.getUserGroupId().intValue()) {
            return true;
        }

        return false;
    }
}
