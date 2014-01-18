/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.db.common.dao;

import java.util.List;

/**
 *
 * @author totoland
 */
public interface GennericDao<T> {
    
    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Long id,Class<T> entityClass);
    
    public T find(Integer id, Class<T> entityClass);

    public List<T> findAll(Class<T> entityClass);

    public List<T> findRange(int[] range);

    public int count(Class<T> entityClass);
    
    public List<T> findByStatus(Integer status,Class<T> entityClass);
}
