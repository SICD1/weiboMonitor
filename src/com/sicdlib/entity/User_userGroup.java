package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//用户与用户组中间表（类购物车）
public class User_userGroup {

	private Integer u_ug_id;
	//一个用户与用户组中间表，包含多个用户
	private Set<User> users = new HashSet<User>();
	//一个用户与用户组中间表，包含多个组
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();
	
	public Integer getU_ug_id() {
		return u_ug_id;
	}
	public Set<User> getUsers() {
		return users;
	}
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setU_ug_id(Integer u_ug_id) {
		this.u_ug_id = u_ug_id;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	
}
