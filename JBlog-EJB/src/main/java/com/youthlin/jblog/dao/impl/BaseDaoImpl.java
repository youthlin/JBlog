package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lin on 2016-09-02-002.
 * BaseDao实现类 实现了基本的增删改查功能 log和EntityManager是protected权限，子类可访问
 */
public class BaseDaoImpl<T extends Serializable, PK extends Serializable> implements BaseDao<T, PK> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext(unitName = "jblog")
    protected EntityManager em;

    @Override
    public T save(T entity) {
        log.debug("save {}", entity);
        em.persist(entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        log.debug("delete {}", entity);
        em.remove(entity);
        return entity;
    }

    @Override
    public T delete(Class<T> clazz, PK primaryKey) {
        log.debug("delete: class={},id={}", clazz, primaryKey);
        T entity = em.find(clazz, primaryKey);
        em.remove(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        log.debug("update {}", entity);
        em.merge(entity);
        return entity;
    }

    @Override
    public T find(Class<T> clazz, PK primaryKey) {
        log.debug("find:class={},id={}", clazz, primaryKey);
        return em.find(clazz, primaryKey);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        log.debug("find all");
        TypedQuery<T> query = em.createQuery("select t from " + entityClass.getSimpleName() + " as t", entityClass);
        return query.getResultList();
    }

    public T getOne(Class<T> entityClass, String jpql, Map<String, Object> param) {
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        for (String key : param.keySet()) {
            query.setParameter(key, param.get(key));
        }
        return getSingleResult(query);
    }

//    public T getOne(Class<T> entityClass, String jpql, Object... param) {
//    }

    public List<T> getAsList(Class<T> entityClass, String jpql, Map<String, Object> param) {
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        for (String key : param.keySet()) {
            query.setParameter(key, param.get(key));
        }
        return query.getResultList();
    }

    /**
     * 获取单个实体，如果没有符合条件的实体将会返回null
     */
    <EntityClass> EntityClass getSingleResult(TypedQuery<EntityClass> query) {
        EntityClass entity = null;
        try {
            entity = query.getSingleResult();
            log.trace("获取单个实体成功:{}", entity);
        } catch (NoResultException e) {
            log.trace("找不到符合条件的实体");
        }
        return entity;
    }
}
