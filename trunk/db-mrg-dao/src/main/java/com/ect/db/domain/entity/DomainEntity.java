/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.domain.entity;

import com.google.gson.Gson;
import java.io.Serializable;
import javax.persistence.Transient;

/**
 *
 * @author Totoland
 */
public class DomainEntity implements Serializable{
    private static final long serialVersionUID = 5726217663187352331L;
    protected static Gson gson = new Gson();
    
    @Transient
    private int key;
    
    public String toJson(){
        return gson.toJson(this);
    }
    
    @Override
    public String toString() {
        return this.toJson();
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }
}
