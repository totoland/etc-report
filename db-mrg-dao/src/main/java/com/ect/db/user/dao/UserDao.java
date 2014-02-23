/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.user.dao;

import com.ect.db.bean.UserCriteria;
import com.ect.db.entity.ViewUser;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserDao {
    List<ViewUser>searchByUserName(UserCriteria criteria);
}
