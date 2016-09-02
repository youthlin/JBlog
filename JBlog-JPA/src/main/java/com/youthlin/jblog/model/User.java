package com.youthlin.jblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    private String username;
    @Column(name = "passwd")
    private String password;
    private String email;
    /*头像地址*/
    private String gravatar;
    /*一般为null，当通过邮件重置密码时，重置链接为/resetPassword?id=userId&key=xxx*/
    @Column(name = "s_key")
    private String key;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gravatar='" + gravatar + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
