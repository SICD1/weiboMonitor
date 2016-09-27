package com.sicdlib.util.dataFilterUtil.dto;

/**
 * Created by Yh on 2016/9/18.
 */
public class BlogSource {
    private String blogID;
    private long userID;
    private String blog;
    private String blogTime;
    private String blogTopics;
    private String blogAt;
    private String praiseNum;

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(String blogTime) {
        this.blogTime = blogTime;
    }

    public String getBlogTopics() {
        return blogTopics;
    }

    public void setBlogTopics(String blogTopics) {
        this.blogTopics = blogTopics;
    }

    public String getBlogAt() {
        return blogAt;
    }

    public void setBlogAt(String blogAt) {
        this.blogAt = blogAt;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }
}
