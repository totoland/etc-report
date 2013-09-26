/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.common.dao;

import com.ect.db.common.entity.DropDownList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CommonDao {
    List<DropDownList> getDropdownList(DropDownList dropDownList);
}
