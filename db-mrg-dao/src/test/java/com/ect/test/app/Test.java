/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.test.app;

import com.ect.db.authen.dao.AuthenDao;
import com.ect.db.common.dao.CommonDao;
import com.ect.db.common.entity.DropDownList;
import com.ect.db.dao.BaseDao;
import com.ect.db.entity.ViewUser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author Totoland
 */
public class Test extends AbstractTest {

    public static void main(String args[]) throws UnknownHostException {
        new Test().test();
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
//        getUrlContents("http://www.google.co.th");
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        System.out.println(a==b);
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

    // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            
            System.out.println(urlConnection);
            
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return content.toString();
    }

    public void test() {
        Website website = new Website();
        website.pInstance.read();
    }

    class ProgrammerInterview {

        public void read() {
            System.out.println("Programmer Interview!");
        }
    }

    class Website extends BaseDao implements AuthenDao, CommonDao {
        /*  This creates an anonymous inner class: */

        ProgrammerInterview pInstance = new ProgrammerInterview() {
            @Override
            public void read() {
                System.out.println("anonymous ProgrammerInterview");
            }
        };

        @Override
        public ViewUser loginUser(String userName, String passWord) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ViewUser findByUserId(Integer userId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<DropDownList> getDropdownList(DropDownList dropDownList) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
