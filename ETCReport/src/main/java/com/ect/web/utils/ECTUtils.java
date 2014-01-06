/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.utils;

import java.util.UUID;

/**
 *
 * @author totoland
 */
public class ECTUtils {
    
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
