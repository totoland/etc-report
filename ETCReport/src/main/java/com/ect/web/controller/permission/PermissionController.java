/*
 * ----------------------------------------------------------------------------
 * Copyright  2009 by Mobile-Technologies Co.,Ltd. . All rights reserved.
 * All intellectual property rights in and/or in the computer program and its related
 * documentation and technology are the sole Mobile-Technologies' property.
 * This computer program is under Mobile-Technologies copyright and cannot be in whole or in part
 * reproduced, sublicensed, leased, sold or
 * used in any form or by any means, including without limitation graphic,
 * electronic, mechanical,
 * photocopying, recording, taping or information storage and
 * retrieval systems without Mobile-Technologies prior written consent. The
 * downloading, exporting or reexporting of this computer program or any related
 * documentation or technology is subject to any export rules, including US
 * regulations.
 * ----------------------------------------------------------------------------
 */


package com.ect.web.controller.permission;

import com.ect.db.common.entity.DropDownList;
import com.ect.web.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
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
public class PermissionController extends BaseController{
    private static final long serialVersionUID = 7787289337811236465L;
    
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
    
    private List<DropDownList>departmentList = new ArrayList<DropDownList>();
    private String departmentValue;
    private List<DropDownList>userGroupList = new ArrayList<DropDownList>();
    private String userGroupValue;
    
    @PostConstruct
    public void init(){
        logger.info("initPage");
    }

    @Override
    public void resetForm() {
        
    }

    /**
     * @return the departmentList
     */
    public List<DropDownList> getDepartmentList() {
        return departmentList;
    }

    /**
     * @param departmentList the departmentList to set
     */
    public void setDepartmentList(List<DropDownList> departmentList) {
        this.departmentList = departmentList;
    }

    /**
     * @return the departmentValue
     */
    public String getDepartmentValue() {
        return departmentValue;
    }

    /**
     * @param departmentValue the departmentValue to set
     */
    public void setDepartmentValue(String departmentValue) {
        this.departmentValue = departmentValue;
    }

    /**
     * @return the userGroupList
     */
    public List<DropDownList> getUserGroupList() {
        return userGroupList;
    }

    /**
     * @param userGroupList the userGroupList to set
     */
    public void setUserGroupList(List<DropDownList> userGroupList) {
        this.userGroupList = userGroupList;
    }

    /**
     * @return the userGroupValue
     */
    public String getUserGroupValue() {
        return userGroupValue;
    }

    /**
     * @param userGroupValue the userGroupValue to set
     */
    public void setUserGroupValue(String userGroupValue) {
        this.userGroupValue = userGroupValue;
    }

}
