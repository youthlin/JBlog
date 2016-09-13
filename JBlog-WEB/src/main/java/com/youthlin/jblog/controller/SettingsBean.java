package com.youthlin.jblog.controller;

import com.youthlin.jblog.dao.SettingsDao;
import com.youthlin.jblog.util.EJBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Map;

/**
 * Created by lin on 2016-09-13-013.
 * 设置项
 */
@ManagedBean
@SessionScoped
public class SettingsBean {
    private final Logger log = LoggerFactory.getLogger(SettingsBean.class);
    private SettingsDao settingsDao = EJBUtil.getBean(SettingsDao.class);
    private Map<String, String> settings = settingsDao.findAll();

    public Map<String, String> getSettings() {
        return settings;
    }
}
