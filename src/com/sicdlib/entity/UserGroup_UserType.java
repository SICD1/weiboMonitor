package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//用户组与用户角色（类型）中间表
public class UserGroup_UserType {

	private Integer ug_ut_id;
	
	//本表包含多个用户角色
	private Set<UserType> usertypes = new HashSet<UserType>();
	//本表包含多个用户组
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();
	
	public Integer getUg_ut_id() {
		return ug_ut_id;
	}
	public Set<UserType> getUsertypes() {
		return usertypes;
	}
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUg_ut_id(Integer ug_ut_id) {
		this.ug_ut_id = ug_ut_id;
	}
	public void setUsertypes(Set<UserType> usertypes) {
		this.usertypes = usertypes;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	
}
