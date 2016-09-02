package com.youthlin.jblog.dao;

import java.io.Serializable;

/**
 * Created by lin on 2016-09-02-002.
 * BaseDao 包含基本的增删改查功能
 * T ：实体
 * PK：主键
 */
public interface BaseDao<T extends Serializable, PK extends Serializable> {
    T save(T entity);

    T delete(T entity);

    T delete(Class<T> clazz, PK primaryKey);

    T update(T entity);

    T find(Class<T> clazz, PK primaryKey);
}
