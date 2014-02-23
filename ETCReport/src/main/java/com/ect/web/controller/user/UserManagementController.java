/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.user;

import com.ect.db.bean.UserCriteria;
import com.ect.db.entity.EctUser;
import com.ect.db.entity.ViewUser;
import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.ReportGennericService;
import com.ect.web.service.UserService;
import com.ect.web.utils.ECTUtils;
import com.ect.web.utils.JsfUtil;
import com.ect.web.utils.MessageUtils;
import com.ect.web.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class UserManagementController extends BaseController {

    private static final long serialVersionUID = 4889668000891738625L;
    private static Logger logger = LoggerFactory.getLogger(UserManagementController.class);
    private List<ViewUser> ectUsers;
    private UserCriteria userCriteria = new UserCriteria();
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    private EctUser ectUser = new EctUser();
    @ManagedProperty("#{reportGennericService}")
    private ReportGennericService<EctUser> reportGennericService;
    private String rePassword;

    @PostConstruct
    public void init() {
        logger.trace("initPage");
        initForm();
    }

    public void search() {
        logger.trace("search userCriteria : {}", getUserCriteria());

        ectUsers = getUserService().findByUserName(getUserCriteria());

        logger.trace("ectUsers size : {}", ectUsers);
    }

    public List<ViewUser> completeUsername(String query) {
        logger.trace("autoComplete find Username text search : {}", query);

        return ectUsers;
    }

    public void initCreateUser() {
        logger.trace("create new User");

        ectUser = new EctUser();
        ectUser.setSex(0);
        ectUser.setIsActive(Boolean.TRUE);
        
        openDialog("modalDialogCreate");
    }

    public void initEditUser(ViewUser selectUser) {
        logger.trace("edit User");

        try {

            this.ectUser.setUserId(selectUser.getUserId());
            this.ectUser.setUsername(selectUser.getUsername());
            this.ectUser.setPassword(ECTUtils.decrypt(selectUser.getPassword()));
            this.rePassword = ECTUtils.decrypt(selectUser.getPassword());
            this.ectUser.setIsActive(selectUser.getIsActive());
            this.ectUser.setFname(selectUser.getFname());
            this.ectUser.setLname(selectUser.getLname());
            this.ectUser.setSex(selectUser.getSex());
            this.ectUser.setUserGroupId(selectUser.getUserGroupId());
            this.ectUser.setProvinceId(selectUser.getProvinceId());
            this.ectUser.setUserGroupLvl(selectUser.getUserGroupLvl());

        } catch (Exception ex) {

            logger.error("cannot initEdit :", ex);

        }
        openDialog("modalDialogEdit");
    }

    public void save() {
        logger.trace("save...");

        if (!validateBeforeSave()) {
            return;
        }

        try {

            ectUser.setPassword(ECTUtils.encrypt(ectUser.getPassword()));

            reportGennericService.create(ectUser);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            search();

            JsfUtil.hidePopup("modalDialogCreate");


        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);
        } finally {

            logger.trace("Save... {} ", ectUser);
            resetForm();

        }
    }

    public void edit() {
        logger.trace("edit...");

        if (!validateBeforeSave()) {
            return;
        }

        try {
            
            ectUser.setPassword(ECTUtils.encrypt(ectUser.getPassword()));
            
            reportGennericService.edit(ectUser);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            search();

            JsfUtil.hidePopup("modalDialogEdit");


        } catch (Exception ex) {

            JsfUtil.addErrorMessage(MessageUtils.SAVE_NOT_SUCCESS());

            logger.error("Cannot Save Data : ", ex);
            
            ectUser.setPassword(rePassword);
            
        } finally {

            logger.trace("Save... {} ", ectUser);

        }
    }

    @Override
    public void resetForm() {
        initForm();
        userCriteria = new UserCriteria();
    }

    public void initForm() {
        userCriteria = new UserCriteria();
        ectUsers = new ArrayList<>();
    }

    /**
     * @return the ectUsers
     */
    public List<ViewUser> getEctUsers() {
        return ectUsers;
    }

    /**
     * @param ectUsers the ectUsers to set
     */
    public void setEctUsers(List<ViewUser> ectUsers) {
        this.ectUsers = ectUsers;
    }

    /**
     * @return the userCriteria
     */
    public UserCriteria getUserCriteria() {
        return userCriteria;
    }

    /**
     * @param userCriteria the userCriteria to set
     */
    public void setUserCriteria(UserCriteria userCriteria) {
        this.userCriteria = userCriteria;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
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
     * @return the ectUser
     */
    public EctUser getEctUser() {
        return ectUser;
    }

    /**
     * @param ectUser the ectUser to set
     */
    public void setEctUser(EctUser ectUser) {
        this.ectUser = ectUser;
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<EctUser> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<EctUser> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private boolean validateBeforeSave() {
        String msg = "";

        if (StringUtils.isBlank(ectUser.getUsername())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "ชื่อผู้ใช้")) + ("\\n");
        }
        if (StringUtils.isBlank(ectUser.getPassword())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "รหัสผ่าน")) + ("\\n");
        }
        if (StringUtils.isBlank(ectUser.getFname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "ชื่อ")) + ("\\n");
        }
        if (StringUtils.isBlank(ectUser.getLname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "นามสกุล")) + ("\\n");
        }
        if (ectUser.getSex() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "เพศ")) + ("\\n");
        }
        if (ectUser.getUserGroupLvl() == null || ectUser.getUserGroupLvl().intValue() == 0) {
            msg += (MessageUtils.getResourceBundleString("require_message", "ระดับผู้ใช้")) + ("\\n");
        }
        if (ectUser.getUserGroupId() == null || ectUser.getUserGroupId().intValue() == 0) {
            msg += (MessageUtils.getResourceBundleString("require_message", "กลุ่มผู้ใช้")) + ("\\n");
        }
        if (ectUser.getIsActive() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "สถานนะ")) + ("\\n");
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        if (!ectUser.getPassword().equals(rePassword)) {
            JsfUtil.alertJavaScript(MessageUtils.getResourceBundleString("password_not_same"));
            return false;
        }

        return true;
    }

    /**
     * @return the rePassword
     */
    public String getRePassword() {
        return rePassword;
    }

    /**
     * @param rePassword the rePassword to set
     */
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
