/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.web.service;

import java.util.List;

/**
 *
 * @author totoland
 */
public interface ReportGennericService<T> {
    
    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Long id,Class<T> entityClass);

    public List<T> findAll(Class<T> entityClass);

    public List<T> findRange(int[] range);

    public int count(Class<T> entityClass);
    
    public List<T> findByStatusId(Integer status,Class<T> entityClass);
}
