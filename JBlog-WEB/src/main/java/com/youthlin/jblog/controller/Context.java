package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 2016-09-04-004.
 * 基于会话的全局信息
 */
@ManagedBean
@SessionScoped
public class Context {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private static HttpSession session;

    public static void staticSetSession(HttpSession session) {
        Context.session = session;
    }

    public static HttpSession staticGetSession() {
        return session;
    }

    public static User staticGetCurrentUser() {
        return (User) session.getAttribute(Constant.CURRENT_USER);
    }

    public static void staticSetCurrentUser(User currentUser) {
        session.setAttribute(Constant.CURRENT_USER, currentUser);
    }

    public User getCurrentUser() {
        return Context.staticGetCurrentUser();
    }

}
