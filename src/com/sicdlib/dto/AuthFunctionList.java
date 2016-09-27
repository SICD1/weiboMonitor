package com.sicdlib.dto;

//功能操作列表
public class AuthFunctionList {
	private Integer afl_id;
	private String afl_name;			//操作名称
	private String afl_code;			//操作编码
	private String afl_filterURLprefix;	//拦截URL前缀
	private String afl_parentID;		//父操作ID
	private Authority_AuthFunctionList authority_AuthFunctionList;

	public Authority_AuthFunctionList getAuthority_AuthFunctionList() {
		return authority_AuthFunctionList;
	}

	public void setAuthority_AuthFunctionList(Authority_AuthFunctionList authority_AuthFunctionList) {
		this.authority_AuthFunctionList = authority_AuthFunctionList;
	}

	//多个功能操作列表属于一个权限与权限功能列表集合
	//private Authority_AuthFunctionList authority_AuthFunctionList;
	public Integer getAfl_id() {
		return afl_id;
	}
	public String getAfl_name() {
		return afl_name;
	}
	public String getAfl_code() {
		return afl_code;
	}
	public String getAfl_filterURLprefix() {
		return afl_filterURLprefix;
	}
	public String getAfl_parentID() {
		return afl_parentID;
	}
	public void setAfl_id(Integer afl_id) {
		this.afl_id = afl_id;
	}
	public void setAfl_name(String afl_name) {
		this.afl_name = afl_name;
	}
	public void setAfl_code(String afl_code) {
		this.afl_code = afl_code;
	}
	public void setAfl_filterURLprefix(String afl_filterURLprefix) {
		this.afl_filterURLprefix = afl_filterURLprefix;
	}
	public void setAfl_parentID(String afl_parentID) {
		this.afl_parentID = afl_parentID;
	}
	
}
