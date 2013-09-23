/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.utils;

import java.math.BigDecimal;

/**
 *
 * @author Totoland
 */
public class NumberUtils {
    private static NumberUtils instance;

    /**
     * @return the instance
     */
    public static NumberUtils getInstance() {
        if(instance==null){
            instance = new NumberUtils();
        }
        return instance;
    }
//    public static void main(String args[]){
//        System.out.println(new NumberUtils().toLong(15));
//    }
    public Long toLong(Object object){
        try{
            if(object!=null){
                return Long.parseLong(object.toString().trim());
            }
        }catch(Exception ex){
        }
        return null;
    }
    public Long bigDecimalToLong(BigDecimal b){
        if(b!=null){
            return new Long(b.longValue());
        }
        return null;
    }
    public boolean isNumber(Object object){
        try{
            Integer.parseInt(object.toString());
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public BigDecimal toBigDecimal(Object object){
        try{
            if(object!=null){
                return new BigDecimal(object.toString());
            }
        }catch(Exception ex){
        }
        return null;
    }
}
