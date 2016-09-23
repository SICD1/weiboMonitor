package com.sicdlib.dto;

import java.util.HashSet;
import java.util.Set;

//微博用户
public class WbUser {
	
	private Integer wbu_id;
	private String wbu_name;		//用户名
	private String wbu_VType;		//V类型
	private boolean wbu_sex;		//性别
	private String wbu_addr;		//地址
	private String wbu_des;			//用户介绍
	private Integer wbu_concernNum;	//关注量
	private Integer wbu_fansNum;	//粉丝量
	private Integer wbu_weiboNum;	//微博量
	private String wbu_userLevel;      //用户等级
	private Set<Weibo> weibos = new HashSet<Weibo>();		//一个微博用户有多个微博

	public Integer getWbu_id() {
		return wbu_id;
	}

	public void setWbu_id(Integer wbu_id) {
		this.wbu_id = wbu_id;
	}

	public String getWbu_name() {
		return wbu_name;
	}

	public void setWbu_name(String wbu_name) {
		this.wbu_name = wbu_name;
	}

	public String getWbu_VType() {
		return wbu_VType;
	}

	public void setWbu_VType(String wbu_VType) {
		this.wbu_VType = wbu_VType;
	}

	public boolean isWbu_sex() {
		return wbu_sex;
	}

	public void setWbu_sex(boolean wbu_sex) {
		this.wbu_sex = wbu_sex;
	}

	public String getWbu_addr() {
		return wbu_addr;
	}

	public void setWbu_addr(String wbu_addr) {
		this.wbu_addr = wbu_addr;
	}

	public String getWbu_des() {
		return wbu_des;
	}

	public void setWbu_des(String wbu_des) {
		this.wbu_des = wbu_des;
	}

	public Integer getWbu_concernNum() {
		return wbu_concernNum;
	}

	public void setWbu_concernNum(Integer wbu_concernNum) {
		this.wbu_concernNum = wbu_concernNum;
	}

	public Integer getWbu_fansNum() {
		return wbu_fansNum;
	}

	public void setWbu_fansNum(Integer wbu_fansNum) {
		this.wbu_fansNum = wbu_fansNum;
	}

	public Integer getWbu_weiboNum() {
		return wbu_weiboNum;
	}

	public void setWbu_weiboNum(Integer wbu_weiboNum) {
		this.wbu_weiboNum = wbu_weiboNum;
	}
	public Set<Weibo> getWeibos() {
		return weibos;
	}

	public void setWeibos(Set<Weibo> weibos) {
		this.weibos = weibos;
	}

	public String getWbu_userLevel() {return wbu_userLevel;}

	public void setWbu_userLevel(String wbu_userLevel) {this.wbu_userLevel = wbu_userLevel;}
}

