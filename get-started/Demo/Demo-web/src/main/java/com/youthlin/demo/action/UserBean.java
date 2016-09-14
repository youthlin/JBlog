package com.youthlin.demo.action;

import com.youthlin.demo.dao.UserDao;
import com.youthlin.demo.model.User;
import com.youthlin.demo.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by lin on 2016-09-14-014.
 * UserBean
 */
@ManagedBean
@SessionScoped
public class UserBean {
    private final Logger log = LoggerFactory.getLogger(UserBean.class);
    private UserDao userDao = EJBUtil.getBean(UserDao.class);
    private String name;

    public void save() {
        User user = new User();
        user.setName(name);
        userDao.save(user);
        log.debug("保存成功");
    }

    public List<User> getUsers() {
        List<User> users = userDao.findAll();
        log.debug("users = {}", users);
        return users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
