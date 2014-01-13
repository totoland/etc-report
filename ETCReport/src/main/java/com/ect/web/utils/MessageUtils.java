/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Totoland
 */
public class MessageUtils {
    
    public static String showStringFlag(String flag){
        if(flag==null || flag.length()==0){
            return "";
        }
        if(flag.equalsIgnoreCase("y")){
            return getResourceBundleString(WebConstant.MESSAGES_PROP, "flag.active");
        }else if(flag.equalsIgnoreCase("n")){
            return getResourceBundleString(WebConstant.MESSAGES_PROP, "flalg.notactive");
        }else{
            return "";
        }
    }
    
    public static String getString(String key){
        return getResourceBundleString(WebConstant.MESSAGES_PROP,key);
    }
    
    public static String getString(String key,String... values){
        MessageFormat messageFormat = new MessageFormat(key);
        return messageFormat.format(values);
    }
    public static String getResourceBundleString(String resourceBundleKey,String... values)throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc,"msg");
        return getString(bundle.getString(resourceBundleKey),values);
    }
    public static String getResourceBundleString(String resourceBundleKey)throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc,"msg");
        return bundle.getString(resourceBundleKey);
    }
    public static String getResourceBundleString(String resourceBundleKey,String values)throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc,"msg");
        return getString(bundle.getString(resourceBundleKey),values);
    }

}
