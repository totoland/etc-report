/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.authen.dao.hibernate;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.EctUser;
import com.ect.db.entity.ViewUser;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("authenDao")
public class AuthenDaoImpl extends BaseDao implements AuthenDao, Serializable {

    private static final long serialVersionUID = -4586932513662487847L;

    @Override
    public ViewUser loginUser(String userName, String passWord) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     ECT_USER.USER_ID, ECT_USER.USERNAME, ECT_USER.PASSWORD, ECT_USER.IS_ACTIVE, ECT_USER.FNAME, ECT_USER.LNAME, ECT_USER.SEX,  ");
        sql.append("                      ECT_USER.PROVINCE_ID, ECT_USER.USER_GROUP_ID, ECT_USER_GROUP.USER_GROUP_NAME, ECT_USER_GROUP.USER_GROUP_LVL ");
        sql.append("FROM         ECT_USER INNER JOIN ");
        sql.append("                      ECT_USER_GROUP ON ECT_USER.USER_GROUP_ID = ECT_USER_GROUP.USER_GROUP_ID ");
        sql.append("                      WHERE ECT_USER.USERNAME = ? AND ECT_USER.PASSWORD = ?");

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, userName, passWord);

    }
}
