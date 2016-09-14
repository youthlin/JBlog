package com.youthlin.jblog.dao.impl;

import com.youthlin.jblog.dao.CommentDao;
import com.youthlin.jblog.model.Comment;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by lin on 2016-09-09-009.
 * 评论
 */
@Stateless
public class CommentDaoImpl extends BaseDaoImpl<Comment, Long> implements CommentDao {
    @Override
    public Comment save(Comment entity) {
        Post p = em.find(Post.class, entity.getPost().getId());
        Query query = em.createQuery("select count(c.id) from Comment as c where c.post=:post");
        query.setParameter("post", p);
        Long count = (Long) query.getSingleResult();
        if (count != null) {
            p.setCommentCount(count + 1);
        }
        p = em.merge(p);
        em.flush();
        entity.setPost(p);
        Comment comment = super.save(entity);
        em.flush();
        System.out.println("dao发表评论成功:" + comment.getContent() + p.getId()
                + comment.getAuthor().getUsername() + p.getCommentCount());
        return comment;
    }

    @Override
    public Page<Comment> findPageByPost(Post post, int startPage, int countPerPage) {
        return findPageByPostId(post.getId(), startPage, countPerPage);
    }

    @Override
    public Page<Comment> findPageByPostId(Long id, int startPage, int countPerPage) {
        TypedQuery<Post> postTypedQuery = em.createQuery("select p from Post as p where p.id=:id", Post.class);
        postTypedQuery.setParameter("id", id);
        Post p = getSingleResult(postTypedQuery);

        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment as c where c.post=:post and c.deleted=false order by c.publishDate desc ",
                Comment.class);
        query.setParameter("post", p);
        query.setFirstResult((startPage - 1) * countPerPage);
        query.setMaxResults(countPerPage);
        Page<Comment> page = new Page<>();
        page.setPageIndex(startPage);
        page.setCountPerPage(countPerPage);
        page.setItem(query.getResultList());

        Query q = em.createQuery(
                "select count(c.id) from Comment as c where c.post.id=:postId and c.deleted=false ");
        q.setParameter("postId", id);
        Integer count;
        try {
            count = Math.toIntExact((Long) q.getSingleResult());
        } catch (Exception e) {
            count = 1;
        }
        count = count / countPerPage + 1;
        page.setPageTotal(count);
        return page;
    }
}
