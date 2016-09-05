package com.sicdlib.entity;

//权限表
public class Authority {

	private Integer a_id;
	private String a_type;		//权限类型
	
	//一个权限属于一个‘用户角色权限中间表’
	private UserType_Authority userType_Authority;
	
	public Integer getA_id() {
		return a_id;
	}
	public String getA_type() {
		return a_type;
	}
	public UserType_Authority getUserType_Authority() {
		return userType_Authority;
	}
	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public void setUserType_Authority(UserType_Authority userType_Authority) {
		this.userType_Authority = userType_Authority;
	}
	
}
