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
                + "                      ECT_USER.USER_GROUP_ID, ECT_USER.USER_GROUP_LVL, REPORT_?.REPORT_MONTH, REPORT_?.REPORT_YEAR, \n"
                + "                      CASE WHEN ISNULL(REPORT_?.CREATED_DATE, '') > ISNULL(REPORT_?.APPROVED_DATE, '') AND ISNULL(REPORT_?.CREATED_DATE, '') \n"
                + "                      > ISNULL(REPORT_?.REJECTED_DATE, '') THEN REPORT_?.CREATED_DATE WHEN ISNULL(REPORT_?.APPROVED_DATE, '') \n"
                + "                      > ISNULL(REPORT_?.CREATED_DATE, '') AND ISNULL(REPORT_?.APPROVED_DATE, '') > ISNULL(REPORT_?.REJECTED_DATE, '') \n"
                + "                      THEN REPORT_?.APPROVED_DATE WHEN ISNULL(REPORT_?.REJECTED_DATE, '') > ISNULL(REPORT_?.CREATED_DATE, '') AND \n"
                + "                      ISNULL(REPORT_?.REJECTED_DATE, '') > ISNULL(REPORT_?.APPROVED_DATE, '') THEN REPORT_?.REJECTED_DATE END AS ACTION_DATE, \n"
                + "                      ECT_USER_GROUP.USER_GROUP_NAME\n"
                + "FROM         REPORT_? INNER JOIN\n"
                + "                      ECT_USER ON REPORT_?.CREATED_USER = ECT_USER.USER_ID INNER JOIN\n"
                + "                      ECT_FLOW_STATUS ON REPORT_?.FLOW_STATUS_ID = ECT_FLOW_STATUS.FLOW_STATUS_ID INNER JOIN\n"
                + "                      ECT_USER_GROUP ON ECT_USER.USER_GROUP_ID = ECT_USER_GROUP.USER_GROUP_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_3 ON REPORT_?.REJECTED_USER = ECT_USER_3.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_2 ON REPORT_?.APPROVED_USER = ECT_USER_2.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_1 ON REPORT_?.UPDATED_USER = ECT_USER_1.USER_ID"
                + " UNION ALL\n");

        for (int i = 1; i < 10; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "00" + i));
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "0" + i));
        }

        sql = new StringBuilder();

        sql.append("ALTER TABLE REPORT_? ADD REPORT_MONTH [int] NULL;\n"
                + "ALTER TABLE REPORT_? ADD REPORT_YEAR [int] NULL;\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานประจำเดือน' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?', @level2type=N'COLUMN',@level2name=N'REPORT_MONTH'\n"
                + "GO\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานของปี' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?', @level2type=N'COLUMN',@level2name=N'REPORT_YEAR'\n"
                + "GO\n"
                + "\n"
                + "ALTER TABLE REPORT_?_HIS ADD REPORT_MONTH [int] NULL;\n"
                + "ALTER TABLE REPORT_?_HIS ADD REPORT_YEAR [int] NULL;\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานประจำเดือน' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?_HIS', @level2type=N'COLUMN',@level2name=N'REPORT_MONTH'\n"
                + "GO\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานของปี' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?_HIS', @level2type=N'COLUMN',@level2name=N'REPORT_YEAR'\n"
                + "GO\n"
                + "\n"
                + "UPDATE REPORT_? SET REPORT_MONTH = right('0'+ rtrim(DATEPART(month,CREATED_DATE)), 2),REPORT_YEAR = DATEPART(year,CREATED_DATE)+543\n"
                + "UPDATE REPORT_?_HIS SET REPORT_MONTH = right('0'+ rtrim(DATEPART(month,CREATED_DATE)), 2),REPORT_YEAR = DATEPART(year,CREATED_DATE)+543");

        for (int i = 1; i < 10; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "00" + i));
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "0" + i));
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("UPDATE [REPORT_00" + i + "] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("UPDATE [REPORT_0" + i + "] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("UPDATE [REPORT_00" + i + "_HIS] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("UPDATE [REPORT_0" + i + "_HIS] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00" + i + "_DETAIL]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0" + i + "_DETAIL]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00" + i + "_DETAIL_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0" + i + "_DETAIL_HIS]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00" + i + "]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0" + i + "]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00" + i + "_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0" + i + "_HIS]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DROP TABLE [REPORT_00" + i + "_DETAIL]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DROP TABLE [REPORT_0" + i + "_DETAIL]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DROP TABLE [REPORT_00" + i + "_DETAIL_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DROP TABLE [REPORT_0" + i + "_DETAIL_HIS]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DROP TABLE [REPORT_00" + i + "]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DROP TABLE [REPORT_0" + i + "]");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("DROP TABLE [REPORT_00" + i + "_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DROP TABLE [REPORT_0" + i + "_HIS]");
        }

        int a = 10;
        int b = 3;

        System.out.println((double) (a * 100) / b);

        System.out.println("###############################################");

        for (int i = 1; i < 10; i++) {
            String s = "SELECT *, (SELECT STATUS FROM DCS_STEP_0" + i + " WHERE DCS_MASTER_ID = @DCS_MASTER_ID ) AS STATUS FROM DCS_STEP WHERE DCS_STEP.STEP_ID = " + i + "";

            System.out.println(s);
            System.out.println("UNION ALL");
        }

        for (int i = 10; i <= 15; i++) {
            String s = "SELECT *, (SELECT STATUS FROM DCS_STEP_" + i + " WHERE DCS_MASTER_ID = @DCS_MASTER_ID ) AS STATUS FROM DCS_STEP WHERE DCS_STEP.STEP_ID = " + i + "";

            System.out.println(s);
            System.out.println("UNION ALL");
        }

        System.out.println("##############################################");

        for (int i = 1; i < 19; i++) {
            String sqlS = ","
                    + "	( "
                    + "	SELECT     COUNT(DCS_MASTER.MASTER_ID) AS COUNT_DEC_" + i + " "
                    + " FROM  DCS_MASTER_DECISION_TYPE INNER JOIN "
                    + " DCS_MASTER ON DCS_MASTER_DECISION_TYPE.MASTER_ID = DCS_MASTER.MASTER_ID "
                    + " WHERE DECISION_TYPE_ID = " + i + " "
                    + " AND VC.VOTE_CASE_ID = VOTE_CASE "
                    + " AND VOTE_TYPE_ID in ('+@VOTE_TYPE_ID+') "
                    + " AND NOTICE = ('+@NOTICE+') AND CREATE_DECISION_DATE BETWEEN '''+@STARTDATE+''' and '''+@ENDDATE+'''\n"
                    + "	) AS COUNT_DEC_" + i + "";
            System.out.println(sqlS);
        }

        System.out.println("##############################################");

        for (int i = 1; i < 22; i++) {

            String sqlS = ",\n"
                    + "      ( SELECT COUNT(1) FROM  DCS_MASTER_DECISION_TYPE INNER JOIN  DCS_MASTER ON DCS_MASTER_DECISION_TYPE.MASTER_ID = DCS_MASTER.MASTER_ID INNER JOIN\n"
                    + "DCS_MASTER_MEMBER ON DCS_MASTER.MASTER_ID = DCS_MASTER_MEMBER.MASTER_ID WHERE 1=1 AND (DCS_MASTER_DECISION_TYPE.DECISION_TYPE_ID = " + i + ") AND (DCS_MASTER.VOTE_TYPE_ID IN ('+@VOTE_TYPE_ID+'))\n"
                    + "AND (DCS_MASTER.NOTICE = '+@NOTICE+') 	AND (DCS_MASTER_MEMBER.MEMBER_TYPE_ID = MT.MEMBER_TYPE_ID) '+@where_date+' ) AS COUNT_DEC_" + i + "";

            System.out.println(sqlS);
        }

        System.out.println("############################################### 3.2");

        for (int i = 1; i < 8; i++) {
            String sqlS = ",( SELECT COUNT(1) AS COUNT_COURT_" + i + " FROM DCS_MASTER INNER JOIN DCS_MASTER_MEMBER ON DCS_MASTER.MASTER_ID = DCS_MASTER_MEMBER.MASTER_ID WHERE (DCS_MASTER.COURT_B_SUPREME_TYPE_ID = " + i + ") AND (DCS_MASTER.NOTICE IN ('+@NOTICE+')) AND (DCS_MASTER.VOTE_TYPE_ID IN ('+@VOTE_TYPE_ID+')) AND DCS_MASTER_MEMBER.MEMBER_TYPE_ID = MT.MEMBER_TYPE_ID '+@where_date+') AS COUNT_COURT_" + i + "";

            System.out.println(sqlS);
        }

        System.out.println("############################################### 1.2");

        for (int i = 1; i < 8; i++) {
            String sqlS = ",( SELECT COUNT(1) FROM DCS_MASTER WHERE (DCS_MASTER.NOTICE IN ('+@NOTICE+')) AND (DCS_MASTER.VOTE_TYPE_ID IN ('+@VOTE_TYPE_ID+')) AND COURT_TYPE_ID = 2 AND DCS_MASTER.COURT_A_SUPREME_TYPE_ID = " + i + " AND VC.VOTE_CASE_ID = DCS_MASTER.VOTE_CASE '+@where_date+') AS COUNT_COURT_" + i;

            System.out.println(sqlS);
        }

        System.out.println("############################################### 4.1");

        for (int i = 1; i < 5; i++) {
            String sqlS = ",(SELECT COUNT(1) FROM DCS_COURT_CONSTITU_TYPE INNER JOIN DCS_MASTER ON DCS_COURT_CONSTITU_TYPE.COURT_CONSTITU_TYPE_ID = DCS_MASTER.COURT_CONSTITU_TYPE WHERE COURT_TYPE_ID = 3 AND COURT_CONSTITU_TYPE_ID = " + i + " AND DCS_MASTER.NOTICE IN ('+@NOTICE+') AND DCS_MASTER.VOTE_TYPE_ID IN ('+@VOTE_TYPE_ID+') AND VC.VOTE_CASE_ID = VOTE_CASE_ID '+@where_date+') AS COUNT_CONSTITU_TYPE" + i + " ";

            System.out.println(sqlS);
        }

        //INSERT INTO [ECT_USER]([USERNAME],[PASSWORD],[IS_ACTIVE],[FNAME],[LNAME],[SEX],[USER_GROUP_LVL]) VALUES ('test0','UrqAhTR93pakz8nDqR+qFw==',1,'ทดสอบ0','ทดสอบ0',0,2)
        System.out.println("############################################### USER");

        for (int i = 1; i < 10; i++) {
            String sqlS = "DELETE FROM DCS_STEP_0" + i + "\n"
                    + "DBCC CHECKIDENT('DCS_STEP_0" + i + "', RESEED, 0)";

            System.out.println(sqlS);
        }

        for (int i = 11; i < 16; i++) {
            String sqlS = "DELETE FROM DCS_STEP_" + i + "\n"
                    + "DBCC CHECKIDENT('DCS_STEP_" + i + "', RESEED, 0)";
            System.out.println(sqlS);
        }

        String view = "ref_no,"
                + "evnkeys,"
                + "notice_no,"
                + "claim_no,"
                + "claim_type,"
                + "claim_subtype,"
                + "policy_no,"
                + "vehicle_no,"
                + "report_name,"
                + "report_tel,"
                + "report_from_id,"
                + "report_from_desc,"
                + "report_relation_id,"
                + "report_relation_desc,"
                + "driver_name,"
                + "driver_tel,"
                + "driver_license,"
                + "no_party_id,"
                + "no_party_desc,"
                + "claim_party_type_id,"
                + "claim_party_type_desc,"
                + "party_name,"
                + "party_regno,"
                + "loss_date,"
                + "loss_time,"
                + "loss_description,"
                + "loss_place,"
                + "loss_province_id,"
                + "loss_amphur_id,"
                + "loss_tambol_id,"
                + "loss_check_place_id,"
                + "loss_check_place_desc,"
                + "Remark,"
                + "report_date,"
                + "report_time,"
                + "call_center_id,"
                + "call_center_name,"
                + "call_center_zone_id,"
                + "call_center_zone_name,"
                + "claim_user_id,"
                + "claim_user_name,"
                + "claim_user_zone_id,"
                + "claim_user_zone_name,"
                + "claim_user_work_zone_id,"
                + "submit_date,"
                + "submit_time,"
                + "row_sync,"
                + "edit_id,"
                + "edit_name,"
                + "edit_date,"
                + "edit_time,"
                + "receive_date,"
                + "receive_time,"
                + "arrive_date,"
                + "arrive_time,"
                + "finish_date,"
                + "finish_time,"
                + "send_date,"
                + "send_time,"
                + "cancel_user_id,"
                + "cancel_user_name,"
                + "cancel_id,"
                + "cancel_desc,"
                + "order_user_id,"
                + "order_user_name,"
                + "order_zone_id,"
                + "order_zone_name,"
                + "order_date,"
                + "order_time,"
                + "order_type,"
                + "cancel_date,"
                + "cancel_time,"
                + "IsPolicy,"
                + "outsource_claim_no,"
                + "outsource_surveyor_name,"
                + "outsource_surveyor_tel,"
                + "outsource_receive,"
                + "appointment_tam_id,"
                + "appointment_tam_name,"
                + "appointment_amp_id,"
                + "appointment_amp_name,"
                + "appointment_prov_id,"
                + "appointment_prov_name,"
                + "appointment_remark,"
                + "appointment_date,"
                + "appointment_time,"
                + "createDate,"
                + "createTime";
        
        String arr[] = view.split(",");
        
        for(String array : arr){
            System.out.println("arrResult[i]."+array+" = list[i]."+array+";");
        }
        
    }
}
