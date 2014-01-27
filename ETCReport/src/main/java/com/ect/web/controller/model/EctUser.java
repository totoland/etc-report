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
public class EctUser {

    private Integer userId;
    private String username;
    private String password;
    private Boolean isActive;
    private String fname;
    private String lname;
    private Integer sex;
    private int userGroupId;
    private Integer provinceId;
    // add by chain
    private Integer group_lvl_id;
    private int status;
    private String create_user;
    private Date create_date;
    private String create_dateTh;
    private String update_date;
    private Date update_user;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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

    public Integer getGroup_lvl_id() {
        return group_lvl_id;
    }

    public void setGroup_lvl_id(Integer group_lvl_id) {
        this.group_lvl_id = group_lvl_id;
    }

    public String getCreate_dateTh() {
        return create_dateTh;
    }

    public void setCreate_dateTh(String create_dateTh) {
        this.create_dateTh = create_dateTh;
    }
}
