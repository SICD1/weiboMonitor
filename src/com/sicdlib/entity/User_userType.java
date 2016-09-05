package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//用户与角色中间表
public class User_userType {

	private Integer u_ut_id;
	//本表包括多个用户
	private Set<User> users = new HashSet<User>();
	//本表包括多个用户类型
	private Set<UserType> userTypes = new HashSet<UserType>();
	
	public Integer getU_ut_id() {
		return u_ut_id;
	}
	public Set<User> getUsers() {
		return users;
	}
	public Set<UserType> getUserTypes() {
		return userTypes;
	}
	public void setU_ut_id(Integer u_ut_id) {
		this.u_ut_id = u_ut_id;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void setUserTypes(Set<UserType> userTypes) {
		this.userTypes = userTypes;
	}
	
}
