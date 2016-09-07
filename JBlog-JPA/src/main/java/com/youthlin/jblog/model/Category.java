package com.youthlin.jblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.youthlin.jblog.constant.Constant;

/**
 * Created by lin on 2016-09-03-003.
 * 分类
 */
@Entity
@Table(name = "t_category")
public class Category implements Serializable {
    private static final Category TEXT_unCategory = new Category();
    private static final Category IMAGE_unCategory = new Category();

    static {
        TEXT_unCategory.name = Constant.CATEGORY_TEXT_UNCATEGORY_NAME;//默认分类
        TEXT_unCategory.status = Constant.CATEGORY_TEXT_TYPE;//0
        IMAGE_unCategory.name = Constant.CATEGORY_IMAGE_UNCATEGORY_NAME;//默认相册
        IMAGE_unCategory.status = Constant.CATEGORY_IMAGE_TYPE;//1
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;    //分类名
    private Byte status;    //0文章分类 1相册分类 2删除的文章分类 3删除的相册分类
    @Column(name = "post_count")
    private Long postCount = 0L;
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", postCount=" + postCount +
                '}';
    }

    //region getter and setter
    public static Category getTEXT_unCategory() {
        return TEXT_unCategory;
    }

    public static Category getIMAGE_unCategory() {
        return IMAGE_unCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public Long getPostCount() {

        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }

    //endregion
}
