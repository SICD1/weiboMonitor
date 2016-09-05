package com.sicdlib.entity;

//角色类型
public class UserType {
	private Integer ut_id;
	private String ut_name;		//角色名称（管理员、普通用户）
	private String ut_baseNum;	//角色基数，是否被1人多人占有
	private String ut_ableUse;	//角色可用标识符	
	//一个角色包含属于一个用户组用户类型中间表
	private UserGroup_UserType userGroup_UserType;
	//一个权限属于一个角色与权限中间表
	private UserType_Authority userType_Authority;
	//多个角色属于一个用户与用户类型中间表
	private User_userType user_userType;
	
	public User_userType getUser_userType() {
		return user_userType;
	}

	public void setUser_userType(User_userType user_userType) {
		this.user_userType = user_userType;
	}

	public UserType_Authority getUserType_Authority() {
		return userType_Authority;
	}
	
	public void setUserType_Authority(UserType_Authority userType_Authority) {
		this.userType_Authority = userType_Authority;
	}
	
	public String getUt_baseNum() {
		return ut_baseNum;
	}
	
	public String getUt_ableUse() {
		return ut_ableUse;
	}
	
	public UserGroup_UserType getUserGroup_UserType() {
		return userGroup_UserType;
	}
	
	public void setUt_baseNum(String ut_baseNum) {
		this.ut_baseNum = ut_baseNum;
	}
	
	public void setUt_ableUse(String ut_ableUse) {
		this.ut_ableUse = ut_ableUse;
	}
	
	public void setUserGroup_UserType(UserGroup_UserType userGroup_UserType) {
		this.userGroup_UserType = userGroup_UserType;
	}
	
	public Integer getUt_id() {
		return ut_id;
	}
	
	public void setUt_id(Integer ut_id) {
		this.ut_id = ut_id;
	}
	
	public String getUt_name() {
		return ut_name;
	}
	
	public void setUt_name(String ut_name) {
		this.ut_name = ut_name;
	}
	
}
