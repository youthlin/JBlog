package com.youthlin.jblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by lin on 2016-09-04-004.
 * 分类
 */
@ManagedBean
@SessionScoped
public class CategoryBean {
    private static final Logger log = LoggerFactory.getLogger(CategoryBean.class);

    public CategoryBean() {
        log.debug("构造CategoryBean");
    }

}
