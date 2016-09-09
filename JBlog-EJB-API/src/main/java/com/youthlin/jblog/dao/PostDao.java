package com.youthlin.jblog.dao;

import com.youthlin.jblog.model.Category;
import com.youthlin.jblog.model.Page;
import com.youthlin.jblog.model.Post;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by lin on 2016-09-03-003.
 * 文章照片DAO
 */
@Remote
public interface PostDao extends BaseDao<Post, Long> {
    Post getNewestText();

    Post getNewestImage();

    List<Post> getByCategory(Category category);

    List<Post> getByCategoryId(Long id);

    List<Post> getByType(String type);

    Page<Post> getTextByPage(int pageStart, int pageSize);

    Page<Post> getImageByPage(int pageStart, int pageSize);

    long[] getPrevAndNextId(Post p);
}
