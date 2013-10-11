/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.db.common.dao.hibernate;

import com.ect.db.common.dao.CommonDao;
import com.ect.db.common.entity.DropDownList;
import com.ect.test.dao.utils.BaseDao;
import com.ect.test.entity.Contract;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Totoland
 */
@Repository("commonDao")
public class CommonDaoImpl extends BaseDao implements CommonDao {

    private static Logger logger = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Override
    public List<DropDownList> getDropdownList(DropDownList dropDownList) {
        logger.info("getDropdownList");
        
        String orderBy = dropDownList.getOrderByField()==null?dropDownList.getName():dropDownList.getOrderByField();
        String sortBy = dropDownList.getSortName()==null?"ASC":dropDownList.getSortName();
        String schemaName = dropDownList.getSchema()==null?"":dropDownList.getSchema();
        String tableName = schemaName!=null?schemaName+"."+dropDownList.getTableName():dropDownList.getTableName();
        
        final String sql = "SELECT "+dropDownList.getName()+" AS feild_name , "+dropDownList.getValue()+" AS feild_value , '"+dropDownList.getTableName()+"'  AS table_name"
                + ", '"+orderBy+"' AS order_by ,'"+sortBy+"' AS sort_by "
                + ",'"+schemaName+"' AS schema_name " 
                + "from "+tableName +" order by "+orderBy + " "+sortBy;

        List<DropDownList> result;

        result = (List<DropDownList>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                SQLQuery sq = session.createSQLQuery(sql);
                sq.addEntity(DropDownList.class);
                List<DropDownList> dropDownLists = sq.list();
                
                return dropDownLists;
            }
        });

        return result;
    }
}
