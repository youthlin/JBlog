package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.UserDao;
import com.youthlin.jblog.model.User;
import com.youthlin.jblog.util.EJBUtil;
import com.youthlin.jblog.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Created by lin on 2016-09-01-001.
 * 登录注册
 */
@ManagedBean
@SessionScoped
public class UserBean {
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);
    private UserDao dao = EJBUtil.getBean(UserDao.class);
    private User user = new User();
    private String loginMsg;

    public UserBean() {
        log.debug("构造UserBean");
        Context.getMap().put(Constant.ADMIN, dao.findAdmin());
    }

    public void validateUsername(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String name = o.toString();
        User u = dao.findByUsername(name);
        String usernameMsg;
        if (u != null) {
            usernameMsg = "<span class='text-danger'>该用户名已被注册.</span>";
            throw new ValidatorException(new FacesMessage("用户名不可用", usernameMsg));
        }
    }

    public void validatePassword(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String password = o.toString();
        String passwordMsg = "";
        if (password.length() < 8) {
            passwordMsg += "密码最少8位长.";
        }
        if (!password.matches(".*([a-zA-Z]+).*") || !password.matches(".*([0-9]+).*")) {
            passwordMsg += "密码必须包含字母与数字.";
            throw new ValidatorException(new FacesMessage("密码不符合要求", passwordMsg));
        }
    }

    public void validateEmail(FacesContext context, UIComponent uiComponent, Object o) throws ValidatorException {
        String email = o.toString();
        String emailMsg = "";
        if (!email.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {
            emailMsg = "请输入正确的邮箱";
            throw new ValidatorException(new FacesMessage("邮箱格式不正确", emailMsg));
        }
    }

    public String register() {
        log.debug("调用注册方法,User={}", user);
        user.setPassword(StringUtil.md5(user.getUsername() + user.getPassword()));
        User admin = getAdmin();
        if (admin == null) {
            log.debug("第一个注册用户是管理员");
            user.setStatus((byte) 0);
        }
        user = dao.save(user);
        Context.setCurrentUser(user);
        if (admin == null) {
            Context.getMap().put(Constant.ADMIN, dao.findAdmin());
        }
        log.debug("调用注册方法完毕,User={}", user);
        return "index";
    }

    public String login() {
        log.debug("登录");
        User u = dao.findByUsername(user.getUsername());
        if (u == null) {
            log.debug("用户名不存在");
            loginMsg = "用户不存在";
            return "login";
        }
        u = dao.findByUsernameAndPassword(user.getUsername(), StringUtil.md5(user.getUsername() + user.getPassword()));
        if (u != null) {
            log.debug("登录成功");
            loginMsg = "";
            Context.setCurrentUser(user = u);
            return "index";
        } else {
            log.debug("登录失败");
            loginMsg = "用户名或密码错误";
            return "login";
        }
    }

    //region //getter and setter
    public User getUser() {
        return user;
    }

    public User getAdmin() {
        return (User) Context.getMap().get(Constant.ADMIN);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoginMsg() {
        return loginMsg;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }
    //endregion
}
