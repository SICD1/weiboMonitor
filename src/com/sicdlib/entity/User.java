package com.sicdlib.entity;

/**
 * 登录用户
 * */
public class User {

	private String  u_id;        //用户ID
	private String u_name;		//用户名
	private String u_pwd;		//密码
	private boolean u_sex;		//性别
	private String u_telphone;	//电话
	private String u_mail;		//邮箱
	//多个用户属于一个'用户用户组表'
	private User_userGroup user_userGroup;
	//多个用户属于一个用户用户类型中间表
	//private User_userType user_userType;
	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public User_userGroup getUser_userGroup() {
		return user_userGroup;
	}
	public void setUser_userGroup(User_userGroup user_userGroup) {
		this.user_userGroup = user_userGroup;
	}

	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public boolean isU_sex() {
		return u_sex;
	}
	public void setU_sex(boolean u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_telphone() {
		return u_telphone;
	}
	public void setU_telphone(String u_telphone) {
		this.u_telphone = u_telphone;
	}
	public String getU_mail() {
		return u_mail;
	}
	public void setU_mail(String u_mail) {
		this.u_mail = u_mail;
	}
}
