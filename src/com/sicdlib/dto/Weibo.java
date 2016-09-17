package com.sicdlib.dto;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"keyWords","wbuser", "wbComments"})
public class Weibo {
	
	private Integer wb_id;
	private String wb_content;		//微博内容
	private String wb_createdAt;	//微博创建时间
	private Integer wb_forwardNum;	//微博转发量
	private Integer wb_readNum;		//微博阅读量
	private Integer wb_commentsNum;	//微博评论量
	private Integer wb_likeNum;		//微博点赞量
	private String wb_fromDevice;	//微博来自于(设备)
	private String wb_originalLink;	//微博原始链接
	private WbUser wbuser;			//一个微博属于一个微博用户
	private KeyWords keyWords;		//一个微博可提取一个关键词序列（热词）
	//一条微博包括多个评论
	private Set<WbComment> wbComments = new HashSet<WbComment>();

	public Set<WbComment> getWbComments() {
		return wbComments;
	}
	public void setWbComments(Set<WbComment> wbComments) {
		this.wbComments = wbComments;
	}
	public KeyWords getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(KeyWords keyWords) {
		this.keyWords = keyWords;
	}
	public WbUser getWbuser() {
		return wbuser;
	}
	public void setWbuser(WbUser wbuser) {
		this.wbuser = wbuser;
	}
	public Integer getWb_id() {
		return wb_id;
	}
	public void setWb_id(Integer wb_id) {
		this.wb_id = wb_id;
	}
	public String getWb_content() {
		return wb_content;
	}
	public void setWb_content(String wb_content) {
		this.wb_content = wb_content;
	}
	public String getWb_createdAt() {
		return wb_createdAt;
	}
	public void setWb_createdAt(String wb_createdAt) {
		this.wb_createdAt = wb_createdAt;
	}
	public Integer getWb_forwardNum() {
		return wb_forwardNum;
	}
	public Integer getWb_readNum() {
		return wb_readNum;
	}
	public Integer getWb_commentsNum() {
		return wb_commentsNum;
	}
	public Integer getWb_likeNum() {
		return wb_likeNum;
	}
	public String getWb_fromDevice() {
		return wb_fromDevice;
	}
	public String getWb_originalLink() {
		return wb_originalLink;
	}
	public void setWb_forwardNum(Integer wb_forwardNum) {
		this.wb_forwardNum = wb_forwardNum;
	}
	public void setWb_readNum(Integer wb_readNum) {
		this.wb_readNum = wb_readNum;
	}
	public void setWb_commentsNum(Integer wb_commentsNum) {
		this.wb_commentsNum = wb_commentsNum;
	}
	public void setWb_likeNum(Integer wb_likeNum) {
		this.wb_likeNum = wb_likeNum;
	}
	public void setWb_fromDevice(String wb_fromDevice) {
		this.wb_fromDevice = wb_fromDevice;
	}
	public void setWb_originalLink(String wb_originalLink) {
		this.wb_originalLink = wb_originalLink;
	}

	
}
