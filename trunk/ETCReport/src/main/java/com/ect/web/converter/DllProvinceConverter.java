/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.converter;

import com.ect.db.common.entity.DropDownList;
import com.ect.db.entity.EctProvince;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author totoland
 */
@FacesConverter(value = "dllProvinceConverter")
public class DllProvinceConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EctProvince ectProvince = new EctProvince();

        if (value == null || value.equals("")) {
            return ectProvince;
        }
        
        List<DropDownList> students = (ArrayList<DropDownList>) ((UISelectItems) component.getChildren().get(1)).getValue();

        ectProvince.setProvinceId(Integer.valueOf(value));
        
        if (students != null && !students.isEmpty()) {
            ectProvince.setProvinceName(students.get(students.size() - 1).getName());
        }

        return ectProvince;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String val = null;
        if (value != null && (value instanceof DropDownList)) {
            DropDownList downList = (DropDownList) value;
            val = downList.getValue();
        }
        return val;
    }
}
