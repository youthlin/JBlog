package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CommentDao;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.dao.SettingsDao;
import com.youthlin.jblog.model.Comment;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.util.EJBUtil;
import com.youthlin.jblog.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lin on 2016-09-08-008.
 * 单篇文章
 */
@ManagedBean
@RequestScoped
public class ArticleBean {
    private static final Logger log = LoggerFactory.getLogger(ArticleBean.class);
    private static PostDao postDao = EJBUtil.getBean(PostDao.class);
    private static CommentDao commentDao = EJBUtil.getBean(CommentDao.class);
    private static SettingsDao settingsDao = EJBUtil.getBean(SettingsDao.class);

    private Post post = new Post();
    private long prevId = -1L;
    private long nextId = -1L;
    private Page<Comment> comments = new Page<>();

    public ArticleBean() {
        log.debug("构造ArticleBean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String returnUrl = request.getParameter(Constant.RETURN_URL);
        if (returnUrl != null) {
            log.debug("return url = {}", returnUrl);
            HTTPUtil.getSession().setAttribute(Constant.RETURN_URL, returnUrl);
            return;
        }
        String strId = request.getParameter("id");
        long id = -1;
        try {
            id = Long.parseLong(strId);
        } catch (Exception e) {
            log.debug("参数错误，文章id参数只接受数字");
        }
        if (id != -1) {
            post = postDao.find(Post.class, id);
            if (post == null || post.getStatus().equals(Constant.POST_DELETED)) {
                log.debug("未找到id={}对应的文章", id);
                post = postDao.getNewestText();
            }
        } else post = postDao.getNewestText();
        if (post != null) {
            //浏览量加一
            log.trace("《{}》浏览量加一,comment count = {}", post.getTitle(), post.getCommentCount());
            post.setHint(post.getHint() + 1);
            postDao.update(post);
        }
        long[] prevAndNextId = postDao.getPrevAndNextId(post);
        prevId = prevAndNextId[0];
        nextId = prevAndNextId[1];

        String strCommentPage = request.getParameter("c-page");
        int commentPage = 1;
        if (strCommentPage != null) {
            try {
                commentPage = Integer.parseInt(strCommentPage);
            } catch (Exception e) {
                log.debug("参数错误，c-page参数只接受数字，表示评论第几页");
                commentPage = 1;
            }
        }

        int commentCountPerPage = 5;
//        String strCommentSize = settingsDao.get(Constant.SETTINGS_COMMENT_COUNT_PER_PAGE);
//        if (strCommentSize == null) {
//            //javax.persistence.TransactionRequiredException:
//            //WFLYJPA0060: Transaction is required to perform this operation
//            //(either use a transaction or extended persistence context)
//            settingsDao.add(Constant.SETTINGS_COMMENT_COUNT_PER_PAGE, Constant.SETTINGS_COMMENT_COUNT_PER_PAGE_DEFAULT);
//        } else {
//            try {
//                commentCountPerPage = Integer.parseInt(strCommentSize);
//            } catch (Exception e) {
//                log.debug("参数错误，c-page参数只接受数字，表示评论第几页");
//            }
//        }
        comments = commentDao.findPageByPostId(post.getId(), commentPage, commentCountPerPage);
        log.debug("comment page = {}", comments);
    }

    public String toLogin() {
        setReturnUrl();
        return "login";
    }

    public String toRegister() {
        setReturnUrl();
        return "register";
    }

    private void setReturnUrl() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String returnUrl = request.getParameter(Constant.RETURN_URL);
        log.debug("to login/register page return url={}", returnUrl);//"returnUrl"
        if (returnUrl != null) {
            HTTPUtil.getSession().setAttribute(Constant.RETURN_URL, returnUrl);
        }
    }


    public Post getPost() {
        return post != null ? post : new Post();
    }

    public long getPrevId() {
        return prevId;
    }

    public long getNextId() {
        return nextId;
    }

    public Page<Comment> getComments() {
        return comments;
    }
}
