package com.sicdlib.util.dataFilterUtil;

import com.sicdlib.util.DBUtils;
import com.sicdlib.util.dataFilterUtil.dto.BlogSource;
import com.sicdlib.util.dataFilterUtil.dto.BlogSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yh on 2016/9/18.
 */
public class TransformData {
    private final String url = "jdbc:mysql://192.168.1.36/weibo_monitor";
    private final String userName = "root";
    private final String pwd = "root";

    public void Transform() {
        Connection srcConn = null;
        PreparedStatement srcState = null;
        ResultSet srcRs = null;

        Connection subConn = null;
        PreparedStatement subState = null;

        try {
            srcConn = DBUtils.getConnection(url, userName, pwd);
            String selectSql = "SELECT * FROM wb_blog";
            srcState = srcConn.prepareStatement(selectSql,
                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            subConn = DBUtils.getConnection(url, userName, pwd);
            String insertSql = "INSERT INTO wb_blog_subject(BLOG_ID, USER_ID, BLOG_CONTENT, BLOG_TOPICS) " +
                    "VALUES(?, ?, ?, ?)";

            srcRs = srcState.executeQuery();

            while(srcRs.next()) {
                BlogSource srcBlog = new BlogSource();
                srcBlog.setBlogID(srcRs.getString("BLOG_ID"));
                srcBlog.setUserID(srcRs.getLong("USER_ID"));
                srcBlog.setBlog(srcRs.getString("BLOG_CONTENT"));
                srcBlog.setBlogTime(srcRs.getString("BLOG_TIME"));
                srcBlog.setBlogTopics(srcRs.getString("BLOG_TOPICS"));
                srcBlog.setBlogAt(srcRs.getString("BLOG_AT"));
                srcBlog.setPraiseNum(srcRs.getString("BOLG_PRAISE_NUM"));

                BlogSubject subject = FilterData.filter(srcBlog);
                if (subject != null) {
                    subState = subConn.prepareStatement(insertSql);
                    subState.setString(1, subject.getBlogID());
                    subState.setLong(2, subject.getUserID());
                    subState.setString(3, subject.getBlog());
                    subState.setString(4, subject.getTopics());
                    subState.execute();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                srcRs.close();
                srcState.close();
                srcConn.close();

                subState.close();
                subConn.close();
            } catch(Exception ex1) {
                System.out.println("数据连接关闭失败！！" + ex1);
            }
        }
    }
}
