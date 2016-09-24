package com.sicdlib.dto;

import java.util.List;

/**
 * 微博评论表
 * @author
 *
 */
public class WbComment {
	private Integer wbc_id;
	private String wbc_u_id;		//微博用户ID
	private String wbc_b_id;        //微博ID
	private String wbc_content;		//微博评论表内容
	private String wbc_zanNum;		//微博评论点赞量
	private String wbc_createdAt;	//微博评论创建时间
	private Weibo weibo;			//一个评论属于一条微博
	public String getWbc_b_id() {
		return wbc_b_id;
	}
	public void setWbc_b_id(String wbc_b_id) {
		this.wbc_b_id = wbc_b_id;
	}
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
	public Integer getWbc_id() {
		return wbc_id;
	}
	public String getWbc_u_id() {
		return wbc_u_id;
	}
	public String getWbc_content() {
		return wbc_content;
	}
	public String getWbc_zanNum() {
		return wbc_zanNum;
	}
	public String getWbc_createdAt() {
		return wbc_createdAt;
	}
	public void setWbc_id(Integer wbc_id) {
		this.wbc_id = wbc_id;
	}
	public void setWbc_u_id(String wbc_u_id) {
		this.wbc_u_id = wbc_u_id;
	}
	public void setWbc_content(String wbc_content) {
		this.wbc_content = wbc_content;
	}
	public void setWbc_zanNum(String wbc_zanNum) {
		this.wbc_zanNum = wbc_zanNum;
	}
	public void setWbc_createdAt(String wbc_createdAt) {
		this.wbc_createdAt = wbc_createdAt;
	}
	
}
