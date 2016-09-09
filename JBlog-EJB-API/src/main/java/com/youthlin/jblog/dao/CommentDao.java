package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.Comment;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;

import javax.ejb.Remote;

/**
 * Created by lin on 2016-09-09-009.
 * 评论
 */
@Remote
public interface CommentDao extends BaseDao<Comment, Long> {
    Page<Comment> findPageByPost(Post post, int startPage, int countPerPage);

    Page<Comment> findPageByPostId(Long id, int startPage, int countPerPage);
}
