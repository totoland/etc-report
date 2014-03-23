/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.bean;

import java.io.Serializable;

/**
 *
 * @author totoland
 */
public class ReportCriteria implements Serializable{
    private static final long serialVersionUID = -5197641653307976361L;
    
    private String year;
    private String month;
    private String resultsOfOperations;
    private String reportTemplat;
    private String strategic;
    private String subStrategic;
    private String plan;
    private String project;
    private String activity;
    private String status;
    private String flowStatus;
    private String reportCode;
    private int startRow = 1;
    private int maxRow = 10;
    private String sortField;
    private String sortOrder;
    private String userGroupId;
    private String userGroupLvl;

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the resultsOfOperations
     */
    public String getResultsOfOperations() {
        return resultsOfOperations;
    }

    /**
     * @param resultsOfOperations the resultsOfOperations to set
     */
    public void setResultsOfOperations(String resultsOfOperations) {
        this.resultsOfOperations = resultsOfOperations;
    }

    /**
     * @return the reportTemplat
     */
    public String getReportTemplat() {
        return reportTemplat;
    }

    /**
     * @param reportTemplat the reportTemplat to set
     */
    public void setReportTemplat(String reportTemplat) {
        this.reportTemplat = reportTemplat;
    }

    /**
     * @return the strategic
     */
    public String getStrategic() {
        return strategic;
    }

    /**
     * @param strategic the strategic to set
     */
    public void setStrategic(String strategic) {
        this.strategic = strategic;
    }

    /**
     * @return the subStrategic
     */
    public String getSubStrategic() {
        return subStrategic;
    }

    /**
     * @param subStrategic the subStrategic to set
     */
    public void setSubStrategic(String subStrategic) {
        this.subStrategic = subStrategic;
    }

    /**
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reportCode
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * @param reportCode the reportCode to set
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * @return the startRow
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * @param startRow the startRow to set
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * @return the maxRow
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * @param maxRow the maxRow to set
     */
    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    /**
     * @return the sortField
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * @param sortField the sortField to set
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * @return the sortOrder
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder the sortOrder to set
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return the flowStatus
     */
    public String getFlowStatus() {
        return flowStatus;
    }

    /**
     * @param flowStatus the flowStatus to set
     */
    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    /**
     * @return the userGroupId
     */
    public String getUserGroupId() {
        return userGroupId;
    }

    /**
     * @param userGroupId the userGroupId to set
     */
    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * @return the userGroupLvl
     */
    public String getUserGroupLvl() {
        return userGroupLvl;
    }

    /**
     * @param userGroupLvl the userGroupLvl to set
     */
    public void setUserGroupLvl(String userGroupLvl) {
        this.userGroupLvl = userGroupLvl;
    }

    @Override
    public String toString() {
        return "ReportCriteria{" + "year=" + year + ", month=" + month + ", resultsOfOperations=" + resultsOfOperations + ", reportTemplat=" + reportTemplat + ", strategic=" + strategic + ", subStrategic=" + subStrategic + ", plan=" + plan + ", project=" + project + ", activity=" + activity + ", status=" + status + ", flowStatus=" + flowStatus + ", reportCode=" + reportCode + ", startRow=" + startRow + ", maxRow=" + maxRow + ", sortField=" + sortField + ", sortOrder=" + sortOrder + ", userGroupId=" + userGroupId + ", userGroupLvl=" + userGroupLvl + '}';
    }
    
}
