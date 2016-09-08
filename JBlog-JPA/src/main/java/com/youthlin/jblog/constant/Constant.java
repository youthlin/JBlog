package com.youthlin.jblog.constant;

/**
 * Created by lin on 2016-09-02-002.
 * 常量
 */
public class Constant {
    public static final String BLOG_TITLE = "blog_title";
    public static final String BLOG_TITLE_DEFAULT = "JBlog";
    public static final String BLOG_DESCRIPTION = "blog_description";
    public static final String BLOG_DESCRIPTION_DEFAULT = "个人博客系统 - JavaEE / EJB / JPA / JSF - Powered By Youth．霖";
    public static final String PAGE_SIZE = "page_size";//每页几项
    public static final String PAGE_SIZE_DEFAULT = "5";

    public static final String ADMIN = "admin";
    public static final String CURRENT_USER = "current_user";

    public static final String POST_TYPE_TEXT = "text";
    public static final String POST_TYPE_IMAGE = "image";
    public static byte POST_PUBLISH = 0;
    public static byte POST_DRAFT = 1;
    public static byte POST_DELETED = 2;
    public static final String CATEGORY_TEXT_UNCATEGORY_NAME = "默认分类";
    public static final String CATEGORY_IMAGE_UNCATEGORY_NAME = "默认相册";
    public static final byte CATEGORY_TEXT_TYPE = 0;
    public static final byte DELETED_CATEGORY_TEXT_TYPE = 2;
    public static final byte CATEGORY_IMAGE_TYPE = 1;
    public static final byte DELETED_CATEGORY_IMAGE_TYPE = 3;

    public static final String SETTINGS_TEXT_COUNT_PER_PAGE = "text_count_per_page";
    public static final String SETTINGS_IMAGE_COUNT_PER_PAGE = "image_count_per_page";
    public static final String SETTINGS_COMMENT_COUNT_PER_PAGE = "comment_count_per_page";
    public static final String SETTINGS_BLOG_ABOUT = "about_blog";

    public static final String PROFILE_DEFAULT_GRAVATAR_URL = "";

}
