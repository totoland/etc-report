/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.admin.dao.hibernate;

import com.ect.db.admin.dao.AdminManagementDao;
import com.ect.db.bean.ActivityCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.EctActivity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("adminManagementDao")
public class AdminManagementDaoImpl extends BaseDao implements AdminManagementDao {

    @Override
    public List<EctActivity> findActivityByCriteria(ActivityCriteria criteria) {

        List<Object>lst = new ArrayList<Object>();
        
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM ECT_ACTIVITY WHERE 1 = 1 ");

        if (criteria.getProject() != null && !criteria.getProject().isEmpty()) {
            sql.append("AND PROJECT_ID =  ? ");
            lst.add(criteria.getProject());
        }
        
        if(criteria.getActivity() !=null && !criteria.getActivity().isEmpty()){
            sql.append("AND ACTIVITY_ID = ? ");
            lst.add(criteria.getActivity());
        }

        return findNativeQuery(sql.toString(), EctActivity.class,lst);

    }
}
