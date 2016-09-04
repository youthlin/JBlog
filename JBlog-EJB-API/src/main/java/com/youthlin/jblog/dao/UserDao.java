package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.User;

import javax.ejb.Remote;

/**
 * Created by lin on 2016-09-01-001.
 * DAO
 */
@Remote
public interface UserDao extends BaseDao<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findAdmin();
}
