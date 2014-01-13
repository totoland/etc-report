/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.authen.dao;

import com.ect.db.entity.EctUser;

/**
 *
 * @author totoland
 */
public interface AuthenDao {
    
    EctUser loginUser(String userName,String passWord);
    
}
