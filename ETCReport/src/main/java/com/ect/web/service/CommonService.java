/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.web.service;

import com.ect.db.common.entity.DropDownList;
import java.util.List;

/**
 *
 * @author Totoland
 */
public interface CommonService {
    List<DropDownList> getDropdownList(DropDownList dropDownList);
}
