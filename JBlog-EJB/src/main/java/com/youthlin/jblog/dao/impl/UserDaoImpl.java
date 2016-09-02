package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.dao.UserDao;
import com.youthlin.jblog.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

/**
 * Created by lin on 2016-09-01-001.
 * DAO实现类
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User findByUsername(String username) {
        log.debug("根据用户名查找用户...");
        TypedQuery<User> query = em.createQuery("select u from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        return getSingleResult(query);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        log.debug("根据用户名和密码查找用户...");
        TypedQuery<User> query = em.createQuery("select u from User as u where u.username=:username and u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return getSingleResult(query);
    }

}
