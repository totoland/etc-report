/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.authen.dao;

import com.ect.db.entity.ViewUser;

/**
 *
 * @author totoland
 */
public interface AuthenDao {
    
    ViewUser loginUser(String userName,String passWord);

    ViewUser findByUserId(Integer userId);
    
}
