/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.login;

import com.ect.web.controller.BaseController;
import com.ect.web.utils.MessageUtils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Totoland
 */
@ManagedBean
@ViewScoped
public class LoginController extends BaseController {

    private static final long serialVersionUID = 3291979904925054393L;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private String userName = "admin";
    private String passWord;

    @PostConstruct
    public void init() {
        logger.info("init");
    }

    public void loginProcess() {
        logger.info("loginProcess!!");

        if (!validateLongin()) {
            addError("loggin fial!!");
            return;
        }

        addInfo(MessageUtils.getResourceBundleString("login.loginprocess"));

        //executeJavaScript("setTimeout(function(){window.location='pages/report/formSavePrintReport.xhtml';},1000);");


        // call หน้าเจ้าหน้าที่บันทึกข้อมูลรีพอต 
        if ("leader".equals(passWord)) {
            //ไปหน้า หัวหน้าจังหวด
            executeJavaScript("setTimeout(function(){window.location='pages/user/formRoleLeader.xhtml';},1000);");
        } else if ("header".equals(passWord)) {
            //ไปหน้า หัวหน้าส่วนกลาง
            executeJavaScript("setTimeout(function(){window.location='pages/user/formRoleHeader.xhtml';},1000);");
        } else {
            //ไปหน้า เจ้าหน้าที่บันทึกข้อมูล
            executeJavaScript("setTimeout(function(){window.location='pages/user/formRoleUser.xhtml';},1000);");
        }


        consoleLog("loggin success!!");
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
        if (userName.equals("admin") && passWord != null) {
            return true;
        }
        return false;
    }

    @Override
    public void resetForm() {
    }
}
