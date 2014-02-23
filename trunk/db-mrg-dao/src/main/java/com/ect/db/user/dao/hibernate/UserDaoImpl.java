/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.user.dao.hibernate;

import com.ect.db.bean.UserCriteria;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.ViewUser;
import com.ect.db.user.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public List<ViewUser> searchByUserName(UserCriteria criteria) {
        List<Object> lst = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     ECT_USER.USER_ID, ECT_USER.USERNAME, ECT_USER.PASSWORD, ECT_USER.IS_ACTIVE, ECT_USER.FNAME, ECT_USER.LNAME, ECT_USER.SEX,  ");
        sql.append("                      ECT_USER.PROVINCE_ID, ECT_USER.USER_GROUP_ID, ECT_USER_GROUP.USER_GROUP_NAME, ECT_USER.USER_GROUP_LVL,  ");
        sql.append("                      ECT_PROVINCE.PROVINCE_NAME, ECT_GROUP_LVL.GROUP_LVL_NAME ");
        sql.append("FROM         ECT_USER INNER JOIN ");
        sql.append("                      ECT_USER_GROUP ON ECT_USER.USER_GROUP_ID = ECT_USER_GROUP.USER_GROUP_ID INNER JOIN ");
        sql.append("                      ECT_GROUP_LVL ON ECT_USER.USER_GROUP_LVL = ECT_GROUP_LVL.GROUP_LVL_ID LEFT OUTER JOIN ");
        sql.append("                      ECT_PROVINCE ON ECT_USER.PROVINCE_ID = ECT_PROVINCE.PROVINCE_ID");
        sql.append("                              WHERE 1=1 ");

        if (criteria.getGroupId() != null && criteria.getGroupId().intValue() != -1) {
            sql.append("AND ECT_USER.USER_GROUP_ID = ? ");
            lst.add(criteria.getGroupId());
        }

        if (criteria.getUserName() != null && !criteria.getUserName().trim().isEmpty()) {
            sql.append("AND FNAME LIKE '%'+?+'%' OR LNAME LIKE '%'+?+'%' ");
            lst.add(criteria.getUserName());
            lst.add(criteria.getUserName());
        }

        if (criteria.getGroupLvl() != null && criteria.getGroupLvl().intValue() != -1) {
            sql.append("AND ECT_USER.USER_GROUP_LVL = ? ");
            lst.add(criteria.getGroupLvl());
        }

        return findNativeQuery(sql.toString(), ViewUser.class, lst);
    }
}
