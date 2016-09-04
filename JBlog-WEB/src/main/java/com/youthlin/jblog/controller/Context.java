package com.youthlin.jblog.controller;

import com.youthlin.jblog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by lin on 2016-09-04-004.
 * 基于会话的全局信息
 */
@ManagedBean
@SessionScoped
public class Context {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Context.currentUser = currentUser;
    }

    public User getUser() {
        return Context.currentUser;
    }
}
