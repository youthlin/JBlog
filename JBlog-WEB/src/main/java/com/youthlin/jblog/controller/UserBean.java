package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.UserDao;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.model.User;
import com.youthlin.jblog.util.EJBUtil;
import com.youthlin.jblog.util.HTTPUtil;
import com.youthlin.jblog.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    private User currentUser;
    private String emailMsg;

    public UserBean() {
        log.debug("构造UserBean");
        HTTPUtil.getSession().setAttribute(Constant.ADMIN, dao.findAdmin());
    }

    public void validateUsername(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String name = o.toString();
        User u = dao.findByUsername(name);
        if (u != null) {
            throw new ValidatorException(new FacesMessage("用户名不可用",
                    "<span class='text-danger'>该用户名已被注册.</span>"));
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
        if (!email.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {
            throw new ValidatorException(new FacesMessage("邮箱格式不正确", "请输入正确的邮箱"));
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
        HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, user);
        currentUser = user;
        if (admin == null) {
            HTTPUtil.getSession().setAttribute(Constant.ADMIN, dao.findAdmin());
        }
        log.debug("调用注册方法完毕,User={}", user);

        return toReturnUrlIfNeeded();
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
            HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, user = u);
            currentUser = user;
            return toReturnUrlIfNeeded();
        } else {
            log.debug("登录失败");
            loginMsg = "用户名或密码错误";
            return "login";
        }
    }

    private String toReturnUrlIfNeeded() {
        String url = (String) HTTPUtil.getSession().getAttribute(Constant.RETURN_URL);
        if (url != null) {
            log.debug("return url={}", url);
            HTTPUtil.getSession().setAttribute(Constant.RETURN_URL, null);
            try {
                ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendRedirect(url);
                return url + "?faces-redirect=true";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }

    public String logout() {
        HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, null);
        currentUser = null;
        return "login";
    }

    public User getCurrentUser() {
        //return (User) HTTPUtil.getSession().getAttribute(Constant.CURRENT_USER);
        return currentUser;
    }

    /*判断用户是否收藏了该文章*/
    public boolean liked(Post post) {
        List<Post> list = currentUser.getLikedPost();
        log.debug("判断是否收藏");
        if (list != null) {
            for (Post p : list) {
                if (p.getId().equals(post.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /*收藏或取消收藏该文章*/
    public void like(Post post) {
        List<Post> list = getCurrentUser().getLikedPost();
        if (liked(post)) {
            log.debug("list size={}", currentUser.getLikedPost().size());
            log.debug("[{}]取消收藏《{}》", currentUser.getUsername(), post.getTitle());
            Iterator<Post> it = list.iterator();
            while (it.hasNext()) {
                Post p = it.next();
                if (p.getId().equals(post.getId())) {
                    it.remove();
                }
            }
            currentUser = dao.update(currentUser);
            log.debug("list size={}", currentUser.getLikedPost().size());
        } else {
            log.debug("[{}]收藏文章《{}》", currentUser.getUsername(), post.getTitle());
            if (list != null) {
                list.add(post);
                currentUser = dao.update(currentUser);
            }
        }
        HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, currentUser);
    }

    public String getEmailHash() {
        return StringUtil.md5(currentUser.getEmail());
    }

    //region //getter and setter
    public User getUser() {
        return user;
    }

    public User getAdmin() {
        return (User) HTTPUtil.getSession().getAttribute(Constant.ADMIN);
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

    public void update() {
        HttpServletRequest request = HTTPUtil.getRequest();
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        boolean e = false;
        boolean newpass = false;
        boolean modified = true;
        if (email != null) {
            if (email.equals(currentUser.getEmail())) {
                emailMsg = "";
            } else if (email.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {
                e = true;
                currentUser.setEmail(email);
                currentUser = dao.update(currentUser);
                emailMsg = "邮箱已修改.";
            } else {
                emailMsg += "email 未指定或格式不正确.";
            }
        } else {
            emailMsg = "email 未指定或格式不正确.";
        }
        if (oldPassword == null || oldPassword.length() != 32 || newPassword == null || newPassword.length() != 32) {
            modified = false;
        }
        if (modified && oldPassword.equals(currentUser.getPassword()) && !oldPassword.equals(newPassword)) {
            newpass = true;
            currentUser.setPassword(newPassword);
            emailMsg += "密码已修改.";
        }
        if (!newpass) {
            emailMsg += "密码没有修改.";
        }

        if (e || newpass) {
            currentUser = dao.update(currentUser);
        }
    }

    public String getEmailMsg() {
        String m = emailMsg;
        emailMsg = "";
        return m;
    }

    //endregion
}
