package com.youthlin.jblog.controller;

import com.youthlin.jblog.util.JMSUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by lin on 2016-09-12-012.
 * 消息
 */
@ManagedBean
@RequestScoped
public class MessageBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String send() {
        JMSUtil.sendTextMessage(text);
        return "topic";
    }
}
