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

        final String sql = "SELECT 'name' AS name , 'value' AS value , 'tableName' AS tableName from Stock";

        List<DropDownList> result;

        result = (List<DropDownList>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                SQLQuery sq = session.createSQLQuery(sql);
                //sq.addEntity(DropDownList.class);
                List<DropDownList> dropDownLists = new ArrayList<DropDownList>();
                List list = sq.list();
                for (int i = 0; i <list.size(); i++) {
                    DropDownList ddl = new DropDownList();
                    ddl.setName(((Object[])list.get(i))[0].toString());
                    ddl.setValue(((Object[])list.get(i))[1].toString());
                    ddl.setTableName(((Object[])list.get(i))[2].toString());
                    dropDownLists.add(ddl);
                }

                return dropDownLists;
            }
        });

        return result;
    }
}
