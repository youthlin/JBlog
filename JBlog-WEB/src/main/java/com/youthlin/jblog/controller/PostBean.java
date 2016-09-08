package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CategoryDao;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.model.User;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2016-09-07-007.
 * 文章
 */
@ManagedBean
@SessionScoped
public class PostBean {
    private String title;
    private String content;
    private Long categoryId;
    private Boolean allowComment = true;
    private final String ALL_POST = "articles";
    private final String WRITE = "write";
    private PostDao postDao = EJBUtil.getBean(PostDao.class);
    private CategoryDao categoryDao = EJBUtil.getBean(CategoryDao.class);
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Post newestText;
    private Post newestImage;
    private List<Post> allTextPost;

    public PostBean() {
        clear();
        update();
    }

    private void update() {
        newestText = postDao.getNewestText();
        newestImage = postDao.getNewestImage();
    }

    private void clear() {
        title = "";
        content = "";
        categoryId = 0L;
        allowComment = true;
    }

    public String post() {
        Post post = new Post();
        post.setType(Constant.POST_TYPE_TEXT);
        post.setTitle(title);
        post.setContent(content);
        post.setPublishDate(new Date());
        post.setAllowComment(allowComment);
        post.setStatus(Constant.POST_PUBLISH);
        User admin = Context.getCurrentUser();
        post.setAuthor(admin);
        Category category = categoryDao.find(Category.class, categoryId);
        post.setCategory(category);
        category.setPostCount(category.getPostCount() + 1);
        postDao.save(post);
        categoryDao.update(category);
        clear();
        update();
        log.debug("发表文章成功");
        return WRITE;
    }

    public List<Post> getAllTextPost() {
        if (allTextPost == null) {
            allTextPost = postDao.getByType(Constant.POST_TYPE_TEXT);
        }
        return allTextPost;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
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
