/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.login;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.entity.ViewUser;
import com.ect.web.controller.BaseController;
import com.ect.web.utils.ECTUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author Totoland
 */
@ManagedBean
@SessionScoped
public class LoginController extends BaseController {

    private static final long serialVersionUID = 3291979904925054393L;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private String userName;
    private String passWord;
    @ManagedProperty(value = "#{authenDao}")
    private AuthenDao authenDao;
    private ViewUser loginUser;
    private boolean loggedIn = false;

    @PostConstruct
    public void init() {

        logger.info("init");
    }

    public void loginProcess() {

        MDC.put("reqId", ECTUtils.generateToken());

        logger.info("loginProcess!!");

        logger.info("userName : {} , passWord : {}", userName, "********");

        if (!validateLongin()) {
            addError("loggin fial!!");
            return;
        }

        /**
         * *
         * Authen Login
         */
        try {

            String nPassWord = ECTUtils.encrypt(passWord);

            loginUser = getAuthenDao().loginUser(userName, nPassWord);

        } catch (Exception ex) {

            logger.error("Cannot Authen : ", ex);
            return;
        }

        if (loginUser == null) {

            addError(MessageUtils.getResourceBundleString("login.authen.fail"));

            logger.warn("Login {} fail!!", loginUser);

            return;

        }

        if (!loginUser.getIsActive()) {

            addError(MessageUtils.getResourceBundleString("login.authen.disabled"));

            logger.warn("Login {} block user was disbled!!", loginUser);

            return;

        }

        loggedIn = true;

        super.getRequest().getSession().setAttribute("userAuthen", loginUser);

        addInfo(MessageUtils.getResourceBundleString("login.loginprocess"));

        String path = JsfUtil.getContextPath();

        logger.trace("path : {}", path);

        executeJavaScript("setTimeout(function(){window.location='" + path + "/pages/form/index.xhtml?firstLogin=true';blockUI.show();},100);");

    }

    public void logout() {

        logger.trace("logout!!");

        redirectPage(JsfUtil.getContextPath() + "/LogoutController");
    
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private boolean validateLongin() {

        if (userName.isEmpty() || passWord.isEmpty()) {
            return false;
        }

        return true;

    }

    @Override
    public void resetForm() {
    }

    /**
     * @return the authenDao
     */
    public AuthenDao getAuthenDao() {
        return authenDao;
    }

    /**
     * @param authenDao the authenDao to set
     */
    public void setAuthenDao(AuthenDao authenDao) {
        this.authenDao = authenDao;
    }

    /**
     * @return the loginUser
     */
    public ViewUser getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser the loginUser to set
     */
    public void setLoginUser(ViewUser loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
