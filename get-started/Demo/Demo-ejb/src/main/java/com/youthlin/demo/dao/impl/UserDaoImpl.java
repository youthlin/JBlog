package com.youthlin.demo.dao.impl;

import com.youthlin.demo.dao.UserDao;
import com.youthlin.demo.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by lin on 2016-09-14-014.
 * user dao Impl
 */
@Stateless
public class UserDaoImpl implements UserDao {
    @PersistenceContext(name = "demo")
    private EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select u from User as u", User.class);
        return query.getResultList();
    }
}
