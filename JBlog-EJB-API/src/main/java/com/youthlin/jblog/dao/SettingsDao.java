package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.Settings;

import javax.ejb.Remote;
import java.util.Map;

/**
 * Created by lin on 2016-09-08-008.
 * 设置项
 */
@Remote
public interface SettingsDao extends BaseDao<Settings, Long> {
    String get(String name);

    void add(String name, String value);

    Map<String, String> findAll();
}
