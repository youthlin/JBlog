package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;

import javax.ejb.Stateless;
import javax.persistence.Query;
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
                "select p from Post as p where p.status<>2 and p.category=:category", Post.class);
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
        TypedQuery<Post> query = em.createQuery("select p from Post as p where p.type=:type and p.status<>2", Post.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public Page<Post> getTextByPage(int pageStart, int pageSize) {
        return getByPage(pageStart, pageSize, Constant.POST_TYPE_TEXT);
    }

    @Override
    public Page<Post> getImageByPage(int pageStart, int pageSize) {
        return getByPage(pageStart, pageSize, Constant.POST_TYPE_IMAGE);
    }

    private Page<Post> getByPage(int pageStart, int pageSize, String type) {
        String jpql = "select p from Post as p where p.status=0 and p.type=:type order by p.publishDate desc ";
        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
        query.setParameter("type", type);
        query.setFirstResult((pageStart - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Post> posts = query.getResultList();
        System.out.println("该页文章有：" + posts);
        Query q = em.createQuery("select count(p.id) from Post as p where p.status<>2 and p.type=:type");
        q.setParameter("type", type);
        Long count;
        count = (Long) q.getSingleResult();
        if (count == null) {
            count = 1L;
        } else {
            count = count / pageSize + 1;
        }
        Page<Post> page = new Page<>();
        page.setPageIndex(pageStart);
        page.setPageTotal(count);
        page.setCountPerPage(pageSize);
        if (posts != null && posts.size() > 0) {
            page.setItem(posts);
        }
        return page;
    }

    private Post getNewestPost(String type) {
        log.debug("获取最新" + type);
        TypedQuery<Post> query = em.createQuery(
                "select p from Post as p where p.type=:type and p.status=0 order by p.publishDate desc",
                Post.class);
        query.setParameter("type", type);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return getSingleResult(query);
    }
}
