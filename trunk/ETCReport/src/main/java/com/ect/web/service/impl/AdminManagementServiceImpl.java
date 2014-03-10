/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.admin.dao.AdminManagementDao;
import com.ect.db.bean.ActivityCriteria;
import com.ect.db.entity.EctActivity;
import com.ect.web.service.AdminManagementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service("adminManagementService")
public class AdminManagementServiceImpl implements AdminManagementService{

    @Autowired
    AdminManagementDao adminManagementDao;
    
    @Override
    public List<EctActivity> findActivityByCriteria(ActivityCriteria criteria) {
        return adminManagementDao.findActivityByCriteria(criteria);
    }
    
}
