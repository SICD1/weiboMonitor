package com.sicdlib.util.dataFilterUtil;

import com.sicdlib.util.dataFilterUtil.dto.BlogSource;
import com.sicdlib.util.dataFilterUtil.dto.BlogSubject;
import net.sf.chineseutils.ChineseUtils;

import java.util.List;

/**
 * Created by Yh on 2016/9/19.
 */
public class FilterData {
    public static BlogSubject filter(BlogSource sourceBlog) {
        BlogSubject subject = new BlogSubject();

        String blogID = sourceBlog.getBlogID();
        long userID = sourceBlog.getUserID();

        subject.setUserID(userID);
        subject.setBlogID(blogID);

        String blog = sourceBlog.getBlog();
        //繁简转换
        blog =  ChineseUtils.tradToSimp(blog);
        blog = blog.replaceAll("//@.+?[:]", " ").trim();
        //非中文字符的过滤
        blog = blog.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5!,;:\'\".?，。？；：、‘’)]", "");
        //过滤多层转发

        //过滤“转发微博”、“分享图片”、“O网页链接”
        if (blog.endsWith("转发微博")) {
            blog = blog.substring(0, blog.length() - 4);
        }
        blog = blog.replace("分享图片", "");
        blog = blog.replace("O网页链接", "");

        //过滤@用户
        String srcTopics = sourceBlog.getBlogTopics();
        String[] topicList = {};
        if (srcTopics.length() > 2) {
            srcTopics.replaceAll("'", "");
            topicList = srcTopics.substring(1, srcTopics.length() - 1).split(", ");
        }

        for (int i = 0; i < topicList.length; i++) {
            blog = blog.replace(topicList[i].substring(1, topicList[i].length() - 1), "");
        }
        if(!blog.trim().equals("")) {
            subject.setBlog(blog);
            return subject;
        }
        return null;
    }
}
