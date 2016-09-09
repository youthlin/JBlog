package com.youthlin.jblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lin on 2016-09-09-009.
 * 评论
 */
@ManagedBean
@RequestScoped
public class CommentBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CommentBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    }
}
