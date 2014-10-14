/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.test;

import com.ect.web.factory.DropdownFactory;
import com.ect.web.utils.NumberUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/config/BeanLocations.xml"
    })
public class App {
    
    @Autowired
    DropdownFactory dropdownFactory;
    
    @Test
    public void test(){
    
        System.out.println(dropdownFactory.ddlStrategic());
        
    }
    
    @Test
    public void devide(){
        BigDecimal a = new BigDecimal(123.13);
        BigDecimal b =  new BigDecimal(123.312);
        if(NumberUtils.convertNUllToZero(a).floatValue()== 0.00 || NumberUtils.convertNUllToZero(b).floatValue() == 0.00){
            return;
        }
        
        System.out.println(a.divide(b,2,RoundingMode.HALF_UP));
    }
    
}
