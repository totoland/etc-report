/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.report.dao.Report001Dao;
import com.ect.db.report.entity.ViewReport001;
import com.ect.web.service.Report001Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service("report001Service")
public class Report001ServiceImpl implements Report001Service{

    @Autowired
    Report001Dao report001Dao;
    
    @Override
    public List<ViewReport001> findByStatus(Integer status) {
        return report001Dao.findByStatus(status);
    }
    
}
