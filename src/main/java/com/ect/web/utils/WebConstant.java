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
    public static String MESSAGES_PROP = "th/go/gregister/web/resources/messages";
    public static String SYSTEM_PROP = "th/go/gregister/web/resources/system";
    public static String SESS_MENU = "session_mene";
    public static String SESS_MEM_ADD_LISTCARDBASE = "listCardBase";
    public static String SESS_MEM_CARDBASE_SELECT = "cardBaseSelect";
    public static String SESS_MEM_MANAGE = "memMember";
    public static String SESS_MEM_XLS = "session_memberTemp";
    static{
        UPLOAD_PATH = MessageUtils.getResourceBundleString(SYSTEM_PROP, "file.path");
    }
}
