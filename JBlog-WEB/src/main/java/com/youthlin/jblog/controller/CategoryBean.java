package com.youthlin.jblog.controller;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CategoryDao;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.model.Post;
import com.youthlin.jblog.util.EJBUtil;
import com.youthlin.jblog.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.util.List;

/**
 * Created by lin on 2016-09-04-004.
 * 分类
 */
@ManagedBean
@SessionScoped
public class CategoryBean {
    private static final Logger log = LoggerFactory.getLogger(CategoryBean.class);
    private static final String CATEGORY = "category";
    private static CategoryDao categoryDao = EJBUtil.getBean(CategoryDao.class);
    private static PostDao postDao = EJBUtil.getBean(PostDao.class);

    private Long id;
    private String name;
    private Byte status = 0;
    private String msg = "";
    private String msgEditTextCategory = "";
    private String msgEditImageCategory = "";
    private Category textUncategory = Category.getTEXT_unCategory();
    private Category imageUncategory = Category.getIMAGE_unCategory();
    private List<Category> textCategory;
    private List<Category> imageCategory;

    public CategoryBean() {
        log.debug("构造CategoryBean");
        Category c = categoryDao.findByNameAndStatus(textUncategory.getName(), textUncategory.getStatus());
        if (c == null) {
            textUncategory = categoryDao.save(textUncategory);
        }
        c = categoryDao.findByNameAndStatus(imageUncategory.getName(), imageUncategory.getStatus());
        if (c == null) {
            imageUncategory = categoryDao.save(imageUncategory);
        }
        updateList();
    }

    private void updateList() {
        textCategory = categoryDao.findAllTextCategory();
        imageCategory = categoryDao.findAllImageCategory();
        name = "";
        status = 0;
    }

    public String add() {
        Category c = categoryDao.findByNameAndStatus(name, status);
        if (c != null) {
            msg = "已有同名分类";
        } else {
            Category category = new Category();
            category.setName(name);
            category.setStatus(status);
            categoryDao.save(category);
            updateList();
            msg = "添加成功";
        }
        return CATEGORY;
    }

    public String edit() {
        log.debug("编辑name={},id={},status={}", name, id, status);
        Category c = categoryDao.findByNameAndStatus(name, status);
        if (c != null) {
            if (status == 0) {
                log.trace("有同名的文章分类");
                msgEditTextCategory = "已有同名分类";
            }
            if (status == 1) {
                log.trace("有同名的相册分类");
                msgEditImageCategory = "已有同名分类";
            }
        } else {
            log.trace("分类新名称可用");
            c = categoryDao.find(Category.class, id);
            c.setName(name);
            categoryDao.update(c);
            if (status == 0) {
                msgEditTextCategory = "修改成功";
            }
            if (status == 1) {
                msgEditImageCategory = "修改成功";
            }
            updateList();
        }
        return CATEGORY;
    }

