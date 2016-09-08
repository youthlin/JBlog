package com.youthlin.jblog.controller;

import com.youthlin.jblog.dao.PostDao;
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
    private Post post;
    private long prevId = -1L;
    private long nextId = -1L;

    public ArticleBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String strId = request.getParameter("id");
        long id = -1;
        try {
            id = Long.parseLong(strId);
        } catch (Exception e) {
            //
        }
        if (id != -1) {
            post = postDao.find(Post.class, id);
            if (post == null) {
                post = postDao.getNewestText();
            }
        } else post = postDao.getNewestText();
    }

    public Post getPost() {
        return post;
    }

}
