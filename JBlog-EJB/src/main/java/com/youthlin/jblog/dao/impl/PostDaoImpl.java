package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.constant.Constant;
import com.youthlin.jblog.dao.PostDao;
import com.youthlin.jblog.model.Post;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

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
