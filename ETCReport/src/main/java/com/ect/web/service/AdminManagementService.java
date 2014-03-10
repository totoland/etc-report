/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.bean.ActivityCriteria;
import com.ect.db.entity.EctActivity;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface AdminManagementService {

    List<EctActivity> findActivityByCriteria(ActivityCriteria criteria);
    
}
