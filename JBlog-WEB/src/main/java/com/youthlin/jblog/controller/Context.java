package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created by lin on 2016-09-04-004.
 * 基于会话的全局信息
 */
@ManagedBean
@SessionScoped
public class Context {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public static User getCurrentUser() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        Object o = session.getAttribute(Constant.CURRENT_USER);
        try {
            return (User) o;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public static void setCurrentUser(User currentUser) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(Constant.CURRENT_USER, currentUser);
    }

    public User getUser() {
        return Context.getCurrentUser();
    }
}
