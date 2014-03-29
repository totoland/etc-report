/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.admin;

import com.ect.db.bean.ActivityCriteria;
import com.ect.db.entity.EctActivity;
import com.ect.web.controller.BaseController;
import com.ect.web.factory.DropdownFactory;
import com.ect.web.service.AdminManagementService;
import com.ect.web.service.ReportGennericService;
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
import org.slf4j.MDC;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class ActivityManagementController extends BaseController {

    private static final long serialVersionUID = 6590630336186089578L;
    private static Logger logger = LoggerFactory.getLogger(ActivityManagementController.class);
    @ManagedProperty(value = "#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty(value = "#{adminManagementService}")
    private AdminManagementService adminManagementService;
    @ManagedProperty(value = "#{reportGennericService}")
    private ReportGennericService<EctActivity> reportGennericService;
    private ActivityCriteria activityCriteria;
    private List<EctActivity> listActivity;
    private EctActivity ectActivity;

    @PostConstruct
    public void init() {

        checkRoleAdmin();
        initCriteria();
        initForm();

    }

    public void search() {

        logger.trace("Search!!");
        logger.trace("Criteria : {}", activityCriteria);

        if (!validateCriteria()) {
            return;
        }

        listActivity = adminManagementService.findActivityByCriteria(activityCriteria);

    }

    public void save() {

        logger.trace("Save... {} ", ectActivity);

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.create(ectActivity);

            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("modalDialogCreate");

            search();


        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);
        } finally {

            logger.trace("Save... {} ", ectActivity);

        }
    }

    public void initCreateActivity() {
        logger.trace("create new Activity");

        if(!validateCriteria()){
            return;
        }
        
        ectActivity = new EctActivity();
        ectActivity.setProjectId(Integer.parseInt(activityCriteria.getProject()));

        openDialog("modalDialogCreate");
    }

    public void initEdit(EctActivity selectedActivity) {
        logger.trace("initEditUser {}",selectedActivity);

        ectActivity = selectedActivity;

        openDialog("modalDialogEdit");
    }

    public void edit() {
        logger.trace("edit...");

        if (!validateBeforeSave()) {
            return;
        }

        try {

            reportGennericService.edit(ectActivity);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            search();

            JsfUtil.hidePopup("modalDialogEdit");


        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);

        } finally {

            logger.trace("Save... {} ", ectActivity);

        }
    }

    @Override
    public void resetForm() {
        initForm();
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
     * @return the activityCriteria
     */
    public ActivityCriteria getActivityCriteria() {
        return activityCriteria;
    }

    /**
     * @param activityCriteria the activityCriteria to set
     */
    public void setActivityCriteria(ActivityCriteria activityCriteria) {
        this.activityCriteria = activityCriteria;
    }

    private void initCriteria() {
        activityCriteria = new ActivityCriteria();
    }

    /**
     * @return the listActivity
     */
    public List<EctActivity> getListActivity() {
        return listActivity;
    }

    /**
     * @param listActivity the listActivity to set
     */
    public void setListActivity(List<EctActivity> listActivity) {
        this.listActivity = listActivity;
    }

    /**
     * @return the adminManagementService
     */
    public AdminManagementService getAdminManagementService() {
        return adminManagementService;
    }

    /**
     * @param adminManagementService the adminManagementService to set
     */
    public void setAdminManagementService(AdminManagementService adminManagementService) {
        this.adminManagementService = adminManagementService;
    }

    /**
     * @return the ectActivity
     */
    public EctActivity getEctActivity() {
        return ectActivity;
    }

    /**
     * @param ectActivity the ectActivity to set
     */
    public void setEctActivity(EctActivity ectActivity) {
        this.ectActivity = ectActivity;
    }

    private void initForm() {
        listActivity = new ArrayList<>();
        ectActivity = new EctActivity();
        activityCriteria = new ActivityCriteria();
    }

    private boolean validateBeforeSave() {

        if (StringUtils.isBlank(ectActivity.getActivityName())) {
            JsfUtil.alertJavaScript(MessageUtils.REQUIRQ_ENTER_ACTIVITY());
            return false;
        }

        return true;
    }

    /**
     * @return the reportGennericService
     */
    public ReportGennericService<EctActivity> getReportGennericService() {
        return reportGennericService;
    }

    /**
     * @param reportGennericService the reportGennericService to set
     */
    public void setReportGennericService(ReportGennericService<EctActivity> reportGennericService) {
        this.reportGennericService = reportGennericService;
    }

    private boolean validateCriteria() {

        boolean validate = true;

        if (StringUtils.isBlank(activityCriteria.getStrategic())) {
            addError(MessageUtils.REQUIRE_SELECT_STRATEGICID());
            validate = false;
        }
        if (StringUtils.isBlank(activityCriteria.getSubStrategic())) {
            addError(MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID());
            validate = false;
        }
        if (StringUtils.isBlank(activityCriteria.getSubStrategic())) {
            addError(MessageUtils.REQUIRE_SELECT_SUBSTRATEGICID());
            validate = false;
        }
        if (StringUtils.isBlank(activityCriteria.getPlan())) {
            addError(MessageUtils.REQUIRE_SELECT_PLAN());
            validate = false;
        }
        if (StringUtils.isBlank(activityCriteria.getProject())) {
            addError(MessageUtils.REQUIRE_SELECT_PROJECT());
            validate = false;
        }

        return validate;
    }
}
