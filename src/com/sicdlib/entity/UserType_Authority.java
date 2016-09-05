package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//用户类型（角色）与权限中间表
public class UserType_Authority {

	private Integer ut_a_id;
	//本表包含多个用户类型
	private Set<UserType> userTypes = new HashSet<UserType>();
	//本表包含多个权限
	private Set<Authority> authorities = new HashSet<Authority>();
	
	public Integer getUt_a_id() {
		return ut_a_id;
	}
	public Set<UserType> getUserTypes() {
		return userTypes;
	}
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setUt_a_id(Integer ut_a_id) {
		this.ut_a_id = ut_a_id;
	}
	public void setUserTypes(Set<UserType> userTypes) {
		this.userTypes = userTypes;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
}
