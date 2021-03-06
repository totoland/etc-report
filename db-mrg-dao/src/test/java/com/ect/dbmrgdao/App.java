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

        String view = "evnkeys,"
                + "party_keys,"
                + "party_keys_refid,"
                + "party_license,"
                + "party_title_id,"
                + "party_title_desc,"
                + "party_name,"
                + "party_surname,"
                + "party_id_card,"
                + "party_relation_id,"
                + "party_work_id,"
                + "party_work_desc,"
                + "party_tel,"
                + "party_sex,"
                + "party_addr,"
                + "party_tam_id,"
                + "party_tam_desc,"
                + "party_amp_id,"
                + "party_amp_name,"
                + "party_prov_id,"
                + "party_prov_desc,"
                + "party_age,"
                + "party_birthday,"
                + "injury_type,"
                + "is_die,"
                + "is_disabled,"
                + "injury_detail,"
                + "injury_estimate,"
                + "is_hospital,"
                + "hospital_type_id,"
                + "hospital_type_desc,"
                + "hospital_name,"
                + "patient_type_id,"
                + "patient_type_desc,"
                + "is_end,"
                + "end_desc,"
                + "end_remark,"
                + "send_analyze,"
                + "send_analyze_desc,"
                + "document_other,"
                + "is_sign,"
                + "no_sign_id,"
                + "no_sign_desc,"
                + "img_party_sign,"
                + "cause_print";

        String arr[] = view.split(",");

        for (String array : arr) {
            System.out.println("arrResult[i]." + array + " = list[i]." + (array.charAt(0) + "").toUpperCase() + array.substring(1, array.length()) + ";");
        }

    }
}
