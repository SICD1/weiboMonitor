package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//用户与用户组中间表（类购物车）
public class User_userGroup {

	private String  g_id;
	private User_userGroup  user_userGroup;
	private String g_name;
	//一个用户与用户组中间表，包含多个用户
	private Set<User> users = new HashSet<User>();
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User_userGroup getUser_userGroup() {
		return user_userGroup;
	}

	public void setUser_userGroup(User_userGroup user_userGroup) {
		this.user_userGroup = user_userGroup;
	}


	/*//一个用户与用户组中间表，包含多个组
	//private Set<UserGroup> userGroups = new HashSet<UserGroup>();
	*/

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}



	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
