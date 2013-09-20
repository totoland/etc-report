/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.controller.menu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Totoland
 */
@ManagedBean
@SessionScoped
public class MenuController implements Serializable {
    
    private static final long serialVersionUID = -1518803556855089611L;
    private MenuModel model;
    private String contextPath = "/pages/";
    private Logger logger = Logger.getLogger(MenuController.class);
    private Map<String,Object>configMap = new HashMap<String, Object>();
    
    @PostConstruct
    public void init() {
        
    }

    /**
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }

    /**
     * @return the configMap
     */
    public Map<String,Object> getConfigMap() {
        return configMap;
    }

    /**
     * @param configMap the configMap to set
     */
    public void setConfigMap(Map<String,Object> configMap) {
        this.configMap = configMap;
    }
}
