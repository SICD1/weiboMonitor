package com.sicdlib.entity;

//文章相似度
public class EssaySimilarityDegree {
	
	private Float simiDegree;		//相似度
	private String oneText;			//一篇文章
	private String twoText;			//另一篇文章
	
	public Float getSimiDegree() {
		return simiDegree;
	}
	public void setSimiDegree(Float simiDegree) {
		this.simiDegree = simiDegree;
	}
	public String getOneText() {
		return oneText;
	}
	public String getTwoText() {
		return twoText;
	}	
	public void setOneText(String oneText) {
		this.oneText = oneText;
	}
	public void setTwoText(String twoText) {
		this.twoText = twoText;
	}
}
