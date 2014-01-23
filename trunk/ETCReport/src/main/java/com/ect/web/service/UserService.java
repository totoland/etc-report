/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.entity.ViewUser;

/**
 *
 * @author totoland
 */
public interface UserService {
    ViewUser findByUserId(Integer userId);
}
