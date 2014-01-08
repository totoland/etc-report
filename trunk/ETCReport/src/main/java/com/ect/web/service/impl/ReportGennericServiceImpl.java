/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service.impl;

import com.ect.db.common.dao.GennericDao;
import com.ect.web.service.ReportGennericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service(value = "reportGennericService")
public class ReportGennericServiceImpl<T> implements ReportGennericService<T> {

    @Autowired
    GennericDao<T> gennericDao;

    @Override
    public void create(T entity) {
        gennericDao.create(entity);
    }

    @Override
    public void edit(T entity) {
        gennericDao.edit(entity);
    }

    @Override
    public void remove(T entity) {
        gennericDao.remove(entity);
    }

    @Override
    public T find(Long id, Class<T> entityClass) {
        return gennericDao.find(id, entityClass);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return gennericDao.findAll(entityClass);
    }

    @Override
    public List<T> findRange(int[] range) {
        return gennericDao.findRange(range);
    }

    @Override
    public int count(Class<T> entityClass) {
        return gennericDao.count(entityClass);
    }
}
