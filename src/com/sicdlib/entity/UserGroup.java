package com.sicdlib.entity;


//用户组
public class UserGroup {

	private Integer ug_id;			//用户组ID
	private String ug_name;			//用户组名称
	private String ug_parentName;	//用户组父类名称
	//一个用户组属于一个‘用户与用户组表’
	private User_userGroup user_userGroup;
	//一个用户组属于一个‘用户组用户类型表’
	private UserGroup_UserType userGroup_UserType;
	
	public Integer getUg_id() {
		return ug_id;
	}
	public String getUg_name() {
		return ug_name;
	}
	public String getUg_parentName() {
		return ug_parentName;
	}
	public User_userGroup getUser_userGroup() {
		return user_userGroup;
	}
	public UserGroup_UserType getUserGroup_UserType() {
		return userGroup_UserType;
	}
	public void setUg_id(Integer ug_id) {
		this.ug_id = ug_id;
	}
	public void setUg_name(String ug_name) {
		this.ug_name = ug_name;
	}
	public void setUg_parentName(String ug_parentName) {
		this.ug_parentName = ug_parentName;
	}
	public void setUser_userGroup(User_userGroup user_userGroup) {
		this.user_userGroup = user_userGroup;
	}
	public void setUserGroup_UserType(UserGroup_UserType userGroup_UserType) {
		this.userGroup_UserType = userGroup_UserType;
	}
	
}
