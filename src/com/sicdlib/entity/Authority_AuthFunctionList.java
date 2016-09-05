package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

//权限，功能操作关系表
public class Authority_AuthFunctionList {

	private Integer a_afl_id;
	//本表包含多个权限
	private Set<Authority> authorities = new HashSet<Authority>();
	//本表包含多个功能操作列表
	private Set<AuthFunctionList> authFunctionLists = new HashSet<AuthFunctionList>();
	
	public Integer getA_afl_id() {
		return a_afl_id;
	}
	
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	
	public Set<AuthFunctionList> getAuthFunctionLists() {
		return authFunctionLists;
	}
	
	public void setA_afl_id(Integer a_afl_id) {
		this.a_afl_id = a_afl_id;
	}
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public void setAuthFunctionLists(Set<AuthFunctionList> authFunctionLists) {
		this.authFunctionLists = authFunctionLists;
	}
	
}
