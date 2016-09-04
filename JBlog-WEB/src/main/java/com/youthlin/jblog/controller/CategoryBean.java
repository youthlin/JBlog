package com.youthlin.jblog.controller;

import com.youthlin.jblog.dao.CategoryDao;
import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016-09-04-004.
 * 分类
 */
@ManagedBean
@SessionScoped
public class CategoryBean {
    private static final Logger log = LoggerFactory.getLogger(CategoryBean.class);
    private CategoryDao categoryDao = EJBUtil.getBean(CategoryDao.class);
    private String name;
    private Byte status;
    private String msg = "";
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
        return "category";
    }

    public String getName() {
        return name;
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
        return textCategory;
    }

    public void setTextCategory(List<Category> textCategory) {
        this.textCategory = textCategory;
    }

    public List<Category> getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(List<Category> imageCategory) {
        this.imageCategory = imageCategory;
    }
}
