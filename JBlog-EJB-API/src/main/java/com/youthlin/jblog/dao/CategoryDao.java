package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.Category;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by lin on 2016-09-04-004.
 * 分类
 */
@Remote
public interface CategoryDao extends BaseDao<Category, Long> {
    Category findByNameAndStatus(String name, Byte status);

    List<Category> findAllTextCategory();

    List<Category> findAllImageCategory();
}
