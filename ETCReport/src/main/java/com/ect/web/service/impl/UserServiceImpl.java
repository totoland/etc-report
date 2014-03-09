/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.bean.UserCriteria;
import com.ect.db.entity.ViewUser;
import com.ect.db.user.dao.UserDao;
import com.ect.web.service.UserService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service("userService")
public class UserServiceImpl implements UserService , Serializable{
    private static final long serialVersionUID = 2970904373229939989L;
    
    @Autowired
    AuthenDao authenDao;
    @Autowired
    UserDao userDao;
    
    @Override
    public ViewUser findByUserId(Integer userId){
        return authenDao.findByUserId(userId);
    }
    
    @Override
    public List<ViewUser> findByUserCriteria(UserCriteria userName){
        return userDao.searchByUserCriteria(userName);
    }

    @Override
    public ViewUser findByUserName(String criteria) {
        return userDao.searchByUserName(criteria);
    }
    
}
