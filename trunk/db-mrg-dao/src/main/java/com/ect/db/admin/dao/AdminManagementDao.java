/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.admin.dao;

import com.ect.db.bean.ActivityCriteria;
import com.ect.db.entity.EctActivity;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface AdminManagementDao {
    
    List<EctActivity> findActivityByCriteria(ActivityCriteria criteria);
    
}
