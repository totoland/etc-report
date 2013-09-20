/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.ect.web.utils.DateTimeUtils;
import com.ect.web.utils.MessageUtils;

/**
 *
 * @author Totoland
 */
@ManagedBean
@SessionScoped
public class MessageController extends BaseController{
    private static final long serialVersionUID = 8201384247554040772L;

    /**
     * Creates a new instance of MessageController
     */
    public MessageController() {
    }
    public static void main(String args[]){
        System.out.println(new MessageController().yyyyMMddToddMMyyyy("25271229"));
    }
    public String showStringFlag(String flag){
        return MessageUtils.showStringFlag(flag);
    }
    public String yyyyMMddToddMMyyyy(String date){
        return DateTimeUtils.getInstance().yyyyMMddToddMMyyyy(date);
    }
    public String yyyyMMddToddMMyyyy(String date,String rex){
        return DateTimeUtils.getInstance().yyyyMMddToddMMyyyy(date,rex);
    }
    public String yyyyMMddToMMyyyy(String date,String rex){
        return DateTimeUtils.getInstance().yyyyMMddToMMyyyy(date,rex);
    }
    public String thDate(Date date,String rex){
        return DateTimeUtils.getInstance().thDate(date, rex+" น.");
    }
    public String messageErrors(List<String>errors){
        String res = "";
        if(errors==null || errors.size()==0){
            return null;
        }
        for(String s : errors){
            res += ","+s;
        }
        return res.replaceFirst(",", "");
    }
}
