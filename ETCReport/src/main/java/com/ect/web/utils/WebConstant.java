/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.utils;

/**
 *
 * @author Totoland
 */
public class WebConstant {
    private static String UPLOAD_PATH = "";
    public static String MESSAGES_PROP = "com/etc/web/resources/messages";
    public static String SYSTEM_PROP = "com/etc/web/resources/system";
    static{
        UPLOAD_PATH = MessageUtils.getResourceBundleString(SYSTEM_PROP, "file.path");
    }
}
