package com.sicdlib.util.dataFilterUtil.dto;

/**
 * Created by Yh on 2016/9/18.
 */
public class BlogSubject {
    private String blogID;
    private long userID;
    private String blog;
    private String topics;

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

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }
}
