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

import com.ect.web.controller.BaseController;
import com.ect.web.controller.model.EctGroupLevel;
import com.ect.web.controller.model.EctUser;
import com.ect.web.controller.model.EctUserGroup;
import com.ect.web.controller.model.LevelEnum;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author chain
 */
@ManagedBean(name = "permisstion")
@ViewScoped
public class PermissionController extends BaseController {

    private static final long serialVersionUID = 7787289337811236465L;
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
    // add by chain
    private List<EctUserGroup> ectUserGroupList = null;
    private EctUserGroup ectUserGroup = new EctUserGroup();
    private List<EctUser> ectUserList = null;
    private List<EctGroupLevel> ectGroupLevelList = null;
    private String mode;
    private Integer sld_lvl;
    private Integer sld_user_group;

    @PostConstruct
    public void init() {
        logger.info("initPage");
        mockdata();
        logger.info("initPage : mockdata");
    }

    @Override
    public void resetForm() {
    }

    public String findLvlDesc(Integer id) {
        for (EctGroupLevel obj : ectGroupLevelList) {
            if (id == obj.getGroup_lvl_id()) {
                return obj.getGroup_lvl_desc();
            }
        }
        return "ยังไม่ได้เลือก";
    }

    public String findGroupDesc(Integer id) {
        for (EctUserGroup obj : ectUserGroupList) {
            if (obj.getUserGroupId().compareTo(id) == 0) {
                return obj.getUserGroupName();
            }
        }
        return "ยังไม่ได้เลือก";
    }

    public void mockdata() {
        ectUserGroupList = new ArrayList<EctUserGroup>();
        String[] provinces = {"เชียงใหม่", "อุดรธานี", "ราชบุรี", "สงขลา", "ส่วนกลาง"};
        for (int i = 0; i < 5; i++) {
            EctUserGroup obj = new EctUserGroup();
            obj.setUserGroupId(i + 1);
            obj.setUserGroupName(provinces[i]);
            obj.setUserGroupDesc("เก็บกลุ่มของผุ้ใช้งานในส่วนของหน่วย" + provinces[i]);
            obj.setStatus(1);
            obj.setCreate_dateTh(formatDateTh(null));
            obj.setCreate_user("admin");
            ectUserGroupList.add(obj);
        }
        // mock user list
        ectUserList = new ArrayList<EctUser>();
        EctUser user = new EctUser();
        user.setUserId(1);
        user.setUsername("user1");
        user.setFname("สมชาย");
        user.setLname("รักชาติ");
        user.setSex(1);
        user.setStatus(1);
        user.setCreate_user("admin");
        user.setCreate_dateTh(formatDateTh(null));
        ectUserList.add(user);

        user = new EctUser();
        user.setUserId(2);
        user.setUsername("user2");
        user.setFname("วิชุดา");
        user.setLname("มั่นใจ");
        user.setSex(2);
        user.setStatus(1);
        user.setCreate_user("admin");
        user.setCreate_dateTh(formatDateTh(null));
        ectUserList.add(user);

        user = new EctUser();
        user.setUserId(3);
        user.setUsername("user3");
        user.setFname("พงษ์ศักดิ์");
        user.setLname("รักดี");
        user.setSex(1);
        user.setStatus(1);
        user.setCreate_user("admin");
        user.setCreate_dateTh(formatDateTh(null));
        ectUserList.add(user);

        // mock lvl
        ectGroupLevelList = new ArrayList<EctGroupLevel>();
        EctGroupLevel ectGroupLevel = new EctGroupLevel();
        ectGroupLevel.setGroup_lvl_id(LevelEnum.admin.getValue());
        ectGroupLevel.setGroup_lvl_name(LevelEnum.admin.name());
        ectGroupLevel.setGroup_lvl_desc(LevelEnum.admin.getDesc());
        ectGroupLevelList.add(ectGroupLevel);

        ectGroupLevel = new EctGroupLevel();
        ectGroupLevel.setGroup_lvl_id(LevelEnum.center.getValue());
        ectGroupLevel.setGroup_lvl_name(LevelEnum.center.name());
        ectGroupLevel.setGroup_lvl_desc(LevelEnum.center.getDesc());
        ectGroupLevelList.add(ectGroupLevel);

        ectGroupLevel = new EctGroupLevel();
        ectGroupLevel.setGroup_lvl_id(LevelEnum.header.getValue());
        ectGroupLevel.setGroup_lvl_name(LevelEnum.header.name());
        ectGroupLevel.setGroup_lvl_desc(LevelEnum.header.getDesc());
        ectGroupLevelList.add(ectGroupLevel);

        ectGroupLevel = new EctGroupLevel();
        ectGroupLevel.setGroup_lvl_id(LevelEnum.auditor.getValue());
        ectGroupLevel.setGroup_lvl_name(LevelEnum.auditor.name());
        ectGroupLevel.setGroup_lvl_desc(LevelEnum.auditor.getDesc());
        ectGroupLevelList.add(ectGroupLevel);

        ectGroupLevel = new EctGroupLevel();
        ectGroupLevel.setGroup_lvl_id(LevelEnum.recorder.getValue());
        ectGroupLevel.setGroup_lvl_name(LevelEnum.recorder.name());
        ectGroupLevel.setGroup_lvl_desc(LevelEnum.recorder.getDesc());
        ectGroupLevelList.add(ectGroupLevel);
    }

