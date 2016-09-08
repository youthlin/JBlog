package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.model.Post;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by lin on 2016-09-03-003.
 * 实现类
 */
@Stateless
public class PostDaoImpl extends BaseDaoImpl<Post, Long> implements PostDao {
    @Override
    public Post getNewestText() {
        return getNewestPost(Constant.POST_TYPE_TEXT);
    }

    @Override
    public Post getNewestImage() {
        return getNewestPost(Constant.POST_TYPE_IMAGE);
    }

    @Override
    public List<Post> getByCategory(Category category) {
        log.debug("获取分类{}下的文章", category);
        TypedQuery<Post> query = em.createQuery(
                "select p from Post as p where p.status=0 and p.category=:category", Post.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Override
    public List<Post> getByCategoryId(Long id) {
        log.debug("获取分类id={}下的文章", id);
        TypedQuery<Post> query = em.createQuery(
                "select p from Post as p where p.status=0 and p.category.id=:id and p.status <> 2", Post.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Post> getByType(String type) {
        log.debug("获取类型Post");
        TypedQuery<Post> query = em.createQuery("select p from Post as p where p.type=:type", Post.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    private Post getNewestPost(String type) {
        log.debug("获取最新" + type);
        TypedQuery<Post> query = em.createQuery(
                "select p from Post as p where p.type=:type and p.status=:status order by p.publishDate desc",
                Post.class);
        query.setParameter("type", type);
        Byte status = 0;
        query.setParameter("status", status);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return getSingleResult(query);
    }
}
