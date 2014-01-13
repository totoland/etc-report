/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.authen.dao.hibernate;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.EctUser;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("authenDao")
public class AuthenDaoImpl extends BaseDao implements AuthenDao, Serializable{
    private static final long serialVersionUID = -4586932513662487847L;

    @Override
    public EctUser loginUser(String userName, String passWord) {
        
        return (EctUser)findUniqNativeQuery("SELECT * FROM ECT_USER WHERE USERNAME = ? AND PASSWORD = ?", EctUser.class,userName,passWord);
        
    }
    
}
