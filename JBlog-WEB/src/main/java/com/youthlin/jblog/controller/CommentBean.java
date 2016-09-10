package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CommentDao;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Comment;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by lin on 2016-09-09-009.
 * 评论
 */
@ManagedBean
@RequestScoped
public class CommentBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private PostDao postDao = EJBUtil.getBean(PostDao.class);
    private CommentDao commentDao = EJBUtil.getBean(CommentDao.class);
    private String content;
    private long postId;

    public CommentBean() {
        log.debug("构造CommentBean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String postIdStr = request.getParameter("id");
        log.debug("post id = {}", postIdStr);
        if (postIdStr == null) {
            return;
        }
        try {
            postId = Long.parseLong(postIdStr);
        } catch (Exception e) {
            Post post = postDao.getNewestText();
            if (post != null) {
                postId = postDao.getNewestText().getId();
            }
        }
    }

    public String comment() {
        Comment comment = new Comment();
        comment.setAuthor(Context.staticGetCurrentUser());
        comment.setContent(content);
        comment.setPublishDate(new Date());
        Post post = postDao.find(Post.class, postId);
        log.debug("post id = {}", post.getId());
        comment.setPost(post);
        commentDao.save(comment);

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse())
                    .sendRedirect(request.getServletContext().getContextPath() + "/article.xhtml?id=" + postId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "article";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
