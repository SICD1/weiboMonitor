package com.sicdlib.dto;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//关键词(热词、复数，能够构成一句短语)
@JsonIgnoreProperties(value="weibos")
public class KeyWords {

	private String kw_id;
	private String kw_name;			//关键词名称
	private String kw_hotDegree;	//关键词热度
	private String kw_province;		//关键词省份（当省份为“1”时，代表全部省份）
	private String kw_date;			//关键词时间
	private Set<Weibo> weibos = new HashSet<Weibo>();		//一个关键词微博列表

	public Set<Weibo> getWeibos() {
		return weibos;
	}
	public void setWeibos(Set<Weibo> weibos) {
		this.weibos = weibos;
	}
	public String getKw_id() {
		return kw_id;
	}
	public void setKw_id(String kw_id) {
		this.kw_id = kw_id;
	}
	public String getKw_name() {
		return kw_name;
	}
	public void setKw_name(String kw_name) {
		this.kw_name = kw_name;
	}
	public String getKw_hotDegree() {
		return kw_hotDegree;
	}
	public void setKw_hotDegree(String kw_hotDegree) {
		this.kw_hotDegree = kw_hotDegree;
	}
	public String getKw_province() {
		return kw_province;
	}
	public void setKw_province(String kw_province) {
		this.kw_province = kw_province;
	}
	public String getKw_date() {
		return kw_date;
	}
	public void setKw_date(String kw_date) {
		this.kw_date = kw_date;
	}

}
