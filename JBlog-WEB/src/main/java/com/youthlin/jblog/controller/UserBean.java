package com.youthlin.jblog.controller;

import com.youthlin.jblog.dao.UserDao;
import com.youthlin.jblog.model.User;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by lin on 2016-09-01-001.
 * 登录注册
 */
@ManagedBean
@RequestScoped
public class UserBean {
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);
    private User user = new User();
    private UserDao dao = EJBUtil.getBean(UserDao.class);

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserBean() {
        log.debug("构造UserBean");
    }

    public String register() {
        log.debug("调用注册方法");
        dao.save(user);
        log.debug("调用注册方法完毕");
        return "register";
    }

    public String login() {
        log.debug("登录");
        User u = dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u != null) {
            log.debug("登录成功");
            return "index";
        } else {
            log.debug("登录失败");
            return "login";
        }
    }

}
