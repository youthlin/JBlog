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
    private static final Map<String, Object> map = new HashMap<>();

    public static Map<String, Object> getMap() {
        return map;
    }

    public static User getCurrentUser() {
        return (User) map.get(Constant.CURRENT_USER);
    }

    public static void setCurrentUser(User currentUser) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(Constant.CURRENT_USER, currentUser);
        map.put(Constant.CURRENT_USER, currentUser);
    }

    public User getUser() {
        return Context.getCurrentUser();
    }

    public Map<String, Object> getHashMap() {
        return Context.map;
    }

    static boolean allTextPostListShouldBeUpdated = false;
    static boolean allImagePostListShouldBeUpdated = false;
    static boolean textCategoryListShouldBeUpdated = false;
    static boolean imageCategoryListShouldBeUpdated = false;
}
