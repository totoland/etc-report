/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.bean.UserCriteria;
import com.ect.db.entity.ViewUser;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserService {
    ViewUser findByUserId(Integer userId);
    List<ViewUser> findByUserCriteria(UserCriteria userName);
    ViewUser findByUserName(String criteria);
}
