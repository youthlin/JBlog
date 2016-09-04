package com.youthlin.jblog.controller;

import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by lin on 2016-09-03-003.
 * 首页
 */
@ManagedBean
@SessionScoped
public class HomeBean {
    private static final Logger log = LoggerFactory.getLogger(HomeBean.class);
    private Post newestText;
    private Post newestImage;
    private PostDao postDao = EJBUtil.getBean(PostDao.class);

    public HomeBean() {
        log.debug("构造HomeBean");
        newestText = postDao.getNewestText();
        newestImage = postDao.getNewestImage();
    }

    public Post getNewestText() {
        return newestText;
    }

    public void setNewestText(Post newestText) {
        this.newestText = newestText;
    }

    public Post getNewestImage() {
        return newestImage;
    }

    public void setNewestImage(Post newestImage) {
        this.newestImage = newestImage;
    }
}
