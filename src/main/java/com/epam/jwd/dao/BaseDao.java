package com.epam.jwd.dao;

import com.epam.jwd.entity.Entity;

import java.util.List;

public interface BaseDao<K, T extends Entity>{
    boolean save (T entity);
    List<T> findAll();
    T findById(K id);
    boolean deleteById(K id);

}
