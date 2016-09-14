package com.youthlin.demo.dao;

import com.youthlin.demo.model.User;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by lin on 2016-09-14-014.
 * user dao
 */
@Remote
public interface UserDao {
    User save(User user);

    List<User> findAll();
}
