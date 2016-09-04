package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.Post;

import javax.ejb.Remote;

/**
 * Created by lin on 2016-09-03-003.
 * 文章照片DAO
 */
@Remote
public interface PostDao extends BaseDao<Post, Long> {
    Post getNewestText();

    Post getNewestImage();
}
