/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import com.ect.db.report.entity.ViewReport001;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface Report001Service {
    
    List<ViewReport001> findByStatus(Integer status);
    
}