    public String delete() {
        log.debug("删除name={},id={},status={}", name, id, status);
        Category c = categoryDao.find(Category.class, id);
        textUncategory = categoryDao.findByNameAndStatus(textUncategory.getName(), textUncategory.getStatus());
        imageUncategory = categoryDao.findByNameAndStatus(imageUncategory.getName(), imageUncategory.getStatus());
        if (c != null) {
            log.debug(">>删除分类id={},name={}", c.getId(), c.getName());
            if (c.getStatus() == Constant.CATEGORY_TEXT_TYPE) {
                if (c.getPostCount() == 0) {
                    log.trace("该分类下没有文章，直接删除");
                    c.setStatus(Constant.DELETED_CATEGORY_TEXT_TYPE);
                } else {
                    log.trace("该分类下有{}篇文章", c.getPostCount());
                    log.trace("删除分类导致文章归属变化，通知文章列表应该更新");
                    HTTPUtil.getSession().setAttribute(Constant.allTextPostListShouldBeUpdated, Boolean.TRUE);
                    List<Post> posts = postDao.getByCategory(c);
                    for (Post post : posts) {
                        post.setCategory(textUncategory);
                        postDao.update(post);
                    }
                    textUncategory.setPostCount(textUncategory.getPostCount() + c.getPostCount());
                    c.setPostCount(0L);
                    c.setStatus(Constant.DELETED_CATEGORY_TEXT_TYPE);
                    textUncategory = categoryDao.update(textUncategory);
                    log.trace("已将该分类下文章归到id={},name={}分类下", textUncategory.getId(), textUncategory.getName());
                }
            } else if (c.getStatus() == Constant.CATEGORY_IMAGE_TYPE) {
                if (c.getPostCount() == 0) {
                    log.trace("该分类下没有图片，直接删除");
                    c.setStatus(Constant.DELETED_CATEGORY_IMAGE_TYPE);
                } else {
                    log.trace("该分类下有{}张图片", c.getPostCount());
                    log.trace("删除分类导致照片归属变化，通知照片列表应该更新");
                    HTTPUtil.getSession().setAttribute(Constant.allImagePostListShouldBeUpdated, true);
                    List<Post> posts = postDao.getByCategoryId(c.getId());
                    for (Post post : posts) {
                        post.setCategory(imageUncategory);
                        postDao.update(post);
                    }
                    imageUncategory.setPostCount(imageUncategory.getPostCount() + c.getPostCount());
                    c.setPostCount(0L);
                    c.setStatus(Constant.DELETED_CATEGORY_IMAGE_TYPE);
                    imageUncategory = categoryDao.update(imageUncategory);
                    log.trace("已将该分类下图片归到id={},name={}分类下", imageUncategory.getId(), imageUncategory.getName());
                }
            }
            categoryDao.update(c);
            log.debug(">>删除分类成功");
            updateList();
        }
        return CATEGORY;
    }

    public SelectItem[] getAllTextCategory() {
        SelectItem[] selectItems = new SelectItem[getTextCategory().size()];
        for (int i = 0; i < textCategory.size(); i++) {
            selectItems[i] = new SelectItem(textCategory.get(i).getId(), textCategory.get(i).getName());
        }
        return selectItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        String m = name;
        name = "";
        return m;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMsg() {
        String m = msg;
        msg = "";
        return m;
    }

    public String getMsgEditTextCategory() {
        String m = msgEditTextCategory;
        msgEditTextCategory = "";
        return m;
    }

    public void setMsgEditTextCategory(String msgEditTextCategory) {
        this.msgEditTextCategory = msgEditTextCategory;
    }

    public String getMsgEditImageCategory() {
        String m = msgEditImageCategory;
        msgEditImageCategory = "";
        return m;
    }

    public void setMsgEditImageCategory(String msgEditImageCategory) {
        this.msgEditImageCategory = msgEditImageCategory;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Category getTextUncategory() {
        return textUncategory;
    }

    public void setTextUncategory(Category textUncategory) {
        this.textUncategory = textUncategory;
    }

    public Category getImageUncategory() {
        return imageUncategory;
    }

    public void setImageUncategory(Category imageUncategory) {
        this.imageUncategory = imageUncategory;
    }

    public List<Category> getTextCategory() {
        if (Boolean.TRUE.equals( HTTPUtil.getSession().getAttribute(Constant.textCategoryListShouldBeUpdated))) {
            log.trace("获取最新文章分类列表");
            textCategory = categoryDao.findAllTextCategory();
            HTTPUtil.getSession().setAttribute(Constant.textCategoryListShouldBeUpdated, false);
        }
        return textCategory;
    }

    public void setTextCategory(List<Category> textCategory) {
        this.textCategory = textCategory;
    }

    public List<Category> getImageCategory() {
        if (Boolean.TRUE.equals( HTTPUtil.getSession().getAttribute(Constant.imageCategoryListShouldBeUpdated))) {
            log.trace("获取最新相册分类列表");
            imageCategory = categoryDao.findAllImageCategory();
            HTTPUtil.getSession().setAttribute(Constant.imageCategoryListShouldBeUpdated, false);
        }
        return imageCategory;
    }

    public void setImageCategory(List<Category> imageCategory) {
        this.imageCategory = imageCategory;
    }
}
