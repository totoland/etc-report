/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.model;

import java.util.Date;

/**
 *
 * @author Jirawat.l
 */
public class EctUserGroup {

    private Integer userGroupId;
    private String userGroupName;
    private String userGroupDesc;
//    private int userGroupLvl;
    //add by chain
    private int status;
    private String create_user;
    private Date create_date;
    private String create_dateTh;
    private String update_date;
    private Date update_user;
//    private String userGroupLvlDesc;

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupDesc() {
        return userGroupDesc;
    }

    public void setUserGroupDesc(String userGroupDesc) {
        this.userGroupDesc = userGroupDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_dateTh() {
        return create_dateTh;
    }

    public void setCreate_dateTh(String create_dateTh) {
        this.create_dateTh = create_dateTh;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public Date getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Date update_user) {
        this.update_user = update_user;
    }

//    public String getUserGroupLvlDesc() {
//        return userGroupLvlDesc;
//    }
//
//    public void setUserGroupLvlDesc(String userGroupLvlDesc) {
//        this.userGroupLvlDesc = userGroupLvlDesc;
//    }
}
