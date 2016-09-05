package com.sicdlib.entity;

//句子（簇）信息
public class SentenceCluster {

	private Integer orderID;		//句子顺序
	private String sentence;		//句子内容
	private Integer senLength;		//句子分词长度
	private String keywords;		//包含的关键词
	private Integer kwordsLength;	//包含的关键词个数
	private Float senIMPDegree;		//包含句子(簇)权重
	
	public Float getSenIMPDegree() {
		return senIMPDegree;
	}
	public void setSenIMPDegree(Float senIMPDegree) {
		this.senIMPDegree = senIMPDegree;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSentence() {
		return sentence;
	}
	public Integer getSenLength() {
		return senLength;
	}
	public Integer getKwordsLength() {
		return kwordsLength;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public void setSenLength(Integer senLength) {
		this.senLength = senLength;
	}
	public void setKwordsLength(Integer kwordsLength) {
		this.kwordsLength = kwordsLength;
	}
	
}
