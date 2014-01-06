/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.web.facade;

import com.ect.db.common.dao.GennericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author totoland
 */
public abstract class AbstractFacade<T> {
    
    @Autowired
    private GennericDao<T> gennericDao;
        
    protected abstract Class<T> getEntityManager();

    public void create(T entity) {
        gennericDao.create(entity);
    }

    public void edit(T entity) {
        gennericDao.edit(entity);
    }

    public void remove(T entity) {
        gennericDao.remove(entity);
    }

    public T find(Object id) {
        return (T) gennericDao.find(Long.parseLong(id.toString()),getEntityManager());
    }

    public List<T> findAll() {
        return gennericDao.findAll(getEntityManager());
    }

    public List<T> findRange(int[] range) {
        return gennericDao.findRange(range);
    }

    public int count() {
        return gennericDao.count(getEntityManager());
    }
    
}
