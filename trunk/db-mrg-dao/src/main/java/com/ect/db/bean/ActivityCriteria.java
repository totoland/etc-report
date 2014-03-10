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
public class ActivityCriteria implements Serializable{
    private static final long serialVersionUID = -8056997381744014128L;
    
    private String strategic;
    private String subStrategic;
    private String plan;
    private String project;
    private String activity;

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

    @Override
    public String toString() {
        return "ActivityCriteria{" + "strategic=" + strategic + ", subStrategic=" + subStrategic + ", plan=" + plan + ", project=" + project + ", activity=" + activity + '}';
    }
    
}
