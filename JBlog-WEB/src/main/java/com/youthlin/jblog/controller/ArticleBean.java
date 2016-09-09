package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CommentDao;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.dao.SettingsDao;
import com.youthlin.jblog.model.Comment;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.util.EJBUtil;
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
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private PostDao postDao = EJBUtil.getBean(PostDao.class);
    private CommentDao commentDao = EJBUtil.getBean(CommentDao.class);
    private SettingsDao settingsDao = EJBUtil.getBean(SettingsDao.class);
    private Post post;
    private long prevId = -1L;
    private long nextId = -1L;
    private Page<Comment> comments;

    public ArticleBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String strId = request.getParameter("id");
        long id = -1;
        try {
            id = Long.parseLong(strId);
        } catch (Exception e) {
            log.debug("参数错误，文章id参数只接受数字");
        }
        if (id != -1) {
            post = postDao.find(Post.class, id);
            if (post == null) {
                log.debug("未找到id={}对应的文章", id);
                post = postDao.getNewestText();
            }
        } else post = postDao.getNewestText();
        long[] prevAndNextId = postDao.getPrevAndNextId(post);
        prevId = prevAndNextId[0];
        nextId = prevAndNextId[1];

        int commentPage = 1;
        String strCommentPage = request.getParameter("c-page");
        if (strCommentPage != null) {
            try {
                commentPage = Integer.parseInt(strCommentPage);
            } catch (Exception e) {
                log.debug("参数错误，c-page参数只接受数字，表示评论第几页");
            }
        }

        int commentCountPerPage = 5;
        String strCommentSize = settingsDao.get(Constant.SETTINGS_COMMENT_COUNT_PER_PAGE);
        if (strCommentSize == null) {
            settingsDao.add(Constant.SETTINGS_COMMENT_COUNT_PER_PAGE, Constant.SETTINGS_COMMENT_COUNT_PER_PAGE_DEFAULT);
        } else {
            try {
                commentCountPerPage = Integer.parseInt(strCommentSize);
            } catch (Exception e) {
                log.debug("参数错误，c-page参数只接受数字，表示评论第几页");
            }
        }
        comments = commentDao.findPageByPostId(post.getId(), commentPage, commentCountPerPage);
    }

    public Post getPost() {
        return post;
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
