package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.CategoryDao;
import com.youthlin.jblog.model.Category;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lin on 2016-09-04-004.
 * 分类
 */
@Stateless
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {

    @Override
    public Category findByNameAndStatus(String name, Byte status) {
        String jpql = "select c from Category as c where c.name=:name and c.status=:status";
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("status", status);
        return super.getOne(Category.class, jpql, param);
    }

    @Override
    public List<Category> findAllTextCategory() {
        return findAll(Constant.CATEGORY_TEXT_TYPE);
    }

    @Override
    public List<Category> findAllImageCategory() {
        return findAll(Constant.CATEGORY_IMAGE_TYPE);
    }

    private List<Category> findAll(Byte status) {
        TypedQuery<Category> query = em.createQuery("select c from Category as c where c.status=:status", Category.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
