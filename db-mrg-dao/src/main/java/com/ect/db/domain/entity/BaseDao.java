package com.ect.db.domain.entity;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao extends HibernateDaoSupport {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    public List findNativeQuery(final String SQL,final Class entity,final Object... value){
        List t =  (List) getHibernateTemplate().execute(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);
                
                for(int i=0;i<value.length;i++){
                    query.setParameter(i, value[i]);
                }
                
                return query.list();
            }
        
        });
        return t;
    }
    
    public List findNativeQuery(final String SQL,final Class entity){
        List t =  (List) getHibernateTemplate().execute(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);
                                
                return query.list();
            }
        
        });
        return t;
    }
}