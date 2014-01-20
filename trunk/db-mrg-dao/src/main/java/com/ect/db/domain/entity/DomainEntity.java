/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.domain.entity;

import com.ect.db.hibernate.utils.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import javax.persistence.Transient;

/**
 *
 * @author Totoland
 */
public class DomainEntity implements Serializable {

    private static final long serialVersionUID = 5726217663187352331L;
    
    protected GsonBuilder b = new GsonBuilder();
    
    @Transient
    private int key;

    public String toJson() {
        return getGson().toJson(this);
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

    /**
     * @return the gson
     */
    public Gson getGson() {
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        return b.create();
    }
}
