package com.youthlin.jblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016-09-01-001.
 * 用户
 */
@Entity
//Can not resolve table xxx:在下标红线上Ctrl+Enter选择Assign Data Source定义数据源即可。数据库中有这张表就不会报错了
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;        //用户名
    @Column(name = "passwd")            //password可能是关键字
    private String password;        //密码
    private String email;           //邮件
    private String gravatar;        //头像地址
    @Column(name = "s_key")             //key可能是关键字
    private String key;             //一般为null，当通过邮件重置密码时，重置链接为/resetPassword?id=userId&key=xxx
    private Byte status = 1;            //状态：0管理员 1访客 2删除

    @OneToMany(mappedBy = "author")     //fetch = FetchType.LAZY是默认值
    private List<Post> posts = new ArrayList<>();       //发表的文章或照片
    @OneToMany(mappedBy = "author")     //mappedBy的值是对方引用本实体的属性名
    private List<Comment> comments = new ArrayList<>(); //发表的评论
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Post> likedPost = new ArrayList<>();   //收藏的图文

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gravatar='" + gravatar + '\'' +
                ", key='" + key + '\'' +
                ", status=" + status +
                ", posts=" + posts +
                ", comments=" + comments +
                ", likedPost=" + likedPost +
                '}';
    }

    //region //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(List<Post> likedPost) {
        this.likedPost = likedPost;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
    //endregion
}
