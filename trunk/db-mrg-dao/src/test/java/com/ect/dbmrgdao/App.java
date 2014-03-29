/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

/**
 *
 * @author totoland
 */
public class App {

    public static void main(String args[]) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT     REPORT_?.REPORT_ID, REPORT_?.REPORT_CODE, REPORT_?.REPORT_DESC, REPORT_?.CREATED_DATE, REPORT_?.CREATED_USER, \n"
                + "                      REPORT_?.UPDATED_DATE, REPORT_?.UPDATED_USER, REPORT_?.REMARK, REPORT_?.APPROVED_USER, REPORT_?.FLOW_STATUS_ID, \n"
                + "                      ECT_USER.USERNAME AS CREATED_USER_NAME, ECT_FLOW_STATUS.FLOW_STATUS_NAME, ECT_USER_1.USERNAME AS UPDATED_USER_NAME, \n"
                + "                      ECT_USER_2.USERNAME AS APPROVED_USER_NAME, REPORT_?.APPROVED_DATE, ECT_USER_3.USERNAME AS REJECTED_USER_NAME, \n"
                + "                      REPORT_?.REJECTED_USER, REPORT_?.REJECTED_DATE, ECT_USER.FNAME + '  ' + ECT_USER.LNAME AS CREATED_USER_FULL_NAME, \n"
                + "                      ECT_USER_1.FNAME + '  ' + ECT_USER_1.LNAME AS UPDATED_USER_FULL_NAME, \n"
                + "                      ECT_USER_2.FNAME + '  ' + ECT_USER_2.LNAME AS APPROVED_USER_FULL_NAME, \n"
                + "                      ECT_USER_3.FNAME + '  ' + ECT_USER_3.LNAME AS REJECTED_USER_FULL_NAME, ISNULL(REPORT_?.REPORT_STATUS, 100) AS REPORT_STATUS, \n"
                + "                      ECT_USER.USER_GROUP_ID, ECT_USER.USER_GROUP_LVL,\n"
                + "                      CASE \n" +
                "				WHEN ISNULL(REPORT_?.CREATED_DATE,'') > ISNULL(REPORT_?.APPROVED_DATE,'') AND ISNULL(REPORT_?.CREATED_DATE,'') > ISNULL(REPORT_?.REJECTED_DATE,'') THEN REPORT_?.CREATED_DATE\n" +
                "				WHEN ISNULL(REPORT_?.APPROVED_DATE,'') > ISNULL(REPORT_?.CREATED_DATE,'') AND ISNULL(REPORT_?.APPROVED_DATE,'') > ISNULL(REPORT_?.REJECTED_DATE,'') THEN REPORT_?.APPROVED_DATE\n" +
                    "				WHEN ISNULL(REPORT_?.REJECTED_DATE,'') > ISNULL(REPORT_?.CREATED_DATE,'') AND ISNULL(REPORT_?.REJECTED_DATE,'') > ISNULL(REPORT_?.APPROVED_DATE,'') THEN REPORT_?.REJECTED_DATE\n" +
                "			  END AS ACTION_DATE\n"
                + "FROM         REPORT_? INNER JOIN\n"
                + "                      ECT_USER ON REPORT_?.CREATED_USER = ECT_USER.USER_ID INNER JOIN\n"
                + "                      ECT_FLOW_STATUS ON REPORT_?.FLOW_STATUS_ID = ECT_FLOW_STATUS.FLOW_STATUS_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_3 ON REPORT_?.REJECTED_USER = ECT_USER_3.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_2 ON REPORT_?.APPROVED_USER = ECT_USER_2.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_1 ON REPORT_?.UPDATED_USER = ECT_USER_1.USER_ID\n"
                + "UNION ALL");

        for(int i=1;i<10;i++){
        
            System.out.println(sql.toString().replaceAll("\\?", "00"+i));
            
        }
     
        
        
    }
}
