/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.go.gregister.web.utils;

/**
 *
 * @author Totoland
 */
public class StringUtils {
    private static StringUtils instance = new StringUtils();
    public static StringUtils getInstance(){
        return instance; 
    }
    public String trim(String pid){
        if(pid==null)
            return null;
        return pid.trim();
    }
    public String replaceDatAndTrim(String pid){
        if(pid==null)
            return null;
        return pid.replaceAll("-", "").trim();
    }
    public boolean isBlank(String s){
        return s==null || s.equals("");
    }
    public String[] split(String s,String rex){
        String[] arr = s.split(rex);
        return arr;
    }
}