    public void preActionGroup() {
        Map<String, String> paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        mode = paramMap.get("mode");
        if ("create".equals(mode)) {
            ectUserGroup = new EctUserGroup();
            ectUserGroup.setStatus(0);
        } else if ("edit".equals(mode)) {
            String id = paramMap.get("id");
            for (EctUserGroup obj : ectUserGroupList) {
                if (obj.getUserGroupId() == Integer.parseInt(id)) {
                    this.ectUserGroup.setUserGroupId(obj.getUserGroupId());
                    this.ectUserGroup.setUserGroupName(obj.getUserGroupName());
                    this.ectUserGroup.setUserGroupDesc(obj.getUserGroupDesc());
                    this.ectUserGroup.setStatus(obj.getStatus());
                }
            }
        }
    }

    public void actionGroup() {
        if ("create".equals(mode)) {
            ectUserGroup.setUserGroupId(ectUserGroupList.size() + 1);
            ectUserGroup.setCreate_dateTh(formatDateTh(null));
            ectUserGroup.setCreate_user("admin");
            ectUserGroupList.add(ectUserGroup);
            ectUserGroup = new EctUserGroup();
        } else if ("edit".equals(mode)) {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            for (EctUserGroup obj : ectUserGroupList) {
                if (obj.getUserGroupId() == Integer.parseInt(id)) {
                    obj.setUserGroupId(this.ectUserGroup.getUserGroupId());
                    obj.setUserGroupName(this.ectUserGroup.getUserGroupName());
                    obj.setUserGroupDesc(this.ectUserGroup.getUserGroupDesc());
                    obj.setStatus(this.ectUserGroup.getStatus());
                }
            }
        }
    }

    public void deleteGroup(ActionEvent e) {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        for (Iterator i = ectUserGroupList.iterator(); i.hasNext();) {
            EctUserGroup obj = (EctUserGroup) i.next();
            if (obj.getUserGroupId() == Integer.parseInt(id)) {
                i.remove();
                return;
            }
        }
    }

    public void onEdit(RowEditEvent event) {
        EctUser user = (EctUser) event.getObject();
        user.setUserGroupId(sld_user_group);
        user.setGroup_lvl_id(sld_lvl);
    }

    public List<EctUserGroup> getEctUserGroupList() {
        return ectUserGroupList;
    }

    public void setEctUserGroupList(List<EctUserGroup> ectUserGroupList) {
        this.ectUserGroupList = ectUserGroupList;
    }

    public EctUserGroup getEctUserGroup() {
        return ectUserGroup;
    }

    public void setEctUserGroup(EctUserGroup ectUserGroup) {
        this.ectUserGroup = ectUserGroup;
    }

    protected String formatDateTh(Date date) {
        if (null == date) {
            date = new Date();
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<EctUser> getEctUserList() {
        return ectUserList;
    }

    public void setEctUserList(List<EctUser> ectUserList) {
        this.ectUserList = ectUserList;
    }

    public Integer getSld_lvl() {
        return sld_lvl;
    }

    public void setSld_lvl(Integer sld_lvl) {
        this.sld_lvl = sld_lvl;
    }

    public Integer getSld_user_group() {
        return sld_user_group;
    }

    public void setSld_user_group(Integer sld_user_group) {
        this.sld_user_group = sld_user_group;
    }

    public List<EctGroupLevel> getEctGroupLevelList() {
        return ectGroupLevelList;
    }

    public void setEctGroupLevelList(List<EctGroupLevel> ectGroupLevelList) {
        this.ectGroupLevelList = ectGroupLevelList;
    }
}
